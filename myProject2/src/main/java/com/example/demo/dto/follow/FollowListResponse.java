package com.example.demo.dto.follow;

import lombok.Getter;

import java.util.List;

@Getter
public class FollowListResponse {

    private List<FollowDto> follows;

    public FollowListResponse(List<FollowDto> follows) {
        this.follows = follows;
    }
}
