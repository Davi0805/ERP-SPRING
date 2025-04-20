package com.gnose.mvp.Core.Infrastructure.Adapter.Outbound.Impl;

import com.gnose.mvp.Core.Adapter.outbound.DTO.UserPermissionsDTO;
import com.gnose.mvp.Core.Adapter.outbound.Repositories.IAuthRepository;
import com.gnose.mvp.Core.Infrastructure.Adapter.Outbound.JpaRepositories.AuthJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthRepositoryAdapter implements IAuthRepository {

    private final AuthJpaRepository authJpaRepository;

    @Autowired
    public AuthRepositoryAdapter(AuthJpaRepository authJpaRepository) {
        this.authJpaRepository = authJpaRepository;
    }

    @Override
    public List<UserPermissionsDTO> findPermissionsByUserId(Long userId) {
        return authJpaRepository.FindPermissionsByUserId(userId).stream()
                .map(row -> new UserPermissionsDTO(
                        ((Number) row[0]).longValue(), // company_id
                        (String) row[1],               // role_name
                        (String) row[2]                // permission_code
                ))
                .collect(Collectors.toList());
    }
}
