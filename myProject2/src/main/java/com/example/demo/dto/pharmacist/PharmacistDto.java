package com.example.demo.dto.pharmacist;

import lombok.Getter;

@Getter
public class PharmacistDto {

    private final String name;
    private final String profileImage;

    public PharmacistDto(String name, String profileImage) {
        this.name = name;
        this.profileImage = profileImage;
    }
}
