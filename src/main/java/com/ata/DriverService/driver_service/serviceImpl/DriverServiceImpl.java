package com.ata.DriverService.driver_service.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ata.DriverService.driver_service.entity.DriverEntity;
import com.ata.DriverService.driver_service.exception.DriverNotFoundException;
import com.ata.DriverService.driver_service.repository.DriverRepository;
import com.ata.DriverService.driver_service.service.DriverService;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    private static final Logger logger = LoggerFactory.getLogger(DriverServiceImpl.class);

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public DriverEntity saveDriver(DriverEntity driver) {
        logger.info("Saving driver: {}", driver);
        return driverRepository.save(driver);
    }

    @Override
    public List<DriverEntity> getAllDrivers() {
        logger.info("Retrieving all drivers");
        return driverRepository.findAll();
    }

    @Override
    public DriverEntity getDriverById(Long id) {
        logger.info("Retrieving driver with id: {}", id);
        return driverRepository.findById(id)
            .orElseThrow(() -> new DriverNotFoundException("Driver not found with id: " + id));
    }

    @Override
    public void deleteDriverById(Long id) {
        if (!driverRepository.existsById(id)) {
            throw new DriverNotFoundException("Driver not found with id: " + id);
        }
        logger.info("Deleting driver with id: {}", id);
        driverRepository.deleteById(id);
    }
}
