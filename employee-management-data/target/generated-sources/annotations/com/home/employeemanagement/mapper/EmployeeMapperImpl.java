package com.home.employeemanagement.mapper;

import com.home.employeemanagement.dto.EmployeeDTOV1;
import com.home.employeemanagement.dto.EmployeeDTOV2;
import com.home.employeemanagement.model.Employee;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-12T10:43:36-0400",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_112 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDTOV1 employeeToEmployeeDTOV1(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTOV1 employeeDTOV1 = new EmployeeDTOV1();

        employeeDTOV1.setEmployeeId( employee.getEmployeeId() );
        employeeDTOV1.setFirstName( employee.getFirstName() );
        employeeDTOV1.setLastName( employee.getLastName() );
        employeeDTOV1.setEmail( employee.getEmail() );

        return employeeDTOV1;
    }

    @Override
    public EmployeeDTOV2 employeeToEmployeeDTOV2(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTOV2 employeeDTOV2 = new EmployeeDTOV2();

        employeeDTOV2.setEmployeeId( employee.getEmployeeId() );
        employeeDTOV2.setFirstName( employee.getFirstName() );
        employeeDTOV2.setLastName( employee.getLastName() );
        employeeDTOV2.setEmail( employee.getEmail() );
        employeeDTOV2.setAddress( employee.getAddress() );

        return employeeDTOV2;
    }
}
