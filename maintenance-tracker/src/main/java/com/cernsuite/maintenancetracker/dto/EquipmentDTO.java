package com.cernsuite.maintenancetracker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Type is required")
    private String type;

    @NotBlank(message = "Serial number is required")
    private String serialNumber;

    private LocalDate installationDate;

    @NotBlank(message = "Status is required")
    private String status;
}
