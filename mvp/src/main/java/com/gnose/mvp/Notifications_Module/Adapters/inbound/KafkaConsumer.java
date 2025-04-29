package com.gnose.mvp.Notifications_Module.Adapters.inbound;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gnose.mvp.Notifications_Module.Adapters.DTO.NotificationDTO;
import com.gnose.mvp.Notifications_Module.Adapters.outbound.NotificationJpaRepository;
import com.gnose.mvp.Notifications_Module.Infrastructure.Entities.NotificationJpaEntity;
import jakarta.persistence.Column;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private final ObjectMapper desserializer;
    private final NotificationJpaRepository jpaRepository;

    public KafkaConsumer(ObjectMapper mapper, NotificationJpaRepository repo)
    {
        this.desserializer = mapper;
        this.jpaRepository = repo;
    }

    @KafkaListener(topics = "notificationEvents", groupId = "notification-group")
    public void notification(String message)
    {
        System.out.println("MESSAGE: " + message);

        try {
            // replace by dto
            NotificationDTO entity = desserializer.readValue(message, NotificationDTO.class);
            System.out.println("MESSAGE: " + entity.getMessage() );

            jpaRepository.save(entity.toEntity());

            //todo: later add the broadcast logic to via ws
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
