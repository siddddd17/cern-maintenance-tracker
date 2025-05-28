package com.cernsuite.maintenancetracker.controller;

import com.cernsuite.maintenancetracker.model.MaintenanceLog;
import com.cernsuite.maintenancetracker.service.MaintenanceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance-logs")
public class MaintenanceLogController {

    @Autowired
    private MaintenanceLogService maintenanceLogService;

    @GetMapping
    public List<MaintenanceLog> getAllLogs() {
        return maintenanceLogService.getAllLogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceLog> getLogById(@PathVariable Long id) {
        return maintenanceLogService.getLogById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MaintenanceLog createLog(@RequestBody MaintenanceLog log) {
        return maintenanceLogService.createLog(log);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceLog> updateLog(@PathVariable Long id, @RequestBody MaintenanceLog updatedLog) {
        try {
            MaintenanceLog log = maintenanceLogService.updateLog(id, updatedLog);
            return ResponseEntity.ok(log);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLog(@PathVariable Long id) {
        if (maintenanceLogService.deleteLog(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
