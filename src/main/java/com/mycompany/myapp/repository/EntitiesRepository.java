package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.Entities;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Entities entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntitiesRepository extends JpaRepository<Entities, Long> {

}
