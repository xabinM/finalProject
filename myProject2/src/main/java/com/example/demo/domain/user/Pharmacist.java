package com.example.demo.domain.user;

import com.example.demo.domain.column.Column;
import com.example.demo.domain.follow.Follow;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
public class Pharmacist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licenseNumber; // 약사 면허 번호
    private String hospitalName;  // 근무 병원명

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "pharmacist")
    private List<Column> columns;

    @OneToMany(mappedBy = "pharmacist")
    private List<Follow> followers;

    public Pharmacist() {
    }

    public Pharmacist(String licenseNumber, String hospitalName, User user) {
        this.licenseNumber = licenseNumber;
        this.hospitalName = hospitalName;
        this.user = user;
    }
}
