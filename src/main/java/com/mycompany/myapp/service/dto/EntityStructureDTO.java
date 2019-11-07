package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.EntityStructure} entity.
 */
public class EntityStructureDTO implements Serializable {

    private Long id;

    private String category;

    private Long capacity;


    private Long idId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Long getIdId() {
        return idId;
    }

    public void setIdId(Long entitiesId) {
        this.idId = entitiesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EntityStructureDTO entityStructureDTO = (EntityStructureDTO) o;
        if (entityStructureDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityStructureDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityStructureDTO{" +
            "id=" + getId() +
            ", category='" + getCategory() + "'" +
            ", capacity=" + getCapacity() +
            ", id=" + getIdId() +
            "}";
    }
}
