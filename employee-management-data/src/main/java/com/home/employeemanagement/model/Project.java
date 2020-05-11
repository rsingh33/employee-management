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
public class Project extends RepresentationModel<Project> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    private String name;
    private String description;
    private Stage stage;


    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name = "project_employee",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;

    public void addEmployee(Employee emp) {
        if (employees == null) {
            employees = new ArrayList<>();
        }
        employees.add(emp);
    }
}
