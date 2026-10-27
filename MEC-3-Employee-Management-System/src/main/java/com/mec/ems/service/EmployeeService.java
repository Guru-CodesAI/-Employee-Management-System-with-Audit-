package com.mec.ems.service;

import com.mec.ems.model.Employee;
import com.mec.ems.repository.EmployeeRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final Validator validator;

    public EmployeeService(EmployeeRepository employeeRepository, Validator validator) {
        this.employeeRepository = employeeRepository;
        this.validator = validator;
    }

    public Employee registerEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee details are required.");
        }

        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        if (!violations.isEmpty()) {
            throw new EmployeeValidationException(violations);
        }

        employee.setName(employee.getName().trim());
        employee.setEmail(employee.getEmail().trim().toLowerCase(Locale.ROOT));
        employee.setDepartment(employee.getDepartment().trim());
        employee.setDesignation(employee.getDesignation().trim());
        return employeeRepository.save(employee);
    }

    public List<Employee> listEmployees() {
        return employeeRepository.findAll();
    }
}
