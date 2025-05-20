package com.zest.employee.service;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public interface CustomUserDetailsService extends UserDetailsService {

      @Override
    public UserDetails loadUserByUsername(String username);
}
