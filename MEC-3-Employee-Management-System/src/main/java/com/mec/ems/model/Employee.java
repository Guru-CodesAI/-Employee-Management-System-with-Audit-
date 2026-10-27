package com.mec.ems.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {

    private Long id;

    @NotBlank(message = "Employee name is required.")
    @Size(max = 80, message = "Employee name must be 80 characters or fewer.")
    private String name;

    @NotBlank(message = "Email address is required.")
    @Email(message = "Enter a valid email address.")
    @Size(max = 120, message = "Email must be 120 characters or fewer.")
    private String email;

    @NotBlank(message = "Department is required.")
    @Size(max = 60, message = "Department must be 60 characters or fewer.")
    private String department;

    @NotBlank(message = "Designation is required.")
    @Size(max = 60, message = "Designation must be 60 characters or fewer.")
    private String designation;

    @NotNull(message = "Salary is required.")
    @DecimalMin(value = "0.01", message = "Salary must be greater than zero.")
    @Digits(integer = 8, fraction = 2, message = "Salary can have up to 8 integer digits and 2 decimal places.")
    private BigDecimal salary;

    @NotNull(message = "Joining date is required.")
    @PastOrPresent(message = "Joining date cannot be in the future.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate joiningDate;

    public Employee() { }

    public Employee copy() {
        Employee copy = new Employee();
        copy.setId(id);
        copy.setName(name);
        copy.setEmail(email);
        copy.setDepartment(department);
        copy.setDesignation(designation);
        copy.setSalary(salary);
        copy.setJoiningDate(joiningDate);
        return copy;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }
    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }
    public LocalDate getJoiningDate() { return joiningDate; }
    public void setJoiningDate(LocalDate joiningDate) { this.joiningDate = joiningDate; }
}
