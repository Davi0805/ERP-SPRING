package com.gnose.mvp.Core.Adapter.inbound.DTO.General;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@NoArgsConstructor
@Getter
public class CreateUserDTO {
    private String name;
    private String email;
    private String password;
}
