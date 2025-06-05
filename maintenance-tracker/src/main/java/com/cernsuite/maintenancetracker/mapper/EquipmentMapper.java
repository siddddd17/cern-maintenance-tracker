package com.cernsuite.maintenancetracker.mapper;

import com.cernsuite.maintenancetracker.dto.EquipmentDTO;
import com.cernsuite.maintenancetracker.model.Equipment;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {
    EquipmentDTO toDTO(Equipment equipment);
    Equipment toEntity(EquipmentDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true) // Ignore the ID when updating an existing entity
    void updateEntityFromDto(EquipmentDTO dto, @MappingTarget Equipment entity);
}
