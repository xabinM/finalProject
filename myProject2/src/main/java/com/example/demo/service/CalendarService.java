package com.example.demo.service;

import com.example.demo.domain.calender.CalendarLog;
import com.example.demo.domain.calender.DailyRecommendedIntake;
import com.example.demo.domain.calender.SupplementIntakeRecord;
import com.example.demo.domain.user.User;
import com.example.demo.dto.calendar.DailyNutrientStatusDto;
import com.example.demo.dto.calendar.DailySummaryResponse;
import com.example.demo.dto.calendar.IntakeRecordRequest;
import com.example.demo.dto.calendar.SupplementIntakeRecordDto;
import com.example.demo.repository.calendar.CalendarLogRepository;
import com.example.demo.repository.calendar.DailyRecommendedIntakeRepository;
import com.example.demo.repository.calendar.SupplementIntakeRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarLogRepository calendarLogRepository;
    private final SupplementIntakeRecordRepository intakeRecordRepository;
    private final DailyRecommendedIntakeRepository dailyRecommendedIntakeRepository;

    @Transactional
    public void saveIntakeRecord(User user, IntakeRecordRequest request) {
        CalendarLog calendarLog = calendarLogRepository.findByUserIdAndDate(user.getId(), request.getLocalDate())
                .orElseGet(() -> {
                    return calendarLogRepository.save(new CalendarLog(request.getLocalDate(), user));
                });

        SupplementIntakeRecord record = new SupplementIntakeRecord(
                request.getSupplementName(),
                request.getAmountTakenMg(),
                request.getIntakeTimeType(),
                calendarLog
        );
        intakeRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public DailySummaryResponse createDailySummary(Long userId, LocalDate date) {
        CalendarLog calendarLog = calendarLogRepository.findByUserIdAndDate(userId, date)
                .orElseThrow(() -> new IllegalArgumentException("해당 날짜의 복용 기록이 없습니다."));

        // 1. 복용 기록 조회
        List<SupplementIntakeRecord> records = intakeRecordRepository.findByCalendarLogId(calendarLog.getId());

        // 2. 영양소 총합 계산
        Map<String, Integer> nutrientTotals = new HashMap<>();
        for (SupplementIntakeRecord record : records) {
            String name = record.getSupplementName();
            int amount = record.getAmountTakenMg();
            nutrientTotals.put(name, nutrientTotals.getOrDefault(name, 0) + amount);
        }

        // 3. 권장 섭취량과 비교 후 메시지 생성
        List<DailyNutrientStatusDto> statusList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : nutrientTotals.entrySet()) {
            String name = entry.getKey();
            int totalTaken = entry.getValue();

            int recommended = dailyRecommendedIntakeRepository.findByNutrientName(name)
                    .map(DailyRecommendedIntake::getRecommendedAmountMg)
                    .orElse(0);

            String message;
            if (totalTaken > recommended) {
                message = name + "를 " + (totalTaken - recommended) + "mg 초과 섭취하셨습니다. 내일은 조금 줄여보아요!";
            } else if (totalTaken < recommended) {
                message = name + "을 " + (recommended - totalTaken) + "mg 덜 섭취하셨습니다. 내일은 조금 더 늘려보아요!";
            } else {
                message = name + " 섭취량이 적절합니다. 잘하고 계십니다!";
            }

            statusList.add(new DailyNutrientStatusDto(name, totalTaken, recommended, message));
        }

        // 4. 기록 DTO로 변환
        List<SupplementIntakeRecordDto> recordDtos = records.stream()
                .map(r -> new SupplementIntakeRecordDto(
                        r.getSupplementName(),
                        r.getAmountTakenMg(),
                        r.getTimeType()))
                .toList();

        // 5. 응답 생성
        return new DailySummaryResponse(date, statusList, recordDtos);
    }

































}
