package com.zest.employee.controller;

import com.zest.employee.dto.EmployeeRequestDto;
import com.zest.employee.dto.EmployeeResponseDto;
import com.zest.employee.mapper.EmployeeMapper;
import com.zest.employee.model.Employee;
import com.zest.employee.repository.EmployeeRepository;
import com.zest.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Employee")

public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;


    // get All
    @GetMapping("/all")
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
        List<EmployeeResponseDto> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // To get by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // To  Update
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeRequestDto dto) {
        Optional<Employee> existing = employeeRepository.findById(id);
        if (existing.isPresent()) {
            Employee employee = existing.get();
            employee.setName(dto.getName());
            employee.setEmail(dto.getEmail());
            employee.setDepartment(dto.getDepartment());
            employee.setSalary(dto.getSalary());
            employee.setPosition(dto.getPosition());
            employee.setDateOfJoining(dto.getDateOfJoining());
            employee.setPassword(dto.getPassword());
            employee.setRole(dto.getRole());

            Employee updated = employeeRepository.save(employee);
            return ResponseEntity.ok(EmployeeMapper.employeeMapper.toDto(updated));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // To Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee with ID " + id + " deleted successfully.", HttpStatus.OK);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<EmployeeResponseDto>> getEmployees(Pageable pageable){
        return ResponseEntity.ok(employeeService.getEmployees(pageable));
    }


}
