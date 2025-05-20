package com.zest.employee.repository;

import com.zest.employee.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeRepositoryTest {

        @Mock
        private EmployeeRepository employeeRepository;

        @Test
        void FindByName() {
            // Test data
            Employee employee = new Employee();
            employee.setName("John Doe");

            when(employeeRepository.findByName("John Doe")).thenReturn(Optional.of(employee));

            // When
            Optional<Employee> actualEmployee = employeeRepository.findByName("John Doe");

            // Then
            assertTrue(actualEmployee.isPresent());
            assertEquals("John Doe", actualEmployee.get().getName());
        }


        @Test
    void FindByEmail() {

            Employee employee = new Employee();
            employee.setEmail("john@examplegmail.com");
            when(employeeRepository.findByEmail("john@examplegmail.com")).thenReturn(Optional.of(employee));

            //when
            Optional<Employee> actualResult = employeeRepository.findByEmail("john@examplegmail.com");

            //then
            assertTrue(actualResult.isPresent());
            assertEquals("john@examplegmail.com", actualResult.get().getEmail());


    }
}