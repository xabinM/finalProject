package com.example.demo.dto.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ProfileEditFormRequest {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min = 5, max = 15, message = "아이디는 5글자 이상 15글자 이하로 입력해주세요.")
    private String name;

    private LocalDate birthDate;
    private String gender;

    @NotBlank(message = "닉네임을 입력해주세요")
    private String nickname;

    @NotBlank(message = "프로필 사진을 넣어주세요.")
    private String profileImage;
}
