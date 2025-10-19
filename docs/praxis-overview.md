# Visão do Praxis e do Starter

Este documento resume como o Praxis (metadata de UI) funciona e como o seed aplica o `praxis-metadata-starter`.

## Componentes‑chave (starter)

- Auto‑configuração base: praxis/backend-libs/praxis-metadata-starter/src/main/java/org/praxisplatform/uischema/configuration/PraxisMetadataAutoConfiguration.java:1
- Beans de grupos e `/schemas/filtered`: praxis/backend-libs/praxis-metadata-starter/src/main/java/org/praxisplatform/uischema/configuration/OpenApiUiSchemaAutoConfiguration.java:1
- Controller base CRUD: praxis/backend-libs/praxis-metadata-starter/src/main/java/org/praxisplatform/uischema/controller/base/AbstractCrudController.java:1
- Controller base Read‑Only: praxis/backend-libs/praxis-metadata-starter/src/main/java/org/praxisplatform/uischema/controller/base/AbstractReadOnlyController.java:1
- Anotação de UI: praxis/backend-libs/praxis-metadata-starter/src/main/java/org/praxisplatform/uischema/extension/annotation/UISchema.java:1
- Filtros tipados: praxis/backend-libs/praxis-metadata-starter/src/main/java/org/praxisplatform/uischema/filter
- Erros globais: praxis/backend-libs/praxis-metadata-starter/src/main/java/org/praxisplatform/uischema/rest/exceptionhandler/GlobalExceptionHandler.java:1

## Fluxo de Enriquecimento de Metadados (x‑ui)

```mermaid
graph TD
  subgraph Entrada
    A[Java DTO]
    B[@UISchema]
    C[Jakarta Validation]
    D[@Schema OpenAPI]
  end
  subgraph Enriquecimento
    E(1. Defaults @UISchema)
    F(2. Detecção OpenAPI)
    G(3. Valores explícitos @UISchema)
    H(4. Regras de validação)
    I(5. Extras)
  end
  subgraph Saída
    J[OpenAPI com extensão x‑ui]
  end
  A --> E
  B --> E
  D --> F
  B --> G
  C --> H
  B --> I
  E --> F --> G --> H --> I --> J
```

Ordem de precedência (menor→maior):
1) Defaults do `@UISchema`; 2) Detecção OpenAPI; 3) Valores explícitos do `@UISchema`; 4) Jakarta Validation; 5) Propriedades extras.

## Estratégia de Grupos OpenAPI

- Grupos individuais automáticos por recurso (derivados de `@ApiResource`).
- Grupos agregados por contexto com `@ApiGroup`.
- Grupos fixos: `praxis-metadata-infra` e `application`.
- Resolução automática em `/schemas/filtered?path=<base>/<op>` via “best match”.

Benefícios: documentos menores, cache eficiente e navegação rápida no Swagger UI.

## Como o seed aplica

- Controllers com `@ApiResource` e `@ApiGroup` (`catalog`, `catalog-public`).
- DTOs com validação Jakarta e `@UISchema` (formulários e filtros).
- Filtros tipados com `@Filterable` e `relation` para joins.
- Endpoints de opções: `/options/filter` e `/options/by-ids` para selects.
- HATEOAS habilitado; cabeçalho `X-Data-Version` disponível quando implementado.

