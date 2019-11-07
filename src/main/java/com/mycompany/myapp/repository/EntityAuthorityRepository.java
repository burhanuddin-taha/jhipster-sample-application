package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.EntityAuthority;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EntityAuthority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntityAuthorityRepository extends JpaRepository<EntityAuthority, Long> {

}
