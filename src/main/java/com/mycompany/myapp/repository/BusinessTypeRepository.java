package com.mycompany.myapp.repository;
import com.mycompany.myapp.domain.BusinessType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BusinessType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BusinessTypeRepository extends JpaRepository<BusinessType, Long> {

}
