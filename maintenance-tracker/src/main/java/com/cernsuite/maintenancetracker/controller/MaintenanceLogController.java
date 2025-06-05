package com.cernsuite.maintenancetracker.controller;

import com.cernsuite.maintenancetracker.dto.MaintenanceLogDTO;
import com.cernsuite.maintenancetracker.service.MaintenanceLogService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance-logs")
@RequiredArgsConstructor
public class MaintenanceLogController {

    private final MaintenanceLogService maintenanceLogService;

    @Operation(summary = "Create new MaintenanceLog")
    @PostMapping
    public ResponseEntity<MaintenanceLogDTO> create(@Valid @RequestBody MaintenanceLogDTO dto) {
        return ResponseEntity.ok(maintenanceLogService.create(dto));
    }

    @Operation(summary = "Update existing MaintenanceLog")
    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceLogDTO> update(@PathVariable Long id, @Valid @RequestBody MaintenanceLogDTO dto) {
        return ResponseEntity.ok(maintenanceLogService.update(id, dto));
    }

    @Operation(summary = "Get all MaintenanceLogs with pagination")
    @GetMapping
    public ResponseEntity<Page<MaintenanceLogDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(maintenanceLogService.getAll(pageable));
    }

    @Operation(summary = "Get MaintenanceLog by ID")
    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceLogDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(maintenanceLogService.getById(id));
    }

    @Operation(summary = "Delete MaintenanceLog by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        maintenanceLogService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/maintenance-logs")
    public ResponseEntity<List<MaintenanceLogDTO>> getLogsByEngineer(@PathVariable Long id) {
        List<MaintenanceLogDTO> logs = maintenanceLogService.findByEngineerId(id);
        return ResponseEntity.ok(logs);
    }

}
