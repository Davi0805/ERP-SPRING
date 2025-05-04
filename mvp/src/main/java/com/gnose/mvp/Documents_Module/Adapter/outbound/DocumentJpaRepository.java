package com.gnose.mvp.Documents_Module.Adapter.outbound;


import com.gnose.mvp.Documents_Module.Infrastructure.DocumentType;
import com.gnose.mvp.Documents_Module.Infrastructure.DocumentsJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentJpaRepository extends JpaRepository<DocumentsJpaEntity, UUID> {
    Optional<List<DocumentsJpaEntity>> findByImportOrderId(Long importOrderId);
    Optional<List<DocumentsJpaEntity>> findByCompanyIdAndType(Long companyId, DocumentType type);
}
