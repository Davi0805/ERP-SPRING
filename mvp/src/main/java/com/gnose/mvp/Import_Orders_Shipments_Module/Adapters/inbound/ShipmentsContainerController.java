package com.gnose.mvp.Import_Orders_Shipments_Module.Adapters.inbound;

import com.gnose.mvp.Authorization.AuthorizationBaseController;
import com.gnose.mvp.Authorization.CheckAccess;
import com.gnose.mvp.Import_Orders_Shipments_Module.Application.UseCases.IShipmentsContainerService;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities.ShipmentContainersJpaEntity;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// TODO: AUTHORIZATION TO ALL ENDPOINTS IS MISSING

@RestController
@RequestMapping("/api/shipment-container")
public class ShipmentsContainerController extends AuthorizationBaseController {

    private final IShipmentsContainerService shipmentsContainerService;

    @Autowired
    public ShipmentsContainerController(IShipmentsContainerService shipmentsContainerService)
    {
        this.shipmentsContainerService = shipmentsContainerService;
    }

    @PostMapping
    @CheckAccess(permission = "UPDATE_SHIPMENT", companyId = "*")
    public ResponseEntity<?> create(@RequestBody ShipmentContainerDTO shipmentContainers)
    {
            shipmentsContainerService.save(shipmentContainers);
            return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @CheckAccess(permission = "VIEW_SHIPMENT", companyId = "*")
    public ResponseEntity<?> getById(@PathVariable Long id)
    {
            return ResponseEntity.ok(shipmentsContainerService.getById(id));
    }

    @DeleteMapping("/{id}")
    @CheckAccess(permission = "DELETE_SHIPMENT", companyId = "*")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
            shipmentsContainerService.deleteById(id);
            return ResponseEntity.ok().build();
    }

    @GetMapping("/shipment/{id}")
    @CheckAccess(permission = "VIEW_SHIPMENT", companyId = "*")
    public ResponseEntity<?> getByShipmentId(@PathVariable Long id)
    {
            return ResponseEntity.ok(shipmentsContainerService.getByShipmentId(id));
    }

    @GetMapping("/container/{id}")
    @CheckAccess(permission = "VIEW_SHIPMENT", companyId = "*")
    public ResponseEntity<?> getByContainerId(@PathVariable Long id)
    {
            return ResponseEntity.ok(shipmentsContainerService.getByContainerId(id));
    }

    @GetMapping("/shipment/{shipmentId}/container/{containerId}")
    @CheckAccess(permission = "VIEW_SHIPMENT", companyId = "*")
    public ResponseEntity<?> getByShipmentIdAndContainerId(@PathVariable Long shipmentId, @PathVariable Long containerId)
    {
            return ResponseEntity.ok(shipmentsContainerService.getByShipmentAndContainerId(shipmentId, containerId));
    }

}
