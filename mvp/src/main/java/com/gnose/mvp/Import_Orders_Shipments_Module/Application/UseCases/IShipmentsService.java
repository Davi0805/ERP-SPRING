package com.gnose.mvp.Import_Orders_Shipments_Module.Application.UseCases;

import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities.ShipmentsJpaEntity;

import java.util.List;
import java.util.Optional;

public interface IShipmentsService {
    ShipmentsJpaEntity getById(Long shipmentId);
    ShipmentsJpaEntity create(ShipmentsJpaEntity shipment);
    ShipmentsJpaEntity update(ShipmentsJpaEntity shipment);
    void deleteShipment(Long shipmentId);
    List<ShipmentsJpaEntity> getByShipId(Long shipId);
    List<ShipmentsJpaEntity> getByImportOrderId(Long importOrderId);
    List<ShipmentsJpaEntity> getAll();
}
