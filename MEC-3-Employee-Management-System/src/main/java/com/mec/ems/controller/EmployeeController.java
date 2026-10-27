package com.mec.ems.controller;

import com.mec.ems.model.Employee;
import com.mec.ems.service.EmployeeService;
import com.mec.ems.service.EmployeeValidationException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping({"/", "/employees"})
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.listEmployees());
        return "employee-list";
    }

    @GetMapping("/employees/new")
    public String showRegistrationForm(Model model) {
        Employee employee = new Employee();
        employee.setJoiningDate(LocalDate.now());
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @PostMapping("/employees")
    public String registerEmployee(@Valid @ModelAttribute("employee") Employee employee,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "employee-form";
        }

        try {
            employeeService.registerEmployee(employee);
        } catch (EmployeeValidationException ex) {
            ex.getFieldErrors().forEach((field, message) ->
                    bindingResult.rejectValue(field, "invalid." + field, message));
            return "employee-form";
        } catch (IllegalArgumentException ex) {
            bindingResult.reject("employee.invalid", ex.getMessage());
            return "employee-form";
        }

        redirectAttributes.addFlashAttribute("successMessage", "Employee registered successfully.");
        return "redirect:/employees";
    }
}
