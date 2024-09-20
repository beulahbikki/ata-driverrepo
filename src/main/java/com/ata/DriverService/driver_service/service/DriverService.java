package com.ata.DriverService.driver_service.service;

import java.util.List;

import com.ata.DriverService.driver_service.entity.DriverEntity;

public interface DriverService {
	 DriverEntity saveDriver(DriverEntity driver);
	    List<DriverEntity> getAllDrivers();
	    DriverEntity getDriverById(Long id);
	    void deleteDriverById(Long id);
}
