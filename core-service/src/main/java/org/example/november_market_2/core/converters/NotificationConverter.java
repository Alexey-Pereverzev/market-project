package org.example.november_market_2.core.converters;

import lombok.RequiredArgsConstructor;
import org.example.november_market_2.api.NotificationDto;
import org.example.november_market_2.core.entities.Notification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationConverter {
    public NotificationDto entityToDto(Notification n) {
        return NotificationDto.Builder.newBuilder()
                .withId(n.getId())
                .withText(n.getText())
                .withCreatedAt(n.getCreatedAt())
                .withProductId(n.getProductId())
                .build();
    }
}

