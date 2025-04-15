package com.gnose.mvp.Core.Adapter.inbound.Rest.General;

import com.gnose.mvp.Core.Models.PermissionsJpaEntity;
import com.gnose.mvp.Core.Adapter.outbound.Repositories.PermissionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionRestController {

    @Autowired
    private PermissionJpaRepository permissionsRepository;

    @GetMapping
    public List<PermissionsJpaEntity> getAllPermissions() {
        return permissionsRepository.findAll();
    }


    @PostMapping
    public PermissionsJpaEntity createPermission(@RequestBody PermissionsJpaEntity permission) {
        return permissionsRepository.save(permission);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermission(@PathVariable Long id) {
        if (permissionsRepository.existsById(id)) {
            permissionsRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}