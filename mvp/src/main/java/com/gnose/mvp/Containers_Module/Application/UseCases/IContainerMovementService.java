package com.gnose.mvp.Containers_Module.Application.UseCases;

import com.gnose.mvp.Containers_Module.Infrastructure.Entities.ContainerMovementsJpaEntity;

import java.util.List;

public interface IContainerMovementService {
    ContainerMovementsJpaEntity createContainerMovement(ContainerMovementsJpaEntity containerMovementsJpaEntity);
    void updateContainerMovement(ContainerMovementsJpaEntity containerMovementsJpaEntity);
    void deleteContainerMovement(Long id);
    ContainerMovementsJpaEntity getById(Long id);
    List<ContainerMovementsJpaEntity> getByContainerId(Long containerId);
    List<ContainerMovementsJpaEntity> getByCompanyId(Long companyId);
    List<ContainerMovementsJpaEntity> getByCompanyIdAndPortId(Long companyId, Long portId);
    List<ContainerMovementsJpaEntity> getByCompanyIdAndPortIdAndDate(Long companyId, Long portId, String date);

}
