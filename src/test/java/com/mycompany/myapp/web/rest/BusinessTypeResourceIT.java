package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.BusinessType;
import com.mycompany.myapp.repository.BusinessTypeRepository;
import com.mycompany.myapp.service.BusinessTypeService;
import com.mycompany.myapp.service.dto.BusinessTypeDTO;
import com.mycompany.myapp.service.mapper.BusinessTypeMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link BusinessTypeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class BusinessTypeResourceIT {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    @Autowired
    private BusinessTypeRepository businessTypeRepository;

    @Autowired
    private BusinessTypeMapper businessTypeMapper;

    @Autowired
    private BusinessTypeService businessTypeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restBusinessTypeMockMvc;

    private BusinessType businessType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BusinessTypeResource businessTypeResource = new BusinessTypeResource(businessTypeService);
        this.restBusinessTypeMockMvc = MockMvcBuilders.standaloneSetup(businessTypeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BusinessType createEntity(EntityManager em) {
        BusinessType businessType = new BusinessType()
            .type(DEFAULT_TYPE);
        return businessType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BusinessType createUpdatedEntity(EntityManager em) {
        BusinessType businessType = new BusinessType()
            .type(UPDATED_TYPE);
        return businessType;
    }

    @BeforeEach
    public void initTest() {
        businessType = createEntity(em);
    }

    @Test
    @Transactional
    public void createBusinessType() throws Exception {
        int databaseSizeBeforeCreate = businessTypeRepository.findAll().size();

        // Create the BusinessType
        BusinessTypeDTO businessTypeDTO = businessTypeMapper.toDto(businessType);
        restBusinessTypeMockMvc.perform(post("/api/business-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the BusinessType in the database
        List<BusinessType> businessTypeList = businessTypeRepository.findAll();
        assertThat(businessTypeList).hasSize(databaseSizeBeforeCreate + 1);
        BusinessType testBusinessType = businessTypeList.get(businessTypeList.size() - 1);
        assertThat(testBusinessType.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createBusinessTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = businessTypeRepository.findAll().size();

        // Create the BusinessType with an existing ID
        businessType.setId(1L);
        BusinessTypeDTO businessTypeDTO = businessTypeMapper.toDto(businessType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBusinessTypeMockMvc.perform(post("/api/business-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BusinessType in the database
        List<BusinessType> businessTypeList = businessTypeRepository.findAll();
        assertThat(businessTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBusinessTypes() throws Exception {
        // Initialize the database
        businessTypeRepository.saveAndFlush(businessType);

        // Get all the businessTypeList
        restBusinessTypeMockMvc.perform(get("/api/business-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(businessType.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)));
    }
    
    @Test
    @Transactional
    public void getBusinessType() throws Exception {
        // Initialize the database
        businessTypeRepository.saveAndFlush(businessType);

        // Get the businessType
        restBusinessTypeMockMvc.perform(get("/api/business-types/{id}", businessType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(businessType.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE));
    }

    @Test
    @Transactional
    public void getNonExistingBusinessType() throws Exception {
        // Get the businessType
        restBusinessTypeMockMvc.perform(get("/api/business-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBusinessType() throws Exception {
        // Initialize the database
        businessTypeRepository.saveAndFlush(businessType);

        int databaseSizeBeforeUpdate = businessTypeRepository.findAll().size();

        // Update the businessType
        BusinessType updatedBusinessType = businessTypeRepository.findById(businessType.getId()).get();
        // Disconnect from session so that the updates on updatedBusinessType are not directly saved in db
        em.detach(updatedBusinessType);
        updatedBusinessType
            .type(UPDATED_TYPE);
        BusinessTypeDTO businessTypeDTO = businessTypeMapper.toDto(updatedBusinessType);

        restBusinessTypeMockMvc.perform(put("/api/business-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessTypeDTO)))
            .andExpect(status().isOk());

        // Validate the BusinessType in the database
        List<BusinessType> businessTypeList = businessTypeRepository.findAll();
        assertThat(businessTypeList).hasSize(databaseSizeBeforeUpdate);
        BusinessType testBusinessType = businessTypeList.get(businessTypeList.size() - 1);
        assertThat(testBusinessType.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingBusinessType() throws Exception {
        int databaseSizeBeforeUpdate = businessTypeRepository.findAll().size();

        // Create the BusinessType
        BusinessTypeDTO businessTypeDTO = businessTypeMapper.toDto(businessType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBusinessTypeMockMvc.perform(put("/api/business-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BusinessType in the database
        List<BusinessType> businessTypeList = businessTypeRepository.findAll();
        assertThat(businessTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBusinessType() throws Exception {
        // Initialize the database
        businessTypeRepository.saveAndFlush(businessType);

        int databaseSizeBeforeDelete = businessTypeRepository.findAll().size();

        // Delete the businessType
        restBusinessTypeMockMvc.perform(delete("/api/business-types/{id}", businessType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BusinessType> businessTypeList = businessTypeRepository.findAll();
        assertThat(businessTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BusinessType.class);
        BusinessType businessType1 = new BusinessType();
        businessType1.setId(1L);
        BusinessType businessType2 = new BusinessType();
        businessType2.setId(businessType1.getId());
        assertThat(businessType1).isEqualTo(businessType2);
        businessType2.setId(2L);
        assertThat(businessType1).isNotEqualTo(businessType2);
        businessType1.setId(null);
        assertThat(businessType1).isNotEqualTo(businessType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BusinessTypeDTO.class);
        BusinessTypeDTO businessTypeDTO1 = new BusinessTypeDTO();
        businessTypeDTO1.setId(1L);
        BusinessTypeDTO businessTypeDTO2 = new BusinessTypeDTO();
        assertThat(businessTypeDTO1).isNotEqualTo(businessTypeDTO2);
        businessTypeDTO2.setId(businessTypeDTO1.getId());
        assertThat(businessTypeDTO1).isEqualTo(businessTypeDTO2);
        businessTypeDTO2.setId(2L);
        assertThat(businessTypeDTO1).isNotEqualTo(businessTypeDTO2);
        businessTypeDTO1.setId(null);
        assertThat(businessTypeDTO1).isNotEqualTo(businessTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(businessTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(businessTypeMapper.fromId(null)).isNull();
    }
}
