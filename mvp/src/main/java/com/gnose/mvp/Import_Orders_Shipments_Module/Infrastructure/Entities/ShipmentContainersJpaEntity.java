package com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities;

import com.gnose.mvp.Import_Orders_Shipments_Module.Adapters.inbound.ShipmentContainerDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shipment_containers")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ShipmentContainersJpaEntity {

    @Id
    private Long id;

    @Column(nullable = false)
    private Long shipmentId;

    @Column(nullable = false)
    private Long containerId;

    public ShipmentContainersJpaEntity(ShipmentContainerDTO dto)
    {
        this.containerId = dto.getContainerId();
        this.shipmentId = dto.getShipmentId();
    }
}
