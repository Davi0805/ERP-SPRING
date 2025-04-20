package com.gnose.mvp.Containers_Module.Infrastructure.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "container_movements")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContainerMovementsJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long container_id;

    @CreatedDate
    private LocalDateTime movement_date;

    @Column(nullable = false)
    private Long port_id;

    private String status;
}
