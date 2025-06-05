package com.cernsuite.maintenancetracker.mapper;

import com.cernsuite.maintenancetracker.dto.EngineerDTO;
import com.cernsuite.maintenancetracker.model.Engineer;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EngineerMapper {
    EngineerDTO toDTO(Engineer engineer);
    Engineer toEntity(EngineerDTO dto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(EngineerDTO dto, @MappingTarget Engineer entity);
}

