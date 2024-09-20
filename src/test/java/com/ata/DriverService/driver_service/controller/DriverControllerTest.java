package com.ata.DriverService.driver_service.controller;

import com.ata.DriverService.driver_service.entity.DriverEntity;
import com.ata.DriverService.driver_service.service.DriverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DriverControllerTest {

    @InjectMocks
    private DriverController driverController;

    @Mock
    private DriverService driverService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateDriver() {
        DriverEntity driver = new DriverEntity();
        when(driverService.saveDriver(driver)).thenReturn(driver);

        ResponseEntity<DriverEntity> response = driverController.createDriver(driver);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(driver, response.getBody());
        verify(driverService, times(1)).saveDriver(driver);
    }

    @Test
    void testGetAllDrivers() {
        List<DriverEntity> drivers = new ArrayList<>();
        drivers.add(new DriverEntity());
        when(driverService.getAllDrivers()).thenReturn(drivers);

        ResponseEntity<List<DriverEntity>> response = driverController.getAllDrivers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(drivers, response.getBody());
        verify(driverService, times(1)).getAllDrivers();
    }

    @Test
    void testGetAllDrivers_NoContent() {
        when(driverService.getAllDrivers()).thenReturn(new ArrayList<>());

        ResponseEntity<List<DriverEntity>> response = driverController.getAllDrivers();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(driverService, times(1)).getAllDrivers();
    }

    @Test
    void testGetDriverById() {
        DriverEntity driver = new DriverEntity();
        when(driverService.getDriverById(1L)).thenReturn(driver);

        ResponseEntity<DriverEntity> response = driverController.getDriverById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(driver, response.getBody());
        verify(driverService, times(1)).getDriverById(1L);
    }

    @Test
    void testDeleteDriverById() {
        doNothing().when(driverService).deleteDriverById(1L);

        ResponseEntity<String> response = driverController.deleteDriverById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Driver deleted successfully", response.getBody());
        verify(driverService, times(1)).deleteDriverById(1L);
    }
}
