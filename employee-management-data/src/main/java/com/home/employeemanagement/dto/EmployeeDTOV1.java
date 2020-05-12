package com.home.employeemanagement.dto;

import com.home.employeemanagement.model.Department;
import com.home.employeemanagement.model.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTOV1 {

    private long employeeId;
    private String firstName;
    private String lastName;
    private String email;

}
