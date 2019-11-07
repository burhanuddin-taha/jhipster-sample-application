package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.BusinessTypeService;
import com.mycompany.myapp.domain.BusinessType;
import com.mycompany.myapp.repository.BusinessTypeRepository;
import com.mycompany.myapp.service.dto.BusinessTypeDTO;
import com.mycompany.myapp.service.mapper.BusinessTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link BusinessType}.
 */
@Service
@Transactional
public class BusinessTypeServiceImpl implements BusinessTypeService {

    private final Logger log = LoggerFactory.getLogger(BusinessTypeServiceImpl.class);

    private final BusinessTypeRepository businessTypeRepository;

    private final BusinessTypeMapper businessTypeMapper;

    public BusinessTypeServiceImpl(BusinessTypeRepository businessTypeRepository, BusinessTypeMapper businessTypeMapper) {
        this.businessTypeRepository = businessTypeRepository;
        this.businessTypeMapper = businessTypeMapper;
    }

    /**
     * Save a businessType.
     *
     * @param businessTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BusinessTypeDTO save(BusinessTypeDTO businessTypeDTO) {
        log.debug("Request to save BusinessType : {}", businessTypeDTO);
        BusinessType businessType = businessTypeMapper.toEntity(businessTypeDTO);
        businessType = businessTypeRepository.save(businessType);
        return businessTypeMapper.toDto(businessType);
    }

    /**
     * Get all the businessTypes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<BusinessTypeDTO> findAll() {
        log.debug("Request to get all BusinessTypes");
        return businessTypeRepository.findAll().stream()
            .map(businessTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one businessType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BusinessTypeDTO> findOne(Long id) {
        log.debug("Request to get BusinessType : {}", id);
        return businessTypeRepository.findById(id)
            .map(businessTypeMapper::toDto);
    }

    /**
     * Delete the businessType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BusinessType : {}", id);
        businessTypeRepository.deleteById(id);
    }
}
