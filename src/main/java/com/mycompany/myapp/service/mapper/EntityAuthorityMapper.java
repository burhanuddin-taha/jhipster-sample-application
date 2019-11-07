package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EntityAuthorityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EntityAuthority} and its DTO {@link EntityAuthorityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EntityAuthorityMapper extends EntityMapper<EntityAuthorityDTO, EntityAuthority> {



    default EntityAuthority fromId(Long id) {
        if (id == null) {
            return null;
        }
        EntityAuthority entityAuthority = new EntityAuthority();
        entityAuthority.setId(id);
        return entityAuthority;
    }
}
