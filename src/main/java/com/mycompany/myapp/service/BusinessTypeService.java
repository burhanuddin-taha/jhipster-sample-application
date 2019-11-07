package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.BusinessTypeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.BusinessType}.
 */
public interface BusinessTypeService {

    /**
     * Save a businessType.
     *
     * @param businessTypeDTO the entity to save.
     * @return the persisted entity.
     */
    BusinessTypeDTO save(BusinessTypeDTO businessTypeDTO);

    /**
     * Get all the businessTypes.
     *
     * @return the list of entities.
     */
    List<BusinessTypeDTO> findAll();


    /**
     * Get the "id" businessType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BusinessTypeDTO> findOne(Long id);

    /**
     * Delete the "id" businessType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
