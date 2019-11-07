package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EntityStructureService;
import com.mycompany.myapp.domain.EntityStructure;
import com.mycompany.myapp.repository.EntityStructureRepository;
import com.mycompany.myapp.service.dto.EntityStructureDTO;
import com.mycompany.myapp.service.mapper.EntityStructureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EntityStructure}.
 */
@Service
@Transactional
public class EntityStructureServiceImpl implements EntityStructureService {

    private final Logger log = LoggerFactory.getLogger(EntityStructureServiceImpl.class);

    private final EntityStructureRepository entityStructureRepository;

    private final EntityStructureMapper entityStructureMapper;

    public EntityStructureServiceImpl(EntityStructureRepository entityStructureRepository, EntityStructureMapper entityStructureMapper) {
        this.entityStructureRepository = entityStructureRepository;
        this.entityStructureMapper = entityStructureMapper;
    }

    /**
     * Save a entityStructure.
     *
     * @param entityStructureDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EntityStructureDTO save(EntityStructureDTO entityStructureDTO) {
        log.debug("Request to save EntityStructure : {}", entityStructureDTO);
        EntityStructure entityStructure = entityStructureMapper.toEntity(entityStructureDTO);
        entityStructure = entityStructureRepository.save(entityStructure);
        return entityStructureMapper.toDto(entityStructure);
    }

    /**
     * Get all the entityStructures.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EntityStructureDTO> findAll() {
        log.debug("Request to get all EntityStructures");
        return entityStructureRepository.findAll().stream()
            .map(entityStructureMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one entityStructure by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EntityStructureDTO> findOne(Long id) {
        log.debug("Request to get EntityStructure : {}", id);
        return entityStructureRepository.findById(id)
            .map(entityStructureMapper::toDto);
    }

    /**
     * Delete the entityStructure by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EntityStructure : {}", id);
        entityStructureRepository.deleteById(id);
    }
}
