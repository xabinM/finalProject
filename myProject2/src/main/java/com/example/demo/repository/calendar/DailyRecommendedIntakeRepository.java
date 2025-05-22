package com.example.demo.repository.calendar;

import com.example.demo.domain.calender.DailyRecommendedIntake;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

// 영양소 이름으로 권장량 조회
public interface DailyRecommendedIntakeRepository extends JpaRepository<DailyRecommendedIntake, Long> {
    Optional<DailyRecommendedIntake> findByNutrientName(String nutrientName);

}
