package io.github.codexrodrigues.praxis.seed.service;

import io.github.codexrodrigues.praxis.seed.domain.Product;
import io.github.codexrodrigues.praxis.seed.dto.ProductDTO;
import io.github.codexrodrigues.praxis.seed.dto.filter.ProductFilterDTO;
import io.github.codexrodrigues.praxis.seed.repository.ProductRepository;
import org.praxisplatform.uischema.filter.specification.GenericSpecificationsBuilder;
import org.praxisplatform.uischema.service.base.AbstractBaseCrudService;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends AbstractBaseCrudService<Product, ProductDTO, Long, ProductFilterDTO> {

    public ProductService(ProductRepository repository) {
        super(repository, new GenericSpecificationsBuilder<>(), Product.class);
    }
}

