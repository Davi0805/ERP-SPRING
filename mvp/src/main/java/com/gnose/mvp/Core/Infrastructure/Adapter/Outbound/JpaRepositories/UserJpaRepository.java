package com.gnose.mvp.Core.Infrastructure.Adapter.Outbound.JpaRepositories;

import com.gnose.mvp.Core.Infrastructure.Entities.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
    Optional<UserJpaEntity> findByEmail(String email);
}

