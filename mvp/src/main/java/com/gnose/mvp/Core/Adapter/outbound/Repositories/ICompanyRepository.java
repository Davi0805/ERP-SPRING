package com.gnose.mvp.Core.Adapter.outbound.Repositories;

import com.gnose.mvp.Core.Infrastructure.Entities.CompanyJpaEntity;

import java.util.List;

public interface ICompanyRepository {
    CompanyJpaEntity createCompany(CompanyJpaEntity company);
    CompanyJpaEntity findById(Long id);
    CompanyJpaEntity findByCnpj(String cnpj);
    CompanyJpaEntity findByName(String name);
    List<CompanyJpaEntity> findAll();
    List<CompanyJpaEntity> getCompaniesByCompanyIds(List<Long> companyIds);
}
