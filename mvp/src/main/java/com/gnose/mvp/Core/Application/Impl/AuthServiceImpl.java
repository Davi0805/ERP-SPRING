package com.gnose.mvp.Core.Application.Impl;

import com.gnose.mvp.Core.Adapter.outbound.Repositories.IAuthRepository;
import com.gnose.mvp.Core.Infrastructure.Adapter.Outbound.JpaRepositories.AuthJpaRepository;
import com.gnose.mvp.Core.Adapter.outbound.DTO.UserPermissionsDTO;
import com.gnose.mvp.Core.Application.UseCases.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements IAuthService {

    private final IAuthRepository jpaRepository;

    @Autowired
    public AuthServiceImpl(IAuthRepository jpaRepository)
    {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<UserPermissionsDTO> getPermissions(Long userId) {
        return jpaRepository.findPermissionsByUserId(userId);
    }
}
