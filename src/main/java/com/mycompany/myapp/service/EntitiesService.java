package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EntitiesDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Entities}.
 */
public interface EntitiesService {

    /**
     * Save a entities.
     *
     * @param entitiesDTO the entity to save.
     * @return the persisted entity.
     */
    EntitiesDTO save(EntitiesDTO entitiesDTO);

    /**
     * Get all the entities.
     *
     * @return the list of entities.
     */
    List<EntitiesDTO> findAll();


    /**
     * Get the "id" entities.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EntitiesDTO> findOne(Long id);

    /**
     * Delete the "id" entities.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
