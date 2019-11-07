package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.EntityAttributes;
import com.mycompany.myapp.repository.EntityAttributesRepository;
import com.mycompany.myapp.service.EntityAttributesService;
import com.mycompany.myapp.service.dto.EntityAttributesDTO;
import com.mycompany.myapp.service.mapper.EntityAttributesMapper;
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
 * Integration tests for the {@link EntityAttributesResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class EntityAttributesResourceIT {

    private static final String DEFAULT_CATEGOTY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CATEGOTY_NAME = "BBBBBBBBBB";

    @Autowired
    private EntityAttributesRepository entityAttributesRepository;

    @Autowired
    private EntityAttributesMapper entityAttributesMapper;

    @Autowired
    private EntityAttributesService entityAttributesService;

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

    private MockMvc restEntityAttributesMockMvc;

    private EntityAttributes entityAttributes;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EntityAttributesResource entityAttributesResource = new EntityAttributesResource(entityAttributesService);
        this.restEntityAttributesMockMvc = MockMvcBuilders.standaloneSetup(entityAttributesResource)
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
    public static EntityAttributes createEntity(EntityManager em) {
        EntityAttributes entityAttributes = new EntityAttributes()
            .categotyName(DEFAULT_CATEGOTY_NAME);
        return entityAttributes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityAttributes createUpdatedEntity(EntityManager em) {
        EntityAttributes entityAttributes = new EntityAttributes()
            .categotyName(UPDATED_CATEGOTY_NAME);
        return entityAttributes;
    }

    @BeforeEach
    public void initTest() {
        entityAttributes = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntityAttributes() throws Exception {
        int databaseSizeBeforeCreate = entityAttributesRepository.findAll().size();

        // Create the EntityAttributes
        EntityAttributesDTO entityAttributesDTO = entityAttributesMapper.toDto(entityAttributes);
        restEntityAttributesMockMvc.perform(post("/api/entity-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityAttributesDTO)))
            .andExpect(status().isCreated());

        // Validate the EntityAttributes in the database
        List<EntityAttributes> entityAttributesList = entityAttributesRepository.findAll();
        assertThat(entityAttributesList).hasSize(databaseSizeBeforeCreate + 1);
        EntityAttributes testEntityAttributes = entityAttributesList.get(entityAttributesList.size() - 1);
        assertThat(testEntityAttributes.getCategotyName()).isEqualTo(DEFAULT_CATEGOTY_NAME);
    }

    @Test
    @Transactional
    public void createEntityAttributesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entityAttributesRepository.findAll().size();

        // Create the EntityAttributes with an existing ID
        entityAttributes.setId(1L);
        EntityAttributesDTO entityAttributesDTO = entityAttributesMapper.toDto(entityAttributes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityAttributesMockMvc.perform(post("/api/entity-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityAttributesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityAttributes in the database
        List<EntityAttributes> entityAttributesList = entityAttributesRepository.findAll();
        assertThat(entityAttributesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEntityAttributes() throws Exception {
        // Initialize the database
        entityAttributesRepository.saveAndFlush(entityAttributes);

        // Get all the entityAttributesList
        restEntityAttributesMockMvc.perform(get("/api/entity-attributes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityAttributes.getId().intValue())))
            .andExpect(jsonPath("$.[*].categotyName").value(hasItem(DEFAULT_CATEGOTY_NAME)));
    }
    
    @Test
    @Transactional
    public void getEntityAttributes() throws Exception {
        // Initialize the database
        entityAttributesRepository.saveAndFlush(entityAttributes);

        // Get the entityAttributes
        restEntityAttributesMockMvc.perform(get("/api/entity-attributes/{id}", entityAttributes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(entityAttributes.getId().intValue()))
            .andExpect(jsonPath("$.categotyName").value(DEFAULT_CATEGOTY_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingEntityAttributes() throws Exception {
        // Get the entityAttributes
        restEntityAttributesMockMvc.perform(get("/api/entity-attributes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntityAttributes() throws Exception {
        // Initialize the database
        entityAttributesRepository.saveAndFlush(entityAttributes);

        int databaseSizeBeforeUpdate = entityAttributesRepository.findAll().size();

        // Update the entityAttributes
        EntityAttributes updatedEntityAttributes = entityAttributesRepository.findById(entityAttributes.getId()).get();
        // Disconnect from session so that the updates on updatedEntityAttributes are not directly saved in db
        em.detach(updatedEntityAttributes);
        updatedEntityAttributes
            .categotyName(UPDATED_CATEGOTY_NAME);
        EntityAttributesDTO entityAttributesDTO = entityAttributesMapper.toDto(updatedEntityAttributes);

        restEntityAttributesMockMvc.perform(put("/api/entity-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityAttributesDTO)))
            .andExpect(status().isOk());

        // Validate the EntityAttributes in the database
        List<EntityAttributes> entityAttributesList = entityAttributesRepository.findAll();
        assertThat(entityAttributesList).hasSize(databaseSizeBeforeUpdate);
        EntityAttributes testEntityAttributes = entityAttributesList.get(entityAttributesList.size() - 1);
        assertThat(testEntityAttributes.getCategotyName()).isEqualTo(UPDATED_CATEGOTY_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingEntityAttributes() throws Exception {
        int databaseSizeBeforeUpdate = entityAttributesRepository.findAll().size();

        // Create the EntityAttributes
        EntityAttributesDTO entityAttributesDTO = entityAttributesMapper.toDto(entityAttributes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityAttributesMockMvc.perform(put("/api/entity-attributes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityAttributesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityAttributes in the database
        List<EntityAttributes> entityAttributesList = entityAttributesRepository.findAll();
        assertThat(entityAttributesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEntityAttributes() throws Exception {
        // Initialize the database
        entityAttributesRepository.saveAndFlush(entityAttributes);

        int databaseSizeBeforeDelete = entityAttributesRepository.findAll().size();

        // Delete the entityAttributes
        restEntityAttributesMockMvc.perform(delete("/api/entity-attributes/{id}", entityAttributes.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EntityAttributes> entityAttributesList = entityAttributesRepository.findAll();
        assertThat(entityAttributesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityAttributes.class);
        EntityAttributes entityAttributes1 = new EntityAttributes();
        entityAttributes1.setId(1L);
        EntityAttributes entityAttributes2 = new EntityAttributes();
        entityAttributes2.setId(entityAttributes1.getId());
        assertThat(entityAttributes1).isEqualTo(entityAttributes2);
        entityAttributes2.setId(2L);
        assertThat(entityAttributes1).isNotEqualTo(entityAttributes2);
        entityAttributes1.setId(null);
        assertThat(entityAttributes1).isNotEqualTo(entityAttributes2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityAttributesDTO.class);
        EntityAttributesDTO entityAttributesDTO1 = new EntityAttributesDTO();
        entityAttributesDTO1.setId(1L);
        EntityAttributesDTO entityAttributesDTO2 = new EntityAttributesDTO();
        assertThat(entityAttributesDTO1).isNotEqualTo(entityAttributesDTO2);
        entityAttributesDTO2.setId(entityAttributesDTO1.getId());
        assertThat(entityAttributesDTO1).isEqualTo(entityAttributesDTO2);
        entityAttributesDTO2.setId(2L);
        assertThat(entityAttributesDTO1).isNotEqualTo(entityAttributesDTO2);
        entityAttributesDTO1.setId(null);
        assertThat(entityAttributesDTO1).isNotEqualTo(entityAttributesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(entityAttributesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(entityAttributesMapper.fromId(null)).isNull();
    }
}
