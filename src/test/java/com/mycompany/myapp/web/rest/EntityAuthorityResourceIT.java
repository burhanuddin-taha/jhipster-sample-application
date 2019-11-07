package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.EntityAuthority;
import com.mycompany.myapp.repository.EntityAuthorityRepository;
import com.mycompany.myapp.service.EntityAuthorityService;
import com.mycompany.myapp.service.dto.EntityAuthorityDTO;
import com.mycompany.myapp.service.mapper.EntityAuthorityMapper;
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
 * Integration tests for the {@link EntityAuthorityResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class EntityAuthorityResourceIT {

    private static final Integer DEFAULT_USER_ID = 1;
    private static final Integer UPDATED_USER_ID = 2;

    private static final Integer DEFAULT_ENTITY_ID = 1;
    private static final Integer UPDATED_ENTITY_ID = 2;

    private static final String DEFAULT_ENTITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ENTITY_NAME = "BBBBBBBBBB";

    @Autowired
    private EntityAuthorityRepository entityAuthorityRepository;

    @Autowired
    private EntityAuthorityMapper entityAuthorityMapper;

    @Autowired
    private EntityAuthorityService entityAuthorityService;

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

    private MockMvc restEntityAuthorityMockMvc;

    private EntityAuthority entityAuthority;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EntityAuthorityResource entityAuthorityResource = new EntityAuthorityResource(entityAuthorityService);
        this.restEntityAuthorityMockMvc = MockMvcBuilders.standaloneSetup(entityAuthorityResource)
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
    public static EntityAuthority createEntity(EntityManager em) {
        EntityAuthority entityAuthority = new EntityAuthority()
            .userId(DEFAULT_USER_ID)
            .entityId(DEFAULT_ENTITY_ID)
            .entityName(DEFAULT_ENTITY_NAME);
        return entityAuthority;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EntityAuthority createUpdatedEntity(EntityManager em) {
        EntityAuthority entityAuthority = new EntityAuthority()
            .userId(UPDATED_USER_ID)
            .entityId(UPDATED_ENTITY_ID)
            .entityName(UPDATED_ENTITY_NAME);
        return entityAuthority;
    }

    @BeforeEach
    public void initTest() {
        entityAuthority = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntityAuthority() throws Exception {
        int databaseSizeBeforeCreate = entityAuthorityRepository.findAll().size();

        // Create the EntityAuthority
        EntityAuthorityDTO entityAuthorityDTO = entityAuthorityMapper.toDto(entityAuthority);
        restEntityAuthorityMockMvc.perform(post("/api/entity-authorities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityAuthorityDTO)))
            .andExpect(status().isCreated());

        // Validate the EntityAuthority in the database
        List<EntityAuthority> entityAuthorityList = entityAuthorityRepository.findAll();
        assertThat(entityAuthorityList).hasSize(databaseSizeBeforeCreate + 1);
        EntityAuthority testEntityAuthority = entityAuthorityList.get(entityAuthorityList.size() - 1);
        assertThat(testEntityAuthority.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testEntityAuthority.getEntityId()).isEqualTo(DEFAULT_ENTITY_ID);
        assertThat(testEntityAuthority.getEntityName()).isEqualTo(DEFAULT_ENTITY_NAME);
    }

    @Test
    @Transactional
    public void createEntityAuthorityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entityAuthorityRepository.findAll().size();

        // Create the EntityAuthority with an existing ID
        entityAuthority.setId(1L);
        EntityAuthorityDTO entityAuthorityDTO = entityAuthorityMapper.toDto(entityAuthority);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntityAuthorityMockMvc.perform(post("/api/entity-authorities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityAuthorityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityAuthority in the database
        List<EntityAuthority> entityAuthorityList = entityAuthorityRepository.findAll();
        assertThat(entityAuthorityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEntityAuthorities() throws Exception {
        // Initialize the database
        entityAuthorityRepository.saveAndFlush(entityAuthority);

        // Get all the entityAuthorityList
        restEntityAuthorityMockMvc.perform(get("/api/entity-authorities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entityAuthority.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].entityId").value(hasItem(DEFAULT_ENTITY_ID)))
            .andExpect(jsonPath("$.[*].entityName").value(hasItem(DEFAULT_ENTITY_NAME)));
    }
    
    @Test
    @Transactional
    public void getEntityAuthority() throws Exception {
        // Initialize the database
        entityAuthorityRepository.saveAndFlush(entityAuthority);

        // Get the entityAuthority
        restEntityAuthorityMockMvc.perform(get("/api/entity-authorities/{id}", entityAuthority.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(entityAuthority.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.entityId").value(DEFAULT_ENTITY_ID))
            .andExpect(jsonPath("$.entityName").value(DEFAULT_ENTITY_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingEntityAuthority() throws Exception {
        // Get the entityAuthority
        restEntityAuthorityMockMvc.perform(get("/api/entity-authorities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntityAuthority() throws Exception {
        // Initialize the database
        entityAuthorityRepository.saveAndFlush(entityAuthority);

        int databaseSizeBeforeUpdate = entityAuthorityRepository.findAll().size();

        // Update the entityAuthority
        EntityAuthority updatedEntityAuthority = entityAuthorityRepository.findById(entityAuthority.getId()).get();
        // Disconnect from session so that the updates on updatedEntityAuthority are not directly saved in db
        em.detach(updatedEntityAuthority);
        updatedEntityAuthority
            .userId(UPDATED_USER_ID)
            .entityId(UPDATED_ENTITY_ID)
            .entityName(UPDATED_ENTITY_NAME);
        EntityAuthorityDTO entityAuthorityDTO = entityAuthorityMapper.toDto(updatedEntityAuthority);

        restEntityAuthorityMockMvc.perform(put("/api/entity-authorities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityAuthorityDTO)))
            .andExpect(status().isOk());

        // Validate the EntityAuthority in the database
        List<EntityAuthority> entityAuthorityList = entityAuthorityRepository.findAll();
        assertThat(entityAuthorityList).hasSize(databaseSizeBeforeUpdate);
        EntityAuthority testEntityAuthority = entityAuthorityList.get(entityAuthorityList.size() - 1);
        assertThat(testEntityAuthority.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testEntityAuthority.getEntityId()).isEqualTo(UPDATED_ENTITY_ID);
        assertThat(testEntityAuthority.getEntityName()).isEqualTo(UPDATED_ENTITY_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingEntityAuthority() throws Exception {
        int databaseSizeBeforeUpdate = entityAuthorityRepository.findAll().size();

        // Create the EntityAuthority
        EntityAuthorityDTO entityAuthorityDTO = entityAuthorityMapper.toDto(entityAuthority);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntityAuthorityMockMvc.perform(put("/api/entity-authorities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entityAuthorityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EntityAuthority in the database
        List<EntityAuthority> entityAuthorityList = entityAuthorityRepository.findAll();
        assertThat(entityAuthorityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEntityAuthority() throws Exception {
        // Initialize the database
        entityAuthorityRepository.saveAndFlush(entityAuthority);

        int databaseSizeBeforeDelete = entityAuthorityRepository.findAll().size();

        // Delete the entityAuthority
        restEntityAuthorityMockMvc.perform(delete("/api/entity-authorities/{id}", entityAuthority.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EntityAuthority> entityAuthorityList = entityAuthorityRepository.findAll();
        assertThat(entityAuthorityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityAuthority.class);
        EntityAuthority entityAuthority1 = new EntityAuthority();
        entityAuthority1.setId(1L);
        EntityAuthority entityAuthority2 = new EntityAuthority();
        entityAuthority2.setId(entityAuthority1.getId());
        assertThat(entityAuthority1).isEqualTo(entityAuthority2);
        entityAuthority2.setId(2L);
        assertThat(entityAuthority1).isNotEqualTo(entityAuthority2);
        entityAuthority1.setId(null);
        assertThat(entityAuthority1).isNotEqualTo(entityAuthority2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntityAuthorityDTO.class);
        EntityAuthorityDTO entityAuthorityDTO1 = new EntityAuthorityDTO();
        entityAuthorityDTO1.setId(1L);
        EntityAuthorityDTO entityAuthorityDTO2 = new EntityAuthorityDTO();
        assertThat(entityAuthorityDTO1).isNotEqualTo(entityAuthorityDTO2);
        entityAuthorityDTO2.setId(entityAuthorityDTO1.getId());
        assertThat(entityAuthorityDTO1).isEqualTo(entityAuthorityDTO2);
        entityAuthorityDTO2.setId(2L);
        assertThat(entityAuthorityDTO1).isNotEqualTo(entityAuthorityDTO2);
        entityAuthorityDTO1.setId(null);
        assertThat(entityAuthorityDTO1).isNotEqualTo(entityAuthorityDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(entityAuthorityMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(entityAuthorityMapper.fromId(null)).isNull();
    }
}
