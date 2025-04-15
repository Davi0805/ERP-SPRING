package com.gnose.mvp.Core.Adapter.inbound.DTO.Specific;

import com.gnose.mvp.Core.Adapter.inbound.DTO.General.CreateCompanyDTO;
import com.gnose.mvp.Core.Adapter.inbound.DTO.General.CreateUserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegisterDTO {

    private CreateUserDTO user;
    private CreateCompanyDTO company;
}
