package com.gnose.mvp.Core.Adapter.inbound.Rest.Specific;

import com.gnose.mvp.Core.Adapter.inbound.DTO.Specific.RegisterDTO;
import com.gnose.mvp.Core.Application.Impl.CompanyServiceImpl;
import com.gnose.mvp.Core.Application.UseCases.ICompanyService;
import com.gnose.mvp.Core.Application.UseCases.IUserCompanyService;
import com.gnose.mvp.Core.Application.UseCases.IUserService;
import com.gnose.mvp.Core.Application.Impl.UserServiceImpl;
import com.gnose.mvp.Core.Infrastructure.Entities.CompanyJpaEntity;
import com.gnose.mvp.Core.Infrastructure.Entities.UserJpaEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    private final ICompanyService companyService;
    private final IUserService userService;
    private final IUserCompanyService userCompanyService;

    public RegisterController(CompanyServiceImpl companyService,
                              UserServiceImpl userService,
                              IUserCompanyService userCompanyService)
    {
        this.companyService = companyService;
        this.userService = userService;
        this.userCompanyService = userCompanyService;
    }

    @PostMapping
    public ResponseEntity<?> Register(@RequestBody RegisterDTO dto)
    {
        try {
            UserJpaEntity user = userService.createUser(dto.getUser());
            CompanyJpaEntity company =companyService.createCompany(dto.getCompany());
            userCompanyService.assignNewAdminToCompany(company, user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }
}
