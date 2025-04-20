package com.gnose.mvp.Core.Infrastructure.Adapter.Outbound.JpaRepositories;

import com.gnose.mvp.Core.Infrastructure.Entities.UserCompanyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCompanyJpaRepository extends JpaRepository<UserCompanyJpaEntity, Long> {
    List<UserCompanyJpaEntity> findByUserId(Long userId);
    List<UserCompanyJpaEntity> findByCompanyId(Long companyId);
}

