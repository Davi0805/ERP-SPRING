package com.gnose.mvp.Containers_Module.Application.UseCases;

import com.gnose.mvp.Containers_Module.Infrastructure.Entities.ContainerJpaEntity;

import java.util.List;

public interface IContainerService {
    ContainerJpaEntity createContainer(ContainerJpaEntity containerJpaEntity);
    void updateContainer(ContainerJpaEntity containerJpaEntity);
    void deleteContainer(Long id, List<Long> companyIds);
    ContainerJpaEntity getById(Long id, List<Long> companyIds);
    ContainerJpaEntity getByNumber(String containerNumber, List<Long> companyIds);
    List<ContainerJpaEntity> getByType(String type, List<Long> companyIds);
    List<ContainerJpaEntity> getByWeight(Integer weight, List<Long> companyIds);
    //ContainerJpaEntity getBySize(String size);
    List<ContainerJpaEntity> getByCompanyId(Long companyId);
    List<ContainerJpaEntity> getAllContainers(List<Long> companyIds);
}
