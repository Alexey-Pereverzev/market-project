package org.example.november_market_2.core.controllers;

import lombok.RequiredArgsConstructor;
import org.example.november_market_2.api.NotificationDto;
import org.example.november_market_2.core.converters.NotificationConverter;
import org.example.november_market_2.core.exceptions.AppError;
import org.example.november_market_2.core.services.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    private final NotificationConverter notificationConverter;

    @GetMapping("")
    public ResponseEntity<?> getNotificationIds() {
        List<NotificationDto> result = notificationService.findTopHundredDesc().stream()
                .map(notificationConverter::entityToDto).toList();
        if (result.isEmpty()) {
            return new ResponseEntity<>(new AppError("NO_NOTIFICATIONS_FOUND",
                    "Уведомления не найдены"), HttpStatus.NOT_FOUND);
        } else {
//            System.out.println(result);
            return ResponseEntity.ok(result);
        }
    }



}
