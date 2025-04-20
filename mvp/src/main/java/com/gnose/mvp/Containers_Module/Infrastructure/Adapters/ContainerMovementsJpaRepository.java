package com.gnose.mvp.Containers_Module.Infrastructure.Adapters;

import com.gnose.mvp.Containers_Module.Infrastructure.Entities.ContainerMovementsJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContainerMovementsJpaRepository extends JpaRepository<ContainerMovementsJpaEntity, Long> {
    // Define any custom query methods here if needed
    // For example:
    // Optional<ContainerMovementsJpaEntity> findByMovementType(String movementType);
}
