package com.gnose.mvp.ImportOrders_Shipments_Tests.Unit;

import com.gnose.mvp.Import_Orders_Shipments_Module.Application.Impl.ShipmentsServiceImpl;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Adapters.ShipmentJpaRepository;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities.ShipmentsJpaEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ShipmentsServiceTest {

    @Mock
    private ShipmentJpaRepository jpaRepository;

    @InjectMocks
    private ShipmentsServiceImpl shipmentsService;

    //Error ones

    @Test
    public void getByIdShouldThrow() {
        assertThrows(RuntimeException.class, () -> shipmentsService.getById(1L));
    }

    @Test
    public void getByImporterIdShouldThrow() {
        assertThrows(RuntimeException.class, () -> shipmentsService.getByImportOrderId(1L));
    }

    @Test
    public void getByShipId() {
        assertThrows(RuntimeException.class, () -> shipmentsService.getByShipId(1L));
    }

    @Test
    public void updateShipmentShouldThrow() {
        ShipmentsJpaEntity entity = new ShipmentsJpaEntity();
        assertThrows(RuntimeException.class, () -> shipmentsService.update(entity));
    }

    @Test
    public void deleteShipmentShouldThrow() {
        assertThrows(RuntimeException.class, () -> shipmentsService.deleteShipment(1L));
    }

    // Success ones

    @Test
    public void getByIdSuccess()
    {
        ShipmentsJpaEntity entity = new ShipmentsJpaEntity();
        entity.setId(1L);
        entity.setShipId(2L);
        entity.setImportOrderId(3L);
        entity.setArrivalDate(LocalDateTime.now());
        entity.setDepartureDate(LocalDateTime.now());

        when(jpaRepository.findById(1L)).thenReturn(Optional.of(entity));

        ShipmentsJpaEntity result = shipmentsService.getById(1L);
        assertEquals(entity, result);
    }

    @Test
    public void getByShipIdSuccess()
    {
        ShipmentsJpaEntity entity = new ShipmentsJpaEntity();
        entity.setId(1L);
        entity.setShipId(2L);
        entity.setImportOrderId(3L);
        entity.setArrivalDate(LocalDateTime.now());
        entity.setDepartureDate(LocalDateTime.now());

        ShipmentsJpaEntity entity2 = new ShipmentsJpaEntity();
        entity.setId(2L);
        entity.setShipId(2L);
        entity.setImportOrderId(4L);
        entity.setArrivalDate(LocalDateTime.now());
        entity.setDepartureDate(LocalDateTime.now());

        when(jpaRepository.findByShipId(2L)).thenReturn(Optional.of(List.of(entity, entity2)));

        List<ShipmentsJpaEntity> result = shipmentsService.getByShipId(2L);
        assertEquals(List.of(entity, entity2), result);
    }

    @Test
    public void getByImportOrderIdSuccess()
    {
        ShipmentsJpaEntity entity = new ShipmentsJpaEntity();
        entity.setId(1L);
        entity.setShipId(2L);
        entity.setImportOrderId(3L);
        entity.setArrivalDate(LocalDateTime.now());
        entity.setDepartureDate(LocalDateTime.now());

        ShipmentsJpaEntity entity2 = new ShipmentsJpaEntity();
        entity.setId(2L);
        entity.setShipId(2L);
        entity.setImportOrderId(3L);
        entity.setArrivalDate(LocalDateTime.now());
        entity.setDepartureDate(LocalDateTime.now());

        when(jpaRepository.findByImportOrderId(3L)).thenReturn(Optional.of(List.of(entity, entity2)));

        List<ShipmentsJpaEntity> result = shipmentsService.getByImportOrderId(3L);
        assertEquals(List.of(entity, entity2), result);
    }

    @Test
    public void createShipmentSuccess()
    {
        ShipmentsJpaEntity entity = new ShipmentsJpaEntity();
        entity.setId(1L);
        entity.setShipId(2L);
        entity.setImportOrderId(3L);
        entity.setArrivalDate(LocalDateTime.now());
        entity.setDepartureDate(LocalDateTime.now());

        shipmentsService.create(entity);

        verify(jpaRepository, times(1)).save(entity);
    }

    @Test
    public void updateShipmentSuccess()
    {
        ShipmentsJpaEntity entity = new ShipmentsJpaEntity();
        entity.setId(1L);
        entity.setShipId(2L);
        entity.setImportOrderId(3L);
        entity.setArrivalDate(LocalDateTime.now());
        entity.setDepartureDate(LocalDateTime.now());

        when(jpaRepository.existsById(1L)).thenReturn(true);

        shipmentsService.update(entity);

        verify(jpaRepository, times(1)).save(entity);
    }

    @Test
    public void deleteShipmentSuccess()
    {
        when(jpaRepository.existsById(1L)).thenReturn(true);

        shipmentsService.deleteShipment(1L);

        verify(jpaRepository, times(1)).deleteById(1L);
    }
}
