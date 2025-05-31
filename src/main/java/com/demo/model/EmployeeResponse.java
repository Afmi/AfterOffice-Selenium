package com.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeResponse {
    @JsonProperty("id")
    public Integer id;

    @JsonProperty("email")
    public String email;

    @JsonProperty("password_hash")
    public String passwordHash;

    @JsonProperty("full_name")
    public String fullName;

    @JsonProperty("department")
    public String department;

    @JsonProperty("title")
    public String title;

    @JsonProperty("create_at")
    public String createAt;

    @JsonProperty("update_at")
    public String updateAt;

    public EmployeeResponse() {
    };
}
