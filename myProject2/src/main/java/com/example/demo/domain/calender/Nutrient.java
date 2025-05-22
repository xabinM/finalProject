package com.example.demo.domain.calender;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
// 영양소 정의 (ex. 비타민C, 마그네슘)
public class Nutrient {
    @Id @GeneratedValue
    private Long id;

    private String name;
}
