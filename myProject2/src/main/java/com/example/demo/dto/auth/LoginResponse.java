package com.example.demo.dto.auth;

import lombok.Getter;

@Getter
public class LoginResponse {
    private final LoginMemberDto user;
    private final String token;

    public LoginResponse(LoginMemberDto user, String token) {
        this.user = user;
        this.token = token;
    }
}
