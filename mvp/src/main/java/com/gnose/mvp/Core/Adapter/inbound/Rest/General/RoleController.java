package com.gnose.mvp.Core.Adapter.inbound.Rest.General;

import com.gnose.mvp.Core.Models.RolesJpaEntity;
import com.gnose.mvp.Core.Adapter.outbound.Repositories.RoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleJpaRepository roleRepository;

    @GetMapping
    public List<RolesJpaEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    @PostMapping
    public RolesJpaEntity createRole(@RequestBody RolesJpaEntity role) {
        return roleRepository.save(role);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}