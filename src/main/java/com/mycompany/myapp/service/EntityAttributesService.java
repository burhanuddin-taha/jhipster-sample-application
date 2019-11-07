package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EntityAttributesDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.EntityAttributes}.
 */
public interface EntityAttributesService {

    /**
     * Save a entityAttributes.
     *
     * @param entityAttributesDTO the entity to save.
     * @return the persisted entity.
     */
    EntityAttributesDTO save(EntityAttributesDTO entityAttributesDTO);

    /**
     * Get all the entityAttributes.
     *
     * @return the list of entities.
     */
    List<EntityAttributesDTO> findAll();


    /**
     * Get the "id" entityAttributes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EntityAttributesDTO> findOne(Long id);

    /**
     * Delete the "id" entityAttributes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
