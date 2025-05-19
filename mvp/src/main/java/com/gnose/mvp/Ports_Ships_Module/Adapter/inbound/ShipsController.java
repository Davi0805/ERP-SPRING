package com.gnose.mvp.Ports_Ships_Module.Adapter.inbound;

import com.gnose.mvp.Authorization.CheckAccess;
import com.gnose.mvp.Ports_Ships_Module.Adapter.ShipInDTO;
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
    @CheckAccess(permission = "CREATE_SHIPS", companyId = "*")
    public ResponseEntity<?> createShip(@RequestBody ShipInDTO ship) {
        shipService.createShip(ship);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @CheckAccess(permission = "UPDATE_SHIPS", companyId = "*")
    public ResponseEntity<?> updateShip(@PathVariable("id") Long id, @RequestBody ShipInDTO ship) {
        shipService.updateShip(ship, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @CheckAccess(permission = "DELETE_SHIPS", companyId = "*")
    public ResponseEntity<?> deleteShip(@PathVariable("id") Long id) {
        shipService.deleteShip(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @CheckAccess(permission = "VIEW_SHIPS", companyId = "*")
    public ResponseEntity<?> getShipById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(shipService.getShipById(id));
    }

    @GetMapping("/imo/{imoNumber}")
    @CheckAccess(permission = "VIEW_SHIPS", companyId = "*")
    public ResponseEntity<?> getShipByImoNumber(@PathVariable("imoNumber") String imoNumber) {
        return ResponseEntity.ok(shipService.getShipByImoNumber(imoNumber));
    }

    @GetMapping
    @CheckAccess(permission = "VIEW_SHIPS", companyId = "*")
    public ResponseEntity<?> getAllShips() {
        return ResponseEntity.ok(shipService.getAllShips());
    }
}
