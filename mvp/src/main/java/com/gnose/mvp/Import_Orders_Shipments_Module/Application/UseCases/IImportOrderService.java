package com.gnose.mvp.Import_Orders_Shipments_Module.Application.UseCases;

import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities.ImportOrdersJpaEntity;

import java.util.List;

public interface IImportOrderService {
    ImportOrdersJpaEntity getById(Long importOrderId);
    ImportOrdersJpaEntity create(ImportOrdersJpaEntity importOrder);
    ImportOrdersJpaEntity update(ImportOrdersJpaEntity importOrder);
    void deleteImportOrder(Long importOrderId);
    List<ImportOrdersJpaEntity> getAllByCompanyId(Long companyId);
    List<ImportOrdersJpaEntity> getAllByCompanyIdAndStatus(Long companyId, String status);
}
