package com.gnose.mvp.Notifications_Module.Adapters.inbound;

import com.gnose.mvp.Notifications_Module.Infrastructure.Entities.NotificationJpaEntity;
import jakarta.persistence.Column;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "notificationEvents", groupId = "notification-group")
    public void notification(NotificationJpaEntity message)
    {
        System.out.println("MESSAGE: " + message);
    }
}
