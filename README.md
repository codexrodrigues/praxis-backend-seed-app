# Praxis Backend Seed App — Produtos/Categorias

Seed de referência usando o Praxis Metadata Starter com H2 (dev), MapStruct (fail‑fast), filtros tipados e resolução automática de grupos OpenAPI.

## Quickstart

Três comandos para rodar localmente (clone → build → run):

```bash
git clone <URL-do-repositório> && cd praxis-backend-seed-app
mvn -B -DskipTests -T 1C package
java -jar target/praxis-backend-seed-app-1.0.0-SNAPSHOT.jar
```

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
- Starter no Maven Central: https://search.maven.org/artifact/io.github.codexrodrigues/praxis-metadata-starter
- Ajuste a propriedade `praxis.metadata.starter.version` no `pom.xml` para a versão publicada no Maven Central (ex.: `1.0.0`).

Exemplo (trecho do `pom.xml`):

```xml
<properties>
  <praxis.metadata.starter.version>1.0.0</praxis.metadata.starter.version>
</properties>
```

Rotas úteis
- Swagger UI: `/swagger-ui.html`
- OpenAPI JSON: `/v3/api-docs`
- Schemas filtrados (auto‑grupo): `/schemas/filtered?path=/api/catalog/products/all`

### Tabela de rotas principais e grupo de schema

| Base Path                           | Tipo       | Grupo OpenAPI     | Schema filtrado (exemplo)                                      |
|-------------------------------------|------------|-------------------|-----------------------------------------------------------------|
| `/api/catalog/products`             | CRUD       | `catalog`         | `/schemas/filtered?path=/api/catalog/products/all`              |
| `/api/catalog/categories`           | CRUD       | `catalog`         | `/schemas/filtered?path=/api/catalog/categories/all`            |
| `/api/catalog/public-products`      | Read-only  | `catalog-public`  | `/schemas/filtered?path=/api/catalog/public-products/all`       |

### cURL / Postman (básico)

Produtos (CRUD):
```bash
# GET paginado padrão / listagem
curl -s http://localhost:8080/api/catalog/products/all | jq .

# POST /filter com campos do ProductFilterDTO
curl -s -X POST http://localhost:8080/api/catalog/products/filter \
  -H 'Content-Type: application/json' \
  -d '{
        "name": "phone",
        "priceRange": [1000, 3000],
        "categoryName": "Eletr",
        "page": 0,
        "size": 20,
        "sort": ["id,desc"]
      }' | jq .
```

Categorias (CRUD):
```bash
curl -s http://localhost:8080/api/catalog/categories/all | jq .

curl -s -X POST http://localhost:8080/api/catalog/categories/filter \
  -H 'Content-Type: application/json' \
  -d '{
        "name": "Eletr",
        "description": "",
        "page": 0,
        "size": 10
      }' | jq .
```

Produtos públicos (somente leitura):
```bash
curl -s http://localhost:8080/api/catalog/public-products/all | jq .

curl -s -X POST http://localhost:8080/api/catalog/public-products/filter \
  -H 'Content-Type: application/json' \
  -d '{
        "name": "",
        "priceRange": [10, 50]
      }' | jq .
```

Recursos somente leitura (exemplo)
- Controller: `PublicProductController` estende `AbstractReadOnlyController`
- Base path: `/api/catalog/public-products`
- Leitura: `GET /api/catalog/public-products/all`, `POST /api/catalog/public-products/filter`
- Escrita bloqueada: `POST /api/catalog/public-products` (→ 405), `PUT /api/catalog/public-products/{id}` (→ 405), `DELETE ...` (→ 405)
- Schema: `/schemas/filtered?path=/api/catalog/public-products/all` inclui `readOnly=true` nos metadados do link

Perfis
- Dev (default): H2 in‑memory, H2 Console em `/h2-console` (JDBC: `jdbc:h2:mem:seeddb`)

## Nomes & Pacote

- Altere `groupId` e `artifactId` no `pom.xml` conforme seu domínio (ex.: `com.suaorg.catalog` e `catalog-service`).
- Ajuste o pacote base Java (`src/main/java/...`) e a classe principal (`PraxisBackendSeedApp`) para refletir o novo pacote.
- Convenções sugeridas:
  - Pacotes: `com.suaorg.<domínio>.(controller|service|repository|domain|dto)`
  - Recursos REST: nomes no plural, base paths prefixados (ex.: `/api/catalog/products`).
  - Grupos OpenAPI via `@ApiGroup` curtos e consistentes (ex.: `catalog`, `catalog-public`).

## Licença e Políticas

- Licença: Apache-2.0 (arquivo `LICENSE`).
- Código de Conduta: `CODE_OF_CONDUCT.md` (Contributor Covenant).
- Contribuição: `CONTRIBUTING.md`.
- Segurança (vulnerabilidades): `SECURITY.md`.
