package org.example.november_market_2.core.services;

import lombok.RequiredArgsConstructor;
import org.example.november_market_2.api.NotificationDto;
import org.example.november_market_2.core.converters.NotificationConverter;
import org.example.november_market_2.core.entities.Notification;
import org.example.november_market_2.core.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationConverter notificationConverter;

    public List<Notification> findAllFromCleared(LocalDate cleared) {
        return notificationRepository.findAllFromCleared(cleared);
    }

    public List<Notification> findTopHundredDesc() {
        return notificationRepository.findTop100ByOrderByIdDesc();
    }

    public List<NotificationDto> getNotificationsByProductIdList(List<Long> selectedNotids) {
        List<NotificationDto> result = new ArrayList<>();
        if (!selectedNotids.isEmpty()) {
            for (Long selectedNotid : selectedNotids) {
                List<Notification> notification = notificationRepository.findTop1ByProductId(selectedNotid);
                if (!notification.isEmpty()) {
                    result.add(notificationConverter.entityToDto(notification.get(0)));
                }
            }
        }
        return result;
    }
}
