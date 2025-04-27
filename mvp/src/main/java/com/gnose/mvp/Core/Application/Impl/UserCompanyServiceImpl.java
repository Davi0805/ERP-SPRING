package com.gnose.mvp.Core.Application.Impl;

import com.gnose.mvp.Core.Adapter.outbound.Repositories.IUserCompanyRepository;
import com.gnose.mvp.Core.Application.UseCases.IUserCompanyService;
import com.gnose.mvp.Core.Infrastructure.Entities.CompanyJpaEntity;
import com.gnose.mvp.Core.Infrastructure.Entities.UserCompanyJpaEntity;
import com.gnose.mvp.Core.Infrastructure.Entities.UserJpaEntity;
import com.gnose.mvp.Core.Infrastructure.Adapter.Outbound.JpaRepositories.UserCompanyJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCompanyServiceImpl implements IUserCompanyService {

    private final IUserCompanyRepository jpaRepository;

    @Autowired
    public UserCompanyServiceImpl(IUserCompanyRepository jpaRepository)
    {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void assignNewAdminToCompany(CompanyJpaEntity company, UserJpaEntity user)
    {
        UserCompanyJpaEntity entity = new UserCompanyJpaEntity();
        entity.setCompanyId(company.getId());
        entity.setUserId(user.getId());
        entity.setRoleId(1L); //admin role id
        jpaRepository.save(entity);
    }

    @Override
    public void assignNewAdminToCompany(Long companyId, Long userId, Long roleId) {

    }

    @Override
    public void assignNewUserToCompany(Long companyId, Long userId, Long roleId) {
        UserCompanyJpaEntity entity = new UserCompanyJpaEntity();
        entity.setUserId(userId);
        entity.setRoleId(roleId);
        entity.setCompanyId(companyId);

        jpaRepository.save(entity);
    }

    // todo: implement methods

    @Override
    public void removeUserFromCompany(Long companyId, Long userId) {

    }

    @Override
    public void removeAdminFromCompany(Long companyId, Long userId) {

    }
}
