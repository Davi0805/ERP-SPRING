package com.gnose.mvp.Containers_Module.Application.Impl;

import com.gnose.mvp.Containers_Module.Application.UseCases.IContainerService;
import com.gnose.mvp.Containers_Module.Infrastructure.Adapters.ContainerJpaRepository;
import com.gnose.mvp.Containers_Module.Infrastructure.Entities.ContainerJpaEntity;
import com.gnose.mvp.Exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContainerServiceImpl implements IContainerService {

    private final ContainerJpaRepository containerJpaRepository;

    public ContainerServiceImpl(ContainerJpaRepository containerJpaRepository) {
        this.containerJpaRepository = containerJpaRepository;
    }

    @Override
    @Transactional
    public ContainerJpaEntity createContainer(ContainerJpaEntity containerJpaEntity) {
        return containerJpaRepository.save(containerJpaEntity);
    }

    @Override
    public void updateContainer(ContainerJpaEntity containerJpaEntity) {
        if (!containerJpaRepository.existsById(containerJpaEntity.getId())) {
            throw new NotFoundException("Container not found!");
        }
        containerJpaRepository.save(containerJpaEntity);
    }

    @Override
    public void deleteContainer(Long id, List<Long> companyIds) {
        if (!containerJpaRepository.existsByIdAndCompanyIdIn(id, companyIds)) {
            throw new NotFoundException("Container not found!");
        }
        containerJpaRepository.deleteById(id);
    }

    @Override
    public ContainerJpaEntity getById(Long id, List<Long> companyIds) {
        ContainerJpaEntity container = containerJpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Container not found!"));

        if (!companyIds.contains(container.getCompanyId())) {
            throw new NotFoundException("Container not found!");
        }

        return container;
    }


    @Override
    public ContainerJpaEntity getByNumber(String containerNumber, List<Long> companyIds) {
        ContainerJpaEntity container = containerJpaRepository.findByContainerNumberAndCompanyIdIn(containerNumber, companyIds)
                .orElseThrow(() -> new NotFoundException("Container not found!"));

        return container;
    }

    @Override
    public List<ContainerJpaEntity> getByType(String type, List<Long> companyIds) {
        return containerJpaRepository.findByTypeAndCompanyIdIn(type, companyIds)
                .orElseThrow(() -> new NotFoundException("Container not found!"));
    }

    @Override
    public List<ContainerJpaEntity> getByWeight(Integer weight, List<Long> companyIds) {
        return containerJpaRepository.findByWeightAndCompanyIdIn(weight, companyIds)
                .orElseThrow(() -> new NotFoundException("Container not found!"));
    }

    @Override
    public List<ContainerJpaEntity> getByCompanyId(Long companyId) {
        return containerJpaRepository.findByCompanyId(companyId)
                .orElseThrow(() -> new RuntimeException("Container not found!"));
    }

    @Override
    public List<ContainerJpaEntity> getAllContainers(List<Long> companyIds) {
        return containerJpaRepository.findByCompanyIdIn(companyIds)
                .orElseThrow(() -> new NotFoundException("Container not found!"));
    }
}
