package com.gnose.mvp.Containers_Module.Infrastructure.Adapters;

import com.gnose.mvp.Containers_Module.Infrastructure.Entities.ContainerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContainerJpaRepository extends JpaRepository<ContainerJpaEntity, Long> {
    // Define any custom query methods here if needed
    // For example:
     Optional<ContainerJpaEntity> findByContainerNumber(String containerNumber);
}
