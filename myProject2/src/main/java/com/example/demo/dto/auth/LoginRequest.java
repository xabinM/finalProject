package com.example.demo.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
