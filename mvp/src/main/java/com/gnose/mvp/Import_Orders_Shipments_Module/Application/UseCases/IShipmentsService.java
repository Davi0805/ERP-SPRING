package com.gnose.mvp.Import_Orders_Shipments_Module.Application.UseCases;

import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities.ShipmentsJpaEntity;

public interface IShipmentsService {
    ShipmentsJpaEntity getById(Long shipmentId);
    ShipmentsJpaEntity create(ShipmentsJpaEntity shipment);
    ShipmentsJpaEntity update(ShipmentsJpaEntity shipment);
    void deleteShipment(Long shipmentId);
    ShipmentsJpaEntity getByShipmentId(Long shipmentId);
    ShipmentsJpaEntity getByImportOrderId(Long importOrderId);
}
