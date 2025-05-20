package com.zest.employee.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private  String secret_key;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody().getSubject();
    }


    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            String username = extractUsername(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (ExpiredJwtException e) {
            return false; //if expired token is invalid
        }
    }


    private boolean isTokenExpired(String token) {
        return Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}
