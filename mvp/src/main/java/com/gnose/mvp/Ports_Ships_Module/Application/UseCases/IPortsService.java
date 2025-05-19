package com.gnose.mvp.Ports_Ships_Module.Application.UseCases;

import com.gnose.mvp.Ports_Ships_Module.Adapter.PortInputDTO;
import com.gnose.mvp.Ports_Ships_Module.Infrastructure.Entities.PortJpaEntity;

import java.util.List;

public interface IPortsService {
    PortJpaEntity createPort(PortInputDTO dto);

    void updatePort(PortInputDTO dto, Long id);

    void deletePort(Long id);

    PortJpaEntity getPortById(Long id);

    List<PortJpaEntity> getAllPorts();

    PortJpaEntity getPortByCountry(String country);

    PortJpaEntity getPortByCode(String code);
}
