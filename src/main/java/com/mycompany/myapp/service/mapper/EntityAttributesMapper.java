package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EntityAttributesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EntityAttributes} and its DTO {@link EntityAttributesDTO}.
 */
@Mapper(componentModel = "spring", uses = {EntitiesMapper.class})
public interface EntityAttributesMapper extends EntityMapper<EntityAttributesDTO, EntityAttributes> {

    @Mapping(source = "id.id", target = "idId")
    EntityAttributesDTO toDto(EntityAttributes entityAttributes);

    @Mapping(source = "idId", target = "id")
    EntityAttributes toEntity(EntityAttributesDTO entityAttributesDTO);

    default EntityAttributes fromId(Long id) {
        if (id == null) {
            return null;
        }
        EntityAttributes entityAttributes = new EntityAttributes();
        entityAttributes.setId(id);
        return entityAttributes;
    }
}
