package com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Adapters;

import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities.ImportOrdersJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImportOrderJpaRepository extends JpaRepository<ImportOrdersJpaEntity, Long> {
    Optional<List<ImportOrdersJpaEntity>> findByCompanyId(Long companyId);
    Optional<List<ImportOrdersJpaEntity>> findByCompanyIdAndStatus(Long companyId, String status);
}
