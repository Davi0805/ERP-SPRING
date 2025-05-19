package com.gnose.mvp.Containers_Tests.Unit;

import com.gnose.mvp.Containers_Module.Application.Impl.ContainerServiceImpl;
import com.gnose.mvp.Containers_Module.Infrastructure.Adapters.ContainerJpaRepository;
import com.gnose.mvp.Containers_Module.Infrastructure.Entities.ContainerJpaEntity;
import com.gnose.mvp.Exceptions.NotFoundException;
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
public class ContainerServiceTest {

    @Mock
    private ContainerJpaRepository jpaRepository;

    @InjectMocks
    private ContainerServiceImpl containerService;

    // Error ones

    @Test
    public void getByIdShouldThrow() {
        assertThrows(NotFoundException.class, () -> containerService.getById(1L, List.of(1L)));
    }

    @Test
    public void getByCompanyIdShouldThrow() {
        assertThrows(RuntimeException.class, () -> containerService.getByCompanyId(1L));

    }

    @Test
    public void getByWeightShouldThrow() {
        assertThrows(RuntimeException.class, () -> containerService.getByWeight(1555, List.of(1L)));
    }

    @Test
    public void getByTypeShouldThrow() {
        assertThrows(RuntimeException.class, () -> containerService.getByType("LIQUID", List.of(1L)));
    }

    @Test
    public void getByNumberShouldThrow() {
        assertThrows(RuntimeException.class, () -> containerService.getByNumber("9869769", List.of(1L)));
    }

    @Test
    public void deleteShouldThrow() {
        assertThrows(RuntimeException.class, () -> containerService.deleteContainer(1L, List.of(1L)));
    }

    @Test
    public void updateShouldThrow() {
        ContainerJpaEntity entity = new ContainerJpaEntity();
        entity.setId(1L);
        assertThrows(RuntimeException.class, () -> containerService.updateContainer(entity));
    }

    // Success ones

    @Test
    public void create()
    {
        ContainerJpaEntity entity = new ContainerJpaEntity();
        entity.setId(1L);
        entity.setType("LIQUID");
        entity.setWeight(4555);
        entity.setContainerNumber("DSDASDSASD");
        entity.setCompanyId(1L);

        containerService.createContainer(entity);
        verify(jpaRepository, times(1)).save(entity);
    }

    @Test
    public void update() {
        ContainerJpaEntity entity = new ContainerJpaEntity();
        entity.setId(1L);
        entity.setType("LIQUID");
        entity.setWeight(4555);
        entity.setContainerNumber("DSDASDSASD");
        entity.setCompanyId(1L);

        when(jpaRepository.existsById(1L)).thenReturn(true);
        containerService.updateContainer(entity);
        verify(jpaRepository, times(1)).save(entity);

    }

    @Test
    public void delete() {
        ContainerJpaEntity entity = new ContainerJpaEntity();
        entity.setId(1L);
        entity.setType("LIQUID");
        entity.setWeight(4555);
        entity.setContainerNumber("DSDASDSASD");
        entity.setCompanyId(1L);

        when(jpaRepository.existsById(1L)).thenReturn(true);
        containerService.deleteContainer(1L, List.of(1L));
        verify(jpaRepository, times(1)).deleteById(1L);
    }

    @Test
    public void getById() {
        ContainerJpaEntity entity = new ContainerJpaEntity();
        entity.setId(1L);
        entity.setType("LIQUID");
        entity.setWeight(4555);
        entity.setContainerNumber("DSDASDSASD");
        entity.setCompanyId(1L);

        when(jpaRepository.findById(1L)).thenReturn(Optional.of(entity));
        ContainerJpaEntity result = containerService.getById(1L, List.of(1L));

        assertEquals(entity, result);
    }

    @Test
    public void getByNumber() {
        ContainerJpaEntity entity = new ContainerJpaEntity();
        entity.setId(1L);
        entity.setType("LIQUID");
        entity.setWeight(4555);
        entity.setContainerNumber("DSDASDSASD");
        entity.setCompanyId(1L);

        when(jpaRepository.findByContainerNumberAndCompanyIdIn("DSDASDSASD", List.of(1L))).thenReturn(Optional.of(entity));
        ContainerJpaEntity result = containerService.getByNumber("DSDASDSASD", List.of(1L));

        assertEquals(entity, result);
    }

    @Test
    public void getByType() {
        ContainerJpaEntity entity = new ContainerJpaEntity();
        entity.setId(1L);
        entity.setType("LIQUID");
        entity.setWeight(4555);
        entity.setContainerNumber("DSDASDSASD");
        entity.setCompanyId(1L);

        ContainerJpaEntity entity2 = new ContainerJpaEntity();
        entity.setId(2L);
        entity.setType("LIQUID");
        entity.setWeight(4555);
        entity.setContainerNumber("556DSDASDSASD");
        entity.setCompanyId(1L);

        when(jpaRepository.findByTypeAndCompanyIdIn("LIQUID", List.of(1L))).thenReturn(Optional.of(List.of(entity, entity2)));
        List<ContainerJpaEntity> result = containerService.getByType("LIQUID", List.of(1L));

        assertEquals(List.of(entity, entity2), result);
    }

    @Test
    public void getByWeight() {
        ContainerJpaEntity entity = new ContainerJpaEntity();
        entity.setId(1L);
        entity.setType("LIQUID");
        entity.setWeight(4555);
        entity.setContainerNumber("DSDASDSASD");
        entity.setCompanyId(1L);

        ContainerJpaEntity entity2 = new ContainerJpaEntity();
        entity.setId(2L);
        entity.setType("LIQUID");
        entity.setWeight(4555);
        entity.setContainerNumber("556DSDASDSASD");
        entity.setCompanyId(1L);

        when(jpaRepository.findByWeightAndCompanyIdIn(4555, List.of(1L))).thenReturn(Optional.of(List.of(entity, entity2)));
        List<ContainerJpaEntity> result = containerService.getByWeight(4555, List.of(1L));

        assertEquals(List.of(entity, entity2), result);
    }

    @Test
    public void getByCompanyId() {
        ContainerJpaEntity entity = new ContainerJpaEntity();
        entity.setId(1L);
        entity.setType("LIQUID");
        entity.setWeight(4555);
        entity.setContainerNumber("DSDASDSASD");
        entity.setCompanyId(1L);

        ContainerJpaEntity entity2 = new ContainerJpaEntity();
        entity.setId(2L);
        entity.setType("LIQUID");
        entity.setWeight(4555);
        entity.setContainerNumber("556DSDASDSASD");
        entity.setCompanyId(1L);

        when(jpaRepository.findByCompanyId(1L)).thenReturn(Optional.of(List.of(entity, entity2)));
        List<ContainerJpaEntity> result = containerService.getByCompanyId(1L);

        assertEquals(List.of(entity, entity2), result);
    }
}
