package com.demo.empmngmntsys.controller.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EmployeeDetails {

    Integer id;
    String firstName;
    String lastName;
    String email;
}
