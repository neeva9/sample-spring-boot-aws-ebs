package com.demo.empmngmntsys.controller;

import com.demo.empmngmntsys.controller.data.EmployeeDetails;
import com.demo.empmngmntsys.controller.data.EmployeeRequest;
import com.demo.empmngmntsys.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Create Employee")
    @PostMapping
    public List<EmployeeDetails> createEmployee(@RequestBody List<@Valid EmployeeRequest> employeeRequest) {
        return employeeService.saveEmployee(employeeRequest);
    }

    @Operation(summary = "List of Employees")
    @GetMapping
    public List<EmployeeDetails> getEmployees() {
        return employeeService.getEmployees();
    }

    @Operation(summary = "Update Employee")
    @PutMapping("/{id}")
    public EmployeeDetails updateEmployee(@PathVariable("id") Integer empId, @Validated @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.updateEmployee(empId, employeeRequest);
    }

    @Operation(summary = "Delete Employee")
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable("id") Integer empId) {
        return employeeService.deleteEmployee(empId);
    }
}
