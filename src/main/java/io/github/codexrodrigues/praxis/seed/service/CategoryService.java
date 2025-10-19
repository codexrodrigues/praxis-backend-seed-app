package io.github.codexrodrigues.praxis.seed.service;

import io.github.codexrodrigues.praxis.seed.domain.Category;
import io.github.codexrodrigues.praxis.seed.dto.CategoryDTO;
import io.github.codexrodrigues.praxis.seed.dto.filter.CategoryFilterDTO;
import io.github.codexrodrigues.praxis.seed.repository.CategoryRepository;
import org.praxisplatform.uischema.filter.specification.GenericSpecificationsBuilder;
import org.praxisplatform.uischema.service.base.AbstractBaseCrudService;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends AbstractBaseCrudService<Category, CategoryDTO, Long, CategoryFilterDTO> {

    public CategoryService(CategoryRepository repository) {
        super(repository, new GenericSpecificationsBuilder<>(), Category.class);
    }
}

