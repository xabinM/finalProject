package com.example.demo.dto.column;

import com.example.demo.domain.column.Column;
import lombok.Getter;

@Getter
public class ColumnDto {
    private final String title;
    private final String content;
    private final String pharmacistName;

    public ColumnDto(Column column) {
        this.title = column.getTitle();
        this.content = column.getContent();
        this.pharmacistName = column.getPharmacist().getUser().getName();
    }
}
