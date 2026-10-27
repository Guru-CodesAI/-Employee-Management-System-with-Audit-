package com.mec.ems.service;

import com.mec.ems.model.Employee;
import com.mec.ems.repository.InMemoryEmployeeRepository;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private ValidatorFactory validatorFactory;
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        employeeService = new EmployeeService(new InMemoryEmployeeRepository(), validator);
    }

    @AfterEach
    void tearDown() {
        validatorFactory.close();
    }

    @Test
    void registersValidEmployeeAndAssignsId() {
        Employee saved = employeeService.registerEmployee(validEmployee());

        assertNotNull(saved.getId());
        assertEquals("Priya Kumar", saved.getName());
        assertEquals("priya@example.com", saved.getEmail());
        assertEquals(1, employeeService.listEmployees().size());
    }

    @Test
    void rejectsInvalidEmployeeWithoutSavingIt() {
        Employee employee = validEmployee();
        employee.setEmail("not-an-email");
        employee.setSalary(new BigDecimal("0"));

        assertThrows(EmployeeValidationException.class,
                () -> employeeService.registerEmployee(employee));
        assertTrue(employeeService.listEmployees().isEmpty());
    }

    @Test
    void trimsTextAndNormalizesEmail() {
        Employee employee = validEmployee();
        employee.setName("  Priya Kumar  ");
        employee.setEmail(" PRIYA@EXAMPLE.COM ");

        Employee saved = employeeService.registerEmployee(employee);

        assertEquals("Priya Kumar", saved.getName());
        assertEquals("priya@example.com", saved.getEmail());
    }

    @Test
    void listsEmployeesInRegistrationOrder() {
        employeeService.registerEmployee(validEmployee());
        Employee second = validEmployee();
        second.setEmail("arun@example.com");
        second.setName("Arun Kumar");
        employeeService.registerEmployee(second);

        List<Employee> employees = employeeService.listEmployees();

        assertEquals(2, employees.size());
        assertEquals("Priya Kumar", employees.get(0).getName());
        assertEquals("Arun Kumar", employees.get(1).getName());
    }

    private Employee validEmployee() {
        Employee employee = new Employee();
        employee.setName("Priya Kumar");
        employee.setEmail("priya@example.com");
        employee.setDepartment("Engineering");
        employee.setDesignation("Software Engineer");
        employee.setSalary(new BigDecimal("45000.00"));
        employee.setJoiningDate(LocalDate.now());
        return employee;
    }
}
