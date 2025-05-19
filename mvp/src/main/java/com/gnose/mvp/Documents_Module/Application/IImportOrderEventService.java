package com.gnose.mvp.Documents_Module.Application;

import com.gnose.mvp.Documents_Module.Adapter.DTO.DocumentDTO;

public interface IImportOrderEventService {
    Boolean isImportOrderValid(DocumentDTO dto);
}
