package com.gnose.mvp.Ports_Ships_Tests.Unit;

import com.gnose.mvp.Ports_Ships_Module.Application.Impl.PortServiceImpl;
import com.gnose.mvp.Ports_Ships_Module.Application.UseCases.IPortsService;
import com.gnose.mvp.Ports_Ships_Module.Infrastructure.Adapters.Repositories.PortJpaRepository;
import com.gnose.mvp.Ports_Ships_Module.Infrastructure.Entities.PortJpaEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.*;

@SpringBootTest
public class PortServiceTest {

    @Mock
    private PortJpaRepository portJpaRepository;

    @InjectMocks
    private PortServiceImpl portsService;

//    @BeforeEach
//    public void setup() {
//        PortJpaEntity port1 = new PortJpaEntity();
//        port1.setId(1L);
//        port1.setCode("42DAPT");
//        port1.setCountry("China");
//        port1.setName("NAME MOCK1");
//
//        PortJpaEntity port2 = new PortJpaEntity();
//        port2.setId(2L);
//        port2.setCode("43DAPT");
//        port2.setCountry("China");
//        port2.setName("NAME MOCK2");
//
//        List<PortJpaEntity> list = new ArrayList();
//        list.add(port1);
//        list.add(port2);
//    }

    // Error ones

    @Test
    public void getPortByCountryShouldThrow() {
        assertThrows(RuntimeException.class, () -> portsService.getPortByCountry("Brasil"));
    }

    @Test
    public void getPortByCodeShouldThrow() {
        assertThrows(RuntimeException.class, () -> portsService.getPortByCode("PDSN"));
    }

    @Test
    public void getPortByIdShouldThrow() {
        assertThrows(RuntimeException.class, () -> portsService.getPortById(3L));
    }

    @Test
    public void deletePortShouldThrow() {
        assertThrows(RuntimeException.class, () -> portsService.deletePort(3L));
    }

    @Test
    public void updatePortShouldThrow() {
        assertThrows(RuntimeException.class, () -> portsService
                .updatePort(3L,
                        "Mock Name 3",
                        "Brasil",
                        "BLABLABLA"));
    }

    // Sucess ones

    @Test
    public void getPortByIdSucess()
    {
        PortJpaEntity port1 = new PortJpaEntity();
        port1.setId(1L);
        port1.setCode("42DAPT");
        port1.setCountry("China");
        port1.setName("NAME MOCK1");

        when(portJpaRepository.findById(1L)).thenReturn(Optional.of(port1));

        PortJpaEntity response = portsService.getPortById(1L);

        assertEquals(port1, response);
    }

    @Test
    public void getPortByCountrySucess()
    {
        PortJpaEntity port1 = new PortJpaEntity();
        port1.setId(1L);
        port1.setCode("42DAPT");
        port1.setCountry("China");
        port1.setName("NAME MOCK1");

        when(portJpaRepository.findByCountry("China")).thenReturn(Optional.of(port1));

        PortJpaEntity response = portsService.getPortByCountry("China");

        assertEquals(port1, response);
    }

    @Test
    public void getPortByCodeSucess()
    {
        PortJpaEntity port1 = new PortJpaEntity();
        port1.setId(1L);
        port1.setCode("42DAPT");
        port1.setCountry("China");
        port1.setName("NAME MOCK1");

        when(portJpaRepository.findByCode("42DAPT")).thenReturn(Optional.of(port1));

        PortJpaEntity response = portsService.getPortByCode("42DAPT");

        assertEquals(port1, response);
    }

    @Test
    public void getAllPortsSucess()
    {
        PortJpaEntity port1 = new PortJpaEntity();
        port1.setId(1L);
        port1.setCode("42DAPT");
        port1.setCountry("China");
        port1.setName("NAME MOCK1");

        PortJpaEntity port2 = new PortJpaEntity();
        port2.setId(2L);
        port2.setCode("43DAPT");
        port2.setCountry("China");
        port2.setName("NAME MOCK2");

        when(portJpaRepository.findAll()).thenReturn(List.of(port1, port2));

        List<PortJpaEntity> response = portsService.getAllPorts();

        assertEquals(response, List.of(port1, port2));
    }

    @Test
    public void deleteShipSuccess()
    {
        PortJpaEntity port1 = new PortJpaEntity();
        port1.setId(1L);
        port1.setCode("42DAPT");
        port1.setCountry("China");
        port1.setName("NAME MOCK1");

        when(portJpaRepository.existsById(1L)).thenReturn(true);

        portsService.deletePort(1L);

        verify(portJpaRepository, times(1)).existsById(1L);
    }

    @Test
    public void updateShipSuccess()
    {
        PortJpaEntity port1 = new PortJpaEntity();
        port1.setId(1L);
        port1.setCode("42DAPT");
        port1.setCountry("China");
        port1.setName("NAME MOCK1");

        when(portJpaRepository.findById(1L)).thenReturn(Optional.of(port1));

        portsService.updatePort(1L, "NAME MOCK2", "China", "41DAPT");

        // need to change the interface and impl to receive a entity

        //verify(portJpaRepository, times(1))
        //        .save(new PortJpaEntity(1L, "NAME MOCK2", "China", "41DAPT"));
    }

}
