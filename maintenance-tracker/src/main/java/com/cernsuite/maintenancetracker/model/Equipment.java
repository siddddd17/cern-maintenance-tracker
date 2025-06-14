package com.cernsuite.maintenancetracker.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "equipment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String type;
    private String serialNumber; //todo : make this a unique feild
    private LocalDate installationDate;
    private String status; // e.g., ACTIVE, UNDER_MAINTENANCE, RETIRED //todo : Create enums for this

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    private List<MaintenanceLog> maintenanceLogs;
}
