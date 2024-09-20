package com.ata.DriverService.driver_service.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ata.DriverService.driver_service.entity.DriverEntity;
import com.ata.DriverService.driver_service.service.DriverService;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private static final Logger logger = LoggerFactory.getLogger(DriverController.class);

    @Autowired
    private DriverService driverService;

    @PostMapping("/add")
    public ResponseEntity<DriverEntity> createDriver(@RequestBody DriverEntity driver) {
        logger.info("Creating driver: {}", driver);
        DriverEntity createdDriver = driverService.saveDriver(driver);
        return new ResponseEntity<>(createdDriver, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DriverEntity>> getAllDrivers() {
        logger.info("Fetching all drivers");
        List<DriverEntity> drivers = driverService.getAllDrivers();
        if (drivers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverEntity> getDriverById(@PathVariable Long id) {
        logger.info("Fetching driver with id: {}", id);
        DriverEntity driver = driverService.getDriverById(id);
        if (driver == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDriverById(@PathVariable Long id) {
        logger.info("Deleting driver with id: {}", id);
        driverService.deleteDriverById(id);
        return new ResponseEntity<>("Driver deleted successfully", HttpStatus.OK);
    }
}
