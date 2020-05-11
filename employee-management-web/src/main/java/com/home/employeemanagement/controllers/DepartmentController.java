package com.home.employeemanagement.controllers;

import com.home.employeemanagement.model.Department;
import com.home.employeemanagement.repositories.DepartmentRepository;
import com.home.employeemanagement.services.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/legacy/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final EmployeeController employeeController;

    public DepartmentController(DepartmentService departmentService,
                                EmployeeController employeeController) {
        this.departmentService = departmentService;
        this.employeeController = employeeController;
    }

    @GetMapping
    public List<Department> getDepartments() {
        List<Department> departments = departmentService.getAllDepartments();

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

    @GetMapping("/{departmentId}")
    public Department getDepartmentById(@PathVariable String departmentId) {
        Department department = departmentService.getDepartmentById(Long.valueOf(departmentId));
        department.add(linkTo(DepartmentController.class)
                .slash(department.getDepartmentId())
                .withSelfRel());

        return department;
    }


}
