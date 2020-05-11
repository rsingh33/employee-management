package com.home.employeemanagement.validation;

import com.home.employeemanagement.model.Employee;
import com.home.employeemanagement.repositories.EmployeeRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {

    private final EmployeeRepository employeeRepository;

    public UniqueValidator(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("Entered custom validation method");
        Employee employee = employeeRepository.findEmployeeByEmail(value);

        if (employee != null)
            return false;
        else
            return true;
    }
}
