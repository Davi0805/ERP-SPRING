package com.gnose.mvp.Core.Application.UseCases;

import java.util.List;

public interface IRolePermission {
    void assignPermissionToRole(Long roleId, Long permissionId);
    void removePermissionFromRole(Long roleId, Long permissionId);
    List<Long> getPermissionsByRoleId(Long roleId);
    List<Long> getRolesByPermissionId(Long permissionId);
}
