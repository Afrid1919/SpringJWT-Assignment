package com.zest.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeRequestDto  {
    private int id;

    private String name;
    private String email;
    private String department;
    private double salary;
    private String position;
    private Date dateOfJoining;
    private String password;
    private String role;

}
