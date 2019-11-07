package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.EntityAuthorityService;
import com.mycompany.myapp.domain.EntityAuthority;
import com.mycompany.myapp.repository.EntityAuthorityRepository;
import com.mycompany.myapp.service.dto.EntityAuthorityDTO;
import com.mycompany.myapp.service.mapper.EntityAuthorityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EntityAuthority}.
 */
@Service
@Transactional
public class EntityAuthorityServiceImpl implements EntityAuthorityService {

    private final Logger log = LoggerFactory.getLogger(EntityAuthorityServiceImpl.class);

    private final EntityAuthorityRepository entityAuthorityRepository;

    private final EntityAuthorityMapper entityAuthorityMapper;

    public EntityAuthorityServiceImpl(EntityAuthorityRepository entityAuthorityRepository, EntityAuthorityMapper entityAuthorityMapper) {
        this.entityAuthorityRepository = entityAuthorityRepository;
        this.entityAuthorityMapper = entityAuthorityMapper;
    }

    /**
     * Save a entityAuthority.
     *
     * @param entityAuthorityDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EntityAuthorityDTO save(EntityAuthorityDTO entityAuthorityDTO) {
        log.debug("Request to save EntityAuthority : {}", entityAuthorityDTO);
        EntityAuthority entityAuthority = entityAuthorityMapper.toEntity(entityAuthorityDTO);
        entityAuthority = entityAuthorityRepository.save(entityAuthority);
        return entityAuthorityMapper.toDto(entityAuthority);
    }

    /**
     * Get all the entityAuthorities.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EntityAuthorityDTO> findAll() {
        log.debug("Request to get all EntityAuthorities");
        return entityAuthorityRepository.findAll().stream()
            .map(entityAuthorityMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one entityAuthority by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EntityAuthorityDTO> findOne(Long id) {
        log.debug("Request to get EntityAuthority : {}", id);
        return entityAuthorityRepository.findById(id)
            .map(entityAuthorityMapper::toDto);
    }

    /**
     * Delete the entityAuthority by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EntityAuthority : {}", id);
        entityAuthorityRepository.deleteById(id);
    }
}
