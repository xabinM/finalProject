package com.example.demo.dto.auth;

import com.example.demo.domain.user.User;
import lombok.Getter;

@Getter
public class LoginUserDto {
    private final Long id;
    private final String username;
    private final String email;

    public LoginUserDto(User user) {
        this.id = user.getId();
        this.username = user.getName();
        this.email = user.getEmail();
    }
}
