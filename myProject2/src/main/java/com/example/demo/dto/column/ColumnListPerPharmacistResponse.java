package com.example.demo.dto.column;

import lombok.Getter;

import java.util.List;

@Getter
public class ColumnListPerPharmacistResponse {

    private List<ColumnResponseDto> columns;

    public ColumnListPerPharmacistResponse(List<ColumnResponseDto> columns) {
        this.columns = columns;
    }
}
