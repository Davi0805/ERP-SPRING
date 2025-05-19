package com.gnose.mvp.Import_Orders_Shipments_Module.Adapters.inbound;

import com.gnose.mvp.Authorization.AuthorizationBaseController;
import com.gnose.mvp.Authorization.CheckAccess;
import com.gnose.mvp.Import_Orders_Shipments_Module.Application.UseCases.IImportOrderService;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities.ImportOrdersJpaEntity;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/import_order")
public class ImportOrderController extends AuthorizationBaseController {

    private final IImportOrderService importOrderService;

    @Autowired
    public ImportOrderController(IImportOrderService iImportOrderService)
    {
        this.importOrderService = iImportOrderService;
    }

    @PostMapping
    @CheckAccess(permission = "CREATE_IMPORT_ORDER", companyId = "#companyId")
    public ResponseEntity<?> create(@RequestBody ImportOrderDTO importOrders)
    {
        importOrderService.create(importOrders);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}") // todo: evaluate safety
    @CheckAccess(permission = "UPDATE_IMPORT_ORDER", companyId = "#companyId")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ImportOrdersJpaEntity importOrders)
    {
        importOrderService.update(importOrders);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @CheckAccess(permission = "VIEW_IMPORT_ORDER", companyId = "*")
    public ResponseEntity<?> getById(@PathVariable Long id)
    {
        return ResponseEntity.ok(importOrderService.getById(id, getAuthorizedCompanyIds()));
    }

    @DeleteMapping("/{id}")
    @CheckAccess(permission = "DELETE_IMPORT_ORDER", companyId = "*")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        importOrderService.deleteImportOrder(id, getAuthorizedCompanyIds());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/company/{id}")
    @CheckAccess(permission = "VIEW_IMPORT_ORDER", companyId = "id")
    public ResponseEntity<?> getAllByCompanyId(@PathVariable Long id)
    {
        return ResponseEntity.ok(importOrderService.getAllByCompanyId(id));
    }

    @GetMapping("/company/{companyId}/status/{status}")
    @CheckAccess(permission = "VIEW_IMPORT_ORDER", companyId = "companyId")
    public ResponseEntity<?> getAllByCompanyIdAndStatus(@PathVariable Long companyId, @PathVariable String status)
    {
            return ResponseEntity.ok(importOrderService.getAllByCompanyIdAndStatus(companyId, status));
    }

}
