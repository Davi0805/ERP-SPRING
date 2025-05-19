package com.gnose.mvp.Ports_Ships_Module.Infrastructure.Entities;

import com.gnose.mvp.Ports_Ships_Module.Adapter.ShipInDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShipsJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String imo; // international ship unique identifier

    private Integer capacity; // in TEU (Twenty-foot Equivalent Unit)

    public ShipsJpaEntity(ShipInDTO dto) {
        this.name = dto.getName();
        this.imo = dto.getImo();
        this.capacity = dto.getCapacity();
    }
}
