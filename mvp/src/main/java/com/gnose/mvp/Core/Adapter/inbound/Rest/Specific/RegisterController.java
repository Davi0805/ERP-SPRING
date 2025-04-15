package com.gnose.mvp.Core.Adapter.inbound.Rest.Specific;

import com.gnose.mvp.Core.Adapter.inbound.DTO.Specific.RegisterDTO;
import com.gnose.mvp.Core.Application.CompanyService;
import com.gnose.mvp.Core.Application.UserCompanyService;
import com.gnose.mvp.Core.Application.UserService;
import com.gnose.mvp.Core.Models.CompanyJpaEntity;
import com.gnose.mvp.Core.Models.UserCompanyJpaEntity;
import com.gnose.mvp.Core.Models.UserJpaEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    private final CompanyService companyService;
    private final UserService userService;
    private final UserCompanyService userCompanyService;

    public RegisterController(CompanyService companyService,
                              UserService userService,
                              UserCompanyService userCompanyService)
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
