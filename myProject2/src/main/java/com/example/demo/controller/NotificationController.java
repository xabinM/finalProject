package com.example.demo.controller;

import com.example.demo.domain.user.User;
import com.example.demo.dto.notification.NotificationDto;
import com.example.demo.dto.notification.NotificationListResponse;
import com.example.demo.dto.notification.NotificationRequest;
import com.example.demo.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<?> registerNotification(@RequestBody NotificationRequest request,
                                                     @AuthenticationPrincipal User user) {
        try {
            System.out.println("와오와요ㅗㅇ" + request.toString());

            notificationService.registerNotification(user, request);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/today")
    public ResponseEntity<?> getTodayNotifications(@AuthenticationPrincipal User user) {
        try {
            List<NotificationDto> notificationDtos = notificationService.getTodayNotifications(user.getId());

            return ResponseEntity.ok().body(new NotificationListResponse(notificationDtos));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getMyNotifications(@AuthenticationPrincipal User user) {
        try {
            List<NotificationDto> notificationDtos = notificationService.getUserNotifications(user.getId());

            return ResponseEntity.ok().body(new NotificationListResponse(notificationDtos));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/read/{id}")
    public ResponseEntity<?> readNotification(@PathVariable Long id) {
        try {
            notificationService.markAsRead(id);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
