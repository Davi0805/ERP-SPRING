package com.gnose.mvp.Ports_Ships_Module.Adapter.inbound;

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
    public ResponseEntity<?> getAllPorts() {
        try {
            return ResponseEntity.ok(portsService.getAllPorts());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<?> getPortByCountry(@PathVariable("country") String country) {
        try {
            return ResponseEntity.ok(portsService.getPortByCountry(country));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<?> getPortByCode(@PathVariable("code") String code) {
        try {
            return ResponseEntity.ok(portsService.getPortByCode(code));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPortById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(portsService.getPortById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deletePort(@PathVariable("id") Long id) {
        try {
            portsService.deletePort(id);
            return ResponseEntity.ok("Port deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createPort(@RequestBody PortJpaEntity port) {
        try {
            portsService.createPort(port.getName(), port.getCountry(), port.getCode());
            return ResponseEntity.ok("Port created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePort(@PathVariable("id") Long id, @RequestBody PortJpaEntity port) {
        try {
            portsService.updatePort(id, port.getName(), port.getCountry(), port.getCode());
            return ResponseEntity.ok("Port updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
