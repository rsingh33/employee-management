package com.home.employeemanagement.controllers;

import com.home.employeemanagement.model.Project;
import com.home.employeemanagement.services.ProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/projects/")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getProjects() {
        List<Project> projects = projectService.getAllProjects();
        projects.forEach(project -> {
                    project.add(linkTo(ProjectController.class)
                            .slash(project.getProjectId())
                            .withSelfRel());
                    project.getEmployees().forEach(
                            employee ->
                                    employee.add(linkTo(EmployeeController.class)
                                            .slash(employee.getEmployeeId())
                                            .withSelfRel())
                    );
                });

        return projects;
    }

    @GetMapping({"{projectId}"})

    public Project getProjectById(@PathVariable String projectId) {
        Project project = projectService.getProjectById(Long.valueOf(projectId));
        project.add(linkTo(ProjectController.class)
                .slash(project.getProjectId())
                .withSelfRel());

        project.getEmployees().forEach(
                employee ->
                        employee.add(linkTo(EmployeeController.class)
                                .slash(employee.getEmployeeId())
                                .withSelfRel())
        );
        return project;
    }
}
