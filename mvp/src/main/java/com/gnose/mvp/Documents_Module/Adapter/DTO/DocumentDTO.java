package com.gnose.mvp.Documents_Module.Adapter.DTO;

import com.gnose.mvp.Documents_Module.Infrastructure.DocumentType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentDTO {

    private Long companyId;
    private Long importOrderId;
    private String description;
    private DocumentType type;

    @Column(nullable = false)
    private String fileType;
}
