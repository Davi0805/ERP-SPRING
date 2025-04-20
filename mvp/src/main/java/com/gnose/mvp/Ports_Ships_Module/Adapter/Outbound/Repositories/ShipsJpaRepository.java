package com.gnose.mvp.Ports_Ships_Module.Adapter.Outbound.Repositories;

import com.gnose.mvp.Ports_Ships_Module.Models.ShipsJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipsJpaRepository extends JpaRepository<ShipsJpaEntity, Long> {
    //Optional<ShipsJpaEntity> findByImo_number(String imo_number);
}
