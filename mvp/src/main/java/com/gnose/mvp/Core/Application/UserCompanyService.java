package com.gnose.mvp.Core.Application;

import com.gnose.mvp.Core.Models.CompanyJpaEntity;
import com.gnose.mvp.Core.Models.UserCompanyJpaEntity;
import com.gnose.mvp.Core.Models.UserJpaEntity;
import com.gnose.mvp.Core.Adapter.outbound.Repositories.UserCompanyJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCompanyService {

    private final UserCompanyJpaRepository jpaRepository;

    @Autowired
    public UserCompanyService(UserCompanyJpaRepository jpaRepository)
    {
        this.jpaRepository = jpaRepository;
    }

    public void assignNewAdminToCompany(CompanyJpaEntity company, UserJpaEntity user)
    {
        UserCompanyJpaEntity entity = new UserCompanyJpaEntity();
        entity.setCompanyId(company.getId());
        entity.setUserId(user.getId());
        entity.setRoleId(1L); //admin role id
        jpaRepository.save(entity);
    }
}
