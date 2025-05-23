package com.example.demo.dto.notification;

import com.example.demo.domain.notification.Notification;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NotificationDto {
    private Long id;
    private String message;
    private boolean isRead;
    private LocalDateTime notifiedAt;

    public NotificationDto(Long id, String message, boolean isRead, LocalDateTime notifiedAt) {
        this.id = id;
        this.message = message;
        this.isRead = isRead;
        this.notifiedAt = notifiedAt;
    }

    public static NotificationDto from(Notification notification) {
        return new NotificationDto(
                notification.getId(),
                notification.getMessage(),
                notification.isIsRead(),
                notification.getCreatedAt()
        );
    }
}
