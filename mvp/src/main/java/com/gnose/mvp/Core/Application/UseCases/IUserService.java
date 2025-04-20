package com.gnose.mvp.Core.Application.UseCases;

import com.gnose.mvp.Core.Adapter.inbound.DTO.General.CreateUserDTO;
import com.gnose.mvp.Core.Infrastructure.Entities.UserJpaEntity;

import java.util.List;

public interface IUserService {
    UserJpaEntity createUser(CreateUserDTO req);

    List<UserJpaEntity> findAll();

    UserJpaEntity findById(Long id);

    UserJpaEntity findByEmail(String email);

    void delete(Long user_id);
}
