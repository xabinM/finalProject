package com.example.demo.dto.calendar;

import lombok.Getter;

@Getter
public class DailyNutrientStatusDto {

    private String nutrientName;     // 예: 비타민C
    private int totalTakenMg;        // 오늘 섭취량 총합
    private int recommendedMg;       // 권장 섭취량
    private String message;          // "비타민C를 40mg 초과 섭취하셨습니다"

    public DailyNutrientStatusDto(String name, int totalTaken, int recommended, String message) {
        this.nutrientName = name;
        this.totalTakenMg = totalTaken;
        this.recommendedMg = recommended;
        this.message = message;
    }
}
