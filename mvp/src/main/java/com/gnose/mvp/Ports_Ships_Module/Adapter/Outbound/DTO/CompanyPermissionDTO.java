package com.gnose.mvp.Ports_Ships_Module.Adapter.Outbound.DTO;

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
