package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.BusinessTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BusinessType} and its DTO {@link BusinessTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {EntitiesMapper.class})
public interface BusinessTypeMapper extends EntityMapper<BusinessTypeDTO, BusinessType> {

    @Mapping(source = "entities.id", target = "entitiesId")
    BusinessTypeDTO toDto(BusinessType businessType);

    @Mapping(source = "entitiesId", target = "entities")
    BusinessType toEntity(BusinessTypeDTO businessTypeDTO);

    default BusinessType fromId(Long id) {
        if (id == null) {
            return null;
        }
        BusinessType businessType = new BusinessType();
        businessType.setId(id);
        return businessType;
    }
}
