package com.gnose.mvp.Core.Adapter.inbound.Rest.General;

import com.gnose.mvp.Core.Adapter.inbound.DTO.General.CreateCompanyDTO;
import com.gnose.mvp.Core.Application.UseCases.ICompanyService;
import com.gnose.mvp.Core.Infrastructure.Entities.CompanyJpaEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final ICompanyService companyService;

    public CompanyController(ICompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyJpaEntity> getAllCompanies() {
        return companyService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createCompany(@RequestBody CreateCompanyDTO company) {
        try {
            companyService.createCompany(company);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}