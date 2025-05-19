package com.gnose.mvp.Import_Orders_Shipments_Module.Application.Impl;

import com.gnose.mvp.Exceptions.UnauthorizedException;
import com.gnose.mvp.Import_Orders_Shipments_Module.Adapters.inbound.ImportOrderDTO;
import com.gnose.mvp.Import_Orders_Shipments_Module.Application.UseCases.IImportOrderService;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Adapters.ImportOrderJpaRepository;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities.ImportOrdersJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportOrderServiceImpl implements IImportOrderService {

    private final ImportOrderJpaRepository importOrderJpaRepository;

    @Autowired
    public ImportOrderServiceImpl(ImportOrderJpaRepository importOrderJpaRepository)
    {
        this.importOrderJpaRepository = importOrderJpaRepository;
    }

    @Override
    public ImportOrdersJpaEntity getById(Long importOrderId, List<Long> companyIds) {
        ImportOrdersJpaEntity entity = importOrderJpaRepository.findById(importOrderId)
                .orElseThrow(() -> new RuntimeException("Order not found!"));
        if (!companyIds.contains(entity.getCompanyId())) throw new UnauthorizedException("Not allowed to see this order");
        return entity;
    }

    @Override
    public ImportOrdersJpaEntity create(ImportOrderDTO importOrder) {
        return importOrderJpaRepository.save(new ImportOrdersJpaEntity(importOrder));
    }

    @Override
    public ImportOrdersJpaEntity update(ImportOrdersJpaEntity importOrder) {
        if (!importOrderJpaRepository.existsById(importOrder.getId()))
            throw new RuntimeException("Order not found!");
        return importOrderJpaRepository.save(importOrder);
    }

    @Override
    public void deleteImportOrder(Long importOrderId, List<Long> companyIds) {
        if (!importOrderJpaRepository.existsById(importOrderId))
            throw new RuntimeException("Order not found!");
        importOrderJpaRepository.deleteById(importOrderId);
    }

    @Override
    public List<ImportOrdersJpaEntity> getAllByCompanyId(Long companyId) {
        return importOrderJpaRepository.findByCompanyId(companyId).orElseThrow(() -> new RuntimeException("Orders not found!"));
    }

    @Override
    public List<ImportOrdersJpaEntity> getAllByCompanyIdAndStatus(Long companyId, String status) {
        return importOrderJpaRepository.findByCompanyIdAndStatus(companyId, status).orElseThrow(() -> new RuntimeException("Orders not found!"));
    }
}
