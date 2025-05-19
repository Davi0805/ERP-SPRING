package com.gnose.mvp.Import_Orders_Shipments_Module.Application.UseCases;

import com.gnose.mvp.Import_Orders_Shipments_Module.Adapters.inbound.ImportOrderDTO;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities.ImportOrdersJpaEntity;

import java.util.List;

public interface IImportOrderService {
    ImportOrdersJpaEntity getById(Long importOrderId, List<Long> companyIds);
    ImportOrdersJpaEntity create(ImportOrderDTO importOrder);
    ImportOrdersJpaEntity update(ImportOrdersJpaEntity importOrder);
    void deleteImportOrder(Long importOrderId, List<Long> companyIds);
    List<ImportOrdersJpaEntity> getAllByCompanyId(Long companyId);
    List<ImportOrdersJpaEntity> getAllByCompanyIdAndStatus(Long companyId, String status);
}
