package com.gnose.mvp.Core.Adapter;

import com.gnose.mvp.Core.Models.CompanyJpaEntity;
import com.gnose.mvp.Core.Repositories.CompanyJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyJpaRepository companyRepository;

    @GetMapping
    public List<CompanyJpaEntity> getAllCompanies() {
        return companyRepository.findAll();
    }

    @PostMapping
    public CompanyJpaEntity createCompany(@RequestBody CompanyJpaEntity company) {
        return companyRepository.save(company);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}