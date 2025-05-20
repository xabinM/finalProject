package com.example.demo.domain.supplement;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Supplement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int doseMg;
    private int dailyLimit;

    @ElementCollection
    private List<String> ingredients;

    @OneToMany(mappedBy = "supplement")
    private List<SupplementIntake> supplementIntakes;
}