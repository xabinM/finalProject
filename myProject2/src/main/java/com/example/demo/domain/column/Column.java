package com.example.demo.domain.column;

import com.example.demo.domain.BaseTimeEntity;
import com.example.demo.domain.user.Pharmacist;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "pharmacist_column")
public class Column extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "pharmacist_id")
    private Pharmacist pharmacist;

    public Column() {
    }

    public Column(String title, String content, Pharmacist pharmacist) {
        this.title = title;
        this.content = content;
        this.pharmacist = pharmacist;
    }
}
