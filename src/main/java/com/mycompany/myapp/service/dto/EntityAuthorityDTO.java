package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.EntityAuthority} entity.
 */
public class EntityAuthorityDTO implements Serializable {

    private Long id;

    private Integer userId;

    private Integer entityId;

    private String entityName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EntityAuthorityDTO entityAuthorityDTO = (EntityAuthorityDTO) o;
        if (entityAuthorityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entityAuthorityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EntityAuthorityDTO{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", entityId=" + getEntityId() +
            ", entityName='" + getEntityName() + "'" +
            "}";
    }
}
