package com.cernsuite.maintenancetracker.repository;

import com.cernsuite.maintenancetracker.model.Engineer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineerRepository extends JpaRepository<Engineer, Long> {
}
