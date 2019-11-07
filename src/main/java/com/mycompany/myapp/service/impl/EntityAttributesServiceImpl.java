package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EntityAttributesService;
import com.mycompany.myapp.domain.EntityAttributes;
import com.mycompany.myapp.repository.EntityAttributesRepository;
import com.mycompany.myapp.service.dto.EntityAttributesDTO;
import com.mycompany.myapp.service.mapper.EntityAttributesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EntityAttributes}.
 */
@Service
@Transactional
public class EntityAttributesServiceImpl implements EntityAttributesService {

    private final Logger log = LoggerFactory.getLogger(EntityAttributesServiceImpl.class);

    private final EntityAttributesRepository entityAttributesRepository;

    private final EntityAttributesMapper entityAttributesMapper;

    public EntityAttributesServiceImpl(EntityAttributesRepository entityAttributesRepository, EntityAttributesMapper entityAttributesMapper) {
        this.entityAttributesRepository = entityAttributesRepository;
        this.entityAttributesMapper = entityAttributesMapper;
    }

    /**
     * Save a entityAttributes.
     *
     * @param entityAttributesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EntityAttributesDTO save(EntityAttributesDTO entityAttributesDTO) {
        log.debug("Request to save EntityAttributes : {}", entityAttributesDTO);
        EntityAttributes entityAttributes = entityAttributesMapper.toEntity(entityAttributesDTO);
        entityAttributes = entityAttributesRepository.save(entityAttributes);
        return entityAttributesMapper.toDto(entityAttributes);
    }

    /**
     * Get all the entityAttributes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EntityAttributesDTO> findAll() {
        log.debug("Request to get all EntityAttributes");
        return entityAttributesRepository.findAll().stream()
            .map(entityAttributesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one entityAttributes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EntityAttributesDTO> findOne(Long id) {
        log.debug("Request to get EntityAttributes : {}", id);
        return entityAttributesRepository.findById(id)
            .map(entityAttributesMapper::toDto);
    }

    /**
     * Delete the entityAttributes by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EntityAttributes : {}", id);
        entityAttributesRepository.deleteById(id);
    }
}
