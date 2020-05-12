package com.home.employeemanagement.mapper;

import com.home.employeemanagement.dto.ProjectMSDto;
import com.home.employeemanagement.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mapping(source = "description", target = "projectDescription")
    ProjectMSDto projectToProjectMSDTO(Project project);

    List<ProjectMSDto> projectListToProjectListMSDTO(List<Project> projects);
}
