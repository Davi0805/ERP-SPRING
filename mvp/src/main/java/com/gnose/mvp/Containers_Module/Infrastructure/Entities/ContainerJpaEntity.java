package com.gnose.mvp.Containers_Module.Infrastructure.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "containers")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ContainerJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String container_number;

    private String type;

    private String weight;

    private String size;

    @Column(nullable = false)
    private Long company_id;
}
