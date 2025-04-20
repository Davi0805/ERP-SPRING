package com.gnose.mvp.Import_Orders_Shipments_Module.Application.UseCases;

public interface IContainerEventService {
    boolean isContainerValid(Long containerId, Long companyId);
}
