package io.github.codexrodrigues.praxis.seed.dto.filter;

import org.praxisplatform.uischema.FieldControlType;
import org.praxisplatform.uischema.FieldDataType;
import org.praxisplatform.uischema.NumericFormat;
import org.praxisplatform.uischema.extension.annotation.UISchema;
import org.praxisplatform.uischema.filter.annotation.Filterable;
import org.praxisplatform.uischema.filter.dto.GenericFilterDTO;

import java.math.BigDecimal;
import java.util.List;

public class ProductFilterDTO implements GenericFilterDTO {

    @UISchema(label = "Nome do produto", placeholder = "Ex.: Teclado")
    @Filterable(operation = Filterable.FilterOperation.LIKE)
    private String name;

    // BETWEEN para preço: lista [min, max]
    @UISchema(type = FieldDataType.NUMBER,
            controlType = FieldControlType.RANGE_SLIDER,
            numericFormat = NumericFormat.CURRENCY,
            numericStep = "0.01")
    @Filterable(operation = Filterable.FilterOperation.BETWEEN)
    private List<BigDecimal> priceRange;

    // Filtrar por nome da categoria via JOIN
    @UISchema(label = "Categoria (nome)", placeholder = "Ex.: Eletrônicos")
    @Filterable(operation = Filterable.FilterOperation.LIKE, relation = "category.name")
    private String categoryName;

    // Filtrar por id de categoria (igualdade) — field direto, via SELECT
    @UISchema(type = FieldDataType.NUMBER, controlType = FieldControlType.SELECT,
            endpoint = "/api/catalog/categories/options/filter",
            valueField = "id", displayField = "label")
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

