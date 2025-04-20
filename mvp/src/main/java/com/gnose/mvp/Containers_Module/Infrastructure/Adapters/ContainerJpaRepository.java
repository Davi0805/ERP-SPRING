package com.gnose.mvp.Containers_Module.Infrastructure.Adapters;

import com.gnose.mvp.Containers_Module.Infrastructure.Entities.ContainerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContainerJpaRepository extends JpaRepository<ContainerJpaEntity, Long> {
    Optional<ContainerJpaEntity> findByContainerNumber(String containerNumber);
    Optional<List<ContainerJpaEntity>> findByType(String type);
    Optional<List<ContainerJpaEntity>> findByWeight(Integer weight);
    Optional<List<ContainerJpaEntity>> findByCompanyId(Long companyId);
}
