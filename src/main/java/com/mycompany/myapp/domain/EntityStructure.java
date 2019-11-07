package com.mycompany.myapp.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A EntityStructure.
 */
@Entity
@Table(name = "entity_structure")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EntityStructure implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category")
    private String category;

    @Column(name = "capacity")
    private Long capacity;

    @ManyToOne
    @JsonIgnoreProperties("ids")
    private Entities entities;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public EntityStructure category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getCapacity() {
        return capacity;
    }

    public EntityStructure capacity(Long capacity) {
        this.capacity = capacity;
        return this;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Entities getEntities() {
        return entities;
    }

    public EntityStructure entities(Entities entities) {
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
        if (!(o instanceof EntityStructure)) {
            return false;
        }
        return id != null && id.equals(((EntityStructure) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EntityStructure{" +
            "id=" + getId() +
            ", category='" + getCategory() + "'" +
            ", capacity=" + getCapacity() +
            "}";
    }
}
