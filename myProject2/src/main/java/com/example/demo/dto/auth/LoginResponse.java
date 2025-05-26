package com.example.demo.dto.auth;

import lombok.Getter;

@Getter
public class LoginResponse {
    private final LoginUserDto user;
    private final String token;

    public LoginResponse(LoginUserDto userDto, String token) {
        this.user = userDto;
        this.token = token;
    }
}
