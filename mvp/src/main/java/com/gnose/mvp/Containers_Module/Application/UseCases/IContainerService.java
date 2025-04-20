package com.gnose.mvp.Containers_Module.Application.UseCases;

import com.gnose.mvp.Containers_Module.Infrastructure.Entities.ContainerJpaEntity;

import java.util.List;

public interface IContainerService {
    ContainerJpaEntity createContainer(ContainerJpaEntity containerJpaEntity);
    void updateContainer(ContainerJpaEntity containerJpaEntity);
    void deleteContainer(Long id);
    ContainerJpaEntity getById(Long id);
    ContainerJpaEntity getByNumber(String containerNumber);
    List<ContainerJpaEntity> getByType(String type);
    List<ContainerJpaEntity> getByWeight(String weight);
    //ContainerJpaEntity getBySize(String size);
    List<ContainerJpaEntity> getByCompanyId(Long companyId);
    List<ContainerJpaEntity> getAllContainers();
}
