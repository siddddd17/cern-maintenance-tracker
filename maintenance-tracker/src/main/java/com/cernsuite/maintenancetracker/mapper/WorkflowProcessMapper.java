package com.cernsuite.maintenancetracker.mapper;

import com.cernsuite.maintenancetracker.dto.WorkflowProcessDTO;
import com.cernsuite.maintenancetracker.model.WorkflowProcess;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkflowProcessMapper {
    WorkflowProcessDTO toDTO(WorkflowProcess process);
    WorkflowProcess toEntity(WorkflowProcessDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true) // Ignore the ID when updating an existing entity
    void updateEntityFromDto(WorkflowProcessDTO dto, @MappingTarget WorkflowProcess entity);
}