package com.mec.ems.service;

import jakarta.validation.ConstraintViolation;
import com.mec.ems.model.Employee;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/** Carries field-level validation messages for service callers. */
public class EmployeeValidationException extends IllegalArgumentException {

    private final Map<String, String> fieldErrors;

    public EmployeeValidationException(Set<ConstraintViolation<Employee>> violations) {
        super("Employee details failed validation.");
        this.fieldErrors = new LinkedHashMap<>();
        violations.stream()
                .sorted((a, b) -> a.getPropertyPath().toString().compareTo(b.getPropertyPath().toString()))
                .forEach(v -> fieldErrors.putIfAbsent(v.getPropertyPath().toString(), v.getMessage()));
    }

    public Map<String, String> getFieldErrors() {
        return Map.copyOf(fieldErrors);
    }
}
