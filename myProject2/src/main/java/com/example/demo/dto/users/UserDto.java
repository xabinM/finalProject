package com.example.demo.dto.users;

import com.example.demo.domain.enums.Gender;
import com.example.demo.domain.user.User;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserDto {
    private final String name;
    private final String email;
    private final LocalDate birthDate;
    private final Gender gender;
    private final String nickName;
    private final String profileImage;

    public UserDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.birthDate = user.getBirthDate();
        this.gender = user.getGender();
        this.nickName = user.getNickname();
        this.profileImage = user.getProfileImage();
    }
}
