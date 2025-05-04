package com.gnose.mvp.Documents_Module.Application;

import com.gnose.mvp.Documents_Module.Infrastructure.DocumentType;
import com.gnose.mvp.Documents_Module.Infrastructure.DocumentsJpaEntity;

import java.util.List;
import java.util.UUID;

public interface IDocumentPersistService {
    UUID saveDocument(String description, Long importOrderId, Long companyId, DocumentType type, String fileType);

    void deleteDocument(String fileName, String filePath);

    List<DocumentsJpaEntity> listDocumentsByImportOrderId(Long importOrderId);

    List<DocumentsJpaEntity> listDocumentsByCompanyIdAndType(Long companyId, DocumentType type);

    DocumentsJpaEntity findDocumentById(UUID filename);
}