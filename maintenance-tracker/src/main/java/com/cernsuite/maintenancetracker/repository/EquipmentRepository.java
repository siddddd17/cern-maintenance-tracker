package com.cernsuite.maintenancetracker.repository;

import com.cernsuite.maintenancetracker.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
