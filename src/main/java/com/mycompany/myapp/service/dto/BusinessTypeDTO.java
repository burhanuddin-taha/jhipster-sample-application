package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.BusinessType} entity.
 */
public class BusinessTypeDTO implements Serializable {

    private Long id;

    private String type;


    private Long entitiesId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getEntitiesId() {
        return entitiesId;
    }

    public void setEntitiesId(Long entitiesId) {
        this.entitiesId = entitiesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BusinessTypeDTO businessTypeDTO = (BusinessTypeDTO) o;
        if (businessTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), businessTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BusinessTypeDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", entities=" + getEntitiesId() +
            "}";
    }
}
