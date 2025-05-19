package com.gnose.mvp.Import_Orders_Shipments_Module.Application.Impl;

import com.gnose.mvp.Import_Orders_Shipments_Module.Adapters.inbound.ShipmentContainerDTO;
import com.gnose.mvp.Import_Orders_Shipments_Module.Application.UseCases.IShipmentsContainerService;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Adapters.ShipmentContainersJpaRepository;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities.ShipmentContainersJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentContainerServiceImpl implements IShipmentsContainerService {

    private final ShipmentContainersJpaRepository shipmentContainersJpaRepository;

    @Autowired
    public ShipmentContainerServiceImpl(ShipmentContainersJpaRepository shipmentContainersJpaRepository)
    {
        this.shipmentContainersJpaRepository = shipmentContainersJpaRepository;
    }

    @Override
    public ShipmentContainersJpaEntity getById(Long id) {
        return shipmentContainersJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ShipmentContainer not found!"));
    }

    @Override
    public ShipmentContainersJpaEntity save(ShipmentContainerDTO shipmentContainer) {
        // todo: implement a auth logic to check if the shipment and container is related to user companies
        return shipmentContainersJpaRepository.save(new ShipmentContainersJpaEntity(shipmentContainer));
    }

    @Override
    public void deleteById(Long id) {
        if (!shipmentContainersJpaRepository.existsById(id))
            throw new RuntimeException("ShipContainer not found!");

        shipmentContainersJpaRepository.deleteById(id);
    }

    @Override
    public List<ShipmentContainersJpaEntity> getByShipmentId(Long shipmentId) {
        return shipmentContainersJpaRepository.findByShipmentId(shipmentId)
                .orElseThrow(() -> new RuntimeException("ShipmentContainer not found!"));
    }

    @Override
    public ShipmentContainersJpaEntity getByContainerId(Long containerId) {
        return shipmentContainersJpaRepository.findByContainerId(containerId)
                .orElseThrow(() -> new RuntimeException("ShipmentContainer not found!"));
    }

    @Override
    public ShipmentContainersJpaEntity getByShipmentAndContainerId(Long shipmentId, Long containerId) {
        return shipmentContainersJpaRepository.findByContainerIdAndShipmentId(containerId, shipmentId)
                .orElseThrow(() -> new RuntimeException("ShipmentContainer not found!"));
    }
}
