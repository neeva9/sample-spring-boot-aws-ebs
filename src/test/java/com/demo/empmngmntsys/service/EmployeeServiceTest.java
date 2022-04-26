package com.demo.empmngmntsys.service;

import com.demo.empmngmntsys.MockDataBuilder;
import com.demo.empmngmntsys.controller.data.EmployeeDetails;
import com.demo.empmngmntsys.entity.Employee;
import com.demo.empmngmntsys.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Test
    public void saveEmp() {
        when(employeeRepository.saveAll(anyList())).thenReturn(MockDataBuilder.mockEmployee());
        List<EmployeeDetails> employeeDetails = employeeService.saveEmployee(MockDataBuilder.mockEmployeeRequest());
        assertEquals(3, employeeDetails.size());
    }

    @Test
    public void getEmp() {
        when(employeeRepository.findAll()).thenReturn(MockDataBuilder.mockEmployee());
        List<EmployeeDetails> employeeDetails = employeeService.getEmployees();
        assertEquals(3, employeeDetails.size());
    }

    @Test
    public void updateEmp() {
        when(employeeRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(MockDataBuilder.mockEmployee().get(0)));
        when(employeeRepository.save(any(Employee.class))).thenReturn(MockDataBuilder.mockEmployee().get(0));
        EmployeeDetails updatedEmployee = employeeService.updateEmployee(1, MockDataBuilder.mockEmployeeRequest().get(0));
        assertEquals(1, updatedEmployee.getId());
    }

    @Test
    public void deleteEmp() {
        when(employeeRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(MockDataBuilder.mockEmployee().get(0)));
        String deleteEmployee = employeeService.deleteEmployee(1);
        assertEquals("Deleted", deleteEmployee);
    }

    @Test
    public void deleteEmp_exc() {
        when(employeeRepository.findById(anyInt())).thenThrow(new RuntimeException("Some Exception"));
        RuntimeException assertThrows = assertThrows(RuntimeException.class, () -> {
            employeeService.deleteEmployee(1);
        });
        assertEquals("Some Exception", assertThrows.getMessage());
    }
}