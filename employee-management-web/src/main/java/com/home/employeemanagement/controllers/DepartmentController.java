package com.home.employeemanagement.controllers;

import com.home.employeemanagement.model.Department;
import com.home.employeemanagement.repositories.DepartmentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/department/")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;
    private final EmployeeController employeeController;

    public DepartmentController(DepartmentRepository departmentRepository,
                                EmployeeController employeeController) {
        this.departmentRepository = departmentRepository;
        this.employeeController = employeeController;
    }

    @GetMapping
    public List<Department> getDepartments() {
        List<Department> departments = departmentRepository.findAll();

        departments.forEach(department -> {
            department.add(linkTo(DepartmentController.class)
                    .slash(department.getDepartmentId())
                    .withSelfRel());
            department.getEmployees().forEach(employee ->
                    employee.add(linkTo(EmployeeController.class)
                            .slash(employee.getEmployeeId())
                            .withSelfRel()
                    ));
        });
        return departments;
    }

    @GetMapping("{departmentId}")
    public Department getDepartmentById(@PathVariable String departmentId) {
        Department department = departmentRepository.findById(Long.valueOf(departmentId)).get();
        department.add(linkTo(DepartmentController.class)
                .slash(department.getDepartmentId())
                .withSelfRel());

        return department;
    }


}
