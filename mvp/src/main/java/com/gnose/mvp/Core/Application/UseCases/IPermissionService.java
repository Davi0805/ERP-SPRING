package com.gnose.mvp.Core.Application.UseCases;

import com.gnose.mvp.Core.Infrastructure.Entities.PermissionsJpaEntity;

import java.util.List;

// No permissions methods for now, cause i will start with static permission
// and then move to dynamic permissions, when finish implementing the main modules
public interface IPermissionService {

    List<PermissionsJpaEntity> getAllPermissions();
    PermissionsJpaEntity createPermission(PermissionsJpaEntity permission);
    void deletePermission(Long id);
    PermissionsJpaEntity getPermissionById(Long id);
    List<PermissionsJpaEntity> getPermissionsByRoleId(Long roleId);
    List<PermissionsJpaEntity> getPermissionsByUserId(Long userId);
}
