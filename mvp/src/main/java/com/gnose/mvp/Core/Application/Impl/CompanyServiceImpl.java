package com.gnose.mvp.Core.Application.Impl;

import com.gnose.mvp.Core.Adapter.inbound.DTO.General.CreateCompanyDTO;
import com.gnose.mvp.Core.Adapter.outbound.Repositories.ICompanyRepository;
import com.gnose.mvp.Core.Application.UseCases.ICompanyService;
import com.gnose.mvp.Core.Infrastructure.Mapper.CompanyMapper;
import com.gnose.mvp.Core.Infrastructure.Entities.CompanyJpaEntity;
import com.gnose.mvp.Core.Infrastructure.Adapter.Outbound.JpaRepositories.CompanyJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements ICompanyService {

    private final ICompanyRepository companyRepository;

    public CompanyServiceImpl(ICompanyRepository repository)
    {
        this.companyRepository = repository;
    }

    @Override
    public List<CompanyJpaEntity> findAll()
    {
        return companyRepository.findAll();
    }

    @Override
    public CompanyJpaEntity findById(Long id) {
        return null;
    }

    @Override
    public CompanyJpaEntity createCompany(CreateCompanyDTO company) {
        CompanyJpaEntity entity = CompanyMapper.createDtoToEntity(company);
        return companyRepository.createCompany(entity);
    }

    @Override
    public CompanyJpaEntity updateCompany(CompanyJpaEntity company) {
        if (!companyRepository.existsById(company.getId()))
            throw new RuntimeException("Company not found!");
        companyRepository.updateCompany(company);
        return company;
    }

    @Override
    public List<CompanyJpaEntity> getCompaniesByCompanyIds(List<Long> companyIds) {
        return companyRepository.getCompaniesByCompanyIds(companyIds);
    }

    @Override
    public void deleteCompany(Long companyId) {
        companyRepository.deleteById(companyId);
    }

}
