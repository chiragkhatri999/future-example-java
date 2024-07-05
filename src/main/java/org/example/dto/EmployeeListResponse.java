package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeListResponse {
    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private List<Employee> employeeList;
}
