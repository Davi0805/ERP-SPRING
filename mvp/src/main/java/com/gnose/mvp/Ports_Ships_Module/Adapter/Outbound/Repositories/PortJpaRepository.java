package com.gnose.mvp.Ports_Ships_Module.Adapter.Outbound.Repositories;

import com.gnose.mvp.Ports_Ships_Module.Models.PortJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortJpaRepository extends JpaRepository<PortJpaEntity, Long> {
    Optional<PortJpaEntity> findByCountry(String country);
    Optional<PortJpaEntity> findByCode(String code);
}
