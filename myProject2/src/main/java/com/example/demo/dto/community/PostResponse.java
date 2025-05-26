package com.example.demo.dto.community;

import lombok.Getter;

@Getter
public class PostResponse {
    private CommunityPostDto communityPostDto;

    public PostResponse(CommunityPostDto detail) {
        this.communityPostDto = detail;
    }
}
