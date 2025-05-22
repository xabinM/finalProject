package com.example.demo.dto.calendar;

import com.example.demo.domain.enums.IntakeTimeType;
import lombok.Getter;

@Getter
public class SupplementIntakeRecordDto {

    private String supplementName;
    private int amountTakenMg;
    private IntakeTimeType timeType;

    public SupplementIntakeRecordDto(String supplementName, int amountTakenMg, IntakeTimeType timeType) {
        this.supplementName = supplementName;
        this.amountTakenMg = amountTakenMg;
        this.timeType = timeType;
    }
}
