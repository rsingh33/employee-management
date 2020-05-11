package com.home.employeemanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.home.employeemanagement.model.Employee;
import com.home.employeemanagement.model.Stage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class ProjectMSDto {

    private String name;
    private Stage stage;
    private List<Employee> employees;
}
