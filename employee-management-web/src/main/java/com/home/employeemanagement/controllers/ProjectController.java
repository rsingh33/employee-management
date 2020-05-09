package com.home.employeemanagement.controllers;

import com.home.employeemanagement.model.Project;
import com.home.employeemanagement.repositories.ProjectRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/projects/")
public class ProjectController {

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public List<Project> getProjects() {
        List<Project> projects = projectRepository.findAll();
        projects.forEach(project -> project.add(linkTo(ProjectController.class)
                .slash(project.getProjectId())
                .withSelfRel()));
        return projects;
    }

    @GetMapping({"{projectId}"})
    public Project getProjectById(@PathVariable String projectId) {
        Project project = projectRepository.findById(Long.valueOf(projectId)).get();
        project.add(linkTo(ProjectController.class)
                .slash(project.getProjectId())
                .withSelfRel());
        return project;
    }
}
