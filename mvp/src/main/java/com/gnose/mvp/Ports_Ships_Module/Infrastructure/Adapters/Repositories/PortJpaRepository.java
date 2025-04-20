package com.gnose.mvp.Ports_Ships_Module.Infrastructure.Adapters.Repositories;

import com.gnose.mvp.Ports_Ships_Module.Infrastructure.Entities.PortJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortJpaRepository extends JpaRepository<PortJpaEntity, Long> {
    Optional<PortJpaEntity> findByCountry(String country);
    Optional<PortJpaEntity> findByCode(String code);
}
