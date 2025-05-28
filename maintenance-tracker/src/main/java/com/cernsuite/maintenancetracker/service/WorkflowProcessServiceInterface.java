package com.cernsuite.maintenancetracker.service;

import com.cernsuite.maintenancetracker.model.WorkflowProcess;

import java.util.List;
import java.util.Optional;

public interface WorkflowProcessServiceInterface {
    List<WorkflowProcess> getAllProcesses();
    Optional<WorkflowProcess> getProcessById(Long id);
    WorkflowProcess createProcess(WorkflowProcess process);
    WorkflowProcess updateProcess(Long id, WorkflowProcess updatedProcess);
    void deleteProcess(Long id);
}
