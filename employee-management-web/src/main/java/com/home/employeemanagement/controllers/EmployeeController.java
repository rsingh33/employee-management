package com.home.employeemanagement.controllers;

import com.home.employeemanagement.model.Employee;
import com.home.employeemanagement.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Slf4j
@RestController
@RequestMapping("/legacy/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployees() {
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee patchEmployee(@PathVariable String id, @RequestBody Employee patchEmployee) {
        return employeeService.patchEmployee(Long.valueOf(id), patchEmployee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        employeeService.deleteByID(Long.valueOf(id));
    }

    @GetMapping(params = {"page", "size"})
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Employee> findPaginatedEmployee(@RequestParam("page") int page,
                                                    @RequestParam("size") int size) {

        Pageable pageAndSize = PageRequest.of(page, size);
        Iterable<Employee> employees = employeeService.getAllEmployees(pageAndSize);
        employees.forEach(employee -> employee.add(linkTo(EmployeeController.class)
                .slash(employee.getEmployeeId())
                .withSelfRel()));

        return employees;
    }
}
