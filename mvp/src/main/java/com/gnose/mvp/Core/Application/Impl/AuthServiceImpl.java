package com.gnose.mvp.Core.Application.Impl;

import com.gnose.mvp.Core.Adapter.outbound.AuthJpaRepository;
import com.gnose.mvp.Core.Adapter.outbound.DTO.UserPermissionsDTO;
import com.gnose.mvp.Core.Application.UseCases.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements IAuthService {

    private final AuthJpaRepository jpaRepository;

    @Autowired
    public AuthServiceImpl(AuthJpaRepository jpaRepository)
    {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<UserPermissionsDTO> getPermissions(Long userId) {
        return jpaRepository.rawFindPermissionsByUserId(userId).stream()
                .map(row -> new UserPermissionsDTO(
                        ((Number) row[0]).longValue(), // company_id
                        (String) row[1],               // role_name
                        (String) row[2]                // permission_code
                ))
                .collect(Collectors.toList());
    }
}
