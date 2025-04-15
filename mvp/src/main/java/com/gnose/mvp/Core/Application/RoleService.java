package com.gnose.mvp.Core.Application;

import com.gnose.mvp.Core.Adapter.outbound.Repositories.RoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleJpaRepository jpaRepository;

    @Autowired
    public RoleService(RoleJpaRepository jpaRepository)
    {
        this.jpaRepository = jpaRepository;
    }
}
