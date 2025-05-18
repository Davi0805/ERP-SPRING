package com.gnose.mvp.Containers_Module.Application.Impl;

import com.gnose.mvp.Containers_Module.Application.UseCases.IContainerMovementService;
import com.gnose.mvp.Containers_Module.Infrastructure.Adapters.ContainerJpaRepository;
import com.gnose.mvp.Containers_Module.Infrastructure.Adapters.ContainerMovementsJpaRepository;
import com.gnose.mvp.Containers_Module.Infrastructure.Entities.ContainerJpaEntity;
import com.gnose.mvp.Containers_Module.Infrastructure.Entities.ContainerMovementsJpaEntity;
import com.gnose.mvp.Exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContainerMovementsServiceImpl implements IContainerMovementService {

    private final ContainerMovementsJpaRepository containerMovementsJpaRepository;
    private final ContainerJpaRepository containerJpaRepository;

    @Autowired
    public ContainerMovementsServiceImpl(ContainerMovementsJpaRepository containerMovementsJpaRepository,
                                         ContainerJpaRepository containerJpaRepository) {
        this.containerMovementsJpaRepository = containerMovementsJpaRepository;
        this.containerJpaRepository = containerJpaRepository;
    }

    @Override
    public ContainerMovementsJpaEntity createContainerMovement(ContainerMovementsJpaEntity containerMovementsJpaEntity,
                                                               List<Long> companyIds) {

        ContainerJpaEntity container = containerJpaRepository.findById(containerMovementsJpaEntity.getContainerId())
                .orElseThrow(() -> new NotFoundException("Container not found!"));

        if (!companyIds.contains(container.getCompanyId())) {
            throw new NotFoundException("Container not found!");
        }

        containerMovementsJpaEntity.setMovement_date(LocalDateTime.now());

        return containerMovementsJpaRepository.save(containerMovementsJpaEntity);
    }

    @Override
    public void updateContainerMovement(ContainerMovementsJpaEntity containerMovementsJpaEntity) {
        if (!containerMovementsJpaRepository.existsById(containerMovementsJpaEntity.getId())) {
            throw new RuntimeException("Container movement not found!");
        }
        containerMovementsJpaRepository.save(containerMovementsJpaEntity);
    }

    @Override
    public void deleteContainerMovement(Long id) {
        if (!containerMovementsJpaRepository.existsById(id)) {
            throw new RuntimeException("Container movement not found!");
        }
        containerMovementsJpaRepository.deleteById(id);
    }

    @Override
    public ContainerMovementsJpaEntity getById(Long id) {
        return containerMovementsJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Container movement not found!"));
    }

    @Override
    public List<ContainerMovementsJpaEntity> getByContainerId(Long containerId, List<Long> companyIds) {
        ContainerJpaEntity container = containerJpaRepository.findByIdAndCompanyIdIn(containerId, companyIds).orElseThrow(
                () -> new NotFoundException("Container not found!"));

        if (!companyIds.contains(container.getCompanyId()))
            throw new NotFoundException("Container not found!");

        List<ContainerMovementsJpaEntity> containerMovements = containerMovementsJpaRepository.findAllByContainerId(containerId);

        return containerMovements;
    }

    @Override
    public List<ContainerMovementsJpaEntity> getByCompanyId(Long companyId) {
        return List.of();
    }

    @Override
    public List<ContainerMovementsJpaEntity> getByCompanyIdAndPortId(Long companyId, Long portId) {
        return List.of();
    }

    @Override
    public List<ContainerMovementsJpaEntity> getByCompanyIdAndPortIdAndDate(Long companyId, Long portId, String date) {
        return List.of();
    }
}
