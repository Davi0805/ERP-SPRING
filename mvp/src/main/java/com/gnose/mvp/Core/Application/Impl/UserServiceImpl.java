package com.gnose.mvp.Core.Application.Impl;

import com.gnose.mvp.Core.Adapter.inbound.DTO.General.CreateUserDTO;
import com.gnose.mvp.Core.Adapter.outbound.Repositories.IUserRepository;
import com.gnose.mvp.Core.Application.UseCases.IUserService;
import com.gnose.mvp.Core.Infrastructure.Mapper.UserMapper;
import com.gnose.mvp.Core.Infrastructure.Entities.UserJpaEntity;
import com.gnose.mvp.Core.Infrastructure.Adapter.Outbound.JpaRepositories.UserJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserServiceImpl(IUserRepository repository)
    {
        this.userRepository = repository;
    }

    @Override
    public UserJpaEntity createUser(CreateUserDTO req)
    {
        UserJpaEntity entity = UserMapper.CreateDtoToEntity(req);
        return userRepository.createUser(entity);
    }

    @Override
    public List<UserJpaEntity> findAll()
    {
        return userRepository.findAll();
    }

    @Override
    public UserJpaEntity findById(Long id)
    {
        return userRepository.findById(id);
    }

    @Override
    public UserJpaEntity findByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    @Override
    public void delete(Long user_id)
    {
        userRepository.delete(user_id);
    }
}
