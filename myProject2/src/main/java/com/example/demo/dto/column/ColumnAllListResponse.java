package com.example.demo.dto.column;

import com.example.demo.domain.column.Column;
import lombok.Getter;

import java.util.List;

@Getter
public class ColumnAllListResponse {
    private List<ColumnResponse> columns;

    public ColumnAllListResponse(List<ColumnResponse> columns) {
        this.columns = columns;
    }
}
