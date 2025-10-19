# Praxis Backend Seed App — Produtos/Categorias

Seed de referência usando o Praxis Metadata Starter com H2 (dev), MapStruct (fail‑fast), filtros tipados e resolução automática de grupos OpenAPI.

Principais recursos
- @ApiResource e @ApiGroup em controllers base
- DTOs com Jakarta Validation + @UISchema
- Filtros com @Filterable (joins via relation)
- OptionDTO (label via @OptionLabel) e endpoints de opções
- Ordenação padrão com @DefaultSortColumn
- Swagger UI com grupos específicos (resolução automática em /schemas/filtered)

Build e execução
- Pré‑requisitos: Java 21; rede habilitada para baixar dependências
- Compilar: `../../mvnw -B -DskipTests -T 1C package`
- Executar: `java -jar target/praxis-backend-seed-app-1.0.0-SNAPSHOT.jar`

Dependências do starter (Maven Central)
- Ajuste a propriedade `praxis.metadata.starter.version` no `pom.xml` para a versão publicada no Maven Central (ex.: `1.0.0`).

Rotas úteis
- Swagger UI: `/swagger-ui.html`
- OpenAPI JSON: `/v3/api-docs`
- Schemas filtrados (auto‑grupo): `/schemas/filtered?path=/api/catalog/products/all`

Recursos somente leitura (exemplo)
- Controller: `PublicProductController` estende `AbstractReadOnlyController`
- Base path: `/api/catalog/public-products`
- Leitura: `GET /api/catalog/public-products/all`, `POST /api/catalog/public-products/filter`
- Escrita bloqueada: `POST /api/catalog/public-products` (→ 405), `PUT /api/catalog/public-products/{id}` (→ 405), `DELETE ...` (→ 405)
- Schema: `/schemas/filtered?path=/api/catalog/public-products/all` inclui `readOnly=true` nos metadados do link

Perfis
- Dev (default): H2 in‑memory, H2 Console em `/h2-console` (JDBC: `jdbc:h2:mem:seeddb`)
