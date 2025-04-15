package com.gnose.mvp.Core.Infrastructure.Mapper;

import com.gnose.mvp.Core.Adapter.inbound.DTO.General.CreateCompanyDTO;
import com.gnose.mvp.Core.Models.CompanyJpaEntity;

import java.time.LocalDateTime;

public class CompanyMapper {
    static public CompanyJpaEntity createDtoToEntity(CreateCompanyDTO dto)
    {
        CompanyJpaEntity entity = new CompanyJpaEntity();
        entity.setCnpj(dto.getCnpj());
        entity.setName(dto.getName());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(entity.getCreatedAt());
        entity.setActive(true);

        return entity;
    }
}
