package com.example.praxisseed.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.praxisplatform.uischema.extension.annotation.UISchema;

public class CategoryDTO {
    private Long id;

    @NotBlank
    @Size(max = 120)
    @UISchema(label = "Nome da Categoria", placeholder = "Ex.: Hardware")
    private String name;

    @Size(max = 255)
    @UISchema(label = "Descrição", controlType = org.praxisplatform.uischema.FieldControlType.TEXTAREA)
    private String description;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

