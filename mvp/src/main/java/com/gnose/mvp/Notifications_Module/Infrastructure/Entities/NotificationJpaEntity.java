package com.gnose.mvp.Notifications_Module.Infrastructure.Entities;

import com.gnose.mvp.Notifications_Module.Adapters.DTO.NotificationDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "notifications")
public class NotificationJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long companyId;

    // maybe replace by enum
    private String service;

    private String message;

    @Enumerated(EnumType.STRING)
    private SeverityStatus severity;

    @CreatedDate
    private LocalDateTime createdAt;

    public NotificationDTO toDTO()
    {
        NotificationDTO dto = new NotificationDTO();
        dto.setService(this.service);
        dto.setMessage(this.message);
        dto.setSeverity(this.severity);
        dto.setCompanyId(this.companyId);

        return dto;
    }

}
