package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.EntityAuthorityService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.EntityAuthorityDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.EntityAuthority}.
 */
@RestController
@RequestMapping("/api")
public class EntityAuthorityResource {

    private final Logger log = LoggerFactory.getLogger(EntityAuthorityResource.class);

    private static final String ENTITY_NAME = "entityAuthority";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EntityAuthorityService entityAuthorityService;

    public EntityAuthorityResource(EntityAuthorityService entityAuthorityService) {
        this.entityAuthorityService = entityAuthorityService;
    }

    /**
     * {@code POST  /entity-authorities} : Create a new entityAuthority.
     *
     * @param entityAuthorityDTO the entityAuthorityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entityAuthorityDTO, or with status {@code 400 (Bad Request)} if the entityAuthority has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/entity-authorities")
    public ResponseEntity<EntityAuthorityDTO> createEntityAuthority(@RequestBody EntityAuthorityDTO entityAuthorityDTO) throws URISyntaxException {
        log.debug("REST request to save EntityAuthority : {}", entityAuthorityDTO);
        if (entityAuthorityDTO.getId() != null) {
            throw new BadRequestAlertException("A new entityAuthority cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EntityAuthorityDTO result = entityAuthorityService.save(entityAuthorityDTO);
        return ResponseEntity.created(new URI("/api/entity-authorities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /entity-authorities} : Updates an existing entityAuthority.
     *
     * @param entityAuthorityDTO the entityAuthorityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entityAuthorityDTO,
     * or with status {@code 400 (Bad Request)} if the entityAuthorityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entityAuthorityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/entity-authorities")
    public ResponseEntity<EntityAuthorityDTO> updateEntityAuthority(@RequestBody EntityAuthorityDTO entityAuthorityDTO) throws URISyntaxException {
        log.debug("REST request to update EntityAuthority : {}", entityAuthorityDTO);
        if (entityAuthorityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EntityAuthorityDTO result = entityAuthorityService.save(entityAuthorityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, entityAuthorityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /entity-authorities} : get all the entityAuthorities.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entityAuthorities in body.
     */
    @GetMapping("/entity-authorities")
    public List<EntityAuthorityDTO> getAllEntityAuthorities() {
        log.debug("REST request to get all EntityAuthorities");
        return entityAuthorityService.findAll();
    }

    /**
     * {@code GET  /entity-authorities/:id} : get the "id" entityAuthority.
     *
     * @param id the id of the entityAuthorityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entityAuthorityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/entity-authorities/{id}")
    public ResponseEntity<EntityAuthorityDTO> getEntityAuthority(@PathVariable Long id) {
        log.debug("REST request to get EntityAuthority : {}", id);
        Optional<EntityAuthorityDTO> entityAuthorityDTO = entityAuthorityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entityAuthorityDTO);
    }

    /**
     * {@code DELETE  /entity-authorities/:id} : delete the "id" entityAuthority.
     *
     * @param id the id of the entityAuthorityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/entity-authorities/{id}")
    public ResponseEntity<Void> deleteEntityAuthority(@PathVariable Long id) {
        log.debug("REST request to delete EntityAuthority : {}", id);
        entityAuthorityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
