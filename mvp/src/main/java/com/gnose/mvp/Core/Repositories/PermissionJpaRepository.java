package com.gnose.mvp.Core.Repositories;

import com.gnose.mvp.Core.Models.PermissionsJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionJpaRepository extends JpaRepository<PermissionsJpaEntity, Long> {
    Optional<PermissionsJpaEntity> findByCode(String code);
}
