package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.EntityAttributesService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.EntityAttributesDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.EntityAttributes}.
 */
@RestController
@RequestMapping("/api")
public class EntityAttributesResource {

    private final Logger log = LoggerFactory.getLogger(EntityAttributesResource.class);

    private static final String ENTITY_NAME = "entityAttributes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EntityAttributesService entityAttributesService;

    public EntityAttributesResource(EntityAttributesService entityAttributesService) {
        this.entityAttributesService = entityAttributesService;
    }

    /**
     * {@code POST  /entity-attributes} : Create a new entityAttributes.
     *
     * @param entityAttributesDTO the entityAttributesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityAttributesDTO, or with status {@code 400 (Bad Request)} if the entityAttributes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/entity-attributes")
    public ResponseEntity<EntityAttributesDTO> createEntityAttributes(@RequestBody EntityAttributesDTO entityAttributesDTO) throws URISyntaxException {
        log.debug("REST request to save EntityAttributes : {}", entityAttributesDTO);
        if (entityAttributesDTO.getId() != null) {
            throw new BadRequestAlertException("A new entityAttributes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EntityAttributesDTO result = entityAttributesService.save(entityAttributesDTO);
        return ResponseEntity.created(new URI("/api/entity-attributes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /entity-attributes} : Updates an existing entityAttributes.
     *
     * @param entityAttributesDTO the entityAttributesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityAttributesDTO,
     * or with status {@code 400 (Bad Request)} if the entityAttributesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityAttributesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/entity-attributes")
    public ResponseEntity<EntityAttributesDTO> updateEntityAttributes(@RequestBody EntityAttributesDTO entityAttributesDTO) throws URISyntaxException {
        log.debug("REST request to update EntityAttributes : {}", entityAttributesDTO);
        if (entityAttributesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EntityAttributesDTO result = entityAttributesService.save(entityAttributesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, entityAttributesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /entity-attributes} : get all the entityAttributes.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityAttributes in body.
     */
    @GetMapping("/entity-attributes")
    public List<EntityAttributesDTO> getAllEntityAttributes() {
        log.debug("REST request to get all EntityAttributes");
        return entityAttributesService.findAll();
    }

    /**
     * {@code GET  /entity-attributes/:id} : get the "id" entityAttributes.
     *
     * @param id the id of the entityAttributesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityAttributesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/entity-attributes/{id}")
    public ResponseEntity<EntityAttributesDTO> getEntityAttributes(@PathVariable Long id) {
        log.debug("REST request to get EntityAttributes : {}", id);
        Optional<EntityAttributesDTO> entityAttributesDTO = entityAttributesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entityAttributesDTO);
    }

    /**
     * {@code DELETE  /entity-attributes/:id} : delete the "id" entityAttributes.
     *
     * @param id the id of the entityAttributesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/entity-attributes/{id}")
    public ResponseEntity<Void> deleteEntityAttributes(@PathVariable Long id) {
        log.debug("REST request to delete EntityAttributes : {}", id);
        entityAttributesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
