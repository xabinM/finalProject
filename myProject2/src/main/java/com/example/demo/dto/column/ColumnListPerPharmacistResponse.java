package com.example.demo.dto.column;

import com.example.demo.domain.column.Column;
import lombok.Getter;

import java.util.List;

@Getter
public class ColumnListPerPharmacistResponse {

    private List<ColumnResponse> columns;

    public ColumnListPerPharmacistResponse(List<ColumnResponse> columns) {
        this.columns = columns;
    }
}
