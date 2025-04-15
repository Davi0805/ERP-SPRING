package com.gnose.mvp.Core.Repositories;

import com.gnose.mvp.Core.Models.CompanyJpaEntity;
import com.gnose.mvp.Core.Models.UserCompanyJpaEntity;
import com.gnose.mvp.Core.Models.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCompanyJpaRepository extends JpaRepository<UserCompanyJpaEntity, Long> {
    List<UserCompanyJpaEntity> findByUserId(Long userId);
    List<UserCompanyJpaEntity> findByCompanyId(Long companyId);
}

