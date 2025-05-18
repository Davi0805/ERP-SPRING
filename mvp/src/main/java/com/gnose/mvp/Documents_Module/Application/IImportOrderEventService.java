package com.gnose.mvp.Documents_Module.Application;

public interface IImportOrderEventService {
    Boolean isImportOrderValid(Long importOrderId, Long companyId);
}
