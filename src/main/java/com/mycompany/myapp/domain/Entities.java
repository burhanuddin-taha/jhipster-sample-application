package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Entities.
 */
@Entity
@Table(name = "entities")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Entities implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company")
    private String company;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "vat_number")
    private String vatNumber;

    @Column(name = "cf")
    private String cf;

    @Column(name = "sdi")
    private String sdi;

    @Column(name = "owned_by")
    private Long ownedBy;

    @Column(name = "geolocation")
    private String geolocation;

    @Column(name = "origin_user_id")
    private Long originUserId;

    @Column(name = "registration")
    private String registration;

    @Column(name = "code")
    private String code;

    @Column(name = "pec")
    private String pec;

    @Column(name = "num_rivendita")
    private String numRivendita;

    @Column(name = "website")
    private String website;

    @Column(name = "fax")
    private String fax;

    @Column(name = "profile")
    private String profile;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_iban")
    private String bankIban;

    @Column(name = "ranking")
    private Integer ranking;

    @Column(name = "business_string")
    private String businessString;

    @Column(name = "origin")
    private String origin;

    @OneToMany(mappedBy = "belongTo")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EntityStructure> entityStructures = new HashSet<>();

    @OneToOne(mappedBy = "entities")
    @JsonIgnore
    private BusinessType type;

    @OneToMany(mappedBy = "entities")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EntityAttributes> attributes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public Entities company(String company) {
        this.company = company;
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Entities countryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getState() {
        return state;
    }

    public Entities state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public Entities city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Entities zipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public Entities vatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
        return this;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getCf() {
        return cf;
    }

    public Entities cf(String cf) {
        this.cf = cf;
        return this;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getSdi() {
        return sdi;
    }

    public Entities sdi(String sdi) {
        this.sdi = sdi;
        return this;
    }

    public void setSdi(String sdi) {
        this.sdi = sdi;
    }

    public Long getOwnedBy() {
        return ownedBy;
    }

    public Entities ownedBy(Long ownedBy) {
        this.ownedBy = ownedBy;
        return this;
    }

    public void setOwnedBy(Long ownedBy) {
        this.ownedBy = ownedBy;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public Entities geolocation(String geolocation) {
        this.geolocation = geolocation;
        return this;
    }

    public void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }

    public Long getOriginUserId() {
        return originUserId;
    }

    public Entities originUserId(Long originUserId) {
        this.originUserId = originUserId;
        return this;
    }

    public void setOriginUserId(Long originUserId) {
        this.originUserId = originUserId;
    }

    public String getRegistration() {
        return registration;
    }

    public Entities registration(String registration) {
        this.registration = registration;
        return this;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getCode() {
        return code;
    }

    public Entities code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPec() {
        return pec;
    }

    public Entities pec(String pec) {
        this.pec = pec;
        return this;
    }

    public void setPec(String pec) {
        this.pec = pec;
    }

    public String getNumRivendita() {
        return numRivendita;
    }

    public Entities numRivendita(String numRivendita) {
        this.numRivendita = numRivendita;
        return this;
    }

    public void setNumRivendita(String numRivendita) {
        this.numRivendita = numRivendita;
    }

    public String getWebsite() {
        return website;
    }

    public Entities website(String website) {
        this.website = website;
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFax() {
        return fax;
    }

    public Entities fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getProfile() {
        return profile;
    }

    public Entities profile(String profile) {
        this.profile = profile;
        return this;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getBankName() {
        return bankName;
    }

    public Entities bankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankIban() {
        return bankIban;
    }

    public Entities bankIban(String bankIban) {
        this.bankIban = bankIban;
        return this;
    }

    public void setBankIban(String bankIban) {
        this.bankIban = bankIban;
    }

    public Integer getRanking() {
        return ranking;
    }

    public Entities ranking(Integer ranking) {
        this.ranking = ranking;
        return this;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getBusinessString() {
        return businessString;
    }

    public Entities businessString(String businessString) {
        this.businessString = businessString;
        return this;
    }

    public void setBusinessString(String businessString) {
        this.businessString = businessString;
    }

    public String getOrigin() {
        return origin;
    }

    public Entities origin(String origin) {
        this.origin = origin;
        return this;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Set<EntityStructure> getEntityStructures() {
        return entityStructures;
    }

    public Entities entityStructures(Set<EntityStructure> entityStructures) {
        this.entityStructures = entityStructures;
        return this;
    }

    public Entities addEntityStructure(EntityStructure entityStructure) {
        this.entityStructures.add(entityStructure);
        entityStructure.setBelongTo(this);
        return this;
    }

    public Entities removeEntityStructure(EntityStructure entityStructure) {
        this.entityStructures.remove(entityStructure);
        entityStructure.setBelongTo(null);
        return this;
    }

    public void setEntityStructures(Set<EntityStructure> entityStructures) {
        this.entityStructures = entityStructures;
    }

    public BusinessType getType() {
        return type;
    }

    public Entities type(BusinessType businessType) {
        this.type = businessType;
        return this;
    }

    public void setType(BusinessType businessType) {
        this.type = businessType;
    }

    public Set<EntityAttributes> getAttributes() {
        return attributes;
    }

    public Entities attributes(Set<EntityAttributes> entityAttributes) {
        this.attributes = entityAttributes;
        return this;
    }

    public Entities addAttributes(EntityAttributes entityAttributes) {
        this.attributes.add(entityAttributes);
        entityAttributes.setEntities(this);
        return this;
    }

    public Entities removeAttributes(EntityAttributes entityAttributes) {
        this.attributes.remove(entityAttributes);
        entityAttributes.setEntities(null);
        return this;
    }

    public void setAttributes(Set<EntityAttributes> entityAttributes) {
        this.attributes = entityAttributes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Entities)) {
            return false;
        }
        return id != null && id.equals(((Entities) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Entities{" +
            "id=" + getId() +
            ", company='" + getCompany() + "'" +
            ", countryCode='" + getCountryCode() + "'" +
            ", state='" + getState() + "'" +
            ", city='" + getCity() + "'" +
            ", zipCode='" + getZipCode() + "'" +
            ", vatNumber='" + getVatNumber() + "'" +
            ", cf='" + getCf() + "'" +
            ", sdi='" + getSdi() + "'" +
            ", ownedBy=" + getOwnedBy() +
            ", geolocation='" + getGeolocation() + "'" +
            ", originUserId=" + getOriginUserId() +
            ", registration='" + getRegistration() + "'" +
            ", code='" + getCode() + "'" +
            ", pec='" + getPec() + "'" +
            ", numRivendita='" + getNumRivendita() + "'" +
            ", website='" + getWebsite() + "'" +
            ", fax='" + getFax() + "'" +
            ", profile='" + getProfile() + "'" +
            ", bankName='" + getBankName() + "'" +
            ", bankIban='" + getBankIban() + "'" +
            ", ranking=" + getRanking() +
            ", businessString='" + getBusinessString() + "'" +
            ", origin='" + getOrigin() + "'" +
            "}";
    }
}
