package com.home.employeemanagement.repositories;


import com.home.employeemanagement.model.Department;
import com.home.employeemanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
