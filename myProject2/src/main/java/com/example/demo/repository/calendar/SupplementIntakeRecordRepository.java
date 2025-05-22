package com.example.demo.repository.calendar;

import com.example.demo.domain.calender.SupplementIntakeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// 특정 날짜(CalendarLog ID)의 섭취 기록 모두 조회
public interface SupplementIntakeRecordRepository extends JpaRepository<SupplementIntakeRecord, Long> {
    List<SupplementIntakeRecord> findByCalendarLogId(Long calendarLogId);
}