package com.gnose.mvp.Import_Orders_Shipments_Module.Adapters.inbound;

import com.gnose.mvp.Import_Orders_Shipments_Module.Application.UseCases.IShipmentsContainerService;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities.ShipmentContainersJpaEntity;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipment-container")
public class ShipmentsContainerController {

    private final IShipmentsContainerService shipmentsContainerService;

    @Autowired
    public ShipmentsContainerController(IShipmentsContainerService shipmentsContainerService)
    {
        this.shipmentsContainerService = shipmentsContainerService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ShipmentContainersJpaEntity shipmentContainers)
    {
        try {
            shipmentsContainerService.save(shipmentContainers);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id)
    {
        try {
            return ResponseEntity.ok(shipmentsContainerService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        try {
            shipmentsContainerService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/shipment/{id}")
    public ResponseEntity<?> getByShipmentId(@PathVariable Long id)
    {
        try {
            return ResponseEntity.ok(shipmentsContainerService.getByShipmentId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/container/{id}")
    public ResponseEntity<?> getByContainerId(@PathVariable Long id)
    {
        try {
            return ResponseEntity.ok(shipmentsContainerService.getByContainerId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/shipment/{shipmentId}/container/{containerId}")
    public ResponseEntity<?> getByShipmentIdAndContainerId(@PathVariable Long shipmentId, @PathVariable Long containerId)
    {
        try {
            return ResponseEntity.ok(shipmentsContainerService.getByShipmentAndContainerId(shipmentId, containerId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
