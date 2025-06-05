package com.cernsuite.maintenancetracker.controller;

import com.cernsuite.maintenancetracker.dto.EquipmentDTO;
import com.cernsuite.maintenancetracker.service.EquipmentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/equipment")
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @Operation(summary = "Create new Equipment")
    @PostMapping
    public ResponseEntity<EquipmentDTO> create(@Valid @RequestBody EquipmentDTO dto) {
        return ResponseEntity.ok(equipmentService.create(dto));
    }

    @Operation(summary = "Update existing Equipment")
    @PutMapping("/{id}")
    public ResponseEntity<EquipmentDTO> update(@PathVariable Long id, @Valid @RequestBody EquipmentDTO dto) {
        return ResponseEntity.ok(equipmentService.update(id, dto));
    }

    @Operation(summary = "Get all Equipment with pagination")
    @GetMapping
    public ResponseEntity<Page<EquipmentDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(equipmentService.getAll(pageable));
    }

    @Operation(summary = "Get Equipment by ID")
    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(equipmentService.getById(id));
    }

    @Operation(summary = "Delete Equipment by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        equipmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
