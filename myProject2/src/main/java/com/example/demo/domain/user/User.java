package com.example.demo.domain.user;

import com.example.demo.domain.BaseTimeEntity;
import com.example.demo.domain.badge.UserBadge;
import com.example.demo.domain.calender.CalendarLog;
import com.example.demo.domain.community.Comment;
import com.example.demo.domain.community.CommunityPost;
import com.example.demo.domain.enums.Gender;
import com.example.demo.domain.enums.UserRole;
import com.example.demo.domain.follow.Follow;
import com.example.demo.domain.notification.Notification;
import com.example.demo.domain.quest.QuestHistory;
import com.example.demo.domain.supplement.SupplementIntake;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "members")
public class User extends BaseTimeEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String nickname;
    private String profileImage;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Pharmacist pharmacist;

    @OneToMany(mappedBy = "user")
    private List<SupplementIntake> supplementIntakes;

    @OneToMany(mappedBy = "user") // OneToMany는 기본이 LAZY
    private List<QuestHistory> questHistories;

    @OneToMany(mappedBy = "user")
    private List<CalendarLog> calendarLogs;

    @OneToMany(mappedBy = "user")
    private List<CommunityPost> posts;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;

    @OneToMany(mappedBy = "user")
    private List<Follow> follows;

    @OneToMany(mappedBy = "user")
    private List<UserBadge> userBadges;

    public User() {
    }

    public User(String name, String password, String email, LocalDate birthDate, String gender,
                String nickname, String profileImage, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.gender = Gender.valueOf(gender.toUpperCase());
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.role = UserRole.valueOf(role.toUpperCase());
    }

    public void assignPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
        pharmacist.setUser(this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.name;
    }
}
