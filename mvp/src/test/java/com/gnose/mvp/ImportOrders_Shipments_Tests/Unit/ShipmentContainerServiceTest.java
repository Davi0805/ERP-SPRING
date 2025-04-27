package com.gnose.mvp.ImportOrders_Shipments_Tests.Unit;

import com.gnose.mvp.Import_Orders_Shipments_Module.Application.Impl.ShipmentContainerServiceImpl;
import com.gnose.mvp.Import_Orders_Shipments_Module.Application.Impl.ShipmentsServiceImpl;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Adapters.ShipmentContainersJpaRepository;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities.ShipmentContainersJpaEntity;
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
public class ShipmentContainerServiceTest {

    @Mock
    private ShipmentContainersJpaRepository jpaRepository;

    @InjectMocks
    private ShipmentContainerServiceImpl shipmentsService;

    //Error ones

    @Test
    public void getByIdShouldThrow() {
        assertThrows(RuntimeException.class, () -> shipmentsService.getById(1L));
    }

    @Test
    public void getByShipmentIdShouldThrow() {
        assertThrows(RuntimeException.class, () -> shipmentsService.getByShipmentId(1L));
    }

    @Test
    public void getByShipmentAndContainerIdShouldThrow() {
        assertThrows(RuntimeException.class, () -> shipmentsService.getByShipmentAndContainerId(1L, 1L));
    }

    @Test
    public void getByContainerIdShouldThrow() {
        assertThrows(RuntimeException.class, () -> shipmentsService.getByContainerId(1L));
    }

    @Test
    public void deleteShouldThrow()
    {
        assertThrows(RuntimeException.class, () -> shipmentsService.deleteById(1L));
    }

    // Success ones


    @Test
    void createSuccess() {
        ShipmentContainersJpaEntity entity = new ShipmentContainersJpaEntity();
        entity.setId(1L);
        entity.setShipmentId(1L);
        entity.setContainerId(1L);

        shipmentsService.save(entity);

        verify(jpaRepository, times(1)).save(entity);
    }

    @Test
    public void deleteSuccess() {
        when(jpaRepository.existsById(1L)).thenReturn(true);
        shipmentsService.deleteById(1L);
        verify(jpaRepository, times(1)).deleteById(1L);
    }

    @Test
    public void getById()
    {
        ShipmentContainersJpaEntity entity = new ShipmentContainersJpaEntity();
        entity.setId(1L);
        entity.setShipmentId(1L);
        entity.setContainerId(1L);

        when(jpaRepository.findById(1L)).thenReturn(Optional.of(entity));
        ShipmentContainersJpaEntity result = shipmentsService.getById(1L);
        assertEquals(result, entity);
    }

    @Test
    public void getByShipmentId()
    {
        ShipmentContainersJpaEntity entity = new ShipmentContainersJpaEntity();
        entity.setId(1L);
        entity.setShipmentId(1L);
        entity.setContainerId(1L);

        ShipmentContainersJpaEntity entity2 = new ShipmentContainersJpaEntity();
        entity.setId(2L);
        entity.setShipmentId(1L);
        entity.setContainerId(2L);

        when(jpaRepository.findByShipmentId(1L)).thenReturn(Optional.of(List.of(entity, entity2)));
        List<ShipmentContainersJpaEntity> result = shipmentsService.getByShipmentId(1L);
        assertEquals(result, List.of(entity, entity2));
    }

    @Test
    public void getByContainerId()
    {
        ShipmentContainersJpaEntity entity = new ShipmentContainersJpaEntity();
        entity.setId(1L);
        entity.setShipmentId(1L);
        entity.setContainerId(1L);

        ShipmentContainersJpaEntity entity2 = new ShipmentContainersJpaEntity();
        entity.setId(2L);
        entity.setShipmentId(1L);
        entity.setContainerId(2L);

        when(jpaRepository.findByContainerId(1L)).thenReturn(Optional.of(entity));
        ShipmentContainersJpaEntity result = shipmentsService.getByContainerId(1L);
        assertEquals(result, entity);
    }

    @Test
    public void getByShipmentIdAndContainerIdSuccess()
    {
        ShipmentContainersJpaEntity entity = new ShipmentContainersJpaEntity();
        entity.setId(1L);
        entity.setShipmentId(1L);
        entity.setContainerId(1L);


        when(jpaRepository.findByContainerIdAndShipmentId(1L, 1L)).thenReturn(Optional.of(entity));
        ShipmentContainersJpaEntity result = shipmentsService.getByShipmentAndContainerId(1L, 1L);
        assertEquals(result, entity);
    }
}
