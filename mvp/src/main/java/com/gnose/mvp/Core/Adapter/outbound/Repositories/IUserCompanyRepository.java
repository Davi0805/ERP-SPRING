package com.gnose.mvp.Core.Adapter.outbound.Repositories;


import com.gnose.mvp.Core.Infrastructure.Entities.UserCompanyJpaEntity;

import java.util.List;

public interface IUserCompanyRepository {
    UserCompanyJpaEntity save(UserCompanyJpaEntity userCompany);
    UserCompanyJpaEntity findById(Long id);
    void delete(Long id);
    List<UserCompanyJpaEntity> findByUserId(Long userId);
    List<UserCompanyJpaEntity> findByCompanyId(Long companyId);
    // todo: maybe a count method in the future

    // why not? you never know when you need to find by userId and companyId etc
    UserCompanyJpaEntity findByUserIdAndCompanyId(Long userId, Long companyId);
    UserCompanyJpaEntity findByUserIdAndRoleId(Long userId, Long roleId);
    UserCompanyJpaEntity findByCompanyIdAndRoleId(Long companyId, Long roleId);
}
