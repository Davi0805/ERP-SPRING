package com.gnose.mvp.Containers_Module.Application.Impl;

import com.gnose.mvp.Containers_Module.Application.UseCases.IContainerService;
import com.gnose.mvp.Containers_Module.Infrastructure.Adapters.ContainerJpaRepository;
import com.gnose.mvp.Containers_Module.Infrastructure.Entities.ContainerJpaEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
            throw new RuntimeException("Container not found!");
        }
        containerJpaRepository.save(containerJpaEntity);
    }

    @Override
    public void deleteContainer(Long id) {
        if (!containerJpaRepository.existsById(id)) {
            throw new RuntimeException("Container not found!");
        }
        containerJpaRepository.deleteById(id);
    }

    @Override
    public ContainerJpaEntity getById(Long id) {
        return containerJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Container not found!"));
    }


    // todo: Implement the following methods
    @Override
    public ContainerJpaEntity getByNumber(String containerNumber) {
        return containerJpaRepository.findByContainerNumber(containerNumber)
                .orElseThrow(() -> new RuntimeException("Container not found!"));
    }

    @Override
    public ContainerJpaEntity getByType(String type) {
        return containerJpaRepository.findByType(type)
                .orElseThrow(() -> new RuntimeException("Container not found!"));
    }

    @Override
    public ContainerJpaEntity getByWeight(String weight) {
        return containerJpaRepository.findByWeight(Integer.parseInt(weight))
                .orElseThrow(() -> new RuntimeException("Container not found!"));
    }

    @Override
    public ContainerJpaEntity getByCompanyId(Long companyId) {
        return containerJpaRepository.findByCompanyId(companyId)
                .orElseThrow(() -> new RuntimeException("Container not found!"));
    }
}
