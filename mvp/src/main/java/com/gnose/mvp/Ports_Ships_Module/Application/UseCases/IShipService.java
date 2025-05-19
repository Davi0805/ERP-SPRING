package com.gnose.mvp.Ports_Ships_Module.Application.UseCases;

import com.gnose.mvp.Ports_Ships_Module.Adapter.ShipInDTO;
import com.gnose.mvp.Ports_Ships_Module.Infrastructure.Entities.ShipsJpaEntity;

import java.util.List;

public interface IShipService {
    ShipsJpaEntity createShip(ShipInDTO dto);
    void updateShip(ShipInDTO dto, Long id);
    void deleteShip(Long id);
    ShipsJpaEntity getShipById(Long id);
    ShipsJpaEntity getShipByImoNumber(String imoNumber);
    List<ShipsJpaEntity> getAllShips();
}
