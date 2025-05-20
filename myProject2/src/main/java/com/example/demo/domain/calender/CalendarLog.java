package com.example.demo.domain.calender;

import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
// 사용자의 특정 날짜(하루) 복용 기록을 모은 상위 엔티티
public class CalendarLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "calendarLog", cascade = CascadeType.ALL)
    private List<SupplementIntakeRecord> intakeRecords = new ArrayList<>();
}
