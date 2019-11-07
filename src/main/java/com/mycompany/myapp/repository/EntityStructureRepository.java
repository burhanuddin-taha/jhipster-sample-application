package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.EntityStructure;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EntityStructure entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityStructureRepository extends JpaRepository<EntityStructure, Long> {

}
