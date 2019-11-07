package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Entities} entity.
 */
public class EntitiesDTO implements Serializable {

    private Long id;

    private String company;

    private String countryCode;

    private String state;

    private String city;

    private String zipCode;

    private String vatNumber;

    private String cf;

    private String sdi;

    private Long ownedBy;

    private String geolocation;

    private Long originUserId;

    private String registration;

    private String code;

    private String pec;

    private String numRivendita;

    private String website;

    private String fax;

    private String profile;

    private String bankName;

    private String bankIban;

    private Integer ranking;

    private String business;

    private String origin;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getSdi() {
        return sdi;
    }

    public void setSdi(String sdi) {
        this.sdi = sdi;
    }

    public Long getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(Long ownedBy) {
        this.ownedBy = ownedBy;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }

    public Long getOriginUserId() {
        return originUserId;
    }

    public void setOriginUserId(Long originUserId) {
        this.originUserId = originUserId;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPec() {
        return pec;
    }

    public void setPec(String pec) {
        this.pec = pec;
    }

    public String getNumRivendita() {
        return numRivendita;
    }

    public void setNumRivendita(String numRivendita) {
        this.numRivendita = numRivendita;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankIban() {
        return bankIban;
    }

    public void setBankIban(String bankIban) {
        this.bankIban = bankIban;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EntitiesDTO entitiesDTO = (EntitiesDTO) o;
        if (entitiesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entitiesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntitiesDTO{" +
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
            ", business='" + getBusiness() + "'" +
            ", origin='" + getOrigin() + "'" +
            "}";
    }
}
