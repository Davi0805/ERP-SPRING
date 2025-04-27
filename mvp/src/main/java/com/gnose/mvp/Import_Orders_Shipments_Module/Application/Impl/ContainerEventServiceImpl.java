package com.gnose.mvp.Import_Orders_Shipments_Module.Application.Impl;

import com.gnose.mvp.Containers_Module.Infrastructure.Adapters.ContainerJpaRepository;
import com.gnose.mvp.Containers_Module.Infrastructure.Entities.ContainerJpaEntity;
import com.gnose.mvp.Import_Orders_Shipments_Module.Application.UseCases.IContainerEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContainerEventServiceImpl implements IContainerEventService {

    private final ContainerJpaRepository containerJpaRepository;

    @Autowired
    public ContainerEventServiceImpl(ContainerJpaRepository containerJpaRepository)
    {
        this.containerJpaRepository = containerJpaRepository;
    }

    @Override
    public boolean isContainerValid(Long containerId, Long companyId) {
        ContainerJpaEntity entity = containerJpaRepository.findByIdAndCompanyId(containerId, companyId).orElse(null);
        return entity != null;
    }
}
