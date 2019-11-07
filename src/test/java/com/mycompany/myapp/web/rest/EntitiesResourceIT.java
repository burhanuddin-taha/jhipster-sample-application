package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.Entities;
import com.mycompany.myapp.repository.EntitiesRepository;
import com.mycompany.myapp.service.EntitiesService;
import com.mycompany.myapp.service.dto.EntitiesDTO;
import com.mycompany.myapp.service.mapper.EntitiesMapper;
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
 * Integration tests for the {@link EntitiesResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class EntitiesResourceIT {

    private static final String DEFAULT_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_ZIP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ZIP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_VAT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_VAT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CF = "AAAAAAAAAA";
    private static final String UPDATED_CF = "BBBBBBBBBB";

    private static final String DEFAULT_SDI = "AAAAAAAAAA";
    private static final String UPDATED_SDI = "BBBBBBBBBB";

    private static final Long DEFAULT_OWNED_BY = 1L;
    private static final Long UPDATED_OWNED_BY = 2L;

    private static final String DEFAULT_GEOLOCATION = "AAAAAAAAAA";
    private static final String UPDATED_GEOLOCATION = "BBBBBBBBBB";

    private static final Long DEFAULT_ORIGIN_USER_ID = 1L;
    private static final Long UPDATED_ORIGIN_USER_ID = 2L;

    private static final String DEFAULT_REGISTRATION = "AAAAAAAAAA";
    private static final String UPDATED_REGISTRATION = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PEC = "AAAAAAAAAA";
    private static final String UPDATED_PEC = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_RIVENDITA = "AAAAAAAAAA";
    private static final String UPDATED_NUM_RIVENDITA = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_PROFILE = "AAAAAAAAAA";
    private static final String UPDATED_PROFILE = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BANK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_IBAN = "AAAAAAAAAA";
    private static final String UPDATED_BANK_IBAN = "BBBBBBBBBB";

    private static final Integer DEFAULT_RANKING = 1;
    private static final Integer UPDATED_RANKING = 2;

    private static final String DEFAULT_BUSINESS = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS = "BBBBBBBBBB";

    private static final String DEFAULT_ORIGIN = "AAAAAAAAAA";
    private static final String UPDATED_ORIGIN = "BBBBBBBBBB";

    @Autowired
    private EntitiesRepository entitiesRepository;

    @Autowired
    private EntitiesMapper entitiesMapper;

    @Autowired
    private EntitiesService entitiesService;

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

    private MockMvc restEntitiesMockMvc;

    private Entities entities;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EntitiesResource entitiesResource = new EntitiesResource(entitiesService);
        this.restEntitiesMockMvc = MockMvcBuilders.standaloneSetup(entitiesResource)
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
    public static Entities createEntity(EntityManager em) {
        Entities entities = new Entities()
            .company(DEFAULT_COMPANY)
            .countryCode(DEFAULT_COUNTRY_CODE)
            .state(DEFAULT_STATE)
            .city(DEFAULT_CITY)
            .zipCode(DEFAULT_ZIP_CODE)
            .vatNumber(DEFAULT_VAT_NUMBER)
            .cf(DEFAULT_CF)
            .sdi(DEFAULT_SDI)
            .ownedBy(DEFAULT_OWNED_BY)
            .geolocation(DEFAULT_GEOLOCATION)
            .originUserId(DEFAULT_ORIGIN_USER_ID)
            .registration(DEFAULT_REGISTRATION)
            .code(DEFAULT_CODE)
            .pec(DEFAULT_PEC)
            .numRivendita(DEFAULT_NUM_RIVENDITA)
            .website(DEFAULT_WEBSITE)
            .fax(DEFAULT_FAX)
            .profile(DEFAULT_PROFILE)
            .bankName(DEFAULT_BANK_NAME)
            .bankIban(DEFAULT_BANK_IBAN)
            .ranking(DEFAULT_RANKING)
            .business(DEFAULT_BUSINESS)
            .origin(DEFAULT_ORIGIN);
        return entities;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Entities createUpdatedEntity(EntityManager em) {
        Entities entities = new Entities()
            .company(UPDATED_COMPANY)
            .countryCode(UPDATED_COUNTRY_CODE)
            .state(UPDATED_STATE)
            .city(UPDATED_CITY)
            .zipCode(UPDATED_ZIP_CODE)
            .vatNumber(UPDATED_VAT_NUMBER)
            .cf(UPDATED_CF)
            .sdi(UPDATED_SDI)
            .ownedBy(UPDATED_OWNED_BY)
            .geolocation(UPDATED_GEOLOCATION)
            .originUserId(UPDATED_ORIGIN_USER_ID)
            .registration(UPDATED_REGISTRATION)
            .code(UPDATED_CODE)
            .pec(UPDATED_PEC)
            .numRivendita(UPDATED_NUM_RIVENDITA)
            .website(UPDATED_WEBSITE)
            .fax(UPDATED_FAX)
            .profile(UPDATED_PROFILE)
            .bankName(UPDATED_BANK_NAME)
            .bankIban(UPDATED_BANK_IBAN)
            .ranking(UPDATED_RANKING)
            .business(UPDATED_BUSINESS)
            .origin(UPDATED_ORIGIN);
        return entities;
    }

    @BeforeEach
    public void initTest() {
        entities = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntities() throws Exception {
        int databaseSizeBeforeCreate = entitiesRepository.findAll().size();

        // Create the Entities
        EntitiesDTO entitiesDTO = entitiesMapper.toDto(entities);
        restEntitiesMockMvc.perform(post("/api/entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entitiesDTO)))
            .andExpect(status().isCreated());

        // Validate the Entities in the database
        List<Entities> entitiesList = entitiesRepository.findAll();
        assertThat(entitiesList).hasSize(databaseSizeBeforeCreate + 1);
        Entities testEntities = entitiesList.get(entitiesList.size() - 1);
        assertThat(testEntities.getCompany()).isEqualTo(DEFAULT_COMPANY);
        assertThat(testEntities.getCountryCode()).isEqualTo(DEFAULT_COUNTRY_CODE);
        assertThat(testEntities.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testEntities.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testEntities.getZipCode()).isEqualTo(DEFAULT_ZIP_CODE);
        assertThat(testEntities.getVatNumber()).isEqualTo(DEFAULT_VAT_NUMBER);
        assertThat(testEntities.getCf()).isEqualTo(DEFAULT_CF);
        assertThat(testEntities.getSdi()).isEqualTo(DEFAULT_SDI);
        assertThat(testEntities.getOwnedBy()).isEqualTo(DEFAULT_OWNED_BY);
        assertThat(testEntities.getGeolocation()).isEqualTo(DEFAULT_GEOLOCATION);
        assertThat(testEntities.getOriginUserId()).isEqualTo(DEFAULT_ORIGIN_USER_ID);
        assertThat(testEntities.getRegistration()).isEqualTo(DEFAULT_REGISTRATION);
        assertThat(testEntities.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testEntities.getPec()).isEqualTo(DEFAULT_PEC);
        assertThat(testEntities.getNumRivendita()).isEqualTo(DEFAULT_NUM_RIVENDITA);
        assertThat(testEntities.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testEntities.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testEntities.getProfile()).isEqualTo(DEFAULT_PROFILE);
        assertThat(testEntities.getBankName()).isEqualTo(DEFAULT_BANK_NAME);
        assertThat(testEntities.getBankIban()).isEqualTo(DEFAULT_BANK_IBAN);
        assertThat(testEntities.getRanking()).isEqualTo(DEFAULT_RANKING);
        assertThat(testEntities.getBusiness()).isEqualTo(DEFAULT_BUSINESS);
        assertThat(testEntities.getOrigin()).isEqualTo(DEFAULT_ORIGIN);
    }

    @Test
    @Transactional
    public void createEntitiesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = entitiesRepository.findAll().size();

        // Create the Entities with an existing ID
        entities.setId(1L);
        EntitiesDTO entitiesDTO = entitiesMapper.toDto(entities);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEntitiesMockMvc.perform(post("/api/entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entitiesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Entities in the database
        List<Entities> entitiesList = entitiesRepository.findAll();
        assertThat(entitiesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEntities() throws Exception {
        // Initialize the database
        entitiesRepository.saveAndFlush(entities);

        // Get all the entitiesList
        restEntitiesMockMvc.perform(get("/api/entities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(entities.getId().intValue())))
            .andExpect(jsonPath("$.[*].company").value(hasItem(DEFAULT_COMPANY)))
            .andExpect(jsonPath("$.[*].countryCode").value(hasItem(DEFAULT_COUNTRY_CODE)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].zipCode").value(hasItem(DEFAULT_ZIP_CODE)))
            .andExpect(jsonPath("$.[*].vatNumber").value(hasItem(DEFAULT_VAT_NUMBER)))
            .andExpect(jsonPath("$.[*].cf").value(hasItem(DEFAULT_CF)))
            .andExpect(jsonPath("$.[*].sdi").value(hasItem(DEFAULT_SDI)))
            .andExpect(jsonPath("$.[*].ownedBy").value(hasItem(DEFAULT_OWNED_BY.intValue())))
            .andExpect(jsonPath("$.[*].geolocation").value(hasItem(DEFAULT_GEOLOCATION)))
            .andExpect(jsonPath("$.[*].originUserId").value(hasItem(DEFAULT_ORIGIN_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].registration").value(hasItem(DEFAULT_REGISTRATION)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].pec").value(hasItem(DEFAULT_PEC)))
            .andExpect(jsonPath("$.[*].numRivendita").value(hasItem(DEFAULT_NUM_RIVENDITA)))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].profile").value(hasItem(DEFAULT_PROFILE)))
            .andExpect(jsonPath("$.[*].bankName").value(hasItem(DEFAULT_BANK_NAME)))
            .andExpect(jsonPath("$.[*].bankIban").value(hasItem(DEFAULT_BANK_IBAN)))
            .andExpect(jsonPath("$.[*].ranking").value(hasItem(DEFAULT_RANKING)))
            .andExpect(jsonPath("$.[*].business").value(hasItem(DEFAULT_BUSINESS)))
            .andExpect(jsonPath("$.[*].origin").value(hasItem(DEFAULT_ORIGIN)));
    }
    
    @Test
    @Transactional
    public void getEntities() throws Exception {
        // Initialize the database
        entitiesRepository.saveAndFlush(entities);

        // Get the entities
        restEntitiesMockMvc.perform(get("/api/entities/{id}", entities.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(entities.getId().intValue()))
            .andExpect(jsonPath("$.company").value(DEFAULT_COMPANY))
            .andExpect(jsonPath("$.countryCode").value(DEFAULT_COUNTRY_CODE))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.zipCode").value(DEFAULT_ZIP_CODE))
            .andExpect(jsonPath("$.vatNumber").value(DEFAULT_VAT_NUMBER))
            .andExpect(jsonPath("$.cf").value(DEFAULT_CF))
            .andExpect(jsonPath("$.sdi").value(DEFAULT_SDI))
            .andExpect(jsonPath("$.ownedBy").value(DEFAULT_OWNED_BY.intValue()))
            .andExpect(jsonPath("$.geolocation").value(DEFAULT_GEOLOCATION))
            .andExpect(jsonPath("$.originUserId").value(DEFAULT_ORIGIN_USER_ID.intValue()))
            .andExpect(jsonPath("$.registration").value(DEFAULT_REGISTRATION))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.pec").value(DEFAULT_PEC))
            .andExpect(jsonPath("$.numRivendita").value(DEFAULT_NUM_RIVENDITA))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.profile").value(DEFAULT_PROFILE))
            .andExpect(jsonPath("$.bankName").value(DEFAULT_BANK_NAME))
            .andExpect(jsonPath("$.bankIban").value(DEFAULT_BANK_IBAN))
            .andExpect(jsonPath("$.ranking").value(DEFAULT_RANKING))
            .andExpect(jsonPath("$.business").value(DEFAULT_BUSINESS))
            .andExpect(jsonPath("$.origin").value(DEFAULT_ORIGIN));
    }

    @Test
    @Transactional
    public void getNonExistingEntities() throws Exception {
        // Get the entities
        restEntitiesMockMvc.perform(get("/api/entities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntities() throws Exception {
        // Initialize the database
        entitiesRepository.saveAndFlush(entities);

        int databaseSizeBeforeUpdate = entitiesRepository.findAll().size();

        // Update the entities
        Entities updatedEntities = entitiesRepository.findById(entities.getId()).get();
        // Disconnect from session so that the updates on updatedEntities are not directly saved in db
        em.detach(updatedEntities);
        updatedEntities
            .company(UPDATED_COMPANY)
            .countryCode(UPDATED_COUNTRY_CODE)
            .state(UPDATED_STATE)
            .city(UPDATED_CITY)
            .zipCode(UPDATED_ZIP_CODE)
            .vatNumber(UPDATED_VAT_NUMBER)
            .cf(UPDATED_CF)
            .sdi(UPDATED_SDI)
            .ownedBy(UPDATED_OWNED_BY)
            .geolocation(UPDATED_GEOLOCATION)
            .originUserId(UPDATED_ORIGIN_USER_ID)
            .registration(UPDATED_REGISTRATION)
            .code(UPDATED_CODE)
            .pec(UPDATED_PEC)
            .numRivendita(UPDATED_NUM_RIVENDITA)
            .website(UPDATED_WEBSITE)
            .fax(UPDATED_FAX)
            .profile(UPDATED_PROFILE)
            .bankName(UPDATED_BANK_NAME)
            .bankIban(UPDATED_BANK_IBAN)
            .ranking(UPDATED_RANKING)
            .business(UPDATED_BUSINESS)
            .origin(UPDATED_ORIGIN);
        EntitiesDTO entitiesDTO = entitiesMapper.toDto(updatedEntities);

        restEntitiesMockMvc.perform(put("/api/entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entitiesDTO)))
            .andExpect(status().isOk());

        // Validate the Entities in the database
        List<Entities> entitiesList = entitiesRepository.findAll();
        assertThat(entitiesList).hasSize(databaseSizeBeforeUpdate);
        Entities testEntities = entitiesList.get(entitiesList.size() - 1);
        assertThat(testEntities.getCompany()).isEqualTo(UPDATED_COMPANY);
        assertThat(testEntities.getCountryCode()).isEqualTo(UPDATED_COUNTRY_CODE);
        assertThat(testEntities.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testEntities.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testEntities.getZipCode()).isEqualTo(UPDATED_ZIP_CODE);
        assertThat(testEntities.getVatNumber()).isEqualTo(UPDATED_VAT_NUMBER);
        assertThat(testEntities.getCf()).isEqualTo(UPDATED_CF);
        assertThat(testEntities.getSdi()).isEqualTo(UPDATED_SDI);
        assertThat(testEntities.getOwnedBy()).isEqualTo(UPDATED_OWNED_BY);
        assertThat(testEntities.getGeolocation()).isEqualTo(UPDATED_GEOLOCATION);
        assertThat(testEntities.getOriginUserId()).isEqualTo(UPDATED_ORIGIN_USER_ID);
        assertThat(testEntities.getRegistration()).isEqualTo(UPDATED_REGISTRATION);
        assertThat(testEntities.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testEntities.getPec()).isEqualTo(UPDATED_PEC);
        assertThat(testEntities.getNumRivendita()).isEqualTo(UPDATED_NUM_RIVENDITA);
        assertThat(testEntities.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testEntities.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testEntities.getProfile()).isEqualTo(UPDATED_PROFILE);
        assertThat(testEntities.getBankName()).isEqualTo(UPDATED_BANK_NAME);
        assertThat(testEntities.getBankIban()).isEqualTo(UPDATED_BANK_IBAN);
        assertThat(testEntities.getRanking()).isEqualTo(UPDATED_RANKING);
        assertThat(testEntities.getBusiness()).isEqualTo(UPDATED_BUSINESS);
        assertThat(testEntities.getOrigin()).isEqualTo(UPDATED_ORIGIN);
    }

    @Test
    @Transactional
    public void updateNonExistingEntities() throws Exception {
        int databaseSizeBeforeUpdate = entitiesRepository.findAll().size();

        // Create the Entities
        EntitiesDTO entitiesDTO = entitiesMapper.toDto(entities);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEntitiesMockMvc.perform(put("/api/entities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(entitiesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Entities in the database
        List<Entities> entitiesList = entitiesRepository.findAll();
        assertThat(entitiesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEntities() throws Exception {
        // Initialize the database
        entitiesRepository.saveAndFlush(entities);

        int databaseSizeBeforeDelete = entitiesRepository.findAll().size();

        // Delete the entities
        restEntitiesMockMvc.perform(delete("/api/entities/{id}", entities.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Entities> entitiesList = entitiesRepository.findAll();
        assertThat(entitiesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Entities.class);
        Entities entities1 = new Entities();
        entities1.setId(1L);
        Entities entities2 = new Entities();
        entities2.setId(entities1.getId());
        assertThat(entities1).isEqualTo(entities2);
        entities2.setId(2L);
        assertThat(entities1).isNotEqualTo(entities2);
        entities1.setId(null);
        assertThat(entities1).isNotEqualTo(entities2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EntitiesDTO.class);
        EntitiesDTO entitiesDTO1 = new EntitiesDTO();
        entitiesDTO1.setId(1L);
        EntitiesDTO entitiesDTO2 = new EntitiesDTO();
        assertThat(entitiesDTO1).isNotEqualTo(entitiesDTO2);
        entitiesDTO2.setId(entitiesDTO1.getId());
        assertThat(entitiesDTO1).isEqualTo(entitiesDTO2);
        entitiesDTO2.setId(2L);
        assertThat(entitiesDTO1).isNotEqualTo(entitiesDTO2);
        entitiesDTO1.setId(null);
        assertThat(entitiesDTO1).isNotEqualTo(entitiesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(entitiesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(entitiesMapper.fromId(null)).isNull();
    }
}
