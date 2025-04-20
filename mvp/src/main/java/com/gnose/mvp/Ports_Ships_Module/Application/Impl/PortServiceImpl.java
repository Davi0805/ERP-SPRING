package com.gnose.mvp.Ports_Ships_Module.Application.Impl;

import com.gnose.mvp.Ports_Ships_Module.Application.UseCases.IPortsService;
import com.gnose.mvp.Ports_Ships_Module.Infrastructure.Adapters.Repositories.PortJpaRepository;
import com.gnose.mvp.Ports_Ships_Module.Infrastructure.Entities.PortJpaEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortServiceImpl implements IPortsService {
    private final PortJpaRepository jpaRepository;

    public PortServiceImpl(PortJpaRepository jpaRepository)
    {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public PortJpaEntity createPort(String name, String country, String code) {
        PortJpaEntity port = new PortJpaEntity();
        port.setName(name);
        port.setCountry(country);
        port.setCode(code);
        return jpaRepository.save(port);
    }

    @Override
    public void updatePort(Long id, String name, String country, String code) {
        PortJpaEntity port = jpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Port not found!"));
        port.setName(name);
        port.setCountry(country);
        port.setCode(code);
        jpaRepository.save(port);
    }

    @Override
    public void deletePort(Long id) {
        if (!jpaRepository.existsById(id))
            throw new RuntimeException("Port not found!");
        jpaRepository.deleteById(id);
    }

    @Override
    public PortJpaEntity getPortById(Long id) {
        return jpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Port not found!"));
    }

    @Override
    public List<PortJpaEntity> getAllPorts()
    {
        return jpaRepository.findAll();
    }

    @Override
    public PortJpaEntity getPortByCountry(String country) {
        return jpaRepository.findByCountry(country).orElseThrow(() -> new RuntimeException("Port not found!"));
    }

    @Override
    public PortJpaEntity getPortByCode(String code) {
        return jpaRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Port not found!"));
    }
}
