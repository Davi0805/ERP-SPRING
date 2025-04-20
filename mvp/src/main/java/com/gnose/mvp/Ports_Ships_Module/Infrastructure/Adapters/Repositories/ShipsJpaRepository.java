package com.gnose.mvp.Ports_Ships_Module.Infrastructure.Adapters.Repositories;

import com.gnose.mvp.Ports_Ships_Module.Infrastructure.Entities.ShipsJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipsJpaRepository extends JpaRepository<ShipsJpaEntity, Long> {
    Optional<ShipsJpaEntity> findByImo(String imo);
}
