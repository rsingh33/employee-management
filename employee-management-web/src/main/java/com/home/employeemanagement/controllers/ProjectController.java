package com.home.employeemanagement.controllers;

import com.home.employeemanagement.model.Employee;
import com.home.employeemanagement.model.Project;
import com.home.employeemanagement.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/legacy/projects")
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

    @GetMapping({"/{projectId}"})

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project saveProject(@RequestBody Project project) {
        return projectService.saveProject(project);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Project updateProject(@RequestBody Project project) {
        return projectService.saveProject(project);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Project patchProject(@PathVariable String id, @RequestBody Project patchProject) {
        return projectService.patchProject(Long.valueOf(id) , patchProject);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable Long id){
        projectService.deleteByID(Long.valueOf(id));
    }
}
