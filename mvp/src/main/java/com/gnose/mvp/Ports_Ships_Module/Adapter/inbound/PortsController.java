package com.gnose.mvp.Ports_Ships_Module.Adapter.inbound;

import com.gnose.mvp.Authorization.CheckAccess;
import com.gnose.mvp.Ports_Ships_Module.Adapter.PortInputDTO;
import com.gnose.mvp.Ports_Ships_Module.Application.UseCases.IPortsService;
import com.gnose.mvp.Ports_Ships_Module.Infrastructure.Entities.PortJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ports")
public class PortsController {

    private final IPortsService portsService;

    @Autowired
    public PortsController(IPortsService portsService) {
        this.portsService = portsService;
    }

    @GetMapping
    @CheckAccess(permission = "VIEW_PORT", companyId = "*")
    public ResponseEntity<?> getAllPorts() {
        return ResponseEntity.ok(portsService.getAllPorts());
    }

    @GetMapping("/country/{country}")
    @CheckAccess(permission = "VIEW_PORT", companyId = "*")
    public ResponseEntity<?> getPortByCountry(@PathVariable("country") String country) {
        return ResponseEntity.ok(portsService.getPortByCountry(country));
    }

    @GetMapping("/code/{code}")
    @CheckAccess(permission = "VIEW_PORT", companyId = "*")
    public ResponseEntity<?> getPortByCode(@PathVariable("code") String code) {
        return ResponseEntity.ok(portsService.getPortByCode(code));
    }

    @GetMapping("/{id}")
    @CheckAccess(permission = "VIEW_PORT", companyId = "*")
    public ResponseEntity<?> getPortById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(portsService.getPortById(id));
    }

    @GetMapping("/delete/{id}")
    @CheckAccess(permission = "DELETE_PORT", companyId = "*")
    public ResponseEntity<?> deletePort(@PathVariable("id") Long id) {
        portsService.deletePort(id);
        return ResponseEntity.ok("Port deleted successfully");
    }

    @PostMapping
    @CheckAccess(permission = "CREATE_PORT", companyId = "*")
    public ResponseEntity<?> createPort(@RequestBody PortInputDTO port) {
        portsService.createPort(port);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @CheckAccess(permission = "UPDATE_PORT", companyId = "*")
    public ResponseEntity<?> updatePort(@PathVariable("id") Long id, @RequestBody PortInputDTO port) {
        portsService.updatePort(port, id);
        return ResponseEntity.ok().build();
    }
}
