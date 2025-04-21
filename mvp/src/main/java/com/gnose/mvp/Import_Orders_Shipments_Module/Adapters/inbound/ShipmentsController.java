package com.gnose.mvp.Import_Orders_Shipments_Module.Adapters.inbound;

import com.gnose.mvp.Import_Orders_Shipments_Module.Application.UseCases.IShipmentsService;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities.ShipmentsJpaEntity;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentsController {

    private final IShipmentsService shipmentsService;

    @Autowired
    public ShipmentsController(IShipmentsService shipmentsService)
    {
        this.shipmentsService = shipmentsService;
    }

    @PostMapping
    public ResponseEntity<?> create(ShipmentsJpaEntity shipment)
    {
        try {
            shipmentsService.create(shipment);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id)
    {
        try {
            return ResponseEntity.ok(shipmentsService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ShipmentsJpaEntity shipments)
    {
        try {
            shipmentsService.update(shipments);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        try {
            shipmentsService.deleteShipment(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/ship/{id}")
    public ResponseEntity<?> getByShipId(@PathVariable Long id)
    {
        try {
            return ResponseEntity.ok(shipmentsService.getByShipId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/importOrder/{id}")
    public ResponseEntity<?> getByImportOrderId(@PathVariable Long id)
    {
        try {
            return ResponseEntity.ok(shipmentsService.getByImportOrderId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
