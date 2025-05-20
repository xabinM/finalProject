package com.example.demo.domain.supplement;

import com.example.demo.domain.BaseTimeEntity;
import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SupplementIntake extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int amountTaken;

    @ManyToOne
    private User user;

    @ManyToOne
    private Supplement supplement;
}