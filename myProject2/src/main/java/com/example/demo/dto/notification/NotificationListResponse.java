package com.example.demo.dto.notification;

import lombok.Getter;

import java.util.List;

@Getter
public class NotificationListResponse {

    private final List<NotificationDto> notifications;

    public NotificationListResponse(List<NotificationDto> notifications) {
        this.notifications = notifications;
    }
}
