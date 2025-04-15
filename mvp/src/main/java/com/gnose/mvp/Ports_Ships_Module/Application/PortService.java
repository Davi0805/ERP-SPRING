package com.gnose.mvp.Ports_Ships_Module.Application;

import com.gnose.mvp.Ports_Ships_Module.Adapter.Outbound.Repositories.PortJpaRepository;
import com.gnose.mvp.Ports_Ships_Module.Models.PortJpaEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortService {
    private final PortJpaRepository jpaRepository;

    public PortService(PortJpaRepository jpaRepository)
    {
        this.jpaRepository = jpaRepository;
    }

    public List<PortJpaEntity> findAll()
    {
        return jpaRepository.findAll();
    }

    public PortJpaEntity findById(Long id)
    {
        return jpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found!"));
    }

    public PortJpaEntity findByCountry(String country)
    {
        return jpaRepository.findByCountry(country).orElseThrow(() -> new RuntimeException("Not found!"));
    }

    public PortJpaEntity findByCode(String code)
    {
        return jpaRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Not found!"));
    }

    public PortJpaEntity create(PortJpaEntity entity)
    {
        return jpaRepository.save(entity);
    }

}
