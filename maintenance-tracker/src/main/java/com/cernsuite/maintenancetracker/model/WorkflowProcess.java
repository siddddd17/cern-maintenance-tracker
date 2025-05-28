package com.cernsuite.maintenancetracker.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "workflow_process")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String processInstanceId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status; // e.g., RUNNING, COMPLETED, FAILED
}
