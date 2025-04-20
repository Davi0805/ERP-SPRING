package com.gnose.mvp.Containers_Module.Application.Impl;

import com.gnose.mvp.Containers_Module.Application.UseCases.IPortEventService;
import com.gnose.mvp.Ports_Ships_Module.Infrastructure.Adapters.Repositories.PortJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortEventServiceImpl implements IPortEventService {

    private final PortJpaRepository portJpaRepository;

    @Autowired
    public PortEventServiceImpl(PortJpaRepository portJpaRepository) {
        this.portJpaRepository = portJpaRepository;
    }

    @Override
    public boolean portIdExists(Long portId) {
        return portJpaRepository.existsById(portId);
    }

}
