package com.example.demo.dto.column;

import com.example.demo.domain.column.Column;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ColumnDetailDto {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final String pharmacistName;

    public ColumnDetailDto(Column column) {
        this.id = column.getId();
        this.title = column.getTitle();
        this.content = column.getContent();
        this.createdAt = column.getCreatedAt();
        this.pharmacistName = column.getPharmacist().getUser().getName();
    }
}
