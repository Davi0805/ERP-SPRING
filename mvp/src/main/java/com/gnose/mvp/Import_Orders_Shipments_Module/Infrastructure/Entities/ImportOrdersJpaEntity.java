package com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "import_orders")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ImportOrdersJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private UUID orderNumber;

    // todo: replace with a enum
    @Column(nullable = false)
    private String status;

    @CreatedDate
    private LocalDateTime createdAt;
}
