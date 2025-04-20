package com.gnose.mvp.Core.Application.Impl;

import com.gnose.mvp.Core.Adapter.inbound.DTO.General.CreateUserDTO;
import com.gnose.mvp.Core.Application.UseCases.IUserService;
import com.gnose.mvp.Core.Infrastructure.Mapper.UserMapper;
import com.gnose.mvp.Core.Infrastructure.Entities.UserJpaEntity;
import com.gnose.mvp.Core.Adapter.outbound.UserJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final UserJpaRepository jpaRepository;

    @Autowired
    public UserServiceImpl(UserJpaRepository repository)
    {
        this.jpaRepository = repository;
    }

    @Override
    @Transactional
    public UserJpaEntity createUser(CreateUserDTO req)
    {
        UserJpaEntity entity = UserMapper.CreateDtoToEntity(req);
        return jpaRepository.save(entity);
    }

    @Override
    public List<UserJpaEntity> findAll()
    {
        return jpaRepository.findAll();
    }

    @Override
    public UserJpaEntity findById(Long id)
    {
        return jpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found!"));
    }

    @Override
    public UserJpaEntity findByEmail(String email)
    {
        return jpaRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Not found!"));
    }

    @Override
    public void delete(Long user_id)
    {
        if (jpaRepository.existsById(user_id)) {
            jpaRepository.deleteById(user_id);
            return ;
        }
        throw new RuntimeException("User not found!");
    }
}
