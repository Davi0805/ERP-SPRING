package com.gnose.mvp.Containers_Module.Adapters.inbound;

import com.gnose.mvp.Containers_Module.Application.UseCases.IContainerMovementService;
import com.gnose.mvp.Containers_Module.Application.UseCases.IPortEventService;
import com.gnose.mvp.Containers_Module.Infrastructure.Entities.ContainerJpaEntity;
import com.gnose.mvp.Containers_Module.Infrastructure.Entities.ContainerMovementsJpaEntity;
import com.gnose.mvp.Ports_Ships_Module.Application.UseCases.IPortsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/container-movements")
public class ContainerMovementsController {

    private final IContainerMovementService containerMovementService;
    private final IPortEventService portEventService;

    @Autowired
    public ContainerMovementsController(IContainerMovementService containerMovementService,
                                        IPortEventService portEventService) {
        this.containerMovementService = containerMovementService;
        this.portEventService = portEventService;
    }

    @PostMapping
    public ResponseEntity<?> registerContainerMovement(@RequestBody ContainerMovementsJpaEntity container) {
        try {
            if (!portEventService.portIdExists(container.getPortId()))
                throw new IllegalArgumentException("Port ID does not exist");
            containerMovementService.createContainerMovement(container);
            return ResponseEntity.ok("Container movement registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{containerId}")
    public ResponseEntity<?> getContainerMovementsByContainerId(@PathVariable Long containerId) {
        try {
            return ResponseEntity.ok(containerMovementService.getByContainerId(containerId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
