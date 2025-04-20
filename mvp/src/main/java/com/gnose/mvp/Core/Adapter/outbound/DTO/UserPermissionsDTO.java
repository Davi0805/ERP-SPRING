package com.gnose.mvp.Core.Adapter.outbound.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserPermissionsDTO {

    private Long company_id;
    private String role_name;
    private String permission_code;
}
