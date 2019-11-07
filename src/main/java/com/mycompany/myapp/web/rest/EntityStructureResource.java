package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.EntityStructureService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.EntityStructureDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.EntityStructure}.
 */
@RestController
@RequestMapping("/api")
public class EntityStructureResource {

    private final Logger log = LoggerFactory.getLogger(EntityStructureResource.class);

    private static final String ENTITY_NAME = "entityStructure";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EntityStructureService entityStructureService;

    public EntityStructureResource(EntityStructureService entityStructureService) {
        this.entityStructureService = entityStructureService;
    }

    /**
     * {@code POST  /entity-structures} : Create a new entityStructure.
     *
     * @param entityStructureDTO the entityStructureDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityStructureDTO, or with status {@code 400 (Bad Request)} if the entityStructure has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/entity-structures")
    public ResponseEntity<EntityStructureDTO> createEntityStructure(@RequestBody EntityStructureDTO entityStructureDTO) throws URISyntaxException {
        log.debug("REST request to save EntityStructure : {}", entityStructureDTO);
        if (entityStructureDTO.getId() != null) {
            throw new BadRequestAlertException("A new entityStructure cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EntityStructureDTO result = entityStructureService.save(entityStructureDTO);
        return ResponseEntity.created(new URI("/api/entity-structures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /entity-structures} : Updates an existing entityStructure.
     *
     * @param entityStructureDTO the entityStructureDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityStructureDTO,
     * or with status {@code 400 (Bad Request)} if the entityStructureDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityStructureDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/entity-structures")
    public ResponseEntity<EntityStructureDTO> updateEntityStructure(@RequestBody EntityStructureDTO entityStructureDTO) throws URISyntaxException {
        log.debug("REST request to update EntityStructure : {}", entityStructureDTO);
        if (entityStructureDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EntityStructureDTO result = entityStructureService.save(entityStructureDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, entityStructureDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /entity-structures} : get all the entityStructures.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityStructures in body.
     */
    @GetMapping("/entity-structures")
    public List<EntityStructureDTO> getAllEntityStructures() {
        log.debug("REST request to get all EntityStructures");
        return entityStructureService.findAll();
    }

    /**
     * {@code GET  /entity-structures/:id} : get the "id" entityStructure.
     *
     * @param id the id of the entityStructureDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityStructureDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/entity-structures/{id}")
    public ResponseEntity<EntityStructureDTO> getEntityStructure(@PathVariable Long id) {
        log.debug("REST request to get EntityStructure : {}", id);
        Optional<EntityStructureDTO> entityStructureDTO = entityStructureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entityStructureDTO);
    }

    /**
     * {@code DELETE  /entity-structures/:id} : delete the "id" entityStructure.
     *
     * @param id the id of the entityStructureDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/entity-structures/{id}")
    public ResponseEntity<Void> deleteEntityStructure(@PathVariable Long id) {
        log.debug("REST request to delete EntityStructure : {}", id);
        entityStructureService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
