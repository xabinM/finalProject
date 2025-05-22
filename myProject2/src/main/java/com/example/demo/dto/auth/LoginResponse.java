package com.example.demo.dto.auth;

import lombok.Getter;

@Getter
public class LoginResponse {
    private final LoginMemberDto user;
    private final String token;

    public LoginResponse(LoginMemberDto userDto, String token) {
        this.user = userDto;
        this.token = token;
    }
}
