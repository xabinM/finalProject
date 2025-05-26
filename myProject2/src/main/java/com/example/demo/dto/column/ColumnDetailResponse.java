package com.example.demo.dto.column;

import lombok.Getter;

@Getter
public class ColumnDetailResponse {

    private final ColumnDetailDto columnDetailDto;

    public ColumnDetailResponse(ColumnDetailDto columnDetailDto) {
        this.columnDetailDto = columnDetailDto;
    }
}
