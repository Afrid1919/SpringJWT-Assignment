package com.zest.employee.service;

import com.zest.employee.dto.EmployeeRequestDto;
import com.zest.employee.dto.EmployeeResponseDto;
import com.zest.employee.exception.EmployeeNotFoundException;
import com.zest.employee.exception.UserAlreadyExist;
import com.zest.employee.mapper.EmployeeMapper;
import com.zest.employee.model.Employee;
import com.zest.employee.repository.EmployeeRepository;
import com.zest.employee.service.serviceimpl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

        @Mock
        private EmployeeRepository employeeRepository;

        @Mock
        private EmployeeMapper employeeMapper;

        @Mock
        private PasswordEncoder passwordEncoder;

        @InjectMocks
        private EmployeeServiceImpl employeeService;

        private Employee employee;
        private EmployeeRequestDto employeeRequestDto;
        private EmployeeResponseDto employeeResponseDto;

        @BeforeEach
        void setUp() {
            employee = new Employee();
            employee.setId(1);
            employee.setEmail("test@example.com");

            employeeRequestDto = new EmployeeRequestDto();
            employeeRequestDto.setEmail("test@example.com");
            employeeRequestDto.setPassword("password");

            employeeResponseDto = new EmployeeResponseDto();
        }

        @Test
        void AddEmployee() {
            when(employeeRepository.findByEmail(employeeRequestDto.getEmail())).thenReturn(Optional.empty());
            when(passwordEncoder.encode(employeeRequestDto.getPassword())).thenReturn("encodedPassword");
            when(employeeMapper.toEntity(employeeRequestDto)).thenReturn(employee);
            when(employeeRepository.save(employee)).thenReturn(employee);
            when(employeeMapper.toDto(employee)).thenReturn(employeeResponseDto);

            EmployeeResponseDto result = employeeService.addEmployee(employeeRequestDto);
            assertNotNull(result);
            verify(employeeRepository).save(employee);
        }


        @Test
        void GetAllEmployees() {
            when(employeeRepository.findAll()).thenReturn(List.of(employee));
            when(employeeMapper.toDto(employee)).thenReturn(employeeResponseDto);

            List<EmployeeResponseDto> result = employeeService.getAllEmployees();
            assertFalse(result.isEmpty());
        }

        @Test
        void GetEmployeeById() {
            when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

            Employee result = employeeService.getEmployeeById(1);
            assertNotNull(result);
        }

        @Test
        void UpdateEmployee() {
            when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
            when(employeeRepository.save(employee)).thenReturn(employee);
            when(employeeMapper.toDto(employee)).thenReturn(employeeResponseDto);

            EmployeeResponseDto result = employeeService.updateEmployee(1, employeeRequestDto);
            assertNotNull(result);
        }


        @Test
        void DeleteEmployee() {
            when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

            employeeService.deleteEmployee(1);
            verify(employeeRepository).delete(employee);
        }


}
