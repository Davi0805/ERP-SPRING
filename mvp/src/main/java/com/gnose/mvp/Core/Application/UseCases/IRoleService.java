package com.gnose.mvp.Core.Application.UseCases;

import com.gnose.mvp.Core.Infrastructure.Entities.RolesJpaEntity;

import java.util.List;

public interface IRoleService {
    List<RolesJpaEntity> getAllRoles();
    RolesJpaEntity createRole(RolesJpaEntity role);
    void deleteRole(Long id);
    RolesJpaEntity getRoleById(Long id);
}
