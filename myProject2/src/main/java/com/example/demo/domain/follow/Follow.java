package com.example.demo.domain.follow;

import com.example.demo.domain.user.Pharmacist;
import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
        name = "follow",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "pharmacist_id"})
        }
)
// N:M 가운데 지점
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pharmacist_id", nullable = false)
    private Pharmacist pharmacist;

    public Follow() {
    }

    public Follow(User user, Pharmacist pharmacist) {
        this.user = user;
        this.pharmacist = pharmacist;
    }
}