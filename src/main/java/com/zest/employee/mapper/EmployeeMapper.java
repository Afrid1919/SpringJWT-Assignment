package com.zest.employee.mapper;

import com.zest.employee.dto.EmployeeRequestDto;
import com.zest.employee.dto.EmployeeResponseDto;
import com.zest.employee.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

    //Mapping from DTO to entity
    Employee toEntity(EmployeeRequestDto dto);

    //Mapping from entity to DTO
//    @Mapping(target = "password", ignore = true)
    EmployeeResponseDto toDto(Employee employee);
}
