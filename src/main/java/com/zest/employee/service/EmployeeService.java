package com.zest.employee.service;

import com.zest.employee.dto.EmployeeRequestDto;
import com.zest.employee.dto.EmployeeResponseDto;
import com.zest.employee.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public interface EmployeeService {

    public EmployeeResponseDto addEmployee(EmployeeRequestDto dto);

    public List<EmployeeResponseDto> getAllEmployees();


    public Employee getEmployeeById(Integer id);


    public EmployeeResponseDto updateEmployee(Integer id, EmployeeRequestDto dto);


    public void deleteEmployee(Integer id);

    public Page<EmployeeResponseDto> getEmployees(Pageable pageable);


}
