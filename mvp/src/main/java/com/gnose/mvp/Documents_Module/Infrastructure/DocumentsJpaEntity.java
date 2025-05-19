package com.gnose.mvp.Documents_Module.Infrastructure;

import com.gnose.mvp.Documents_Module.Adapter.DTO.DocumentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "documents")
public class DocumentsJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long companyId;

    private Long importOrderId;

    private String description;

    @Enumerated(EnumType.STRING)
    private DocumentType type;

    private String fileType;

    private LocalDateTime createdAt;

    public DocumentsJpaEntity(DocumentDTO dto) {
        this.companyId = dto.getCompanyId();
        this.importOrderId = dto.getImportOrderId();
        this.description = dto.getDescription();
        this.type = dto.getType();
        this.fileType = dto.getFileType();
        this.createdAt = LocalDateTime.now();
    }
}
