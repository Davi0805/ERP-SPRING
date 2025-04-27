package com.gnose.mvp.Ports_Ships_Tests.Unit;

import com.gnose.mvp.Ports_Ships_Module.Application.Impl.ShipServiceImpl;
import com.gnose.mvp.Ports_Ships_Module.Infrastructure.Adapters.Repositories.ShipsJpaRepository;
import com.gnose.mvp.Ports_Ships_Module.Infrastructure.Entities.ShipsJpaEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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

    // Success ones

    @Test
    public void getShipByIdSuccess()
    {
        ShipsJpaEntity entity = new ShipsJpaEntity();
        entity.setName("VINGANCA DA RAINHA ANA");
        entity.setId(1L);
        entity.setCapacity(4555);
        entity.setImo("IMO234234");

        when(shipsJpaRepository.findById(1L)).thenReturn(Optional.of(entity));

        ShipsJpaEntity response = shipService.getShipById(1L);

        assertEquals(response, entity);
    }

    @Test
    public void getShipByImoNumberSuccess()
    {
        ShipsJpaEntity entity = new ShipsJpaEntity();
        entity.setName("VINGANCA DA RAINHA ANA");
        entity.setId(1L);
        entity.setCapacity(4555);
        entity.setImo("IMO234234");

        when(shipsJpaRepository.findByImo("IMO234234")).thenReturn(Optional.of(entity));

        ShipsJpaEntity result = shipService.getShipByImoNumber("IMO234234");

        assertEquals(result, entity);
    }

    @Test
    public void getAllSuccess()
    {
        ShipsJpaEntity entity = new ShipsJpaEntity();
        entity.setName("VINGANCA DA RAINHA ANA");
        entity.setId(1L);
        entity.setCapacity(4555);
        entity.setImo("IMO234234");

        ShipsJpaEntity entity2 = new ShipsJpaEntity();
        entity.setName("VINGANCA DA RAINHA ANA2");
        entity.setId(2L);
        entity.setCapacity(4555);
        entity.setImo("IMO234236");

        when(shipsJpaRepository.findAll()).thenReturn(List.of(entity, entity2));

        List<ShipsJpaEntity> result = shipService.getAllShips();

        assertEquals(result, List.of(entity, entity2));
    }

    @Test
    public void deleteShipSuccess()
    {
        ShipsJpaEntity entity = new ShipsJpaEntity();
        entity.setName("VINGANCA DA RAINHA ANA");
        entity.setId(1L);
        entity.setCapacity(4555);
        entity.setImo("IMO234234");

        when(shipsJpaRepository.existsById(1L)).thenReturn(true);

        shipService.deleteShip(1L);

        verify(shipsJpaRepository, times(1)).deleteById(1L);
    }

    @Test
    public void updateShipSuccess()
    {
        ShipsJpaEntity entity = new ShipsJpaEntity();
        entity.setName("VINGANCA DA RAINHA ANA");
        entity.setId(1L);
        entity.setCapacity(4555);
        entity.setImo("IMO234234");

        when(shipsJpaRepository.findById(1L)).thenReturn(Optional.of(entity));

        shipService.updateShip(1L, "BLABLA", "IMO1234", 46465);

        verify(shipsJpaRepository, times(1)).save(entity);
    }

    @Test
    public void createShipSuccess()
    {
        ShipsJpaEntity entity = new ShipsJpaEntity();
        entity.setName("VINGANCA DA RAINHA ANA");
        entity.setId(1L);
        entity.setCapacity(4555);
        entity.setImo("IMO234234");


        // need to change the interface and impl to receive a entity
    }
}
