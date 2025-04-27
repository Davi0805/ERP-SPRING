package com.gnose.mvp.Core.Adapter.inbound.Rest.General;

import com.gnose.mvp.Core.Adapter.inbound.DTO.General.CreateCompanyDTO;
import com.gnose.mvp.Core.Adapter.outbound.DTO.CompanyPermissionDTO;
import com.gnose.mvp.Core.Adapter.outbound.DTO.SessionRedisDTO;
import com.gnose.mvp.Core.Application.Impl.RedisServiceImpl;
import com.gnose.mvp.Core.Application.UseCases.ICompanyService;
import com.gnose.mvp.Core.Infrastructure.Entities.CompanyJpaEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final ICompanyService companyService;
    private final RedisServiceImpl redisServiceImpl;

    public CompanyController(ICompanyService companyService, RedisServiceImpl redisServiceImpl) {
        this.companyService = companyService;
        this.redisServiceImpl = redisServiceImpl;
    }

    @GetMapping
    public ResponseEntity<?> getAllCompanies(@RequestHeader("Authorization") String token) {
        try {
            SessionRedisDTO dto = redisServiceImpl.getSession(token.replace("Bearer ", ""));

            List<Long> companyIds = dto.getCompanyPermission().stream()
                    .map(CompanyPermissionDTO::getCompanyId)
                    .toList();

            return ResponseEntity.ok(companyService.getCompaniesByCompanyIds(companyIds));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
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