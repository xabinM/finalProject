package com.example.demo.repository.calendar;

import com.example.demo.domain.calender.CalendarLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CalendarLogRepository extends JpaRepository<CalendarLog, Long> {
    Optional<CalendarLog> findByUserIdAndDate(Long userId, LocalDate createdAt);
}
