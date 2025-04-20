package com.gnose.mvp.Ports_Ships_Module.Application.UseCases;

import com.gnose.mvp.Ports_Ships_Module.Infrastructure.Entities.ShipsJpaEntity;

import java.util.List;

public interface IShipService {
    ShipsJpaEntity createShip(String name, String imoNumber, Integer capacity);
    void updateShip(Long id, String name, String imoNumber, Integer capacity);
    void deleteShip(Long id);
    ShipsJpaEntity getShipById(Long id);
    ShipsJpaEntity getShipByImoNumber(String imoNumber);
    List<ShipsJpaEntity> getAllShips();
}
