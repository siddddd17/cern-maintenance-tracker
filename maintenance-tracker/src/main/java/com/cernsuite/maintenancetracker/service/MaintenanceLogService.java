package com.cernsuite.maintenancetracker.service;

import com.cernsuite.maintenancetracker.model.MaintenanceLog;
import com.cernsuite.maintenancetracker.repository.MaintenanceLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceLogService implements MaintenanceLogServiceInterface{

    @Autowired
    private MaintenanceLogRepository maintenanceLogRepository;

    public List<MaintenanceLog> getAllLogs() {
        return maintenanceLogRepository.findAll();
    }

    public Optional<MaintenanceLog> getLogById(Long id) {
        return maintenanceLogRepository.findById(id);
    }

    public MaintenanceLog createLog(MaintenanceLog log) {
        return maintenanceLogRepository.save(log);
    }

    public MaintenanceLog updateLog(Long id, MaintenanceLog updatedLog) {
        return maintenanceLogRepository.findById(id)
                .map(existing -> {
                    existing.setDate(updatedLog.getDate());
                    existing.setDescription(updatedLog.getDescription());
                    existing.setStatus(updatedLog.getStatus());
                    existing.setEquipment(updatedLog.getEquipment());
                    existing.setEngineer(updatedLog.getEngineer());
                    existing.setWorkflowProcess(updatedLog.getWorkflowProcess());
                    return maintenanceLogRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Maintenance log not found"));
    }

    public Boolean deleteLog(Long id) {
        if (maintenanceLogRepository.existsById(id)) {
            maintenanceLogRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
