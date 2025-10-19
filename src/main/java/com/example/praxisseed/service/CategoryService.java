package com.example.praxisseed.service;

import com.example.praxisseed.domain.Category;
import com.example.praxisseed.dto.CategoryDTO;
import com.example.praxisseed.dto.filter.CategoryFilterDTO;
import com.example.praxisseed.repository.CategoryRepository;
import org.praxisplatform.uischema.filter.specification.GenericSpecificationsBuilder;
import org.praxisplatform.uischema.service.base.AbstractBaseCrudService;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends AbstractBaseCrudService<Category, CategoryDTO, Long, CategoryFilterDTO> {

    public CategoryService(CategoryRepository repository) {
        super(repository, new GenericSpecificationsBuilder<>(), Category.class);
    }
}

