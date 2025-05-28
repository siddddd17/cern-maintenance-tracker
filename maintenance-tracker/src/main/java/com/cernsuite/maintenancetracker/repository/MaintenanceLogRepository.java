package com.cernsuite.maintenancetracker.repository;

import com.cernsuite.maintenancetracker.model.MaintenanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceLogRepository extends JpaRepository<MaintenanceLog, Long> {
}
