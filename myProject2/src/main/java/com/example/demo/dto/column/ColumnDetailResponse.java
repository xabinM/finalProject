package com.example.demo.dto.column;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ColumnDetailResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String pharmacistName;
    private Long pharmacistId;

    public ColumnDetailResponse(Long id, String title, String content, LocalDateTime createdAt, String pharmacistName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.pharmacistName = pharmacistName;
    }
}
