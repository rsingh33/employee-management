package com.home.employeemanagement.services;

import com.home.employeemanagement.model.Department;
import com.home.employeemanagement.model.Employee;
import com.home.employeemanagement.model.Project;
import com.home.employeemanagement.repositories.DepartmentRepository;
import com.home.employeemanagement.repositories.EmployeeRepository;
import com.home.employeemanagement.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService implements CrudService {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public ProjectService(ProjectRepository projectRepository,
                             EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long projectID) {
        return projectRepository
                .findById(projectID)
                .orElseThrow(RuntimeException::new);
    }

    public List<Employee> getAllEmployeesByProjectById(Long projectId) {
        return projectRepository
                .findById(projectId)
                .get().getEmployees();

    }
}
