package com.gnose.mvp.Core.Repositories;

import com.gnose.mvp.Core.Models.RolePermissionsJpaEntity;
import com.gnose.mvp.Core.Models.RolesJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermissionsJpaEntity, Long> {
    List<RolePermissionsJpaEntity> findByRoleId(Long roleId);
}
