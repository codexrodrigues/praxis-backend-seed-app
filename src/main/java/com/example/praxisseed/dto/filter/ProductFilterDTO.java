package com.example.praxisseed.dto.filter;

import org.praxisplatform.uischema.filter.annotation.Filterable;
import org.praxisplatform.uischema.filter.dto.GenericFilterDTO;

import java.math.BigDecimal;
import java.util.List;

public class ProductFilterDTO extends GenericFilterDTO {

    @Filterable(operation = Filterable.FilterOperation.LIKE)
    private String name;

    // BETWEEN para preço: lista [min, max]
    @Filterable(operation = Filterable.FilterOperation.BETWEEN)
    private List<BigDecimal> priceRange;

    // Filtrar por nome da categoria via JOIN
    @Filterable(operation = Filterable.FilterOperation.LIKE, relation = "category.name")
    private String categoryName;

    // Filtrar por id de categoria (igualdade) — field direto
    @Filterable(operation = Filterable.FilterOperation.EQUAL, relation = "category.id")
    private Long categoryId;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<BigDecimal> getPriceRange() { return priceRange; }
    public void setPriceRange(List<BigDecimal> priceRange) { this.priceRange = priceRange; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
}

