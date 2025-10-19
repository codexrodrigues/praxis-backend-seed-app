package com.example.praxisseed.mapper;

import com.example.praxisseed.domain.Category;
import com.example.praxisseed.domain.Product;
import com.example.praxisseed.dto.ProductDTO;
import org.mapstruct.*;
import org.praxisplatform.uischema.mapper.config.CorporateMapperConfig;

@Mapper(componentModel = "spring", config = CorporateMapperConfig.class)
public interface ProductMapper {

    @Mappings({
            @Mapping(target = "categoryId", source = "category.id"),
            @Mapping(target = "categoryName", source = "category.name")
    })
    ProductDTO toDto(Product entity);

    @Mappings({
            @Mapping(target = "category", source = "categoryId", qualifiedByName = "categoryFromId")
    })
    Product toEntity(ProductDTO dto);

    @Named("categoryFromId")
    default Category categoryFromId(Long id) {
        if (id == null) return null;
        Category c = new Category();
        c.setId(id);
        return c;
    }
}

