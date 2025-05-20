package com.example.demo.domain.notification;

import com.example.demo.domain.BaseTimeEntity;
import com.example.demo.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
// 시스템이 사용자에게 발송한 알림 기록. 메시지, 읽음 여부, 발송 시각 포함.
public class Notification extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private boolean IsRead;

    @ManyToOne
    private User user;
}