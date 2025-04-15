package com.gnose.mvp.Core.Adapter.outbound.Repositories.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyPermissionDTO {

    private Long companyId;
    private List<String> permissions;
}
