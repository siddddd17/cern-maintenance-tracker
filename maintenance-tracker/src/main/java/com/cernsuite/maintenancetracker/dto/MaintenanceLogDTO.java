package com.cernsuite.maintenancetracker.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceLogDTO {
    private Long id;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Equipment ID is required")
    private Long equipmentId;

    @NotNull(message = "Engineer ID is required")
    private Long engineerId;

    private Long workflowProcessId;
}
