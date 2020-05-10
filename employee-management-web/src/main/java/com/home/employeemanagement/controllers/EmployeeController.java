package com.home.employeemanagement.controllers;

import com.home.employeemanagement.model.Employee;
import com.home.employeemanagement.repositories.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAll() {
        List<Employee> employees = employeeRepository.findAll();

        employees.forEach(employee -> employee.add(linkTo(EmployeeController.class)
                .slash(employee.getEmployeeId())
                .withSelfRel()));

        return employees;
    }

    @GetMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable String employeeId) {
        Employee employee = employeeRepository.findById(Long.valueOf(employeeId)).get();
        employee.add(linkTo(EmployeeController.class)
                .slash(employee.getEmployeeId())
                .withSelfRel());
        return employee;

    }

}
