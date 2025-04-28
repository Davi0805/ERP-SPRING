package com.gnose.mvp.Import_Orders_Shipments_Module.Adapters.inbound;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImportOrderDTO {

    private Long companyId;

    private String status;

}
