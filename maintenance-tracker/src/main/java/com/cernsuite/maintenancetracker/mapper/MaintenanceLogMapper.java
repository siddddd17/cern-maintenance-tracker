package com.cernsuite.maintenancetracker.mapper;

import com.cernsuite.maintenancetracker.dto.MaintenanceLogDTO;
import com.cernsuite.maintenancetracker.model.Equipment;
import com.cernsuite.maintenancetracker.model.Engineer;
import com.cernsuite.maintenancetracker.model.MaintenanceLog;
import com.cernsuite.maintenancetracker.model.WorkflowProcess;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MaintenanceLogMapper {

    // Entity → DTO: Extract IDs from nested objects
    @Mapping(source = "engineer.id", target = "engineerId")
    @Mapping(source = "equipment.id", target = "equipmentId")
    @Mapping(source = "workflowProcess.id", target = "workflowProcessId")
    MaintenanceLogDTO toDTO(MaintenanceLog log);

    // DTO → Entity: Build minimal references using only their IDs
    @Mapping(target = "engineer", source = "engineerId")
    @Mapping(target = "equipment", source = "equipmentId")
    @Mapping(target = "workflowProcess", source = "workflowProcessId")
    MaintenanceLog toEntity(MaintenanceLogDTO dto);

    // Helper methods to instantiate nested references
    default Engineer mapEngineer(Long id) {
        if (id == null) return null;
        Engineer e = new Engineer();
        e.setId(id);
        return e;
    }

    default Equipment mapEquipment(Long id) {
        if (id == null) return null;
        Equipment e = new Equipment();
        e.setId(id);
        return e;
    }

    default WorkflowProcess mapWorkflowProcess(Long id) {
        if (id == null) return null;
        WorkflowProcess wp = new WorkflowProcess();
        wp.setId(id);
        return wp;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true) // Ignore ID as we're updating an existing entity
    @Mapping(target = "engineer", source = "engineerId")
    @Mapping(target = "equipment", source = "equipmentId")
    @Mapping(target = "workflowProcess", source = "workflowProcessId")
    void updateEntityFromDto(MaintenanceLogDTO dto, @MappingTarget MaintenanceLog entity);

}
