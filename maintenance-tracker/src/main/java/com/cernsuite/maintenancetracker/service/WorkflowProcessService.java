
package com.cernsuite.maintenancetracker.service;

import com.cernsuite.maintenancetracker.model.WorkflowProcess;
import com.cernsuite.maintenancetracker.repository.WorkflowProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkflowProcessService implements WorkflowProcessServiceInterface{

    @Autowired
    private WorkflowProcessRepository workflowProcessRepository;

    public List<WorkflowProcess> getAllProcesses() {
        return workflowProcessRepository.findAll();
    }

    public Optional<WorkflowProcess> getProcessById(Long id) {
        return workflowProcessRepository.findById(id);
    }

    public WorkflowProcess createProcess(WorkflowProcess process) {
        return workflowProcessRepository.save(process);
    }

    public WorkflowProcess updateProcess(Long id, WorkflowProcess updatedProcess) {
        return workflowProcessRepository.findById(id)
                .map(existing -> {
                    existing.setProcessInstanceId(updatedProcess.getProcessInstanceId());
                    existing.setStartDate(updatedProcess.getStartDate());
                    existing.setEndDate(updatedProcess.getEndDate());
                    existing.setStatus(updatedProcess.getStatus());
                    return workflowProcessRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Workflow process not found"));
    }

    public Boolean deleteProcess(Long id) {
        if (workflowProcessRepository.existsById(id)) {
            workflowProcessRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
