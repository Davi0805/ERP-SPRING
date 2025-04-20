package com.gnose.mvp.Containers_Module.Adapters.inbound;

import com.gnose.mvp.Containers_Module.Application.UseCases.IContainerService;
import com.gnose.mvp.Containers_Module.Infrastructure.Entities.ContainerJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/containers")
public class ContainerController {

    private final IContainerService containerService;

    @Autowired
    public ContainerController(IContainerService containerService) {
        this.containerService = containerService;
    }

    @GetMapping
    public ResponseEntity<?> getAllContainers() {
        try {
            List<ContainerJpaEntity> containers = containerService.getAllContainers();
            return ResponseEntity.ok(containers);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createContainer(@RequestBody ContainerJpaEntity container) {
        try {
            // it dont check the companyId cause it trusts the redis session permission
            containerService.createContainer(container);
            return ResponseEntity.ok("Container created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContainer(@PathVariable Long id , @RequestBody ContainerJpaEntity container) {
        try {
            containerService.updateContainer(container);
            return ResponseEntity.ok("Container updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContainer(@PathVariable Long id) {
        try {
            containerService.deleteContainer(id);
            return ResponseEntity.ok("Container deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContainerById(@PathVariable Long id) {
        try {
            ContainerJpaEntity container = containerService.getById(id);
            return ResponseEntity.ok(container);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/number/{containerNumber}")
    public ResponseEntity<?> getContainerByNumber(@PathVariable String containerNumber) {
        try {
            ContainerJpaEntity container = containerService.getByNumber(containerNumber);
            return ResponseEntity.ok(container);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<?> getContainerByType(@PathVariable String type) {
        try {
            List<ContainerJpaEntity> container = containerService.getByType(type);
            return ResponseEntity.ok(container);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/weight/{weight}")
    public ResponseEntity<?> getContainerByWeight(@PathVariable String weight) {
        try {
            List<ContainerJpaEntity> container = containerService.getByWeight(weight);
            return ResponseEntity.ok(container);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<?> getContainerByCompanyId(@PathVariable Long companyId) {
        try {
            List<ContainerJpaEntity> container = containerService.getByCompanyId(companyId);
            return ResponseEntity.ok(container);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
