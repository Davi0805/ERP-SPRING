package com.gnose.mvp.Notifications_Module.Adapters.DTO;

import com.gnose.mvp.Notifications_Module.Infrastructure.Entities.NotificationJpaEntity;
import com.gnose.mvp.Notifications_Module.Infrastructure.Entities.SeverityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

//{"companyId": 1,"service": "container","message": "teste2","severity": "LOW"}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

    private Long companyId;

    private String message;

    private SeverityStatus severity;

    private String service;

    public NotificationJpaEntity toEntity()
    {
        NotificationJpaEntity entity = new NotificationJpaEntity();
        entity.setMessage(this.getMessage());
        entity.setService(this.getService());
        entity.setCompanyId(this.getCompanyId());
        entity.setSeverity(this.getSeverity());
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }
}
