package com.cernsuite.maintenancetracker.service;

import com.cernsuite.maintenancetracker.model.Equipment;

import java.util.List;
import java.util.Optional;

public interface EquipmentServiceInterface {
    Equipment create(Equipment equipment);
    Optional<Equipment> getById(Long id);
    List<Equipment> getAll();
    Equipment update(Long id, Equipment equipment);
    Boolean deleteEquipment(Long id);
}

