package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.EntityStructure;
import com.mycompany.myapp.repository.EntityStructureRepository;
import com.mycompany.myapp.service.EntityStructureService;
import com.mycompany.myapp.service.dto.EntityStructureDTO;
import com.mycompany.myapp.service.mapper.EntityStructureMapper;
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
 * Integration tests for the {@link EntityStructureResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class EntityStructureResourceIT {

    private static final String DEFAULT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY = "BBBBBBBBBB";

    private static final Long DEFAULT_CAPACITY = 1L;
    private static final Long UPDATED_CAPACITY = 2L;

    @Autowired
    private EntityStructureRepository entityStructureRepository;

    @Autowired
    private EntityStructureMapper entityStructureMapper;

    @Autowired
    private EntityStructureService entityStructureService;

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

    private MockMvc restEntityStructureMockMvc;

    private EntityStructure entityStructure;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EntityStructureResource entityStructureResource = new EntityStructureResource(entityStructureService);
        this.restEntityStructureMockMvc = MockMvcBuilders.standaloneSetup(entityStructureResource)
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
    public static EntityStructure createEntity(EntityManager em) {
        EntityStructure entityStructure = new EntityStructure()
            .category(DEFAULT_CATEGORY)
            .capacity(DEFAULT_CAPACITY);
        return entityStructure;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityStructure createUpdatedEntity(EntityManager em) {
        EntityStructure entityStructure = new EntityStructure()
            .category(UPDATED_CATEGORY)
            .capacity(UPDATED_CAPACITY);
        return entityStructure;
    }

    @BeforeEach
    public void initTest() {
        entityStructure = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntityStructure() throws Exception {
        int databaseSizeBeforeCreate = entityStructureRepository.findAll().size();

        // Create the EntityStructure
        EntityStructureDTO entityStructureDTO = entityStructureMapper.toDto(entityStructure);
        restEntityStructureMockMvc.perform(post("/api/entity-structures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityStructureDTO)))
            .andExpect(status().isCreated());

        // Validate the EntityStructure in the database
        List<EntityStructure> entityStructureList = entityStructureRepository.findAll();
        assertThat(entityStructureList).hasSize(databaseSizeBeforeCreate + 1);
        EntityStructure testEntityStructure = entityStructureList.get(entityStructureList.size() - 1);
        assertThat(testEntityStructure.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testEntityStructure.getCapacity()).isEqualTo(DEFAULT_CAPACITY);
    }

    @Test
    @Transactional
    public void createEntityStructureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entityStructureRepository.findAll().size();

        // Create the EntityStructure with an existing ID
        entityStructure.setId(1L);
        EntityStructureDTO entityStructureDTO = entityStructureMapper.toDto(entityStructure);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityStructureMockMvc.perform(post("/api/entity-structures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityStructureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityStructure in the database
        List<EntityStructure> entityStructureList = entityStructureRepository.findAll();
        assertThat(entityStructureList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEntityStructures() throws Exception {
        // Initialize the database
        entityStructureRepository.saveAndFlush(entityStructure);

        // Get all the entityStructureList
        restEntityStructureMockMvc.perform(get("/api/entity-structures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityStructure.getId().intValue())))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY)))
            .andExpect(jsonPath("$.[*].capacity").value(hasItem(DEFAULT_CAPACITY.intValue())));
    }
    
    @Test
    @Transactional
    public void getEntityStructure() throws Exception {
        // Initialize the database
        entityStructureRepository.saveAndFlush(entityStructure);

        // Get the entityStructure
        restEntityStructureMockMvc.perform(get("/api/entity-structures/{id}", entityStructure.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(entityStructure.getId().intValue()))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY))
            .andExpect(jsonPath("$.capacity").value(DEFAULT_CAPACITY.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEntityStructure() throws Exception {
        // Get the entityStructure
        restEntityStructureMockMvc.perform(get("/api/entity-structures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntityStructure() throws Exception {
        // Initialize the database
        entityStructureRepository.saveAndFlush(entityStructure);

        int databaseSizeBeforeUpdate = entityStructureRepository.findAll().size();

        // Update the entityStructure
        EntityStructure updatedEntityStructure = entityStructureRepository.findById(entityStructure.getId()).get();
        // Disconnect from session so that the updates on updatedEntityStructure are not directly saved in db
        em.detach(updatedEntityStructure);
        updatedEntityStructure
            .category(UPDATED_CATEGORY)
            .capacity(UPDATED_CAPACITY);
        EntityStructureDTO entityStructureDTO = entityStructureMapper.toDto(updatedEntityStructure);

        restEntityStructureMockMvc.perform(put("/api/entity-structures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityStructureDTO)))
            .andExpect(status().isOk());

        // Validate the EntityStructure in the database
        List<EntityStructure> entityStructureList = entityStructureRepository.findAll();
        assertThat(entityStructureList).hasSize(databaseSizeBeforeUpdate);
        EntityStructure testEntityStructure = entityStructureList.get(entityStructureList.size() - 1);
        assertThat(testEntityStructure.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testEntityStructure.getCapacity()).isEqualTo(UPDATED_CAPACITY);
    }

    @Test
    @Transactional
    public void updateNonExistingEntityStructure() throws Exception {
        int databaseSizeBeforeUpdate = entityStructureRepository.findAll().size();

        // Create the EntityStructure
        EntityStructureDTO entityStructureDTO = entityStructureMapper.toDto(entityStructure);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityStructureMockMvc.perform(put("/api/entity-structures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityStructureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityStructure in the database
        List<EntityStructure> entityStructureList = entityStructureRepository.findAll();
        assertThat(entityStructureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEntityStructure() throws Exception {
        // Initialize the database
        entityStructureRepository.saveAndFlush(entityStructure);

        int databaseSizeBeforeDelete = entityStructureRepository.findAll().size();

        // Delete the entityStructure
        restEntityStructureMockMvc.perform(delete("/api/entity-structures/{id}", entityStructure.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EntityStructure> entityStructureList = entityStructureRepository.findAll();
        assertThat(entityStructureList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityStructure.class);
        EntityStructure entityStructure1 = new EntityStructure();
        entityStructure1.setId(1L);
        EntityStructure entityStructure2 = new EntityStructure();
        entityStructure2.setId(entityStructure1.getId());
        assertThat(entityStructure1).isEqualTo(entityStructure2);
        entityStructure2.setId(2L);
        assertThat(entityStructure1).isNotEqualTo(entityStructure2);
        entityStructure1.setId(null);
        assertThat(entityStructure1).isNotEqualTo(entityStructure2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityStructureDTO.class);
        EntityStructureDTO entityStructureDTO1 = new EntityStructureDTO();
        entityStructureDTO1.setId(1L);
        EntityStructureDTO entityStructureDTO2 = new EntityStructureDTO();
        assertThat(entityStructureDTO1).isNotEqualTo(entityStructureDTO2);
        entityStructureDTO2.setId(entityStructureDTO1.getId());
        assertThat(entityStructureDTO1).isEqualTo(entityStructureDTO2);
        entityStructureDTO2.setId(2L);
        assertThat(entityStructureDTO1).isNotEqualTo(entityStructureDTO2);
        entityStructureDTO1.setId(null);
        assertThat(entityStructureDTO1).isNotEqualTo(entityStructureDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(entityStructureMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(entityStructureMapper.fromId(null)).isNull();
    }
}
