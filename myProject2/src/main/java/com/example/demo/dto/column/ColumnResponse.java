package com.example.demo.dto.column;

import com.example.demo.domain.column.Column;
import lombok.Getter;

@Getter
public class ColumnResponse {
    private String title;
    private String content;
    private String pharmacistName;

    public ColumnResponse(Column column) {
        this.title = column.getTitle();
        this.content = column.getContent();
        this.pharmacistName = column.getPharmacist().getUser().getName();
    }
}
