package com.cernsuite.maintenancetracker.service;

import com.cernsuite.maintenancetracker.dto.EngineerDTO;
import com.cernsuite.maintenancetracker.mapper.EngineerMapper;
import com.cernsuite.maintenancetracker.model.Engineer;
import com.cernsuite.maintenancetracker.repository.EngineerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

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
        Engineer existing = engineerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Engineer not found"));
        engineerMapper.updateEntityFromDto(dto, existing);
        return engineerMapper.toDTO(engineerRepository.save(existing));
    }

    public Page<EngineerDTO> getAll(Pageable pageable) {
        return engineerRepository.findAll(pageable).map(engineerMapper::toDTO);
    }

    public EngineerDTO getById(Long id) {
        Engineer engineer = engineerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Engineer not found"));
        return engineerMapper.toDTO(engineer);
    }

    public void delete(Long id) {
        if (!engineerRepository.existsById(id)) {
            throw new EntityNotFoundException("Engineer not found");
        }
        engineerRepository.deleteById(id);
    }
}
