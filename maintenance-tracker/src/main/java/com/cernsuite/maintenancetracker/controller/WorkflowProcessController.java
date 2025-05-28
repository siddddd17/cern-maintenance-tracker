package com.cernsuite.maintenancetracker.controller;

import com.cernsuite.maintenancetracker.model.WorkflowProcess;
import com.cernsuite.maintenancetracker.service.WorkflowProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workflow-processes")
public class WorkflowProcessController {

    @Autowired
    private WorkflowProcessService workflowProcessService;

    @GetMapping
    public List<WorkflowProcess> getAllProcesses() {
        return workflowProcessService.getAllProcesses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkflowProcess> getProcessById(@PathVariable Long id) {
        return workflowProcessService.getProcessById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public WorkflowProcess createProcess(@RequestBody WorkflowProcess process) {
        return workflowProcessService.createProcess(process);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkflowProcess> updateProcess(@PathVariable Long id, @RequestBody WorkflowProcess updatedProcess) {
        try {
            WorkflowProcess process = workflowProcessService.updateProcess(id, updatedProcess);
            return ResponseEntity.ok(process);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcess(@PathVariable Long id) {
        if (workflowProcessService.deleteProcess(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
