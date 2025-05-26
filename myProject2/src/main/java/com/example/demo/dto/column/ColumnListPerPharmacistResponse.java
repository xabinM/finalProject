package com.example.demo.dto.column;

import lombok.Getter;

import java.util.List;

@Getter
public class ColumnListPerPharmacistResponse {

    private final List<ColumnDto> columns;

    public ColumnListPerPharmacistResponse(List<ColumnDto> columns) {
        this.columns = columns;
    }
}
