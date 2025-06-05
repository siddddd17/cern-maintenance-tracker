package com.cernsuite.maintenancetracker.controller;

import com.cernsuite.maintenancetracker.dto.EngineerDTO;
import com.cernsuite.maintenancetracker.service.EngineerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/engineers")
@RequiredArgsConstructor
public class EngineerController {

    private final EngineerService engineerService;

    @Operation(summary = "Create new Engineer")
    @PostMapping
    public ResponseEntity<EngineerDTO> create(@Valid @RequestBody EngineerDTO dto) {
        return ResponseEntity.ok(engineerService.create(dto));
    }

    /*
    todo : in future consider giving a single feild for updation. ie we pass the engineer id and the feild to update rather than giving the entire enginner dto feilds
     */
    @Operation(summary = "Update existing Engineer")
    @PutMapping("/{id}")
    public ResponseEntity<EngineerDTO> update(@PathVariable Long id, @Valid @RequestBody EngineerDTO dto) {
        return ResponseEntity.ok(engineerService.update(id, dto));
    }

    @Operation(summary = "Get all Engineers with pagination")
    @GetMapping
    public ResponseEntity<Page<?>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "false") boolean includeMaintenanceLogs
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(engineerService.getAll(pageable, includeMaintenanceLogs));
    }

    @Operation(summary = "Get Engineer by ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @PathVariable Long id,
            @RequestParam(defaultValue = "false") boolean includeMaintenanceLogs
    ) {
        Object dto = engineerService.getById(id, includeMaintenanceLogs);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Delete Engineer by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        engineerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
