package com.gnose.mvp.Documents_Module.Adapter.inbound;

import com.gnose.mvp.Core.Adapter.outbound.DTO.SessionRedisDTO;
import com.gnose.mvp.Core.Application.Impl.RedisServiceImpl;
import com.gnose.mvp.Core.Application.UseCases.IAuthService;
import com.gnose.mvp.Documents_Module.Adapter.DTO.DocumentDTO;
import com.gnose.mvp.Documents_Module.Adapter.outbound.DocumentJpaRepository;
import com.gnose.mvp.Documents_Module.Application.IDocumentPersistService;
import com.gnose.mvp.Documents_Module.Application.IDocumentsBlobService;
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

    @Autowired
    public DocumentsController(IDocumentsBlobService blobService,
                               IDocumentPersistService documentPersistService,
                               RedisServiceImpl redisServiceImpl) {
        this.blobService = blobService;
        this.documentPersistService = documentPersistService;
        this.redisServiceImpl = redisServiceImpl;
    }

    @PostMapping("/generate-upload-url")
    public ResponseEntity<?> generateUploadUrl(@RequestBody DocumentDTO req,
                                               @RequestHeader("Authorization") String token) {
        try {
            //todo: check import order
            SessionRedisDTO session = redisServiceImpl.getSession(token.replace("Bearer ", ""));
            if (session.getCompanyPermission().stream().noneMatch(company ->
                            company.getCompanyId().equals(req.getCompanyId())))
            {
                throw new RuntimeException("Unauthorized");
            }


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

    //todo: authorization
    @GetMapping("/generate-download-url")
    public ResponseEntity<?> generateDownloadUrl(@RequestParam String id) {
        try {
            DocumentsJpaEntity entity = documentPersistService.findDocumentById(UUID.fromString(id));
            return ResponseEntity.ok(Map.of("url", blobService.createDocumentBlob(id + "." + entity.getFileType())));

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/list/{importOrderId}")
    public ResponseEntity<?> listDocuments(@PathVariable Long importOrderId) {
        try {
            return ResponseEntity.ok(documentPersistService.listDocumentsByImportOrderId(importOrderId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return badRequest().build();
        }
    }
}
