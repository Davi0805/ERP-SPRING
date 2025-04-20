package com.gnose.mvp.Containers_Module.Application.UseCases;

import com.gnose.mvp.Containers_Module.Infrastructure.Entities.ContainerJpaEntity;

public interface IContainerService {
    ContainerJpaEntity createContainer(ContainerJpaEntity containerJpaEntity);
    void updateContainer(ContainerJpaEntity containerJpaEntity);
    void deleteContainer(Long id);
    ContainerJpaEntity getById(Long id);
    ContainerJpaEntity getByNumber(String containerNumber);
    ContainerJpaEntity getByType(String type);
    ContainerJpaEntity getByWeight(String weight);
    //ContainerJpaEntity getBySize(String size);
    ContainerJpaEntity getByCompanyId(Long companyId);
}
