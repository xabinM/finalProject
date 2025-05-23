package com.example.demo.dto.community;

import lombok.Getter;

import java.util.List;

@Getter
public class PostListResponse {

    private List<CommunityPostDto> postDtos;

    public PostListResponse(List<CommunityPostDto> postDtos) {
        this.postDtos = postDtos;
    }
}
