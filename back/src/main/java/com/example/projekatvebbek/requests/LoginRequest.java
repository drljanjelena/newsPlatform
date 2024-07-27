package com.example.projekatvebbek.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginRequest {

    @NotNull(message = "email is required")
    @NotEmpty(message = "email is required")
    private String email;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    private String password;

    public LoginRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

