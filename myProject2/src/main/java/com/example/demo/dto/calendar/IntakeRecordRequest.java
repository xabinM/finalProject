package com.example.demo.dto.calendar;

import lombok.Getter;

import java.time.LocalDate;


@Getter
public class IntakeRecordRequest {

    private String supplementName;           // ex. "비타민C"
    private int amountTakenMg;               // ex. 300
    private LocalDate localDate;
    private String intakeTimeType;           // MORNING, LUNCH 등
}
