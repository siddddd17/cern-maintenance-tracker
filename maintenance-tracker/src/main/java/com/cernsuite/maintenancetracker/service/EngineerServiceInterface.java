package com.cernsuite.maintenancetracker.service;

import com.cernsuite.maintenancetracker.model.Engineer;

import java.util.List;
import java.util.Optional;

public interface EngineerServiceInterface {
    Engineer createEngineer(Engineer engineer);
    Optional<Engineer> getEngineerById(Long id);
    List<Engineer> getAllEngineers();
    Engineer updateEngineer(Long id, Engineer engineer);
    Boolean deleteEngineer(Long id);
}
