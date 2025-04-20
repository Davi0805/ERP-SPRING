package com.gnose.mvp.Core.Infrastructure.Mapper;

import com.gnose.mvp.Core.Adapter.inbound.DTO.General.CreateUserDTO;
import com.gnose.mvp.Core.Infrastructure.Entities.UserJpaEntity;

import java.time.LocalDateTime;

public class UserMapper {
    static public UserJpaEntity CreateDtoToEntity(CreateUserDTO dto)
    {
        UserJpaEntity entity = new UserJpaEntity();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(entity.getCreatedAt());
        entity.setActive(true);
        return entity;
    }
}
