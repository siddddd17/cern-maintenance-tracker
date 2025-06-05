package com.cernsuite.maintenancetracker.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * for a particular log , lets say the engineer id = 1 , equipment id = 1 and workflow process id = 1
 * in future, if the engineer retires, we could delete the engineer from the db, what would happen to this log
 * or in future, if the equipment gets decommissioned, should we delete the equipment from the db ? if that is the case,
 * should we keep this log with equipment id as null or retain the equipment id here ?
 * research into this
 *
 * logs cannot be lost
 * consider safe deleting engineers, equipments
 */

@Entity
@Table(name = "maintenance_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String description;
    private String status;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment; //todo : shouldnt this be not null? look into it

    @ManyToOne
    @JoinColumn(name = "engineer_id")
    private Engineer engineer; //todo : shouldnt this be not null? look into it

    @ManyToOne
    @JoinColumn(name = "workflow_process_id")
    private WorkflowProcess workflowProcess; //todo : shouldnt this be not null? look into it

}
