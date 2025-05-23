package com.example.demo.dto.comment;

import lombok.Getter;

import java.util.List;

@Getter
public class CommentListResponse {
    private List<CommentDto> comments;

    public CommentListResponse(List<CommentDto> comments) {
        this.comments = comments;
    }
}
