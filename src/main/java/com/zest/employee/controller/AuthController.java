package com.zest.employee.controller;

import com.zest.employee.dto.AuthRequest;
import com.zest.employee.dto.EmployeeRequestDto;
import com.zest.employee.dto.EmployeeResponseDto;
import com.zest.employee.security.JwtUtil;
import com.zest.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<EmployeeResponseDto> register(@RequestBody EmployeeRequestDto dto) {
        EmployeeResponseDto savedUser = null;
        try {
            EmployeeResponseDto response = employeeService.addEmployee(dto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        String token = jwtUtil.generateToken(authRequest.getUsername());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/home")
    public ResponseEntity<String> home(){
        return new ResponseEntity<>("Welcome to Employee Management System", HttpStatus.OK);
    }
}
