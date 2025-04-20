package com.gnose.mvp.Core.Infrastructure.Adapter.Outbound.JpaRepositories;

import com.gnose.mvp.Core.Infrastructure.Entities.CompanyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyJpaRepository extends JpaRepository<CompanyJpaEntity, Long> {
    Optional<CompanyJpaEntity> findByCnpj(String cnpj);
    Optional<CompanyJpaEntity> findByName(String name);
}
