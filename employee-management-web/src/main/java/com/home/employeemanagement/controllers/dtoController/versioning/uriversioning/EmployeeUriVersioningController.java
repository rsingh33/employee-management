package com.home.employeemanagement.controllers.dtoController.versioning.uriversioning;


import com.home.employeemanagement.dto.EmployeeDTOV1;
import com.home.employeemanagement.dto.EmployeeDTOV2;
import com.home.employeemanagement.mapper.EmployeeMapper;
import com.home.employeemanagement.services.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/versioning/uri/employees")
public class EmployeeUriVersioningController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeUriVersioningController(
            EmployeeService employeeService,
            EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping({"/v1.0/{id}" , "/v1.1/{id}"})
    public EmployeeDTOV1 getEmployeeV1(@PathVariable Long id) {
        return employeeMapper.
                employeeToEmployeeDTOV1(employeeService.
                        getEmployeeById(Long.valueOf(id)));
    }

    @GetMapping({"/v2.0/{id}" , "/v2.1/{id}"})
    public EmployeeDTOV2 getEmployeeV2(@PathVariable Long id) {
        return employeeMapper.
                employeeToEmployeeDTOV2(employeeService.
                        getEmployeeById(Long.valueOf(id)));
    }
}
