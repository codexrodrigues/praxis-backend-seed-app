package com.example.praxisseed.controller;

import com.example.praxisseed.domain.Category;
import com.example.praxisseed.dto.CategoryDTO;
import com.example.praxisseed.dto.filter.CategoryFilterDTO;
import com.example.praxisseed.mapper.CategoryMapper;
import com.example.praxisseed.service.CategoryService;
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

