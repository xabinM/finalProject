package com.example.demo.dto.users;

import com.example.demo.domain.enums.Gender;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserProfileResponse {
    private String name;
    private String email;
    private LocalDate birthDate;
    private String gender;
    private String nickname;
    private String profileImage;

    public UserProfileResponse(String name, String email, LocalDate birthDate, Gender gender, String nickname, String profileImage) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender.toString();
        this.nickname = nickname;
        this.profileImage = profileImage;
    }
}
