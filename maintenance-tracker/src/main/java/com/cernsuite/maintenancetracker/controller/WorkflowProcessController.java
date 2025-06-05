package com.cernsuite.maintenancetracker.controller;

import com.cernsuite.maintenancetracker.dto.WorkflowProcessDTO;
import com.cernsuite.maintenancetracker.service.WorkflowProcessService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workflow")
@RequiredArgsConstructor
public class WorkflowProcessController {

    private final WorkflowProcessService workflowProcessService;

    @Operation(summary = "Create new WorkflowProcess")
    @PostMapping
    public ResponseEntity<WorkflowProcessDTO> create(@Valid @RequestBody WorkflowProcessDTO dto) {
        return ResponseEntity.ok(workflowProcessService.create(dto));
    }

    @Operation(summary = "Update existing WorkflowProcess")
    @PutMapping("/{id}")
    public ResponseEntity<WorkflowProcessDTO> update(@PathVariable Long id, @Valid @RequestBody WorkflowProcessDTO dto) {
        return ResponseEntity.ok(workflowProcessService.update(id, dto));
    }

    @Operation(summary = "Get all WorkflowProcesses with pagination")
    @GetMapping
    public ResponseEntity<Page<WorkflowProcessDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(workflowProcessService.getAll(pageable));
    }

    @Operation(summary = "Get WorkflowProcess by ID")
    @GetMapping("/{id}")
    public ResponseEntity<WorkflowProcessDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(workflowProcessService.getById(id));
    }

    @Operation(summary = "Delete WorkflowProcess by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        workflowProcessService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
