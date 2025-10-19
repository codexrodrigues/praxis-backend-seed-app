package com.example.praxisseed.dto.filter;

import org.praxisplatform.uischema.filter.annotation.Filterable;
import org.praxisplatform.uischema.filter.dto.GenericFilterDTO;

public class CategoryFilterDTO extends GenericFilterDTO {

    @Filterable(operation = Filterable.FilterOperation.LIKE)
    private String name;

    @Filterable(operation = Filterable.FilterOperation.LIKE)
    private String description;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

