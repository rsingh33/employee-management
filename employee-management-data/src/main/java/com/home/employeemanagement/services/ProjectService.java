package com.home.employeemanagement.services;

import com.home.employeemanagement.model.Department;
import com.home.employeemanagement.model.Employee;
import com.home.employeemanagement.model.Project;
import com.home.employeemanagement.repositories.DepartmentRepository;
import com.home.employeemanagement.repositories.EmployeeRepository;
import com.home.employeemanagement.repositories.ProjectRepository;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public Project patchProject(Long id, Project patchProject) {

        Project project = projectRepository.findById(id).get();

        if (patchProject.getEmployees() != null) {
            project.setEmployees(patchProject.getEmployees());
        }
        if (patchProject.getDescription() != null) {
            project.setDescription(patchProject.getDescription());
        }

        if (patchProject.getName() != null) {
            project.setName(patchProject.getName());
        }

        if (patchProject.getStage() != null) {
            project.setStage(patchProject.getStage());
        }

        return projectRepository.save(project);
    }

    public void deleteByID(Long id) {
        try {
            projectRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex){

        }
    }
}
