package com.gnose.mvp.Import_Orders_Shipments_Module.Application.Impl;

import com.gnose.mvp.Import_Orders_Shipments_Module.Application.UseCases.IShipEventService;
import com.gnose.mvp.Ports_Ships_Module.Infrastructure.Adapters.Repositories.ShipsJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipEventServiceImpl implements IShipEventService {

    private final ShipsJpaRepository shipsJpaRepository;

    @Autowired
    public ShipEventServiceImpl(ShipsJpaRepository shipsJpaRepository)
    {
        this.shipsJpaRepository = shipsJpaRepository;
    }

    @Override
    public boolean isShipValid(Long shipId) {
        return shipsJpaRepository.existsById(shipId);
    }
}
