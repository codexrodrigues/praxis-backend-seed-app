package io.github.codexrodrigues.praxis.seed.controller;

import io.github.codexrodrigues.praxis.seed.domain.Product;
import io.github.codexrodrigues.praxis.seed.dto.ProductDTO;
import io.github.codexrodrigues.praxis.seed.dto.filter.ProductFilterDTO;
import io.github.codexrodrigues.praxis.seed.mapper.ProductMapper;
import io.github.codexrodrigues.praxis.seed.service.ProductService;
import org.praxisplatform.uischema.annotation.ApiGroup;
import org.praxisplatform.uischema.annotation.ApiResource;
import org.praxisplatform.uischema.controller.base.AbstractReadOnlyController;
import org.praxisplatform.uischema.service.base.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Exemplo de recurso somente leitura para catálogo público de produtos.
 * Expõe apenas GETs e filtros; operações de escrita retornam 405.
 */
@ApiResource("/api/catalog/public-products")
@ApiGroup("catalog-public")
public class PublicProductController extends AbstractReadOnlyController<Product, ProductDTO, Long, ProductFilterDTO> {

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
        // Não é utilizado em read-only; implementação de conveniência
        return mapper.toEntity(dto);
    }
}

