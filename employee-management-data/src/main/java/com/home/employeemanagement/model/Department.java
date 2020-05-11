package com.home.employeemanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"employees"})
@ToString(exclude = {"employees"})
@Entity
public class Department extends RepresentationModel<Department> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    public void addEmployee(Employee emp) {
        if(employees==null) {
            employees = new ArrayList<>();
        }
        employees.add(emp);
    }
}
