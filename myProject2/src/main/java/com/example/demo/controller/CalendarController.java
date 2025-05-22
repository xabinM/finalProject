package com.example.demo.controller;

import com.example.demo.domain.user.User;
import com.example.demo.dto.calendar.DailySummaryResponse;
import com.example.demo.dto.calendar.IntakeRecordRequest;
import com.example.demo.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    /**
     * 복용 기록 저장
     */
    @PostMapping("/intake")
    public ResponseEntity<?> registerIntake(@RequestBody IntakeRecordRequest request,
                                            @AuthenticationPrincipal User user) {
        try {
            calendarService.saveIntakeRecord(user, request);

            return ResponseEntity.ok("복용 기록이 저장되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 하루 요약 조회
     */
    @GetMapping("/summary")
    public ResponseEntity<?> getDailySummary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                                @AuthenticationPrincipal User user) {
        try {
            DailySummaryResponse summary = calendarService.createDailySummary(user.getId(), date);

            return ResponseEntity.ok().body(summary);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
