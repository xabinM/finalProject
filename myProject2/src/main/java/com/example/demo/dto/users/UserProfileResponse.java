package com.example.demo.dto.users;

import lombok.Getter;

@Getter
public class UserProfileResponse {
    private UserDto userDto;

    public UserProfileResponse(UserDto userDto) {
        this.userDto = userDto;
    }
}

