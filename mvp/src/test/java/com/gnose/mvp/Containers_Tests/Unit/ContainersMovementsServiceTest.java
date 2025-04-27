package com.gnose.mvp.Containers_Tests.Unit;

import com.gnose.mvp.Containers_Module.Application.Impl.ContainerMovementsServiceImpl;
import com.gnose.mvp.Containers_Module.Application.Impl.ContainerServiceImpl;
import com.gnose.mvp.Containers_Module.Infrastructure.Adapters.ContainerJpaRepository;
import com.gnose.mvp.Containers_Module.Infrastructure.Adapters.ContainerMovementsJpaRepository;
import com.gnose.mvp.Containers_Module.Infrastructure.Entities.ContainerMovementsJpaEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class ContainersMovementsServiceTest {

    @Mock
    private ContainerMovementsJpaRepository jpaRepository;

    @Mock
    private ContainerJpaRepository containerJpaRepository;

    @InjectMocks
    private ContainerMovementsServiceImpl containerMovementsService;

    // Success ones

    @Test
    void create() {
        ContainerMovementsJpaEntity entity = new ContainerMovementsJpaEntity();
        entity.setId(1L);
        entity.setContainerId(1L);
        entity.setMovement_date(LocalDateTime.now());
        entity.setStatus("PROCESSAMENTO");
        entity.setPortId(1L);

        when(containerJpaRepository.existsById(1L)).thenReturn(true);

        containerMovementsService.createContainerMovement(entity);
        verify(jpaRepository, times(1)).save(entity);
    }

    @Test
    void update() {
        ContainerMovementsJpaEntity entity = new ContainerMovementsJpaEntity();
        entity.setId(1L);
        entity.setContainerId(1L);
        entity.setMovement_date(LocalDateTime.now());
        entity.setStatus("PROCESSAMENTO");
        entity.setPortId(1L);

        when(jpaRepository.existsById(1L)).thenReturn(true);

        containerMovementsService.updateContainerMovement(entity);
        verify(jpaRepository, times(1)).save(entity);
    }

    @Test
    void delete() {
        ContainerMovementsJpaEntity entity = new ContainerMovementsJpaEntity();
        entity.setId(1L);
        entity.setContainerId(1L);
        entity.setMovement_date(LocalDateTime.now());
        entity.setStatus("PROCESSAMENTO");
        entity.setPortId(1L);

        when(jpaRepository.existsById(1L)).thenReturn(true);
        containerMovementsService.deleteContainerMovement(1L);
        verify(jpaRepository, times(1)).deleteById(1L);
    }

    @Test
    void getById() {
        ContainerMovementsJpaEntity entity = new ContainerMovementsJpaEntity();
        entity.setId(1L);
        entity.setContainerId(1L);
        entity.setMovement_date(LocalDateTime.now());
        entity.setStatus("PROCESSAMENTO");
        entity.setPortId(1L);

        when(jpaRepository.findById(1L)).thenReturn(Optional.of(entity));
        ContainerMovementsJpaEntity result = containerMovementsService.getById(1L);

        assertEquals(entity, result);
    }
}
