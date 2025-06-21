package com.cernsuite.maintenancetracker.service;

import com.cernsuite.maintenancetracker.dto.EquipmentDTO;
import com.cernsuite.maintenancetracker.mapper.EquipmentMapper;
import com.cernsuite.maintenancetracker.model.Equipment;
import com.cernsuite.maintenancetracker.model.enums.EquipmentStatus;
import com.cernsuite.maintenancetracker.repository.EquipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final EquipmentMapper equipmentMapper;

    public EquipmentDTO create(EquipmentDTO dto) {

        if (dto.getInstallationDate() == null) {
            dto.setInstallationDate(java.time.LocalDate.now());
        }
        if (dto.getStatus() == null || dto.getStatus().isEmpty() || !dto.getStatus().equalsIgnoreCase(EquipmentStatus.OPERATIONAL.name())) {
            dto.setStatus(EquipmentStatus.OPERATIONAL.name());
        }

        Equipment equipment = equipmentMapper.toEntity(dto);
        return equipmentMapper.toDTO(equipmentRepository.save(equipment));
    }

    public EquipmentDTO update(Long id, EquipmentDTO dto) {
        Equipment existing = equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipment not found"));
        equipmentMapper.updateEntityFromDto(dto, existing);
        return equipmentMapper.toDTO(equipmentRepository.save(existing));
    }

    public Page<EquipmentDTO> getAll(Pageable pageable) {
        return equipmentRepository.findAll(pageable).map(equipmentMapper::toDTO);
    }

    public EquipmentDTO getById(Long id) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipment not found"));
        return equipmentMapper.toDTO(equipment);
    }

    public void delete(Long id) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipment not found"));
        equipment.setStatus(EquipmentStatus.DECOMMISSIONED.name());
        equipmentRepository.save(equipment);
    }
}
