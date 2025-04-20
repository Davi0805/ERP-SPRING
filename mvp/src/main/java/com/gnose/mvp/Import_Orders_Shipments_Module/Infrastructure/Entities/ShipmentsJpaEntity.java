package com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "shipments")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ShipmentsJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long importOrderId;

    @Column(nullable = false)
    private Long shipId;

    private LocalDateTime departureDate;

    private LocalDateTime arrivalDate;
}
