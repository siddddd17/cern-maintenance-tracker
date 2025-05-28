package com.cernsuite.maintenancetracker.service;

import com.cernsuite.maintenancetracker.model.Equipment;

import java.util.List;
import java.util.Optional;

public interface EquipmentServiceInterface {
    List<Equipment> getAllEquipment();
    Optional<Equipment> getEquipmentById(Long id);
    Equipment createEquipment(Equipment equipment);
    Equipment updateEquipment(Long id, Equipment updatedEquipment);
    Boolean deleteEquipment(Long id);
}

