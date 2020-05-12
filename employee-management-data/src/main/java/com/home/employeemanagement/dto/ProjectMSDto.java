package com.home.employeemanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.home.employeemanagement.model.Employee;
import com.home.employeemanagement.model.Stage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class ProjectMSDto {

    private String name;

    private String projectDescription;
    private Stage stage;
    private List<Employee> employees;
}
