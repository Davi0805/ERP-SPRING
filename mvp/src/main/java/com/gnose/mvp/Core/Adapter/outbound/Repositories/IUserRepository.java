package com.gnose.mvp.Core.Adapter.outbound.Repositories;

import com.gnose.mvp.Core.Infrastructure.Entities.UserCompanyJpaEntity;
import com.gnose.mvp.Core.Infrastructure.Entities.UserJpaEntity;

import java.util.List;

public interface IUserRepository {
    UserJpaEntity createUser(UserJpaEntity user);
    UserJpaEntity findById(Long id);
    UserJpaEntity findByEmail(String email);
    List<UserJpaEntity> findAll();
    void delete(Long user_id);
}
