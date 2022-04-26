package com.demo.empmngmntsys;

import com.demo.empmngmntsys.controller.data.EmployeeDetails;
import com.demo.empmngmntsys.controller.data.EmployeeRequest;
import com.demo.empmngmntsys.entity.Employee;

import java.util.Arrays;
import java.util.List;

public class MockDataBuilder {

    public static List<EmployeeRequest> mockEmployeeRequest() {
        List<EmployeeRequest> employeeRequestList = Arrays.asList(
                new EmployeeRequest("aaa", "xxx", "f1l1@mail.com"),
                new EmployeeRequest("bbb", "yyy", "f2l2@mail.com"),
                new EmployeeRequest("ccc", "zzz", "f3l3@mail.com")
        );
        return employeeRequestList;
    }

    public static List<EmployeeDetails> mockEmployeeDetails() {
        List<EmployeeDetails> employeeDetailsList = Arrays.asList(
                new EmployeeDetails(1, "aaa", "xxx", "f1l1@mail.com"),
                new EmployeeDetails(2, "bbb", "yyy", "f2l2@mail.com"),
                new EmployeeDetails(3, "ccc", "zzz", "f3l3@mail.com")
        );
        return employeeDetailsList;
    }

    public static List<Employee> mockEmployee() {
        List<Employee> employeeList = Arrays.asList(
                new Employee(1, "aaa", "xxx", "f1l1@mail.com"),
                new Employee(2, "bbb", "yyy", "f2l2@mail.com"),
                new Employee(3, "ccc", "zzz", "f3l3@mail.com")
        );
        return employeeList;
    }
}
