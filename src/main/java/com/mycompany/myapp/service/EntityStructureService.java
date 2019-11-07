package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EntityStructureDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.EntityStructure}.
 */
public interface EntityStructureService {

    /**
     * Save a entityStructure.
     *
     * @param entityStructureDTO the entity to save.
     * @return the persisted entity.
     */
    EntityStructureDTO save(EntityStructureDTO entityStructureDTO);

    /**
     * Get all the entityStructures.
     *
     * @return the list of entities.
     */
    List<EntityStructureDTO> findAll();


    /**
     * Get the "id" entityStructure.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EntityStructureDTO> findOne(Long id);

    /**
     * Delete the "id" entityStructure.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
