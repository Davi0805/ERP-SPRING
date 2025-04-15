package com.gnose.mvp.Core.Application;

import com.gnose.mvp.Core.Adapter.inbound.DTO.General.CreateUserDTO;
import com.gnose.mvp.Core.Infrastructure.Mapper.UserMapper;
import com.gnose.mvp.Core.Models.UserJpaEntity;
import com.gnose.mvp.Core.Adapter.outbound.Repositories.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserJpaRepository jpaRepository;

    @Autowired
    public UserService(UserJpaRepository repository)
    {
        this.jpaRepository = repository;
    }

    public UserJpaEntity createUser(CreateUserDTO req)
    {
        UserJpaEntity entity = UserMapper.CreateDtoToEntity(req);
        return jpaRepository.save(entity);
    }

    public List<UserJpaEntity> findAll()
    {
        return jpaRepository.findAll();
    }

    public UserJpaEntity findById(Long id)
    {
        return jpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found!"));
    }

    public UserJpaEntity findByEmail(String email)
    {
        return jpaRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Not found!"));
    }

    public void delete(Long user_id)
    {
        if (jpaRepository.existsById(user_id)) {
            jpaRepository.deleteById(user_id);
            return ;
        }
        throw new RuntimeException("User not found!");
    }
}
