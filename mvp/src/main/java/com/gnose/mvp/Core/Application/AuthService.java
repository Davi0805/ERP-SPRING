package com.gnose.mvp.Core.Application;

import com.gnose.mvp.Core.Adapter.outbound.Repositories.AuthJpaRepository;
import com.gnose.mvp.Core.Adapter.outbound.Repositories.DTO.UserPermissionsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final AuthJpaRepository jpaRepository;

    @Autowired
    public AuthService(AuthJpaRepository jpaRepository)
    {
        this.jpaRepository = jpaRepository;
    }

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
