package com.example.praxisseed.mapper;

import com.example.praxisseed.domain.Category;
import com.example.praxisseed.dto.CategoryDTO;
import org.mapstruct.Mapper;
import org.praxisplatform.uischema.mapper.config.CorporateMapperConfig;

@Mapper(componentModel = "spring", config = CorporateMapperConfig.class)
public interface CategoryMapper {
    CategoryDTO toDto(Category entity);
    Category toEntity(CategoryDTO dto);
}

