package com.zest.employee.service.serviceimpl;

import com.zest.employee.model.Employee;
import com.zest.employee.repository.EmployeeRepository;
import com.zest.employee.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Service
public class CustomUserServiceDetailsImpl implements CustomUserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public UserDetails loadUserByUsername(String username) {
        Employee emp = employeeRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        return new User(emp.getName(), emp.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(emp.getRole())));
    }
}
