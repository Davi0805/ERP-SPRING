package com.gnose.mvp.Ports_Ships_Tests.Unit;

import com.gnose.mvp.Ports_Ships_Module.Application.Impl.ShipServiceImpl;
import com.gnose.mvp.Ports_Ships_Module.Infrastructure.Adapters.Repositories.ShipsJpaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ShipServiceTest {

    @Mock
    private ShipsJpaRepository shipsJpaRepository;

    @InjectMocks
    private ShipServiceImpl shipService;

    // Error ones

    @Test
    public void getShipByIdShouldThrow()
    {
        assertThrows(RuntimeException.class, () -> shipService.getShipById(1L));
    }

    @Test
    public void getShipByImoNumberShouldThrow()
    {
        assertThrows(RuntimeException.class, () -> shipService.getShipByImoNumber("blablabla"));
    }

    @Test
    public void deleteShipShouldThrow()
    {
        assertThrows(RuntimeException.class, () -> shipService.deleteShip(1L));
    }

    @Test
    public void updateShouldThrow()
    {
        assertThrows(RuntimeException.class, () -> shipService.updateShip(1L, "blabla",
                                                                            "blablabla", 456));
    }
}
