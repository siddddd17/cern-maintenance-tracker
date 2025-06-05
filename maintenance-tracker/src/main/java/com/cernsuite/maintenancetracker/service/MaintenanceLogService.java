package com.cernsuite.maintenancetracker.service;

import com.cernsuite.maintenancetracker.dto.MaintenanceLogDTO;
import com.cernsuite.maintenancetracker.mapper.MaintenanceLogMapper;
import com.cernsuite.maintenancetracker.model.*;
import com.cernsuite.maintenancetracker.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaintenanceLogService {

    private final MaintenanceLogRepository maintenanceLogRepository;
    private final MaintenanceLogMapper maintenanceLogMapper;
    private final EquipmentRepository equipmentRepository;
    private final EngineerRepository engineerRepository;
    private final WorkflowProcessRepository workflowProcessRepository;

    public MaintenanceLogDTO create(MaintenanceLogDTO dto) {
        MaintenanceLog log = maintenanceLogMapper.toEntity(dto);
        log.setEquipment(equipmentRepository.findById(dto.getEquipmentId())
                .orElseThrow(() -> new EntityNotFoundException("Equipment not found")));
        log.setEngineer(engineerRepository.findById(dto.getEngineerId())
                .orElseThrow(() -> new EntityNotFoundException("Engineer not found")));
        if (dto.getWorkflowProcessId() != null) {
            log.setWorkflowProcess(workflowProcessRepository.findById(dto.getWorkflowProcessId())
                    .orElseThrow(() -> new EntityNotFoundException("WorkflowProcess not found")));
        }
        return maintenanceLogMapper.toDTO(maintenanceLogRepository.save(log));
    }

    public MaintenanceLogDTO update(Long id, MaintenanceLogDTO dto) {
        MaintenanceLog existing = maintenanceLogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MaintenanceLog not found"));
        maintenanceLogMapper.updateEntityFromDto(dto, existing);
        existing.setEquipment(equipmentRepository.findById(dto.getEquipmentId())
                .orElseThrow(() -> new EntityNotFoundException("Equipment not found")));
        existing.setEngineer(engineerRepository.findById(dto.getEngineerId())
                .orElseThrow(() -> new EntityNotFoundException("Engineer not found")));
        if (dto.getWorkflowProcessId() != null) {
            existing.setWorkflowProcess(workflowProcessRepository.findById(dto.getWorkflowProcessId())
                    .orElseThrow(() -> new EntityNotFoundException("WorkflowProcess not found")));
        } else {
            existing.setWorkflowProcess(null);
        }
        return maintenanceLogMapper.toDTO(maintenanceLogRepository.save(existing));
    }

    public Page<MaintenanceLogDTO> getAll(Pageable pageable) {
        return maintenanceLogRepository.findAll(pageable).map(maintenanceLogMapper::toDTO);
    }

    public MaintenanceLogDTO getById(Long id) {
        MaintenanceLog log = maintenanceLogRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MaintenanceLog not found"));
        return maintenanceLogMapper.toDTO(log);
    }

    public void delete(Long id) {
        if (!maintenanceLogRepository.existsById(id)) {
            throw new EntityNotFoundException("MaintenanceLog not found");
        }
        maintenanceLogRepository.deleteById(id);
    }

    public List<MaintenanceLogDTO> findByEngineerId(Long engineerId) {
        List<MaintenanceLog> logs = maintenanceLogRepository.findByEngineerId(engineerId);
        return logs.stream()
                .map(maintenanceLogMapper::toDTO)
                .collect(Collectors.toList());
    }

}
