package com.mec.ems.repository;

import com.mec.ems.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/** Simple in-memory repository for a classroom/demo project. */
@Repository
public class InMemoryEmployeeRepository implements EmployeeRepository {

    private final ConcurrentMap<Long, Employee> employees = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    public Employee save(Employee employee) {
        Employee stored = employee.copy();
        if (stored.getId() == null) {
            stored.setId(sequence.incrementAndGet());
        }
        employees.put(stored.getId(), stored.copy());
        return stored.copy();
    }

    @Override
    public List<Employee> findAll() {
        return employees.values().stream()
                .sorted(Comparator.comparing(Employee::getId))
                .map(Employee::copy)
                .toList();
    }
}
