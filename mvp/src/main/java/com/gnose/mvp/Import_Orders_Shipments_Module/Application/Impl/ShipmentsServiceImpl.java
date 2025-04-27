package com.gnose.mvp.Import_Orders_Shipments_Module.Application.Impl;

import com.gnose.mvp.Import_Orders_Shipments_Module.Application.UseCases.IShipmentsService;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Adapters.ShipmentJpaRepository;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities.ShipmentsJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentsServiceImpl implements IShipmentsService {

    private final ShipmentJpaRepository shipmentJpaRepository;

    @Autowired
    public ShipmentsServiceImpl(ShipmentJpaRepository shipmentJpaRepository)
    {
        this.shipmentJpaRepository = shipmentJpaRepository;
    }

    @Override
    public ShipmentsJpaEntity getById(Long shipmentId) {
        return shipmentJpaRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Shipment not found!"));
    }

    @Override
    public ShipmentsJpaEntity create(ShipmentsJpaEntity shipment) {
        return shipmentJpaRepository.save(shipment);
    }

    @Override
    public ShipmentsJpaEntity update(ShipmentsJpaEntity shipment) {
        if (!shipmentJpaRepository.existsById(shipment.getId()))
            throw new RuntimeException("Shipment not found!");
        return shipmentJpaRepository.save(shipment);
    }

    @Override
    public void deleteShipment(Long shipmentId) {
        if (!shipmentJpaRepository.existsById(shipmentId))
            throw new RuntimeException("Shipment not found!");
        shipmentJpaRepository.deleteById(shipmentId);
    }

    @Override
    public List<ShipmentsJpaEntity> getByShipId(Long shipId) {
        return shipmentJpaRepository.findByShipId(shipId)
                .orElseThrow(() -> new RuntimeException("Shipment not found!"));
    }

    @Override
    public List<ShipmentsJpaEntity> getByImportOrderId(Long importOrderId) {
        return shipmentJpaRepository.findByImportOrderId(importOrderId)
                .orElseThrow(() -> new RuntimeException("Shipment not found!"));
    }
}
