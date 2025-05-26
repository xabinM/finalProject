package com.example.demo.service;

import com.example.demo.domain.notification.Notification;
import com.example.demo.domain.user.User;
import com.example.demo.dto.notification.NotificationDto;
import com.example.demo.dto.notification.NotificationRequest;
import com.example.demo.exception.Exception;
import com.example.demo.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void registerNotification(User user, NotificationRequest request) {
        Notification notification = new Notification(request.getMessage(), request.getNotifiedAt(), user);

        notificationRepository.save(notification);
    }

    public List<NotificationDto> getUserNotifications(Long userId) {

        List<Notification> notifications = notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);

        return notifications.stream()
                .map(NotificationDto::from)
                .toList();
    }

    public List<NotificationDto> getTodayNotifications(Long userId) {
        LocalDate today = LocalDate.now(); // ex. 2025-05-20
        LocalDateTime start = today.atStartOfDay();             // 2025-05-20T00:00:00
        LocalDateTime end = today.plusDays(1).atStartOfDay();   // 2025-05-21T00:00:00

        List<Notification> notifications = notificationRepository
                .findByUserIdAndCreatedAtBetween(userId, start, end);

        return notifications.stream()
                .map(NotificationDto::from) // DTO 변환
                .toList();
    }

    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException(Exception.NOT_EXIST_NOTIFICATION.getMessage()));

        notification.changeInReadStatus();

        notificationRepository.save(notification);
    }
}
