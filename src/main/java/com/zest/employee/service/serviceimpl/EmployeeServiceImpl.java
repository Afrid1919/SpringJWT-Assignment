package com.zest.employee.service.serviceimpl;

import com.zest.employee.dto.EmployeeRequestDto;
import com.zest.employee.dto.EmployeeResponseDto;
import com.zest.employee.exception.EmployeeNotFoundException;
import com.zest.employee.exception.UserAlreadyExist;
import com.zest.employee.mapper.EmployeeMapper;
import com.zest.employee.model.Employee;
import com.zest.employee.repository.EmployeeRepository;
import com.zest.employee.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public EmployeeResponseDto addEmployee(EmployeeRequestDto dto) {
        Optional<Employee> user = employeeRepository.findByEmail(dto.getEmail());
        if (user.isPresent()) {
            throw new UserAlreadyExist();
        }

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        Employee employee = employeeMapper.toEntity(dto);
        Employee saved = employeeRepository.save(employee);

        return employeeMapper.toDto(saved);
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeResponseDto> collect = employees.stream().map(map ->
                employeeMapper.toDto(map)).collect(Collectors.toList());
        return collect;

    }

    @Override
    public Employee getEmployeeById(Integer id) {

        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found"));

//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
//
//        return mapper.map(employee, EmployeeResponseDto.class);

    }

    @Override
    public EmployeeResponseDto updateEmployee(Integer id, EmployeeRequestDto dto) {
        Optional<Employee> existing = employeeRepository.findById(id);
        if (existing.isPresent()) {
            Employee employee = existing.get();
            employee.setName(dto.getName());
            employee.setEmail(dto.getEmail());
            employee.setDepartment(dto.getDepartment());
            employee.setSalary(dto.getSalary());
            employee.setPosition(dto.getPosition());
            employee.setDateOfJoining(dto.getDateOfJoining());
            employee.setPassword(passwordEncoder.encode(dto.getPassword()));
            employee.setRole(dto.getRole());

            Employee updated = employeeRepository.save(employee);
            return employeeMapper.toDto(updated);
        }
        throw new EmployeeNotFoundException("Employee not found");
    }


    @Override
    public void deleteEmployee(Integer id) {
        Employee existingEmployee = getEmployeeById(id);
        employeeRepository.delete(existingEmployee);
    }

    @Override
    public Page<EmployeeResponseDto> getEmployees(Pageable pageable) {
        Page<Employee> page = employeeRepository.findAll(pageable);
        return page.map(employeeMapper::toDto);
    }

}
