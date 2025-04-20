package com.gnose.mvp.Core.Adapter.inbound.Rest.General;

import com.gnose.mvp.Core.Application.UseCases.IRoleService;
import com.gnose.mvp.Core.Infrastructure.Entities.RolesJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final IRoleService roleService;

    @Autowired
    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<RolesJpaEntity> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody RolesJpaEntity role) {

        try {
            return ResponseEntity.ok(roleService.createRole(role));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating role!");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        try {
            roleService.deleteRole(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}