package com.example.demo.dto.community;

import lombok.Getter;

@Getter
public class RegisterPostResponse {
    private Long id;

    public RegisterPostResponse(Long id) {
        this.id = id;
    }
}
