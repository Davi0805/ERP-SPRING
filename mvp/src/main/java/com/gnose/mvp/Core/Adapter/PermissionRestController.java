package com.gnose.mvp.Core.Adapter;

import com.gnose.mvp.Core.Models.PermissionsJpaEntity;
import com.gnose.mvp.Core.Repositories.PermissionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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