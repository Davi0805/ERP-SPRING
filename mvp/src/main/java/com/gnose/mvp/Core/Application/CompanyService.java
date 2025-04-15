package com.gnose.mvp.Core.Application;

import com.gnose.mvp.Core.Adapter.inbound.DTO.General.CreateCompanyDTO;
import com.gnose.mvp.Core.Infrastructure.Mapper.CompanyMapper;
import com.gnose.mvp.Core.Models.CompanyJpaEntity;
import com.gnose.mvp.Core.Adapter.outbound.Repositories.CompanyJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyJpaRepository jpaRepository;

    public CompanyService(CompanyJpaRepository repository)
    {
        this.jpaRepository = repository;
    }

    public CompanyJpaEntity createCompany(CreateCompanyDTO dto)
    {
        CompanyJpaEntity entity = CompanyMapper.createDtoToEntity(dto);
        return jpaRepository.save(entity);
    }

    public List<CompanyJpaEntity> findAll()
    {
        return jpaRepository.findAll();
    }

}
