package io.github.codexrodrigues.praxis.seed.mapper;

import io.github.codexrodrigues.praxis.seed.domain.Category;
import io.github.codexrodrigues.praxis.seed.dto.CategoryDTO;
import org.mapstruct.Mapper;
import org.praxisplatform.uischema.mapper.config.CorporateMapperConfig;

@Mapper(componentModel = "spring", config = CorporateMapperConfig.class)
public interface CategoryMapper {
    CategoryDTO toDto(Category entity);
    Category toEntity(CategoryDTO dto);
}

