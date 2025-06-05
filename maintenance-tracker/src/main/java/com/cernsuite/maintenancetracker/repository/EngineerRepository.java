package com.cernsuite.maintenancetracker.repository;

import com.cernsuite.maintenancetracker.model.Engineer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EngineerRepository extends JpaRepository<Engineer, Long> {

    @Query("SELECT e FROM Engineer e WHERE e.status = 'ACTIVE'")
    Page<Engineer> findAllActive(Pageable pageable);

    @Query("SELECT e FROM Engineer e WHERE e.id = :id AND e.status = 'ACTIVE'")
    Optional<Engineer> findActiveById(Long id);

}
