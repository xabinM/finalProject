package com.example.demo.dto.column;

import lombok.Getter;

import java.util.List;

@Getter
public class ColumnAllListResponse {
    private List<ColumnResponseDto> columns;

    public ColumnAllListResponse(List<ColumnResponseDto> columns) {
        this.columns = columns;
    }
}
