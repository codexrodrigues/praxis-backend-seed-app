package com.example.praxisseed.service;

import com.example.praxisseed.domain.Product;
import com.example.praxisseed.dto.ProductDTO;
import com.example.praxisseed.dto.filter.ProductFilterDTO;
import com.example.praxisseed.repository.ProductRepository;
import org.praxisplatform.uischema.filter.specification.GenericSpecificationsBuilder;
import org.praxisplatform.uischema.service.base.AbstractBaseCrudService;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends AbstractBaseCrudService<Product, ProductDTO, Long, ProductFilterDTO> {

    public ProductService(ProductRepository repository) {
        super(repository, new GenericSpecificationsBuilder<>(), Product.class);
    }
}

