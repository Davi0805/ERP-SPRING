package com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Adapters;

import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities.ShipmentsJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipmentJpaRepository extends JpaRepository<ShipmentsJpaEntity, Long> {
    Optional<List<ShipmentsJpaEntity>> findByShipId(Long shipId);
    Optional<List<ShipmentsJpaEntity>> findByImportOrderId(Long importOrderId);
}
