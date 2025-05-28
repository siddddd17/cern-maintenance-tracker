package com.cernsuite.maintenancetracker.service;

import com.cernsuite.maintenancetracker.model.Engineer;
import com.cernsuite.maintenancetracker.repository.EngineerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EngineerService {

    @Autowired
    private EngineerRepository engineerRepository;

    public List<Engineer> getAllEngineers() {
        return engineerRepository.findAll();
    }

    public Optional<Engineer> getEngineerById(Long id) {
        return engineerRepository.findById(id);
    }

    public Engineer createEngineer(Engineer engineer) {
        return engineerRepository.save(engineer);
    }

    public Engineer updateEngineer(Long id, Engineer updatedEngineer) {
        return engineerRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedEngineer.getName());
                    existing.setEmail(updatedEngineer.getEmail());
                    existing.setRole(updatedEngineer.getRole());
                    return engineerRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Engineer not found"));
    }

    public void deleteEngineer(Long id) {
        engineerRepository.deleteById(id);
    }
}
