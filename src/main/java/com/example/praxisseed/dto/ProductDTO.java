package com.example.praxisseed.dto;

import jakarta.validation.constraints.*;
import org.praxisplatform.uischema.FieldControlType;
import org.praxisplatform.uischema.extension.annotation.UISchema;

import java.math.BigDecimal;

public class ProductDTO {
    private Long id;

    @NotBlank
    @Size(max = 160)
    @UISchema(label = "Produto", placeholder = "Ex.: Teclado Mecânico")
    private String name;

    @NotNull
    @DecimalMin(value = "0.00")
    @UISchema(label = "Preço", type = org.praxisplatform.uischema.FieldDataType.NUMBER,
            controlType = FieldControlType.CURRENCY_INPUT)
    private BigDecimal price;

    @NotNull
    @UISchema(label = "Categoria", controlType = FieldControlType.SELECT)
    private Long categoryId;

    @UISchema(label = "Categoria (nome)", readOnly = true)
    private String categoryName;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}

