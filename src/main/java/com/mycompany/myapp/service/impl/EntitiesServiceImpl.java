package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EntitiesService;
import com.mycompany.myapp.domain.Entities;
import com.mycompany.myapp.repository.EntitiesRepository;
import com.mycompany.myapp.service.dto.EntitiesDTO;
import com.mycompany.myapp.service.mapper.EntitiesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Entities}.
 */
@Service
@Transactional
public class EntitiesServiceImpl implements EntitiesService {

    private final Logger log = LoggerFactory.getLogger(EntitiesServiceImpl.class);

    private final EntitiesRepository entitiesRepository;

    private final EntitiesMapper entitiesMapper;

    public EntitiesServiceImpl(EntitiesRepository entitiesRepository, EntitiesMapper entitiesMapper) {
        this.entitiesRepository = entitiesRepository;
        this.entitiesMapper = entitiesMapper;
    }

    /**
     * Save a entities.
     *
     * @param entitiesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EntitiesDTO save(EntitiesDTO entitiesDTO) {
        log.debug("Request to save Entities : {}", entitiesDTO);
        Entities entities = entitiesMapper.toEntity(entitiesDTO);
        entities = entitiesRepository.save(entities);
        return entitiesMapper.toDto(entities);
    }

    /**
     * Get all the entities.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EntitiesDTO> findAll() {
        log.debug("Request to get all Entities");
        return entitiesRepository.findAll().stream()
            .map(entitiesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
    *  Get all the entities where Type is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<EntitiesDTO> findAllWhereTypeIsNull() {
        log.debug("Request to get all entities where Type is null");
        return StreamSupport
            .stream(entitiesRepository.findAll().spliterator(), false)
            .filter(entities -> entities.getType() == null)
            .map(entitiesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one entities by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EntitiesDTO> findOne(Long id) {
        log.debug("Request to get Entities : {}", id);
        return entitiesRepository.findById(id)
            .map(entitiesMapper::toDto);
    }

    /**
     * Delete the entities by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Entities : {}", id);
        entitiesRepository.deleteById(id);
    }
}
