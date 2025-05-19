package com.gnose.mvp.Import_Orders_Shipments_Module.Application.UseCases;

import com.gnose.mvp.Import_Orders_Shipments_Module.Adapters.inbound.ShipmentsDTO;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities.ShipmentsJpaEntity;

import java.util.List;
import java.util.Optional;

public interface IShipmentsService {
    ShipmentsJpaEntity getById(Long shipmentId, List<Long> companyIds);
    ShipmentsJpaEntity create(ShipmentsDTO shipment, List<Long> companyIds);
    ShipmentsJpaEntity update(Long id, ShipmentsDTO shipment, List<Long> companyIds);
    void deleteShipment(Long shipmentId, List<Long> companyIds);
    List<ShipmentsJpaEntity> getByShipId(Long shipId, List<Long> companyIds);
    List<ShipmentsJpaEntity> getByImportOrderId(Long importOrderId, List<Long> companyIds);
    List<ShipmentsJpaEntity> getAll(List<Long> companyIds);
}
