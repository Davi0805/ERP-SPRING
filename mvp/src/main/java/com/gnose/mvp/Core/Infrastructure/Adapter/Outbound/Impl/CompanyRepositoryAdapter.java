package com.gnose.mvp.Core.Infrastructure.Adapter.Outbound.Impl;

import com.gnose.mvp.Core.Adapter.outbound.Repositories.ICompanyRepository;
import com.gnose.mvp.Core.Infrastructure.Adapter.Outbound.JpaRepositories.CompanyJpaRepository;
import com.gnose.mvp.Core.Infrastructure.Entities.CompanyJpaEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyRepositoryAdapter implements ICompanyRepository {

    private final CompanyJpaRepository companyJpaRepository;

    @Autowired
    public CompanyRepositoryAdapter(CompanyJpaRepository companyJpaRepository) {
        this.companyJpaRepository = companyJpaRepository;
    }

    @Override
    @Transactional
    public CompanyJpaEntity createCompany(CompanyJpaEntity company) {
        return companyJpaRepository.save(company);
    }

    @Override
    public CompanyJpaEntity findById(Long id) {
        return companyJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found!"));
    }

    @Override
    public CompanyJpaEntity findByCnpj(String cnpj) {
        return companyJpaRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new RuntimeException("Company not found!"));
    }

    @Override
    public CompanyJpaEntity findByName(String name) {
        return companyJpaRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Company not found!"));
    }

    @Override
    public List<CompanyJpaEntity> findAll() {
        return companyJpaRepository.findAll();
    }

    @Override
    public List<CompanyJpaEntity> getCompaniesByCompanyIds(List<Long> companyIds) {
        return companyJpaRepository.findByIdIn(companyIds)
                .orElseThrow(() -> new RuntimeException("Companies not found!"));
    }
}
