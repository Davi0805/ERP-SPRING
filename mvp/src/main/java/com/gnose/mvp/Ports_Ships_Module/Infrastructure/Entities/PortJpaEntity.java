package com.gnose.mvp.Ports_Ships_Module.Infrastructure.Entities;

import com.gnose.mvp.Ports_Ships_Module.Adapter.PortInputDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ports")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PortJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(unique = true)
    private String code;

    public PortJpaEntity(PortInputDTO dto) {
        this.name = dto.getName();
        this.country = dto.getCountry();
        this.code = dto.getCode();
    }
}
