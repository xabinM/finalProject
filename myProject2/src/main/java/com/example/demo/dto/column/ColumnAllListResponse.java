package com.example.demo.dto.column;

import lombok.Getter;

import java.util.List;

@Getter
public class ColumnAllListResponse {
    private final List<ColumnDto> columns;

    public ColumnAllListResponse(List<ColumnDto> columns) {
        this.columns = columns;
    }
}
