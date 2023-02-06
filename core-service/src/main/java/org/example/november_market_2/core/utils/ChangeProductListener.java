package org.example.november_market_2.core.utils;

import lombok.RequiredArgsConstructor;
import org.example.november_market_2.core.entities.Notification;
import org.example.november_market_2.core.repositories.NotificationRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangeProductListener {

    private final NotificationRepository notificationRepository;

    @EventListener(ProductChangeEvent.class)
    public void reportPriceChange(ProductChangeEvent event) {
        Notification notification = Notification.Builder.newBuilder()
                .withProductId(event.getProductId())
                .withText("Цена продукта '" + event.getProductTitle() + "' изменена. Новая цена " + event.getNewPrice()
                        + ". Чтобы обновить цену в заказе очистите корзину и выберете товар снова")
                .build();
        notificationRepository.save(notification);
    }

    @EventListener(ProductDeleteEvent.class)
    public void reportDeletion(ProductDeleteEvent event) {
        Notification notification = Notification.Builder.newBuilder()
                .withProductId(event.getProductId())
                .withText("Продукт '" + event.getProductTitle() + "' более не доступен для заказа"
                        + ". Заказ с этим товаром не будет оформлен. Пожалуйста, удалите товар из корзины")
                .build();
        notificationRepository.save(notification);
    }
}
