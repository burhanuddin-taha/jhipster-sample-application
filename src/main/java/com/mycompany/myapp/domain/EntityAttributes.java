package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A EntityAttributes.
 */
@Entity
@Table(name = "entity_attributes")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EntityAttributes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "categoty_name")
    private String categotyName;

    @ManyToOne
    @JsonIgnoreProperties("attributes")
    private Entities entities;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategotyName() {
        return categotyName;
    }

    public EntityAttributes categotyName(String categotyName) {
        this.categotyName = categotyName;
        return this;
    }

    public void setCategotyName(String categotyName) {
        this.categotyName = categotyName;
    }

    public Entities getEntities() {
        return entities;
    }

    public EntityAttributes entities(Entities entities) {
        this.entities = entities;
        return this;
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityAttributes)) {
            return false;
        }
        return id != null && id.equals(((EntityAttributes) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EntityAttributes{" +
            "id=" + getId() +
            ", categotyName='" + getCategotyName() + "'" +
            "}";
    }
}
