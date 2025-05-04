package com.gnose.mvp.Documents_Module.Application.Impl;

import com.gnose.mvp.Documents_Module.Adapter.outbound.DocumentJpaRepository;
import com.gnose.mvp.Documents_Module.Application.IDocumentPersistService;
import com.gnose.mvp.Documents_Module.Infrastructure.DocumentType;
import com.gnose.mvp.Documents_Module.Infrastructure.DocumentsJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DocumentsPersistServiceImpl implements IDocumentPersistService {

    private final DocumentJpaRepository documentJpaRepository;

    @Autowired
    public DocumentsPersistServiceImpl(DocumentJpaRepository documentJpaRepository) {
        this.documentJpaRepository = documentJpaRepository;
    }

    @Override
    public UUID saveDocument(String description, Long importOrderId, Long companyId, DocumentType type, String fileType) {
        return documentJpaRepository.save(new DocumentsJpaEntity(null, companyId, importOrderId, description, type, fileType)).getId();
    }

    @Override
    public void deleteDocument(String fileName, String filePath) {

    }

    @Override
    public List<DocumentsJpaEntity> listDocumentsByImportOrderId(Long importOrderId) {
        return documentJpaRepository.findByImportOrderId(importOrderId)
                .orElseThrow(() -> new RuntimeException("Documents not found!"));
    }

    @Override
    public List<DocumentsJpaEntity> listDocumentsByCompanyIdAndType(Long companyId, DocumentType type) {
        return documentJpaRepository.findByCompanyIdAndType(companyId, type)
                .orElseThrow(() -> new RuntimeException("Documents not found!"));
    }

    @Override
    public DocumentsJpaEntity findDocumentById(UUID filename) {
        return documentJpaRepository.findById(filename)
                .orElseThrow(() -> new RuntimeException("Document not found!"));
    }
}
