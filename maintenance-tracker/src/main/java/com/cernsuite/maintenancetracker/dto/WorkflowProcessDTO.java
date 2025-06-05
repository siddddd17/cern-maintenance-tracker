package com.cernsuite.maintenancetracker.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowProcessDTO {
    private Long id;

    @NotBlank(message = "Process Instance ID is required")
    private String processInstanceId;

    private LocalDate startDate;
    private LocalDate endDate;

    @NotBlank(message = "Status is required")
    private String status;
}
