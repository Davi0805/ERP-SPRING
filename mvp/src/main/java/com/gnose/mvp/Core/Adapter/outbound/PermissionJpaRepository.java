package com.gnose.mvp.Core.Adapter.outbound;

import com.gnose.mvp.Core.Infrastructure.Entities.PermissionsJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionJpaRepository extends JpaRepository<PermissionsJpaEntity, Long> {
    Optional<PermissionsJpaEntity> findByCode(String code);
}
