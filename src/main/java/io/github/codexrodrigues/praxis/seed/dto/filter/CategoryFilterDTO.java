package io.github.codexrodrigues.praxis.seed.dto.filter;

import org.praxisplatform.uischema.extension.annotation.UISchema;
import org.praxisplatform.uischema.filter.annotation.Filterable;
import org.praxisplatform.uischema.filter.dto.GenericFilterDTO;

public class CategoryFilterDTO implements GenericFilterDTO {

    @UISchema(label = "Nome", placeholder = "Ex.: Eletrônicos")
    @Filterable(operation = Filterable.FilterOperation.LIKE)
    private String name;

    @UISchema(label = "Descrição")
    @Filterable(operation = Filterable.FilterOperation.LIKE)
    private String description;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

