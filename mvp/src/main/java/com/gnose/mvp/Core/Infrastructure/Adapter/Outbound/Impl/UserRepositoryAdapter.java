package com.gnose.mvp.Core.Infrastructure.Adapter.Outbound.Impl;

import com.gnose.mvp.Core.Adapter.outbound.Repositories.IUserRepository;
import com.gnose.mvp.Core.Infrastructure.Adapter.Outbound.JpaRepositories.UserJpaRepository;
import com.gnose.mvp.Core.Infrastructure.Entities.UserJpaEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepositoryAdapter implements IUserRepository {

    private final UserJpaRepository jpaRepository;

    @Autowired
    public UserRepositoryAdapter(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    @Transactional
    public UserJpaEntity createUser(UserJpaEntity user) {
        return jpaRepository.save(user);
    }

    @Override
    public UserJpaEntity findById(Long id) {
        return jpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }

    @Override
    public UserJpaEntity findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }

    @Override
    public List<UserJpaEntity> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void delete(Long user_id) {
        if (jpaRepository.existsById(user_id)) {
            jpaRepository.deleteById(user_id);
            return;
        }
        throw new RuntimeException("User not found!");
    }
}
