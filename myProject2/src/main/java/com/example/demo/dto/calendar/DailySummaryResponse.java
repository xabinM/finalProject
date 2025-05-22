package com.example.demo.dto.calendar;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class DailySummaryResponse {

    private LocalDate date;
    private List<DailyNutrientStatusDto> nutrientStatusList;
    private List<SupplementIntakeRecordDto> intakeRecords;

    public DailySummaryResponse(LocalDate date, List<DailyNutrientStatusDto> nutrientStatusList, List<SupplementIntakeRecordDto> intakeRecords) {
        this.date = date;
        this.nutrientStatusList = nutrientStatusList;
        this.intakeRecords = intakeRecords;
    }
}
