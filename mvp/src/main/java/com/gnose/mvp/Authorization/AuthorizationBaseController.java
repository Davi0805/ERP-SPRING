package com.gnose.mvp.Authorization;

import com.gnose.mvp.Core.Adapter.outbound.DTO.CompanyPermissionDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AuthorizationBaseController {
    protected HttpServletRequest request;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @SuppressWarnings("unchecked")
    protected List<CompanyPermissionDTO> getAuthorizedCompanies() {
        return (List<CompanyPermissionDTO>) request.getAttribute("authorizedCompanies");
    }

    protected List<Long> getAuthorizedCompanyIds() {
        return getAuthorizedCompanies().stream()
                .map(CompanyPermissionDTO::getCompanyId)
                .toList();
    }

    protected Long getCompanyId() {
        return (Long) request.getAttribute("companyId");
    }
}
