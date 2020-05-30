package com.home.employeemanagement.mapper;

import com.home.employeemanagement.dto.ProjectMSDto;
import com.home.employeemanagement.model.Employee;
import com.home.employeemanagement.model.Project;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-30T17:43:13-0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_112 (Oracle Corporation)"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public ProjectMSDto projectToProjectMSDTO(Project project) {
        if ( project == null ) {
            return null;
        }

        ProjectMSDto projectMSDto = new ProjectMSDto();

        projectMSDto.setProjectDescription( project.getDescription() );
        projectMSDto.setName( project.getName() );
        projectMSDto.setStage( project.getStage() );
        List<Employee> list = project.getEmployees();
        if ( list != null ) {
            projectMSDto.setEmployees( new ArrayList<Employee>( list ) );
        }

        return projectMSDto;
    }

    @Override
    public List<ProjectMSDto> projectListToProjectListMSDTO(List<Project> projects) {
        if ( projects == null ) {
            return null;
        }

        List<ProjectMSDto> list = new ArrayList<ProjectMSDto>( projects.size() );
        for ( Project project : projects ) {
            list.add( projectToProjectMSDTO( project ) );
        }

        return list;
    }
}
