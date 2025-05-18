package com.gnose.mvp.Containers_Module.Infrastructure.Adapters;

import com.gnose.mvp.Containers_Module.Infrastructure.Entities.ContainerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContainerJpaRepository extends JpaRepository<ContainerJpaEntity, Long> {
    Optional<ContainerJpaEntity> findByContainerNumberAndCompanyIdIn(String containerNumber, List<Long> companyIds);
    Optional<List<ContainerJpaEntity>> findByTypeAndCompanyIdIn(String type, List<Long> companyIds);
    Optional<List<ContainerJpaEntity>> findByWeightAndCompanyIdIn(Integer weight, List<Long> companyIds);
    Optional<List<ContainerJpaEntity>> findByCompanyId(Long companyId);
    Optional<ContainerJpaEntity> findByIdAndCompanyId(Long id, Long CompanyId);
    Optional<ContainerJpaEntity> findByIdAndCompanyIdIn(Long id, List<Long> companyIds);
    Optional<List<ContainerJpaEntity>> findByCompanyIdIn(List<Long> companyIds);
    Boolean existsByIdAndCompanyIdIn(Long id, List<Long> companyIds);
}
