package com.gnose.mvp.Import_Orders_Shipments_Module.Adapters.inbound;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentContainerDTO {
    private Long shipmentId;
    private Long containerId;
}
