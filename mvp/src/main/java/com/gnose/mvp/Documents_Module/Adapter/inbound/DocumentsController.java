package com.gnose.mvp.Documents_Module.Adapter.inbound;

import com.gnose.mvp.Authorization.AuthorizationBaseController;
import com.gnose.mvp.Authorization.CheckAccess;
import com.gnose.mvp.Core.Adapter.outbound.DTO.SessionRedisDTO;
import com.gnose.mvp.Core.Application.Impl.RedisServiceImpl;
import com.gnose.mvp.Core.Application.UseCases.IAuthService;
import com.gnose.mvp.Documents_Module.Adapter.DTO.DocumentDTO;
import com.gnose.mvp.Documents_Module.Adapter.outbound.DocumentJpaRepository;
import com.gnose.mvp.Documents_Module.Application.IDocumentPersistService;
import com.gnose.mvp.Documents_Module.Application.IDocumentsBlobService;
import com.gnose.mvp.Documents_Module.Application.IImportOrderEventService;
import com.gnose.mvp.Documents_Module.Infrastructure.DocumentsJpaEntity;
import com.gnose.mvp.Exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.badRequest;

@RestController
@RequestMapping("/api/documents")
public class DocumentsController extends AuthorizationBaseController {

    private final IDocumentsBlobService blobService;
    private final IDocumentPersistService documentPersistService;
    private final IImportOrderEventService importOrderEventService;

    @Autowired
    public DocumentsController(IDocumentsBlobService blobService,
                               IDocumentPersistService documentPersistService,
                               IImportOrderEventService importOrderEventService) {
        this.blobService = blobService;
        this.documentPersistService = documentPersistService;
        this.importOrderEventService = importOrderEventService;
    }

    @PostMapping("/generate-upload-url")
    @CheckAccess(permission = "UPLOAD_DOCUMENT", companyId = "#companyId")
    public ResponseEntity<?> generateUploadUrl(@RequestBody DocumentDTO req) {
//            if (!importOrderEventService.isImportOrderValid(req.getImportOrderId(), req.getCompanyId()))
//                throw new RuntimeException("import order not valid");
            UUID id = documentPersistService.saveDocument(req.getDescription(),
                    req.getImportOrderId(), req.getCompanyId(),
                    req.getType(),
                    req.getFileType());
            String url = blobService.getDocumentBlobUrl(id.toString()
                                                        + "." + req.getFileType());
            return ResponseEntity.ok(Map.of("url", url));
    }

    @GetMapping("/generate-download-url")
    @CheckAccess(permission = "DOWNLOAD_DOCUMENT", companyId = "*")
    public ResponseEntity<?> generateDownloadUrl(@RequestParam String id) {
            DocumentsJpaEntity entity = documentPersistService.findDocumentById(UUID.fromString(id));
            if (!getAuthorizedCompanyIds().contains(entity.getCompanyId()))
                throw new UnauthorizedException("Unauthorized to access this document");
            return ResponseEntity.ok(Map.of("url", blobService.createDocumentBlob(id + "." + entity.getFileType())));
    }

    @GetMapping("/list/{importOrderId}")
    @CheckAccess(permission = "VIEW_LIST_OF_DOCUMENT", companyId = "*")
    public ResponseEntity<?> listDocuments(@PathVariable Long importOrderId) {
        return ResponseEntity.ok(documentPersistService.listDocumentsByImportOrderAndCompanyIdIn(importOrderId, getAuthorizedCompanyIds()));
    }
}
