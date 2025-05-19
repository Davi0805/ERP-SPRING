package com.gnose.mvp.Import_Orders_Shipments_Module.Adapters.inbound;

import com.gnose.mvp.Authorization.AuthorizationBaseController;
import com.gnose.mvp.Authorization.CheckAccess;
import com.gnose.mvp.Import_Orders_Shipments_Module.Application.UseCases.IShipmentsService;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities.ShipmentsJpaEntity;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentsController extends AuthorizationBaseController {

    private final IShipmentsService shipmentsService;

    @Autowired
    public ShipmentsController(IShipmentsService shipmentsService)
    {
        this.shipmentsService = shipmentsService;
    }

    @PostMapping // for some reason is not working and the logs say its on checkAccess
    @CheckAccess(permission = "CREATE_SHIPMENT", companyId = "*")
    public ResponseEntity<?> create(ShipmentsDTO shipment)
    {
        shipmentsService.create(shipment, getAuthorizedCompanyIds());
        return ResponseEntity.ok().build();
    }

    //! debug - comment or remove befor prod
    @GetMapping
    @CheckAccess(permission = "VIEW_SHIPMENT", companyId = "*")
    public ResponseEntity<?> getAll()
    {
        return ResponseEntity.ok(shipmentsService.getAll(getAuthorizedCompanyIds()));
    }

    @GetMapping("/{id}")
    @CheckAccess(permission = "VIEW_SHIPMENT", companyId = "*")
    public ResponseEntity<?> getById(@PathVariable Long id)
    {
        return ResponseEntity.ok(shipmentsService.getById(id, getAuthorizedCompanyIds()));
    }

    @PutMapping("/{id}")
    @CheckAccess(permission = "UPDATE_SHIPMENT", companyId = "*")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ShipmentsDTO shipments)
    {
        shipmentsService.update(id, shipments, getAuthorizedCompanyIds());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @CheckAccess(permission = "DELETE_SHIPMENT", companyId = "*")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        shipmentsService.deleteShipment(id, getAuthorizedCompanyIds());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ship/{id}")
    @CheckAccess(permission = "VIEW_SHIPMENT", companyId = "*")
    public ResponseEntity<?> getByShipId(@PathVariable Long id)
    {
        return ResponseEntity.ok(shipmentsService.getByShipId(id, getAuthorizedCompanyIds()));
    }

    @GetMapping("/importOrder/{id}")
    @CheckAccess(permission = "VIEW_SHIPMENT", companyId = "*")
    public ResponseEntity<?> getByImportOrderId(@PathVariable Long id)
    {
        return ResponseEntity.ok(shipmentsService.getByImportOrderId(id, getAuthorizedCompanyIds()));
    }

}
