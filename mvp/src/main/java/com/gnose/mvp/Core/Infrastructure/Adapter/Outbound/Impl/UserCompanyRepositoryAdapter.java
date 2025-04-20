package com.gnose.mvp.Core.Infrastructure.Adapter.Outbound.Impl;

import com.gnose.mvp.Core.Adapter.outbound.Repositories.IUserCompanyRepository;
import com.gnose.mvp.Core.Infrastructure.Adapter.Outbound.JpaRepositories.UserCompanyJpaRepository;
import com.gnose.mvp.Core.Infrastructure.Entities.UserCompanyJpaEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserCompanyRepositoryAdapter implements IUserCompanyRepository {

    private final UserCompanyJpaRepository userCompanyJpaRepository;

    public UserCompanyRepositoryAdapter(UserCompanyJpaRepository userCompanyJpaRepository) {
        this.userCompanyJpaRepository = userCompanyJpaRepository;
    }

    @Override
    public UserCompanyJpaEntity save(UserCompanyJpaEntity userCompany) {
        return userCompanyJpaRepository.save(userCompany);
    }

    @Override
    public UserCompanyJpaEntity findById(Long id) {
        return userCompanyJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserCompany not found!"));
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<UserCompanyJpaEntity> findByUserId(Long userId) {
        return userCompanyJpaRepository.findByUserId(userId);
    }

    @Override
    public List<UserCompanyJpaEntity> findByCompanyId(Long companyId) {
        return List.of();
    }

    @Override
    public UserCompanyJpaEntity findByUserIdAndCompanyId(Long userId, Long companyId) {
        return null;
    }

    @Override
    public UserCompanyJpaEntity findByUserIdAndRoleId(Long userId, Long roleId) {
        return null;
    }

    @Override
    public UserCompanyJpaEntity findByCompanyIdAndRoleId(Long companyId, Long roleId) {
        return null;
    }

}
