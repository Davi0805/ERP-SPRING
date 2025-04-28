package com.gnose.mvp.Notifications_Module.Infrastructure.Entities;

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
    private Long id;

    private Long companyId;

    private String service;

    private String message;

    @Enumerated(EnumType.STRING)
    private SeverityStatus severity;

    @CreatedDate
    private LocalDateTime createdAt;
}
