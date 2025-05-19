package com.gnose.mvp.Import_Orders_Shipments_Module.Adapters.inbound;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentsDTO {
    private Long shipId;
    private Long importOrderId;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
}
