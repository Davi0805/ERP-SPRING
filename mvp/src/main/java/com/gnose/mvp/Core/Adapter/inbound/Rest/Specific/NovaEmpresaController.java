package com.gnose.mvp.Core.Adapter.inbound.Rest.Specific;

import com.gnose.mvp.Core.Adapter.inbound.DTO.General.CreateCompanyDTO;
import com.gnose.mvp.Core.Adapter.outbound.DTO.SessionRedisDTO;
import com.gnose.mvp.Core.Application.Impl.JwtServiceImpl;
import com.gnose.mvp.Core.Application.Impl.RedisServiceImpl;
import com.gnose.mvp.Core.Application.UseCases.ICompanyService;
import com.gnose.mvp.Core.Application.UseCases.IUserCompanyService;
import com.gnose.mvp.Core.Infrastructure.Entities.CompanyJpaEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user/new-company")
public class NovaEmpresaController {

    private final ICompanyService companyService;
    private final IUserCompanyService userCompanyService;
    private final RedisServiceImpl redisServiceImpl;
    private final JwtServiceImpl jwtServiceImpl;

    public NovaEmpresaController(ICompanyService companyService, IUserCompanyService userCompanyService,
                                 RedisServiceImpl redisServiceImpl, JwtServiceImpl jwtServiceImpl) {
        this.companyService = companyService;
        this.userCompanyService = userCompanyService;
        this.redisServiceImpl = redisServiceImpl;
        this.jwtServiceImpl = jwtServiceImpl;
    }

    @PostMapping
    public ResponseEntity<?> createAndAttribute(@RequestHeader("Authorization") String token,
                                                @RequestBody Map<String, Object> companyData) {
        try {
            SessionRedisDTO dto = redisServiceImpl.getSession(token.replace("Bearer ", ""));
            Long userId = jwtServiceImpl.getUserIdFromToken(token.replace("Bearer ", ""));

            CompanyJpaEntity company = companyService.createCompany(new CreateCompanyDTO(companyData.get("name").toString(),
                    companyData.get("cnpj").toString()));

            userCompanyService.assignNewAdminToCompany(company.getId(), userId, 1L);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
