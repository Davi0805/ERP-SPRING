package com.gnose.mvp.Core.Application.UseCases;

import com.gnose.mvp.Core.Infrastructure.Entities.CompanyJpaEntity;
import com.gnose.mvp.Core.Infrastructure.Entities.UserJpaEntity;

public interface IUserCompanyService {
    void assignNewAdminToCompany(Long companyId, Long userId, Long roleId);
    void assignNewAdminToCompany(CompanyJpaEntity company, UserJpaEntity user);
    void assignNewUserToCompany(Long companyId, Long userId, Long roleId);
    void removeUserFromCompany(Long companyId, Long userId);
    void removeAdminFromCompany(Long companyId, Long userId);
}
