package com.example.demo.dto.comment;

import com.example.demo.domain.community.Comment;
import lombok.Getter;

@Getter
public class CommentDto {

    private String content;

    public CommentDto(String content) {
        this.content = content;
    }

    public static CommentDto from(Comment comment) {
        return new CommentDto(comment.getContent());
    }
}
