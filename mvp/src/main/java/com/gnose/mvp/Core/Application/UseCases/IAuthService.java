package com.gnose.mvp.Core.Application.UseCases;

import com.gnose.mvp.Core.Adapter.outbound.DTO.UserPermissionsDTO;

import java.util.List;

public interface IAuthService {
    List<UserPermissionsDTO> getPermissions(Long userId);
}
