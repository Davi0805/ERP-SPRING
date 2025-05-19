package com.gnose.mvp.Ports_Ships_Module.Adapter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipInDTO {
    private String name;
    private String imo; // international ship unique identifier
    private Integer capacity; // in TEU (Twenty-foot Equivalent Unit)
}
