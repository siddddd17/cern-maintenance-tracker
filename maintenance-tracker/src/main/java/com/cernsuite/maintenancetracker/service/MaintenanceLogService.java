package com.cernsuite.maintenancetracker.service;

import com.cernsuite.maintenancetracker.model.Engineer;
import com.cernsuite.maintenancetracker.model.Equipment;
import com.cernsuite.maintenancetracker.model.MaintenanceLog;
import com.cernsuite.maintenancetracker.model.WorkflowProcess;
import com.cernsuite.maintenancetracker.repository.EngineerRepository;
import com.cernsuite.maintenancetracker.repository.EquipmentRepository;
import com.cernsuite.maintenancetracker.repository.MaintenanceLogRepository;
import com.cernsuite.maintenancetracker.repository.WorkflowProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceLogService implements MaintenanceLogServiceInterface{

    @Autowired
    private MaintenanceLogRepository maintenanceLogRepository;

    @Autowired
    private EngineerRepository engineerRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private WorkflowProcessRepository workflowProcessRepository;

    public List<MaintenanceLog> getAllLogs() {
        return maintenanceLogRepository.findAll();
    }

    public Optional<MaintenanceLog> getLogById(Long id) {
        return maintenanceLogRepository.findById(id);
    }

    public MaintenanceLog createLog(MaintenanceLog log) {
        // Fetch existing Equipment and Engineer entities from DB
        Equipment equipment = equipmentRepository.findById(log.getEquipment().getId())
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        Engineer engineer = engineerRepository.findById(log.getEngineer().getId())
                .orElseThrow(() -> new RuntimeException("Engineer not found"));

        log.setEquipment(equipment);
        log.setEngineer(engineer);

        equipment.getMaintenanceLogs().add(log);
        engineer.getMaintenanceLogs().add(log);

        if (log.getWorkflowProcess() != null && log.getWorkflowProcess().getId() != null) {
            WorkflowProcess process = workflowProcessRepository.findById(log.getWorkflowProcess().getId())
                    .orElseThrow(() -> new RuntimeException("Workflow process not found"));
            log.setWorkflowProcess(process);
        }

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
