package com.cernsuite.maintenancetracker.dto;

import lombok.Data;

import java.util.List;

@Data
public class EngineerWithLogsDTO {
    private Long id;
    private String name;
    private String email;
    private String role;
    private List<MaintenanceLogDTO> maintenanceLogs;
}
