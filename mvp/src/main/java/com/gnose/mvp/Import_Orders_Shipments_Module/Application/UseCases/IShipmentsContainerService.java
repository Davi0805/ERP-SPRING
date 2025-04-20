package com.gnose.mvp.Import_Orders_Shipments_Module.Application.UseCases;

import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities.ShipmentContainersJpaEntity;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities.ShipmentsJpaEntity;

public interface IShipmentsContainerService {
    ShipmentContainersJpaEntity getById(Long id);
    ShipmentContainersJpaEntity save(ShipmentContainersJpaEntity shipmentContainer);
    void deleteById(Long id);
    ShipmentContainersJpaEntity getByShipmentId(Long shipmentId);
    ShipmentContainersJpaEntity getByContainerId(Long containerId);
    ShipmentContainersJpaEntity getByShipmentAndContainerId(Long shipmentId, Long containerId);

}
