package com.home.employeemanagement.services;

import com.home.employeemanagement.model.Employee;
import com.home.employeemanagement.repositories.EmployeeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements CrudService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository
                .findById(employeeId)
                .orElseThrow(RuntimeException::new);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee patchEmployee(Long id, Employee patchEmployee) {

        Employee employee = employeeRepository.findById(id).get();

        if (patchEmployee.getDepartment() != null) {
            employee.setDepartment(patchEmployee.getDepartment());
        }
        if (patchEmployee.getEmail() != null) {
            employee.setEmail(patchEmployee.getEmail());
        }

        if (patchEmployee.getFirstName() != null) {
            employee.setFirstName(patchEmployee.getFirstName());
        }

        if (patchEmployee.getLastName() != null) {
            employee.setLastName(patchEmployee.getLastName());
        }

        if (patchEmployee.getProjects() != null) {
            employee.setProjects(patchEmployee.getProjects());
        }

        return employeeRepository.save(employee);
    }

    public void deleteByID(Long id) {
       try {
           employeeRepository.deleteById(id);
       } catch (EmptyResultDataAccessException ex){

       }
    }

    public Iterable<Employee> getAllEmployees(Pageable pageAndSize) {
        return employeeRepository.findAll(pageAndSize);
    }
}
