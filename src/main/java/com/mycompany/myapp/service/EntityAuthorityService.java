package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.EntityAuthorityDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.EntityAuthority}.
 */
public interface EntityAuthorityService {

    /**
     * Save a entityAuthority.
     *
     * @param entityAuthorityDTO the entity to save.
     * @return the persisted entity.
     */
    EntityAuthorityDTO save(EntityAuthorityDTO entityAuthorityDTO);

    /**
     * Get all the entityAuthorities.
     *
     * @return the list of entities.
     */
    List<EntityAuthorityDTO> findAll();


    /**
     * Get the "id" entityAuthority.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EntityAuthorityDTO> findOne(Long id);

    /**
     * Delete the "id" entityAuthority.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
