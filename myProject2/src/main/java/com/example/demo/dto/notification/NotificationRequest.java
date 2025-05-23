package com.example.demo.dto.notification;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NotificationRequest {
    private String message;
    private LocalDateTime notifiedAt;
}
