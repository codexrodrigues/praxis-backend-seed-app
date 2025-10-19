package com.example.praxisseed.controller;

import com.example.praxisseed.domain.Product;
import com.example.praxisseed.dto.ProductDTO;
import com.example.praxisseed.dto.filter.ProductFilterDTO;
import com.example.praxisseed.mapper.ProductMapper;
import com.example.praxisseed.service.ProductService;
import org.praxisplatform.uischema.annotation.ApiGroup;
import org.praxisplatform.uischema.annotation.ApiResource;
import org.praxisplatform.uischema.controller.base.AbstractCrudController;
import org.praxisplatform.uischema.service.base.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;

@ApiResource("/api/catalog/products")
@ApiGroup("catalog")
public class ProductController extends AbstractCrudController<Product, ProductDTO, Long, ProductFilterDTO> {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductMapper mapper;

    @Override
    protected BaseCrudService<Product, ProductDTO, Long, ProductFilterDTO> getService() {
        return service;
    }

    @Override
    protected ProductDTO toDto(Product entity) {
        return mapper.toDto(entity);
    }

    @Override
    protected Product toEntity(ProductDTO dto) {
        return mapper.toEntity(dto);
    }
}

