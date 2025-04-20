package com.gnose.mvp.Containers_Module.Infrastructure.Adapters;

import com.gnose.mvp.Containers_Module.Infrastructure.Entities.ContainerMovementsJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContainerMovementsJpaRepository extends JpaRepository<ContainerMovementsJpaEntity, Long> {
    // Define any custom query methods here if needed
    // For example:
    Optional<ContainerMovementsJpaEntity> findByContainerId(Long ContainerId);
    List<ContainerMovementsJpaEntity> findAllByContainerId(Long ContainerId);
    List<ContainerMovementsJpaEntity> findAllByPortId(Long portId);
    List<ContainerMovementsJpaEntity> findAllByStatus(String status);
}
