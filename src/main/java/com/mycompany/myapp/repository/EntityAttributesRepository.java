package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.EntityAttributes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EntityAttributes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityAttributesRepository extends JpaRepository<EntityAttributes, Long> {

}
