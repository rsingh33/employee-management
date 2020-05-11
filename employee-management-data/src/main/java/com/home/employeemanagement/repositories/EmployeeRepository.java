package com.home.employeemanagement.repositories;

import com.home.employeemanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel= "employees",path="employees")
public interface EmployeeRepository extends JpaRepository<Employee , Long> {
    Employee findEmployeeByEmail(String email);
}
