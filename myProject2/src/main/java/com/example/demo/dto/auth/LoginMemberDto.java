package com.example.demo.dto.auth;

import com.example.demo.domain.user.User;
import lombok.Getter;

@Getter
public class LoginMemberDto {
    private final Long id;
    private final String username;
    private final String email;

    public LoginMemberDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
