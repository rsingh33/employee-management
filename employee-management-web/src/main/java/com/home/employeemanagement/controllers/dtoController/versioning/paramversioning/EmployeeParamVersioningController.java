package com.home.employeemanagement.controllers.dtoController.versioning.paramversioning;


import com.home.employeemanagement.dto.EmployeeDTOV1;
import com.home.employeemanagement.dto.EmployeeDTOV2;
import com.home.employeemanagement.mapper.EmployeeMapper;
import com.home.employeemanagement.services.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/versioning/params/employees")
public class EmployeeParamVersioningController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeParamVersioningController(
            EmployeeService employeeService,
            EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping(value = "/{id}" , params = "version=1")
    public EmployeeDTOV1 getEmployeeV1(@PathVariable Long id) {
        return employeeMapper.
                employeeToEmployeeDTOV1(employeeService.
                        getEmployeeById(Long.valueOf(id)));
    }

    @GetMapping(value = "/{id}" , params = "version=2")
    public EmployeeDTOV2 getEmployeeV2(@PathVariable Long id) {
        return employeeMapper.
                employeeToEmployeeDTOV2(employeeService.
                        getEmployeeById(Long.valueOf(id)));
    }
}
