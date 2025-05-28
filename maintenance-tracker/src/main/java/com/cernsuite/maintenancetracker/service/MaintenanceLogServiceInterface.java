package com.cernsuite.maintenancetracker.service;

import com.cernsuite.maintenancetracker.model.MaintenanceLog;

import java.util.List;
import java.util.Optional;

public interface MaintenanceLogServiceInterface {
    List<MaintenanceLog> getAllLogs();
    Optional<MaintenanceLog> getLogById(Long id);
    MaintenanceLog createLog(MaintenanceLog log);
    MaintenanceLog updateLog(Long id, MaintenanceLog updatedLog);
    void deleteLog(Long id);
}
