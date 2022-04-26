package com.demo.empmngmntsys.controller;

import com.demo.empmngmntsys.MockDataBuilder;
import com.demo.empmngmntsys.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @MockBean
    EmployeeService employeeService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void createEmp() throws Exception {
        when(employeeService.saveEmployee(MockDataBuilder.mockEmployeeRequest())).thenReturn(MockDataBuilder.mockEmployeeDetails());
        mockMvc.perform(post("/v1/employee")
                .content(this.mapper.writeValueAsString(MockDataBuilder.mockEmployeeRequest()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].firstName", Matchers.is("aaa")))
                .andExpect(jsonPath("$[2].email", Matchers.is("f3l3@mail.com")));
        verify(employeeService, times(1)).saveEmployee(anyList());
    }

    @Test
    public void getEmp() throws Exception {
        when(employeeService.getEmployees()).thenReturn(MockDataBuilder.mockEmployeeDetails());
        mockMvc.perform(get("/v1/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].firstName", Matchers.is("aaa")))
                .andExpect(jsonPath("$[2].email", Matchers.is("f3l3@mail.com")));
        verify(employeeService, times(1)).getEmployees();
    }

    @Test
    public void updateEmp() throws Exception {
        when(employeeService.updateEmployee(1, MockDataBuilder.mockEmployeeRequest().get(0))).thenReturn(MockDataBuilder.mockEmployeeDetails().get(0));
        mockMvc.perform(put("/v1/employee/{id}", 1)
                .content(this.mapper.writeValueAsString(MockDataBuilder.mockEmployeeRequest().get(0)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName", Matchers.is("aaa")))
                .andExpect(jsonPath("email", Matchers.is("f1l1@mail.com")));
        verify(employeeService, times(1)).updateEmployee(anyInt(), any());
    }

    @Test
    public void deleteEmp() throws Exception {
        when(employeeService.deleteEmployee(1)).thenReturn("Deleted");
        mockMvc.perform(delete("/v1/employee/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals("Deleted", result.getResponse().getContentAsString()));
        verify(employeeService, times(1)).deleteEmployee(anyInt());
    }


}