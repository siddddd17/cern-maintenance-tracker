package com.cernsuite.maintenancetracker.repository;

import com.cernsuite.maintenancetracker.model.MaintenanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceLogRepository extends JpaRepository<MaintenanceLog, Long> {
    List<MaintenanceLog> findByEngineerId(Long engineerId);

}
