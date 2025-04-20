package com.gnose.mvp.Core.Application.Impl;

import com.gnose.mvp.Core.Infrastructure.Adapter.Outbound.JpaRepositories.RoleJpaRepository;
import com.gnose.mvp.Core.Application.UseCases.IRoleService;
import com.gnose.mvp.Core.Infrastructure.Entities.RolesJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    private final RoleJpaRepository jpaRepository;

    @Autowired
    public RoleServiceImpl(RoleJpaRepository jpaRepository)
    {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<RolesJpaEntity> getAllRoles() {
        return jpaRepository.findAll();
    }
    @Override
    public RolesJpaEntity createRole(RolesJpaEntity role) {
        return jpaRepository.save(role);
    }

    @Override
    public void deleteRole(Long id) {
        if (!jpaRepository.existsById(id))
            throw new RuntimeException("Role not found!");
        jpaRepository.deleteById(id);
    }

    @Override
    public RolesJpaEntity getRoleById(Long id) {
        return jpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found!"));
    }
}
