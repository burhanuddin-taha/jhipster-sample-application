package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.BusinessTypeService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.BusinessTypeDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.BusinessType}.
 */
@RestController
@RequestMapping("/api")
public class BusinessTypeResource {

    private final Logger log = LoggerFactory.getLogger(BusinessTypeResource.class);

    private static final String ENTITY_NAME = "businessType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BusinessTypeService businessTypeService;

    public BusinessTypeResource(BusinessTypeService businessTypeService) {
        this.businessTypeService = businessTypeService;
    }

    /**
     * {@code POST  /business-types} : Create a new businessType.
     *
     * @param businessTypeDTO the businessTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new businessTypeDTO, or with status {@code 400 (Bad Request)} if the businessType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/business-types")
    public ResponseEntity<BusinessTypeDTO> createBusinessType(@RequestBody BusinessTypeDTO businessTypeDTO) throws URISyntaxException {
        log.debug("REST request to save BusinessType : {}", businessTypeDTO);
        if (businessTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new businessType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BusinessTypeDTO result = businessTypeService.save(businessTypeDTO);
        return ResponseEntity.created(new URI("/api/business-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /business-types} : Updates an existing businessType.
     *
     * @param businessTypeDTO the businessTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated businessTypeDTO,
     * or with status {@code 400 (Bad Request)} if the businessTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the businessTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/business-types")
    public ResponseEntity<BusinessTypeDTO> updateBusinessType(@RequestBody BusinessTypeDTO businessTypeDTO) throws URISyntaxException {
        log.debug("REST request to update BusinessType : {}", businessTypeDTO);
        if (businessTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BusinessTypeDTO result = businessTypeService.save(businessTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, businessTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /business-types} : get all the businessTypes.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of businessTypes in body.
     */
    @GetMapping("/business-types")
    public List<BusinessTypeDTO> getAllBusinessTypes() {
        log.debug("REST request to get all BusinessTypes");
        return businessTypeService.findAll();
    }

    /**
     * {@code GET  /business-types/:id} : get the "id" businessType.
     *
     * @param id the id of the businessTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the businessTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/business-types/{id}")
    public ResponseEntity<BusinessTypeDTO> getBusinessType(@PathVariable Long id) {
        log.debug("REST request to get BusinessType : {}", id);
        Optional<BusinessTypeDTO> businessTypeDTO = businessTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(businessTypeDTO);
    }

    /**
     * {@code DELETE  /business-types/:id} : delete the "id" businessType.
     *
     * @param id the id of the businessTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/business-types/{id}")
    public ResponseEntity<Void> deleteBusinessType(@PathVariable Long id) {
        log.debug("REST request to delete BusinessType : {}", id);
        businessTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
