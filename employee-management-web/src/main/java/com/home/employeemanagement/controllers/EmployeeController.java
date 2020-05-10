package com.home.employeemanagement.controllers;

import com.home.employeemanagement.model.Employee;
import com.home.employeemanagement.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAll() {
        List<Employee> employees = employeeService.getAllEmployees();

        employees.forEach(employee -> employee.add(linkTo(EmployeeController.class)
                .slash(employee.getEmployeeId())
                .withSelfRel()));

        return employees;
    }

    @GetMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable String employeeId) {
        Employee employee = employeeService.getEmployeeById(Long.valueOf(employeeId));
        employee.add(linkTo(EmployeeController.class)
                .slash(employee.getEmployeeId())
                .withSelfRel());
        return employee;

    }

}
