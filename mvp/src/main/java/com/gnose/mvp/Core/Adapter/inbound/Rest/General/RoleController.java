package com.gnose.mvp.Core.Adapter.inbound.Rest.General;

import com.gnose.mvp.Authorization.AuthorizationBaseController;
import com.gnose.mvp.Authorization.CheckAccess;
import com.gnose.mvp.Core.Application.UseCases.IRoleService;
import com.gnose.mvp.Core.Infrastructure.Entities.RolesJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController extends AuthorizationBaseController {

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
    @CheckAccess(permission = "MANAGE_USERS", companyId = "#companyId")
    public ResponseEntity<?> createRole(@RequestBody RolesJpaEntity role) {
            return ResponseEntity.ok(roleService.createRole(role));
    }


    @DeleteMapping("/{id}")
    @CheckAccess(permission = "MANAGE_USERS", companyId = "*")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
            roleService.deleteRole(id, getAuthorizedCompanyIds());
            return ResponseEntity.noContent().build();
    }
}