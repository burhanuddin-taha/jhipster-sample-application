package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.EntitiesService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.EntitiesDTO;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Entities}.
 */
@RestController
@RequestMapping("/api")
public class EntitiesResource {

    private final Logger log = LoggerFactory.getLogger(EntitiesResource.class);

    private static final String ENTITY_NAME = "entities";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EntitiesService entitiesService;

    public EntitiesResource(EntitiesService entitiesService) {
        this.entitiesService = entitiesService;
    }

    /**
     * {@code POST  /entities} : Create a new entities.
     *
     * @param entitiesDTO the entitiesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entitiesDTO, or with status {@code 400 (Bad Request)} if the entities has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/entities")
    public ResponseEntity<EntitiesDTO> createEntities(@RequestBody EntitiesDTO entitiesDTO) throws URISyntaxException {
        log.debug("REST request to save Entities : {}", entitiesDTO);
        if (entitiesDTO.getId() != null) {
            throw new BadRequestAlertException("A new entities cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EntitiesDTO result = entitiesService.save(entitiesDTO);
        return ResponseEntity.created(new URI("/api/entities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /entities} : Updates an existing entities.
     *
     * @param entitiesDTO the entitiesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entitiesDTO,
     * or with status {@code 400 (Bad Request)} if the entitiesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entitiesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/entities")
    public ResponseEntity<EntitiesDTO> updateEntities(@RequestBody EntitiesDTO entitiesDTO) throws URISyntaxException {
        log.debug("REST request to update Entities : {}", entitiesDTO);
        if (entitiesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EntitiesDTO result = entitiesService.save(entitiesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, entitiesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /entities} : get all the entities.
     *

     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entities in body.
     */
    @GetMapping("/entities")
    public List<EntitiesDTO> getAllEntities(@RequestParam(required = false) String filter) {
        if ("type-is-null".equals(filter)) {
            log.debug("REST request to get all Entitiess where type is null");
            return entitiesService.findAllWhereTypeIsNull();
        }
        log.debug("REST request to get all Entities");
        return entitiesService.findAll();
    }

    /**
     * {@code GET  /entities/:id} : get the "id" entities.
     *
     * @param id the id of the entitiesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entitiesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/entities/{id}")
    public ResponseEntity<EntitiesDTO> getEntities(@PathVariable Long id) {
        log.debug("REST request to get Entities : {}", id);
        Optional<EntitiesDTO> entitiesDTO = entitiesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entitiesDTO);
    }

    /**
     * {@code DELETE  /entities/:id} : delete the "id" entities.
     *
     * @param id the id of the entitiesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/entities/{id}")
    public ResponseEntity<Void> deleteEntities(@PathVariable Long id) {
        log.debug("REST request to delete Entities : {}", id);
        entitiesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
