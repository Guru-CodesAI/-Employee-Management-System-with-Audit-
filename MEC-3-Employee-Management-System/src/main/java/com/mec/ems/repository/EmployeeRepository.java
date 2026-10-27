package com.mec.ems.repository;

import com.mec.ems.model.Employee;
import java.util.List;

public interface EmployeeRepository {
    Employee save(Employee employee);
    List<Employee> findAll();
}
