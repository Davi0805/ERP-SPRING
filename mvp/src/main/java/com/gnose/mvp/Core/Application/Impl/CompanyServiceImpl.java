package com.gnose.mvp.Core.Application.Impl;

import com.gnose.mvp.Core.Adapter.inbound.DTO.General.CreateCompanyDTO;
import com.gnose.mvp.Core.Application.UseCases.ICompanyService;
import com.gnose.mvp.Core.Infrastructure.Mapper.CompanyMapper;
import com.gnose.mvp.Core.Infrastructure.Entities.CompanyJpaEntity;
import com.gnose.mvp.Core.Adapter.outbound.CompanyJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements ICompanyService {

    private final CompanyJpaRepository jpaRepository;

    public CompanyServiceImpl(CompanyJpaRepository repository)
    {
        this.jpaRepository = repository;
    }

    @Override
    public List<CompanyJpaEntity> findAll()
    {
        return jpaRepository.findAll();
    }

    @Override
    public CompanyJpaEntity findById(Long id) {
        return null;
    }

    @Override
    public CompanyJpaEntity createCompany(CreateCompanyDTO company) {
        CompanyJpaEntity entity = CompanyMapper.createDtoToEntity(company);
        return jpaRepository.save(entity);
    }

    @Override
    public CompanyJpaEntity updateCompany(CompanyJpaEntity company) {
        return null;
    }

    @Override
    public void deleteCompany(Long companyId) {

    }

}
