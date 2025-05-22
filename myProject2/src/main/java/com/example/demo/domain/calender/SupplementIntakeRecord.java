package com.example.demo.domain.calender;

import com.example.demo.domain.enums.IntakeTimeType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SupplementIntakeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String supplementName;      // 직접 입력한 이름 (ex. 비타민C)
    private int amountTakenMg;          // 복용량

    @Enumerated(EnumType.STRING)
    private IntakeTimeType timeType;    // 아침/점심/저녁/기타

    @ManyToOne
    private CalendarLog calendarLog;

    public SupplementIntakeRecord() {
    }

    public SupplementIntakeRecord(String supplementName, int amountTakenMg,
                                  String timeType, CalendarLog calendarLog) {
        this.supplementName = supplementName;
        this.amountTakenMg = amountTakenMg;
        this.timeType = IntakeTimeType.valueOf(timeType.toUpperCase());
        this.calendarLog = calendarLog;
    }
}
