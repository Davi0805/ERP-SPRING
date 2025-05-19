package com.gnose.mvp.Documents_Module.Application.Impl;

import com.gnose.mvp.Documents_Module.Adapter.DTO.DocumentDTO;
import com.gnose.mvp.Documents_Module.Application.IImportOrderEventService;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Adapters.ImportOrderJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ImportOrderEventServiceImpl implements IImportOrderEventService {

    private final ImportOrderJpaRepository importOrderJpaRepository;

    public ImportOrderEventServiceImpl(ImportOrderJpaRepository importOrderJpaRepository) {
        this.importOrderJpaRepository = importOrderJpaRepository;
    }

    @Override
    public Boolean isImportOrderValid(DocumentDTO dto) {
        return importOrderJpaRepository.findById(dto.getImportOrderId())
                .filter(importOrder -> importOrder.getCompanyId().equals(dto.getCompanyId()))
                .isPresent();
    }
}
