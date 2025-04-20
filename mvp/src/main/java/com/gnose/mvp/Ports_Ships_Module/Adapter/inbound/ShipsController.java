package com.gnose.mvp.Ports_Ships_Module.Adapter.inbound;

import com.gnose.mvp.Ports_Ships_Module.Application.UseCases.IShipService;
import com.gnose.mvp.Ports_Ships_Module.Infrastructure.Entities.ShipsJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ships")
public class ShipsController {

    private final IShipService shipService;

    @Autowired
    public ShipsController(IShipService shipService) {
        this.shipService = shipService;
    }

    @PostMapping
    public ResponseEntity<?> createShip(@RequestBody ShipsJpaEntity ship) {
        try {
            shipService.createShip(ship.getName(), ship.getImo(), ship.getCapacity());
            return ResponseEntity.ok("Ship created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateShip(@PathVariable("id") Long id, @RequestBody ShipsJpaEntity ship) {
        try {
            shipService.updateShip(id, ship.getName(), ship.getImo(), ship.getCapacity());
            return ResponseEntity.ok("Ship updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShip(@PathVariable("id") Long id) {
        try {
            shipService.deleteShip(id);
            return ResponseEntity.ok("Ship deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getShipById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(shipService.getShipById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/imo/{imoNumber}")
    public ResponseEntity<?> getShipByImoNumber(@PathVariable("imoNumber") String imoNumber) {
        try {
            return ResponseEntity.ok(shipService.getShipByImoNumber(imoNumber));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllShips() {
        try {
            return ResponseEntity.ok(shipService.getAllShips());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
