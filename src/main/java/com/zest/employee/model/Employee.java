package com.zest.employee.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String department;
    private double salary;
    private String position;
    private Date dateOfJoining;
    private String password;
    private String role;

    
}
