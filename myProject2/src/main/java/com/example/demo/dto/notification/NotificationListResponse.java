package com.example.demo.dto.notification;

import lombok.Getter;

import java.util.List;

@Getter
public class NotificationListResponse {

    private List<NotificationDto> notificationDtos;

    public NotificationListResponse(List<NotificationDto> notificationDtos) {
        this.notificationDtos = notificationDtos;
    }
}
