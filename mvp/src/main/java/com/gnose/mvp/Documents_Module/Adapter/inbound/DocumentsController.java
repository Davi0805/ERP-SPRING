package com.gnose.mvp.Documents_Module.Adapter.inbound;

import com.gnose.mvp.Core.Adapter.outbound.DTO.SessionRedisDTO;
import com.gnose.mvp.Core.Application.Impl.RedisServiceImpl;
import com.gnose.mvp.Core.Application.UseCases.IAuthService;
import com.gnose.mvp.Documents_Module.Adapter.DTO.DocumentDTO;
import com.gnose.mvp.Documents_Module.Adapter.outbound.DocumentJpaRepository;
import com.gnose.mvp.Documents_Module.Application.IDocumentPersistService;
import com.gnose.mvp.Documents_Module.Application.IDocumentsBlobService;
import com.gnose.mvp.Documents_Module.Application.IImportOrderEventService;
import com.gnose.mvp.Documents_Module.Infrastructure.DocumentsJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.badRequest;

@RestController
@RequestMapping("/api/documents")
public class DocumentsController {

    private final IDocumentsBlobService blobService;
    private final IDocumentPersistService documentPersistService;
    private final RedisServiceImpl redisServiceImpl;
    private final IImportOrderEventService importOrderEventService;

    @Autowired
    public DocumentsController(IDocumentsBlobService blobService,
                               IDocumentPersistService documentPersistService,
                               RedisServiceImpl redisServiceImpl,
                               IImportOrderEventService importOrderEventService) {
        this.blobService = blobService;
        this.documentPersistService = documentPersistService;
        this.redisServiceImpl = redisServiceImpl;
        this.importOrderEventService = importOrderEventService;
    }

    @PostMapping("/generate-upload-url")
    public ResponseEntity<?> generateUploadUrl(@RequestBody DocumentDTO req,
                                               @RequestHeader("Authorization") String token) {
        try {
            //todo: check import order
//            SessionRedisDTO session = redisServiceImpl.getSession(token.replace("Bearer ", ""));
//            if (session.getCompanyPermission().stream().noneMatch(company ->
//                            company.getCompanyId().equals(req.getCompanyId())))
//            {
//                throw new RuntimeException("Unauthorized");
//            }

            // todo: ativar quando possuir testes de integracao ou antes da prod
//            if (!importOrderEventService.isImportOrderValid(req.getImportOrderId(), req.getCompanyId()))
//                throw new RuntimeException("import order not valid");


            UUID id = documentPersistService.saveDocument(req.getDescription(),
                    req.getImportOrderId(), req.getCompanyId(),
                    req.getType(),
                    req.getFileType());
            String url = blobService.getDocumentBlobUrl(id.toString()
                                                        + "." + req.getFileType());

            return ResponseEntity.ok(Map.of("url", url));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/generate-download-url")
    public ResponseEntity<?> generateDownloadUrl(@RequestParam String id,
                                                 @RequestHeader("Authorization") String token) {
        try {
//            SessionRedisDTO session = redisServiceImpl.getSession(token.replace("Bearer ", ""));
            DocumentsJpaEntity entity = documentPersistService.findDocumentById(UUID.fromString(id));
//            if (session.getCompanyPermission().stream().noneMatch(company ->
//                    company.getCompanyId().equals(entity.getCompanyId())))
//                throw new RuntimeException("Unauthorized");


            return ResponseEntity.ok(Map.of("url", blobService.createDocumentBlob(id + "." + entity.getFileType())));

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // todo: authorization
    @GetMapping("/list/{importOrderId}")
    public ResponseEntity<?> listDocuments(@PathVariable Long importOrderId,
                                           @RequestHeader("Authorization") String token) {
        try {
            SessionRedisDTO session = redisServiceImpl.getSession(token.replace("Bearer ", ""));
            // todo: check if the user has permission to access the import order and import order id is from the same company
            return ResponseEntity.ok(documentPersistService.listDocumentsByImportOrderId(importOrderId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return badRequest().build();
        }
    }
}
