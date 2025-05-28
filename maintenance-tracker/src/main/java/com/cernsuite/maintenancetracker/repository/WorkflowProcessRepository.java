package com.cernsuite.maintenancetracker.repository;

import com.cernsuite.maintenancetracker.model.WorkflowProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowProcessRepository extends JpaRepository<WorkflowProcess, Long> {
    WorkflowProcess findByProcessInstanceId(String processInstanceId);
}
