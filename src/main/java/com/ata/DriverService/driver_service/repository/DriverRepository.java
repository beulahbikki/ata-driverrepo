package com.ata.DriverService.driver_service.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ata.DriverService.driver_service.entity.DriverEntity;
@Repository
public interface DriverRepository extends JpaRepository<DriverEntity, Long>{

}
