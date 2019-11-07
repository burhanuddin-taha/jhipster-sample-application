package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.EntitiesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Entities} and its DTO {@link EntitiesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EntitiesMapper extends EntityMapper<EntitiesDTO, Entities> {


    @Mapping(target = "attributes", ignore = true)
    @Mapping(target = "removeAttributes", ignore = true)
    @Mapping(target = "ids", ignore = true)
    @Mapping(target = "removeId", ignore = true)
    Entities toEntity(EntitiesDTO entitiesDTO);

    default Entities fromId(Long id) {
        if (id == null) {
            return null;
        }
        Entities entities = new Entities();
        entities.setId(id);
        return entities;
    }
}
