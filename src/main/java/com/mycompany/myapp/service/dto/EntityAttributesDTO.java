package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.EntityAttributes} entity.
 */
public class EntityAttributesDTO implements Serializable {

    private Long id;

    private String categotyName;


    private Long entityIdId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategotyName() {
        return categotyName;
    }

    public void setCategotyName(String categotyName) {
        this.categotyName = categotyName;
    }

    public Long getEntityIdId() {
        return entityIdId;
    }

    public void setEntityIdId(Long entitiesId) {
        this.entityIdId = entitiesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EntityAttributesDTO entityAttributesDTO = (EntityAttributesDTO) o;
        if (entityAttributesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityAttributesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityAttributesDTO{" +
            "id=" + getId() +
            ", categotyName='" + getCategotyName() + "'" +
            ", entityId=" + getEntityIdId() +
            "}";
    }
}
