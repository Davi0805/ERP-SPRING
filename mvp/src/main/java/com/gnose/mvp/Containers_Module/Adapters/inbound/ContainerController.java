package com.gnose.mvp.Containers_Module.Adapters.inbound;

import com.gnose.mvp.Authorization.AuthorizationBaseController;
import com.gnose.mvp.Authorization.CheckAccess;
import com.gnose.mvp.Containers_Module.Application.UseCases.IContainerService;
import com.gnose.mvp.Containers_Module.Infrastructure.Entities.ContainerJpaEntity;
import com.gnose.mvp.Core.Adapter.outbound.DTO.CompanyPermissionDTO;
import com.gnose.mvp.Logging.LogContext;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/containers")
@LogContext(module = "Containers")
public class ContainerController extends AuthorizationBaseController {

    private final IContainerService containerService;
    private static final Logger log = LoggerFactory.getLogger(ContainerController.class);

    @Autowired
    public ContainerController(IContainerService containerService) {
        this.containerService = containerService;
    }

    @PostMapping
    @CheckAccess(permission = "CREATE_CONTAINER", companyId = "#companyId")
    public ResponseEntity<?> createContainer(@RequestBody ContainerJpaEntity container) {
        log.info("TESTANDOOOOO");
        containerService.createContainer(container);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @CheckAccess(permission = "UPDATE_CONTAINER", companyId = "#companyId")
    public ResponseEntity<?> updateContainer(@PathVariable Long id , @RequestBody ContainerJpaEntity container) {
        container.setId(id);
        containerService.updateContainer(container);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @CheckAccess(permission = "DELETE_CONTAINER", companyId = "*")
    public ResponseEntity<?> deleteContainer(@PathVariable Long id) {
        containerService.deleteContainer(id, getAuthorizedCompanyIds());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @CheckAccess(permission = "VIEW_CONTAINER", companyId = "*")
    public ResponseEntity<?> getAllContainers() {
        List<ContainerJpaEntity> containers = containerService.getAllContainers(getAuthorizedCompanyIds());
        return ResponseEntity.ok(containers);
    }

    @GetMapping("/{id}")
    @CheckAccess(permission = "VIEW_CONTAINER", companyId = "*")
    public ResponseEntity<?> getContainerById(@PathVariable Long id) {
        ContainerJpaEntity container = containerService.getById(id, getAuthorizedCompanyIds());
        return ResponseEntity.ok(container);
    }

    @GetMapping("/number/{containerNumber}")
    @CheckAccess(permission = "VIEW_CONTAINER", companyId = "*")
    public ResponseEntity<?> getContainerByNumber(@PathVariable String containerNumber) {
        ContainerJpaEntity container = containerService.getByNumber(containerNumber, getAuthorizedCompanyIds());
        return ResponseEntity.ok(container);
    }

    @GetMapping("/type/{type}")
    @CheckAccess(permission = "VIEW_CONTAINER", companyId = "*")
    public ResponseEntity<?> getContainerByType(@PathVariable String type) {
        List<ContainerJpaEntity> container = containerService.getByType(type, getAuthorizedCompanyIds());
        return ResponseEntity.ok(container);
    }

    @GetMapping("/weight/{weight}")
    @CheckAccess(permission = "VIEW_CONTAINER", companyId = "*")
    public ResponseEntity<?> getContainerByWeight(@PathVariable Integer weight) {
        List<ContainerJpaEntity> container = containerService.getByWeight(weight, getAuthorizedCompanyIds());
        return ResponseEntity.ok(container);
    }

    @GetMapping("/company/{companyId}")
    @CheckAccess(permission = "VIEW_CONTAINER", companyId = "companyId")
    public ResponseEntity<?> getContainerByCompanyId(@PathVariable Long companyId) {
        List<ContainerJpaEntity> container = containerService.getByCompanyId(companyId);
        return ResponseEntity.ok(container);
    }

}
