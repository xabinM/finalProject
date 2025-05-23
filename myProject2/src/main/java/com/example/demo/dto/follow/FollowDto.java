package com.example.demo.dto.follow;

import com.example.demo.domain.follow.Follow;
import lombok.Getter;

@Getter
public class FollowDto {
    private Long pharmacistId;
    private String name;
    private String hospitalName;
    private String profileImage;

    public FollowDto(Long id, String name, String hospitalName, String profileImage) {
        this.pharmacistId = id;
        this.name = name;
        this.hospitalName = hospitalName;
        this.profileImage = profileImage;
    }

    public static FollowDto from(Follow follow) {
        return new FollowDto(
                follow.getPharmacist().getId(),
                follow.getUser().getName(),
                follow.getPharmacist().getHospitalName(),
                follow.getUser().getProfileImage()
        );
    }
}
