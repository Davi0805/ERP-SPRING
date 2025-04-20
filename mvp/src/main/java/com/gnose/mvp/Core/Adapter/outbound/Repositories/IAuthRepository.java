package com.gnose.mvp.Core.Adapter.outbound.Repositories;

import com.gnose.mvp.Core.Adapter.outbound.DTO.UserPermissionsDTO;

import java.util.List;

public interface IAuthRepository {
    List<UserPermissionsDTO> findPermissionsByUserId(Long userId);
}
