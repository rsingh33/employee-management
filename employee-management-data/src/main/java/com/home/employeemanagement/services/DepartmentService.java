package com.home.employeemanagement.services;

import com.home.employeemanagement.model.Department;
import com.home.employeemanagement.model.Employee;
import com.home.employeemanagement.repositories.DepartmentRepository;
import com.home.employeemanagement.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService implements CrudService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository,
                             EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long departmentID) {
        return departmentRepository
                .findById(departmentID)
                .orElseThrow(RuntimeException::new);
    }

    public List<Employee> getAllEmployeesByDepartmentById(Long departmentID) {
        return departmentRepository
                .findById(departmentID)
                .get().getEmployees();

    }
}
