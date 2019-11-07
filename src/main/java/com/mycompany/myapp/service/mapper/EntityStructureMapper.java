package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EntityStructureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EntityStructure} and its DTO {@link EntityStructureDTO}.
 */
@Mapper(componentModel = "spring", uses = {EntitiesMapper.class})
public interface EntityStructureMapper extends EntityMapper<EntityStructureDTO, EntityStructure> {

    @Mapping(source = "entities.id", target = "entitiesId")
    EntityStructureDTO toDto(EntityStructure entityStructure);

    @Mapping(source = "entitiesId", target = "entities")
    EntityStructure toEntity(EntityStructureDTO entityStructureDTO);

    default EntityStructure fromId(Long id) {
        if (id == null) {
            return null;
        }
        EntityStructure entityStructure = new EntityStructure();
        entityStructure.setId(id);
        return entityStructure;
    }
}
