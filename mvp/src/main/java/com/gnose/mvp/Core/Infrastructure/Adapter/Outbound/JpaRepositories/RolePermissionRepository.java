package com.gnose.mvp.Core.Infrastructure.Adapter.Outbound.JpaRepositories;

import com.gnose.mvp.Core.Infrastructure.Entities.RolePermissionsJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermissionsJpaEntity, Long> {
    List<RolePermissionsJpaEntity> findByRoleId(Long roleId);
}
