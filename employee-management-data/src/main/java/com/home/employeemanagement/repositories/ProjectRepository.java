package com.home.employeemanagement.repositories;


import com.home.employeemanagement.model.Department;
import com.home.employeemanagement.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
