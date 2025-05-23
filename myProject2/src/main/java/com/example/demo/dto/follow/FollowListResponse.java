package com.example.demo.dto.follow;

import lombok.Getter;

import java.util.List;

@Getter
public class FollowListResponse {

    private List<FollowDto> followDtos;

    public FollowListResponse(List<FollowDto> followDtos) {
        this.followDtos = followDtos;
    }
}
