package com.cernsuite.maintenancetracker.controller;

import com.cernsuite.maintenancetracker.model.Engineer;
import com.cernsuite.maintenancetracker.service.EngineerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/engineers")
public class EngineerController {

    @Autowired
    private EngineerService engineerService;

    @GetMapping
    public List<Engineer> getAllEngineers() {
        return engineerService.getAllEngineers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Engineer> getEngineerById(@PathVariable Long id) {
        return engineerService.getEngineerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Engineer createEngineer(@RequestBody Engineer engineer) {
        return engineerService.createEngineer(engineer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Engineer> updateEngineer(@PathVariable Long id, @RequestBody Engineer engineerDetails) {
        return engineerService.updateEngineer(id, engineerDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEngineer(@PathVariable Long id) {
        if (engineerService.deleteEngineer(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
