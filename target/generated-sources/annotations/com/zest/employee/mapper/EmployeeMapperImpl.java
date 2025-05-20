package com.zest.employee.mapper;

import com.zest.employee.dto.EmployeeRequestDto;
import com.zest.employee.dto.EmployeeResponseDto;
import com.zest.employee.model.Employee;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-20T17:44:27+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee toEntity(EmployeeRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( dto.getId() );
        employee.setName( dto.getName() );
        employee.setEmail( dto.getEmail() );
        employee.setDepartment( dto.getDepartment() );
        employee.setSalary( dto.getSalary() );
        employee.setPosition( dto.getPosition() );
        employee.setDateOfJoining( dto.getDateOfJoining() );
        employee.setPassword( dto.getPassword() );
        employee.setRole( dto.getRole() );

        return employee;
    }

    @Override
    public EmployeeResponseDto toDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();

        employeeResponseDto.setId( employee.getId() );
        employeeResponseDto.setName( employee.getName() );
        employeeResponseDto.setEmail( employee.getEmail() );
        employeeResponseDto.setPassword( employee.getPassword() );
        employeeResponseDto.setDepartment( employee.getDepartment() );
        employeeResponseDto.setSalary( employee.getSalary() );
        employeeResponseDto.setPosition( employee.getPosition() );
        employeeResponseDto.setDateOfJoining( employee.getDateOfJoining() );
        employeeResponseDto.setRole( employee.getRole() );

        return employeeResponseDto;
    }
}
