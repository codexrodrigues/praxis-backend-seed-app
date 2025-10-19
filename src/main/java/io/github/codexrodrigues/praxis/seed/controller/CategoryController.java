package io.github.codexrodrigues.praxis.seed.controller;

import io.github.codexrodrigues.praxis.seed.domain.Category;
import io.github.codexrodrigues.praxis.seed.dto.CategoryDTO;
import io.github.codexrodrigues.praxis.seed.dto.filter.CategoryFilterDTO;
import io.github.codexrodrigues.praxis.seed.mapper.CategoryMapper;
import io.github.codexrodrigues.praxis.seed.service.CategoryService;
import org.praxisplatform.uischema.annotation.ApiGroup;
import org.praxisplatform.uischema.annotation.ApiResource;
import org.praxisplatform.uischema.controller.base.AbstractCrudController;
import org.praxisplatform.uischema.service.base.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;

@ApiResource("/api/catalog/categories")
@ApiGroup("catalog")
public class CategoryController extends AbstractCrudController<Category, CategoryDTO, Long, CategoryFilterDTO> {

    @Autowired
    private CategoryService service;

    @Autowired
    private CategoryMapper mapper;

    @Override
    protected BaseCrudService<Category, CategoryDTO, Long, CategoryFilterDTO> getService() {
        return service;
    }

    @Override
    protected CategoryDTO toDto(Category entity) {
        return mapper.toDto(entity);
    }

    @Override
    protected Category toEntity(CategoryDTO dto) {
        return mapper.toEntity(dto);
    }
}

