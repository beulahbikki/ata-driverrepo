package com.ata.DriverService.driver_service.serviceimpl;

import com.ata.DriverService.driver_service.entity.DriverEntity;
import com.ata.DriverService.driver_service.exception.DriverNotFoundException;
import com.ata.DriverService.driver_service.repository.DriverRepository;
import com.ata.DriverService.driver_service.service.DriverService;
import com.ata.DriverService.driver_service.serviceImpl.DriverServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DriverServiceImplTest {

    @InjectMocks
    private DriverServiceImpl driverService;

    @Mock
    private DriverRepository driverRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveDriver() {
        DriverEntity driver = new DriverEntity();
        when(driverRepository.save(driver)).thenReturn(driver);

        DriverEntity savedDriver = driverService.saveDriver(driver);
        assertEquals(driver, savedDriver);
        verify(driverRepository, times(1)).save(driver);
    }

    @Test
    void testGetAllDrivers() {
        List<DriverEntity> drivers = new ArrayList<>();
        drivers.add(new DriverEntity());
        when(driverRepository.findAll()).thenReturn(drivers);

        List<DriverEntity> retrievedDrivers = driverService.getAllDrivers();
        assertEquals(drivers, retrievedDrivers);
        verify(driverRepository, times(1)).findAll();
    }

    @Test
    void testGetDriverById_Success() {
        DriverEntity driver = new DriverEntity();
        when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));

        DriverEntity retrievedDriver = driverService.getDriverById(1L);
        assertEquals(driver, retrievedDriver);
        verify(driverRepository, times(1)).findById(1L);
    }

    @Test
    void testGetDriverById_NotFound() {
        when(driverRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DriverNotFoundException.class, () -> {
            driverService.getDriverById(1L);
        });
        verify(driverRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteDriverById_Success() {
        when(driverRepository.existsById(1L)).thenReturn(true);
        doNothing().when(driverRepository).deleteById(1L);

        driverService.deleteDriverById(1L);
        verify(driverRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteDriverById_NotFound() {
        when(driverRepository.existsById(1L)).thenReturn(false);

        assertThrows(DriverNotFoundException.class, () -> {
            driverService.deleteDriverById(1L);
        });
        verify(driverRepository, never()).deleteById(1L);
    }
}
