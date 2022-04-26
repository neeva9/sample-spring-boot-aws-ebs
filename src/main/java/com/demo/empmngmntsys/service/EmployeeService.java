package com.demo.empmngmntsys.service;


import com.demo.empmngmntsys.controller.data.EmployeeDetails;
import com.demo.empmngmntsys.controller.data.EmployeeRequest;
import com.demo.empmngmntsys.entity.Employee;
import com.demo.empmngmntsys.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDetails> saveEmployee(List<EmployeeRequest> employeeRequestList) {

        List<Employee> employees = getEmployeeList(employeeRequestList);
        List<Employee> employeeList = employeeRepository.saveAll(employees);

        List<EmployeeDetails> employeeDetailsList = getEmployeeDetails(employeeList);

        return employeeDetailsList;
    }

    public List<EmployeeDetails> getEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeDetails> employeeDetails = getEmployeeDetails(employeeList);
        return employeeDetails;
    }

    public EmployeeDetails updateEmployee(Integer empId, EmployeeRequest employeeRequest) {
        Optional<Employee> employee = employeeRepository.findById(empId);
        Employee emp = new Employee();
        if (employee.isPresent()) {
            emp.setId(empId);
        }
        emp.setFirstName(employeeRequest.getFirstName());
        emp.setLastName(employeeRequest.getLastName());
        emp.setEmail(employeeRequest.getEmail());
        Employee updated = employeeRepository.save(emp);
        return getEmployeeDetails(updated);

    }

    public String deleteEmployee(Integer empId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(() -> new RuntimeException("Employee not found for this id :: " + empId));
        employeeRepository.delete(employee);
        return "Deleted";
    }

    private List<Employee> getEmployeeList(List<EmployeeRequest> employeeRequestList) {
        List<Employee> employees = new ArrayList<>();
        employeeRequestList.stream().forEach(emp -> {
            Employee employee = new Employee();
            employee.setFirstName(emp.getFirstName());
            employee.setLastName(emp.getLastName());
            employee.setEmail((emp.getEmail()));
            employees.add(employee);
        });
        return employees;
    }

    private List<EmployeeDetails> getEmployeeDetails(List<Employee> employeeList) {
        List<EmployeeDetails> employeeDetails = new ArrayList<>();
        employeeList.stream().forEach(employee -> {
            EmployeeDetails details = getEmployeeDetails(employee);
            employeeDetails.add(details);
        });
        return employeeDetails;
    }

    private EmployeeDetails getEmployeeDetails(Employee employee) {
        EmployeeDetails details = new EmployeeDetails();
        details.setId(employee.getId());
        details.setFirstName(employee.getFirstName());
        details.setLastName(employee.getLastName());
        details.setEmail(employee.getEmail());
        return details;
    }


}
