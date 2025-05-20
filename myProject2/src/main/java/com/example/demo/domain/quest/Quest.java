package com.example.demo.domain.quest;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int point;

    @OneToMany(mappedBy = "quest")
    private List<QuestHistory> questHistories;
}