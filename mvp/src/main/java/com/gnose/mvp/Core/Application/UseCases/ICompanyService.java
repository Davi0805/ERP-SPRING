package com.gnose.mvp.Core.Application.UseCases;

import com.gnose.mvp.Core.Adapter.inbound.DTO.General.CreateCompanyDTO;
import com.gnose.mvp.Core.Infrastructure.Entities.CompanyJpaEntity;

import java.util.List;

public interface ICompanyService {
    List<CompanyJpaEntity> findAll();
    CompanyJpaEntity findById(Long id);
    CompanyJpaEntity createCompany(CreateCompanyDTO company);
    CompanyJpaEntity updateCompany(CompanyJpaEntity company);
    List<CompanyJpaEntity> getCompaniesByCompanyIds(List<Long> companyIds);
    void deleteCompany(Long companyId);
}
