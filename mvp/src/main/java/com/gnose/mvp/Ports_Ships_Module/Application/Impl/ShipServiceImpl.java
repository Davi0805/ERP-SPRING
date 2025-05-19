package com.gnose.mvp.Ports_Ships_Module.Application.Impl;

import com.gnose.mvp.Ports_Ships_Module.Adapter.ShipInDTO;
import com.gnose.mvp.Ports_Ships_Module.Application.UseCases.IShipService;
import com.gnose.mvp.Ports_Ships_Module.Infrastructure.Adapters.Repositories.ShipsJpaRepository;
import com.gnose.mvp.Ports_Ships_Module.Infrastructure.Entities.ShipsJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipServiceImpl implements IShipService {

    private final ShipsJpaRepository jpaRepository;

    @Autowired
    public ShipServiceImpl(ShipsJpaRepository jpaRepository)
    {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public ShipsJpaEntity createShip(ShipInDTO dto) {
        ShipsJpaEntity ship = new ShipsJpaEntity();
        ship.setName(dto.getName());
        ship.setImo(dto.getImo());
        ship.setCapacity(dto.getCapacity());

        return jpaRepository.save(ship);
    }

    @Override
    public void updateShip(ShipInDTO dto, Long id) {
        ShipsJpaEntity ship = jpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Ship not found!"));
        ship.setName(dto.getName());
        ship.setImo(dto.getImo());

        jpaRepository.save(ship);
    }

    @Override
    public void deleteShip(Long id) {
        if (!jpaRepository.existsById(id))
            throw new RuntimeException("Ship not found!");
        jpaRepository.deleteById(id);
    }

    @Override
    public ShipsJpaEntity getShipById(Long id) {
        return jpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Ship not found!"));
    }

    @Override
    public ShipsJpaEntity getShipByImoNumber(String imoNumber) {
        return jpaRepository.findByImo(imoNumber).orElseThrow(() -> new RuntimeException("Ship not found!"));
    }

    @Override
    public List<ShipsJpaEntity> getAllShips() {
        return jpaRepository.findAll();
    }
}
