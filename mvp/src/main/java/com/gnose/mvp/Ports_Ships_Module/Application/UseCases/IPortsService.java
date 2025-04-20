package com.gnose.mvp.Ports_Ships_Module.Application.UseCases;

import com.gnose.mvp.Ports_Ships_Module.Infrastructure.Entities.PortJpaEntity;

import java.util.List;

public interface IPortsService {
    PortJpaEntity createPort(String name, String country, String code);

    void updatePort(Long id, String name, String country, String code);

    void deletePort(Long id);

    PortJpaEntity getPortById(Long id);

    List<PortJpaEntity> getAllPorts();

    PortJpaEntity getPortByCountry(String country);

    PortJpaEntity getPortByCode(String code);
}
