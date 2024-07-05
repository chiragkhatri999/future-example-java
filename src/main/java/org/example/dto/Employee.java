package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Employee {
    @JsonProperty("id")
    private long id;
    @JsonProperty("employee_name")
    private String name;
    @JsonProperty("employee_salary")
    private long salary;
    @JsonProperty("employee_age")
    private int age;
    @JsonProperty("profile_image")
    private String profileImage;
}
