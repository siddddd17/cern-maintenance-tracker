package com.cernsuite.maintenancetracker.service;

import com.cernsuite.maintenancetracker.dto.WorkflowProcessDTO;
import com.cernsuite.maintenancetracker.mapper.WorkflowProcessMapper;
import com.cernsuite.maintenancetracker.model.WorkflowProcess;
import com.cernsuite.maintenancetracker.repository.WorkflowProcessRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkflowProcessService {

    private final WorkflowProcessRepository workflowProcessRepository;
    private final WorkflowProcessMapper workflowProcessMapper;

    public WorkflowProcessDTO create(WorkflowProcessDTO dto) {
        WorkflowProcess process = workflowProcessMapper.toEntity(dto);
        return workflowProcessMapper.toDTO(workflowProcessRepository.save(process));
    }

    public WorkflowProcessDTO update(Long id, WorkflowProcessDTO dto) {
        WorkflowProcess existing = workflowProcessRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("WorkflowProcess not found"));
        workflowProcessMapper.updateEntityFromDto(dto, existing);
        return workflowProcessMapper.toDTO(workflowProcessRepository.save(existing));
    }

    public Page<WorkflowProcessDTO> getAll(Pageable pageable) {
        return workflowProcessRepository.findAll(pageable).map(workflowProcessMapper::toDTO);
    }

    public WorkflowProcessDTO getById(Long id) {
        WorkflowProcess process = workflowProcessRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("WorkflowProcess not found"));
        return workflowProcessMapper.toDTO(process);
    }

    public void delete(Long id) {
        if (!workflowProcessRepository.existsById(id)) {
            throw new EntityNotFoundException("WorkflowProcess not found");
        }
        workflowProcessRepository.deleteById(id);
    }
}
