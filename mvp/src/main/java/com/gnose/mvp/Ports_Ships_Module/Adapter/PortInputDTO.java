package com.gnose.mvp.Ports_Ships_Module.Adapter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PortInputDTO {
    private String name;
    private String code;
    private String country;
}
