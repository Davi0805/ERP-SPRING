package com.gnose.mvp.Core.Infrastructure.Adapter.Outbound.JpaRepositories;

import com.gnose.mvp.Core.Infrastructure.Entities.RolesJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleJpaRepository extends JpaRepository<RolesJpaEntity, Long> {
    List<RolesJpaEntity> findByCompanyId(Long companyId);
}
