package com.cernsuite.maintenancetracker.model;

import com.cernsuite.maintenancetracker.model.enums.EngineerStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "engineer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Engineer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String role;

    @Enumerated(EnumType.STRING)
    private EngineerStatus status = EngineerStatus.ACTIVE;

    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "engineer", cascade = CascadeType.ALL)
    private List<MaintenanceLog> maintenanceLogs;
}
