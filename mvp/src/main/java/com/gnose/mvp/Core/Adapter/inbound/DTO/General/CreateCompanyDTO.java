package com.gnose.mvp.Core.Adapter.inbound.DTO.General;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCompanyDTO {

    private String name;
    private String cnpj;
}
