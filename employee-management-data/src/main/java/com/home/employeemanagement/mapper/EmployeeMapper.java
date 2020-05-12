package com.home.employeemanagement.mapper;

import com.home.employeemanagement.dto.EmployeeDTOV1;
import com.home.employeemanagement.dto.EmployeeDTOV2;
import com.home.employeemanagement.dto.ProjectMSDto;
import com.home.employeemanagement.model.Employee;
import com.home.employeemanagement.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);


    EmployeeDTOV1 employeeToEmployeeDTOV1(Employee employee);
    EmployeeDTOV2 employeeToEmployeeDTOV2(Employee employee);


}
