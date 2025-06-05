package com.cernsuite.maintenancetracker.service;

import com.cernsuite.maintenancetracker.dto.EngineerDTO;
import com.cernsuite.maintenancetracker.mapper.EngineerMapper;
import com.cernsuite.maintenancetracker.model.Engineer;
import com.cernsuite.maintenancetracker.model.enums.EngineerStatus;
import com.cernsuite.maintenancetracker.repository.EngineerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EngineerService {

    private final EngineerRepository engineerRepository;
    private final EngineerMapper engineerMapper;

    public EngineerDTO create(EngineerDTO dto) {
        Engineer engineer = engineerMapper.toEntity(dto);
        return engineerMapper.toDTO(engineerRepository.save(engineer));
    }

    public EngineerDTO update(Long id, EngineerDTO dto) {
        Engineer existing = engineerRepository.findActiveById(id) //todo: do we need to return active engineers here? or all the engineers?
                .orElseThrow(() -> new EntityNotFoundException("Engineer not found"));
        engineerMapper.updateEntityFromDto(dto, existing);
        return engineerMapper.toDTO(engineerRepository.save(existing));
    }

    public Page<?> getAll(Pageable pageable, boolean includeMaintenanceLogs) {
        Page<Engineer> engineers = engineerRepository.findAllActive(pageable);
        return includeMaintenanceLogs
                ? engineers.map(engineerMapper::toWithLogsDTO)
                : engineers.map(engineerMapper::toDTO);
    }

    public Object getById(Long id, boolean includeLogs) {
        Engineer engineer = engineerRepository.findActiveById(id)
                .orElseThrow(() -> new EntityNotFoundException("Engineer not found"));

        return includeLogs
                ? engineerMapper.toWithLogsDTO(engineer)
                : engineerMapper.toDTO(engineer);
    }

    public void delete(Long id) {
        Engineer engineer = engineerRepository.findActiveById(id)
                .orElseThrow(() -> new EntityNotFoundException("Engineer not found"));

        engineer.setStatus(EngineerStatus.RETIRED);
        engineer.setDeletedAt(LocalDateTime.now());

        engineerRepository.save(engineer);
    }
}
