package com.example.demo.dto.community;

import com.example.demo.domain.community.CommunityPost;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommunityPostDto {

    private Long id;
    private String title;
    private String content;
    private String authorName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer viewCount;

    public CommunityPostDto(Long id, String title, String content, String authorName,
                            LocalDateTime createdAt, LocalDateTime updatedAt, Integer viewCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.viewCount = viewCount;
    }

    public static CommunityPostDto from(CommunityPost post) {
        return new CommunityPostDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUser().getName(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getViewCount()
        );
    }
}
