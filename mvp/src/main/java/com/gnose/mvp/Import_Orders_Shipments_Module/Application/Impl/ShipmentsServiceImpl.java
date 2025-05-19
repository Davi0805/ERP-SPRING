package com.gnose.mvp.Import_Orders_Shipments_Module.Application.Impl;

import com.gnose.mvp.Exceptions.NotFoundException;
import com.gnose.mvp.Import_Orders_Shipments_Module.Adapters.inbound.ShipmentsDTO;
import com.gnose.mvp.Import_Orders_Shipments_Module.Application.UseCases.IImportOrderService;
import com.gnose.mvp.Import_Orders_Shipments_Module.Application.UseCases.IShipmentsService;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Adapters.ImportOrderJpaRepository;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Adapters.ShipmentJpaRepository;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities.ShipmentsJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentsServiceImpl implements IShipmentsService {

    private final ShipmentJpaRepository shipmentJpaRepository;
    private final IImportOrderService importOrderService;

    @Autowired
    public ShipmentsServiceImpl(ShipmentJpaRepository shipmentJpaRepository,
                                IImportOrderService importOrderService)
    {
        this.shipmentJpaRepository = shipmentJpaRepository;
        this.importOrderService = importOrderService;
    }

    @Override
    public ShipmentsJpaEntity getById(Long shipmentId, List<Long> companyIds) {
        ShipmentsJpaEntity entity = shipmentJpaRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Shipment not found!"));
        // checks if the importOrder exists and the user is authorized
        importOrderService.getById(entity.getImportOrderId(), companyIds);
        return entity;
    }

    @Override
    public ShipmentsJpaEntity create(ShipmentsDTO shipment, List<Long> companyIds) {
        // checks if the importOrder exists and the user is authorized
        importOrderService.getById(shipment.getImportOrderId(), companyIds);
        return shipmentJpaRepository.save(new ShipmentsJpaEntity(shipment));
    }

    @Override
    public ShipmentsJpaEntity update(Long id, ShipmentsDTO shipment, List<Long> companyIds) {
        // checks if the importOrder exists and the user is authorized
        importOrderService.getById(shipment.getImportOrderId(), companyIds);
        if (!shipmentJpaRepository.existsById(id)) throw new NotFoundException("Shipment not found!");
        ShipmentsJpaEntity entity = new ShipmentsJpaEntity(shipment);
        entity.setId(id);
        return shipmentJpaRepository.save(entity);
    }

    @Override
    public void deleteShipment(Long shipmentId, List<Long> companyIds) {
        // todo: check if the importORder exist and is part of one of his companies with permissions
        if (!shipmentJpaRepository.existsById(shipmentId)) throw new RuntimeException("Shipment not found!");
        shipmentJpaRepository.deleteById(shipmentId);
    }

    @Override
    public List<ShipmentsJpaEntity> getByShipId(Long shipId, List<Long> companyIds) {
        //todo: modify query to look for the ones connected to companies that user is part of
        return shipmentJpaRepository.findByShipId(shipId)
                .orElseThrow(() -> new RuntimeException("Shipment not found!"));
    }

    @Override
    public List<ShipmentsJpaEntity> getByImportOrderId(Long importOrderId, List<Long> companyIds) {
        // checks if the importOrder exists and the user is authorized
        importOrderService.getById(importOrderId, companyIds);
        return shipmentJpaRepository.findByImportOrderId(importOrderId)
                .orElseThrow(() -> new RuntimeException("Shipment not found!"));
    }


    // todo: implement custom query
//    @Override
//    public List<ShipmentsJpaEntity> getAll(List<Long> companyIds) {
//        return shipmentJpaRepository.findAllByCompanyIdIn(companyIds)
//                .orElseThrow(() -> new NotFoundException("Shipments not found!"));
//    }

    @Override
    public List<ShipmentsJpaEntity> getAll(List<Long> companyIds) {
        return shipmentJpaRepository.findAll();
    }
}
