package com.gnose.mvp.ImportOrders_Shipments_Tests.Unit;

import com.gnose.mvp.Import_Orders_Shipments_Module.Application.Impl.ImportOrderServiceImpl;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Adapters.ImportOrderJpaRepository;
import com.gnose.mvp.Import_Orders_Shipments_Module.Infrastructure.Entities.ImportOrdersJpaEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.*;


@SpringBootTest
public class ImportOrdersServiceTest {

    @Mock
    ImportOrderJpaRepository jpaRepository;

    @InjectMocks
    ImportOrderServiceImpl importOrderService;

    // Error ones

    @Test
    public void getByIdShouldThrow()
    {
        assertThrows(RuntimeException.class, () -> importOrderService.getById(1L));
    }

    @Test
    public void getAllByCompanyIdShouldThrow()
    {
        assertThrows(RuntimeException.class, () -> importOrderService.getAllByCompanyId(1L));
    }

    @Test
    public void getAllCompanyIdAndStatusShouldThrow()
    {
        assertThrows(RuntimeException.class, () -> importOrderService
                .getAllByCompanyIdAndStatus(1L, "COMPLETED"));
    }

    @Test
    public void deleteImportOrderShouldThrow()
    {
        assertThrows(RuntimeException.class, () -> importOrderService.deleteImportOrder(1L));
    }

    @Test
    public void updateOrderShouldThrow()
    {
        ImportOrdersJpaEntity entity = new ImportOrdersJpaEntity();
        entity.setId(1L);
        entity.setOrderNumber(UUID.randomUUID());
        entity.setStatus("COMPLETED");
        entity.setCompanyId(2L);
        entity.setCreatedAt(LocalDateTime.now());

        assertThrows(RuntimeException.class, () -> importOrderService.update(entity));
    }

    // Success ones

    @Test
    public void getByIdSuccess()
    {
        ImportOrdersJpaEntity entity = new ImportOrdersJpaEntity();
        entity.setId(1L);
        entity.setOrderNumber(UUID.randomUUID());
        entity.setStatus("COMPLETED");
        entity.setCompanyId(2L);
        entity.setCreatedAt(LocalDateTime.now());

        when(jpaRepository.findById(1L)).thenReturn(Optional.of(entity));

        ImportOrdersJpaEntity result = importOrderService.getById(1L);

        assertEquals(result, entity);
    }

    @Test
    public void getAllByCompanyIdSuccess()
    {
        ImportOrdersJpaEntity entity = new ImportOrdersJpaEntity();
        entity.setId(1L);
        entity.setOrderNumber(UUID.randomUUID());
        entity.setStatus("COMPLETED");
        entity.setCompanyId(2L);
        entity.setCreatedAt(LocalDateTime.now());

        ImportOrdersJpaEntity entity2 = new ImportOrdersJpaEntity();
        entity.setId(2L);
        entity.setOrderNumber(UUID.randomUUID());
        entity.setStatus("COMPLETED");
        entity.setCompanyId(2L);
        entity.setCreatedAt(LocalDateTime.now());

        when(jpaRepository.findByCompanyId(1L)).thenReturn(Optional.of(List.of(entity, entity2)));

        List<ImportOrdersJpaEntity> result = importOrderService.getAllByCompanyId(1L);

        assertEquals(List.of(entity, entity2), result);
    }

    @Test
    public void getAllByCompanyIdAndStatusSuccess()
    {
        ImportOrdersJpaEntity entity = new ImportOrdersJpaEntity();
        entity.setId(1L);
        entity.setOrderNumber(UUID.randomUUID());
        entity.setStatus("COMPLETED");
        entity.setCompanyId(2L);
        entity.setCreatedAt(LocalDateTime.now());

        ImportOrdersJpaEntity entity2 = new ImportOrdersJpaEntity();
        entity.setId(2L);
        entity.setOrderNumber(UUID.randomUUID());
        entity.setStatus("COMPLETED");
        entity.setCompanyId(2L);
        entity.setCreatedAt(LocalDateTime.now());

        when(jpaRepository.findByCompanyIdAndStatus(2L, "COMPLETED"))
                .thenReturn(Optional.of(List.of(entity, entity2)));

        List<ImportOrdersJpaEntity> result = importOrderService
                .getAllByCompanyIdAndStatus(2L, "COMPLETED");

        assertEquals(List.of(entity, entity2), result);
    }

    @Test
    public void deleteImportOrderSuccess()
    {
        when(jpaRepository.existsById(1L)).thenReturn(true);
        importOrderService.deleteImportOrder(1L);
        verify(jpaRepository, times(1)).deleteById(1L);
    }

    @Test
    public void updateImportOrderSuccess()
    {
        ImportOrdersJpaEntity entity = new ImportOrdersJpaEntity();
        entity.setId(1L);
        entity.setOrderNumber(UUID.randomUUID());
        entity.setStatus("COMPLETED");
        entity.setCompanyId(2L);
        entity.setCreatedAt(LocalDateTime.now());

        when(jpaRepository.existsById(1L)).thenReturn(true);

        importOrderService.update(entity);

        verify(jpaRepository, times(1)).save(entity);
    }

    @Test
    public void createImportOrderSuccess()
    {
        ImportOrdersJpaEntity entity = new ImportOrdersJpaEntity();
        entity.setId(1L);
        entity.setOrderNumber(UUID.randomUUID());
        entity.setStatus("COMPLETED");
        entity.setCompanyId(2L);
        entity.setCreatedAt(LocalDateTime.now());

        importOrderService.create(entity);

        verify(jpaRepository, times(1)).save(entity);
    }

}
