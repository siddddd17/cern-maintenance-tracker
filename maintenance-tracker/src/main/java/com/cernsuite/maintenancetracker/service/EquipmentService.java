package com.cernsuite.maintenancetracker.service;

import com.cernsuite.maintenancetracker.model.Equipment;
import com.cernsuite.maintenancetracker.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService implements EquipmentServiceInterface{

    @Autowired
    private EquipmentRepository equipmentRepository;

    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    public Optional<Equipment> getEquipmentById(Long id) {
        return equipmentRepository.findById(id);
    }

    public Equipment createEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public Equipment updateEquipment(Long id, Equipment updatedEquipment) {
        return equipmentRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedEquipment.getName());
                    existing.setLocation(updatedEquipment.getLocation());
                    existing.setType(updatedEquipment.getType());
                    existing.setSerialNumber(updatedEquipment.getSerialNumber());
                    existing.setInstallationDate(updatedEquipment.getInstallationDate());
                    existing.setStatus(updatedEquipment.getStatus());
                    return equipmentRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Equipment not found"));
    }

    public void deleteEquipment(Long id) {
        equipmentRepository.deleteById(id);
    }
}

