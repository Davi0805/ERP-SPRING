package com.gnose.mvp.Import_Orders_Shipments_Module.Adapters.inbound;

import com.gnose.mvp.Import_Orders_Shipments_Module.Application.UseCases.IImportOrderService;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities.ImportOrdersJpaEntity;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/import_order")
public class ImportOrderController {

    private final IImportOrderService importOrderService;

    @Autowired
    public ImportOrderController(IImportOrderService iImportOrderService)
    {
        this.importOrderService = iImportOrderService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ImportOrdersJpaEntity importOrders)
    {
        try {
            importOrderService.create(importOrders);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ImportOrdersJpaEntity importOrders)
    {
        try {
            importOrderService.update(importOrders);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id)
    {
        try {
            return ResponseEntity.ok(importOrderService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        try {
            importOrderService.deleteImportOrder(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<?> getAllByCompanyId(@PathVariable Long id)
    {
        try {
            return ResponseEntity.ok(importOrderService.getAllByCompanyId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/company/{company}/status/{status}")
    public ResponseEntity<?> getAllByCompanyIdAndStatus(@PathVariable Long companyId, @PathVariable String status)
    {
        try {
            return ResponseEntity.ok(importOrderService.getAllByCompanyIdAndStatus(companyId, status));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
