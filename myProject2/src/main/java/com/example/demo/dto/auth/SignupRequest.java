package com.example.demo.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SignupRequest {
    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min = 5, max = 15, message = "아이디는 5글자 이상 15글자 이하로 입력해주세요.")
    private String name;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 20, message = "비밀번호는 8글자 이상 20글자 이하로 입력해주세요.")
    private String password;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email
    private String email;

    private LocalDate birthDate;
    private String gender; // MALE or FEMALE

    @NotBlank(message = "닉네임을 입력해주세요")
    private String nickname;

    @NotBlank(message = "프로필 사진을 넣어주세요.")
    private String profileImage;

    private String role; // USER or PHARMACIST

    // 약사 전용 필드 (선택)
    private String licenseNumber;
    private String hospitalName;

    public SignupRequest(String name, String password, String email, LocalDate birthDate,
                         String gender, String nickname, String profileImage, String role,
                         String licenseNumber, String hospitalName) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.role = role;
        this.licenseNumber = licenseNumber;
        this.hospitalName = hospitalName;
    }
}
