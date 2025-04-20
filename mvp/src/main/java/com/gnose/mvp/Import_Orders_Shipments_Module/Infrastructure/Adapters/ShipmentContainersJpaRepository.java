package com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Adapters;

import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities.ShipmentContainersJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipmentContainersJpaRepository extends JpaRepository<ShipmentContainersJpaEntity, Long> {
    List<ShipmentContainersJpaEntity> findByShipmentId(Long shipmentId);
    List<ShipmentContainersJpaEntity> findByContainerId(Long containerId);
    Optional<List<ShipmentContainersJpaEntity>> findByContainerIdAndShipmentId(Long containerId, Long shipmentId);
}
