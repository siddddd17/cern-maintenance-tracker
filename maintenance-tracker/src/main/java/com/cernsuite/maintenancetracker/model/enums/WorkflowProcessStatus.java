package com.cernsuite.maintenancetracker.model.enums;

public enum WorkflowProcessStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED,
    ON_HOLD,
    FAILED;

    public static WorkflowProcessStatus fromString(String status) {
        for (WorkflowProcessStatus s : WorkflowProcessStatus.values()) {
            if (s.name().equalsIgnoreCase(status)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + status);
    }
}
