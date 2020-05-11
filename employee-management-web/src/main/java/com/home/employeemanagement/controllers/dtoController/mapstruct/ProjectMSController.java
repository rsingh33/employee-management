package com.home.employeemanagement.controllers.dtoController.mapstruct;

import com.home.employeemanagement.dto.ProjectMSDto;
import com.home.employeemanagement.mapper.ProjectMapper;
import com.home.employeemanagement.services.ProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mapstruct/projects")
public class ProjectMSController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    public ProjectMSController(ProjectService projectService,
                               ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }

    @GetMapping
    public List<ProjectMSDto> getAllProjectDTO() {
        return projectMapper.projectListToProjectListMSDTO(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ProjectMSDto getProjectDTOByID(@PathVariable Long id) {
        return projectMapper.projectToProjectMSDTO(projectService.getProjectById(Long.valueOf(id)));
    }
}
