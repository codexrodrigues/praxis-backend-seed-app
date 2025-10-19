# Praxis Backend Seed App — Produtos/Categorias

Seed de referência usando o Praxis Metadata Starter com H2 (dev), MapStruct (fail‑fast), filtros tipados e resolução automática de grupos OpenAPI.

## Sobre o Projeto Praxis e o Starter

O Praxis é um conjunto de bibliotecas para tornar APIs autodescritivas, expondo metadados de UI a partir do backend.
Este seed usa o módulo Spring Boot `praxis-metadata-starter` para:

- Resolver grupos OpenAPI automaticamente a partir dos paths dos controllers
- Enriquecer o OpenAPI com `x-ui` via `@UISchema` e validações Jakarta
- Fornecer controllers base com HATEOAS e auto-detecção de path
- Oferecer filtros JPA tipados com `@Filterable` (incluindo joins por `relation`)
- Expor endpoints de opções (`OptionDTO`) e limites de segurança configuráveis

Referências úteis do starter (código e docs):
- Auto-configuração: praxis/backend-libs/praxis-metadata-starter/src/main/java/org/praxisplatform/uischema/configuration/PraxisMetadataAutoConfiguration.java:1
- Beans principais (grupos e `/schemas/filtered`): praxis/backend-libs/praxis-metadata-starter/src/main/java/org/praxisplatform/uischema/configuration/OpenApiUiSchemaAutoConfiguration.java:1
- Controller base: praxis/backend-libs/praxis-metadata-starter/src/main/java/org/praxisplatform/uischema/controller/base/AbstractCrudController.java:1
- Filtros: praxis/backend-libs/praxis-metadata-starter/src/main/java/org/praxisplatform/uischema/filter
- GlobalExceptionHandler: praxis/backend-libs/praxis-metadata-starter/src/main/java/org/praxisplatform/uischema/rest/exceptionhandler/GlobalExceptionHandler.java:1
- Docs técnicas (starter): praxis/backend-libs/praxis-metadata-starter/docs/technical/README.md:1

## Quickstart

Três comandos para rodar localmente (clone → build → run):

```bash
git clone <URL-do-repositório> && cd praxis-backend-seed-app
mvn -B -DskipTests -T 1C package
java -jar target/praxis-backend-seed-1.0.0-SNAPSHOT.jar
```

Para uma visão conceitual e técnica do starter, consulte: `docs/praxis-overview.md`.

Principais recursos
- @ApiResource e @ApiGroup em controllers base
- DTOs com Jakarta Validation + @UISchema
- Filtros com @Filterable (joins via relation)
- OptionDTO (label via @OptionLabel) e endpoints de opções
- Ordenação padrão com @DefaultSortColumn
- Swagger UI com grupos específicos (resolução automática em /schemas/filtered)

### Como o Starter opera aqui

- Grupos OpenAPI
  - Grupos individuais por recurso são criados automaticamente a partir de `@ApiResource`.
  - Grupos agregados podem ser definidos com `@ApiGroup` para contextos (ex.: `catalog`, `catalog-public`).
  - Grupos fixos de infra e fallback são expostos: `praxis-metadata-infra` e `application`.
- Schemas filtrados e x-ui
  - `GET /schemas/filtered?path=<base>/all` resolve o grupo correto e retorna OpenAPI enriquecido com `x-ui`.
  - `@UISchema` e validações Jakarta são refletidos em `x-ui.validation`.
- Opções (OptionDTO)
  - `POST /{resource}/options/filter` → `OptionDTO {id,label,extra}` para SELECTs/autocomplete.
  - `GET /{resource}/options/by-ids` reidrata opções preservando a ordem.
  - `@OptionLabel` na entidade garante label preciso sem heurística.
- Filtros tipados
  - `@Filterable(operation=..., relation=...)` com joins por `relation` (ex.: `category.id`).
  - UI sugerida para filtros: texto=LIKE; números/datas=BETWEEN com RANGE_SLIDER/DATE_RANGE; boolean=CHECKBOX; relações=SELECT com endpoint de options.
- HATEOAS e versionamento lógico
  - Links automáticos: self, all, filter e create/update/delete (desabilitados em read-only).
  - Cabeçalho `X-Data-Version` é incluído quando o service expõe `getDatasetVersion()`.
- BigDecimal no OpenAPI
  - Mapeado para `type:number, format:decimal` pela auto‑configuração.

Propriedades úteis (limites e políticas):
- `praxis.pagination.max-size` → tamanho máximo de página (ex.: 200)
- `praxis.query.by-ids.max` → limite de IDs em `by-ids` (ex.: 200)
- `praxis.openapi.validation.api-resource-required` → `WARN|FAIL|IGNORE` (neste seed: DEV=WARN, PROD=FAIL)

Build e execução
- Pré‑requisitos: Java 21; rede habilitada para baixar dependências
- Compilar: `../../mvnw -B -DskipTests -T 1C package`
- Executar: `java -jar target/praxis-backend-seed-1.0.0-SNAPSHOT.jar`

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

Opções — by-ids (reidratar opções selecionadas):
```bash
curl -s "http://localhost:8080/api/catalog/categories/options/by-ids?ids=1&ids=3" | jq .
```

Schemas filtrados (avançado):
```bash
# Response schema de GET /products/all
curl -s "http://localhost:8080/schemas/filtered?path=/api/catalog/products/all&operation=get&schemaType=response" | jq .

# Request schema de POST /products/filter
curl -s "http://localhost:8080/schemas/filtered?path=/api/catalog/products/filter&operation=post&schemaType=request" | jq .
```

Recursos somente leitura (exemplo)
- Controller: `PublicProductController` estende `AbstractReadOnlyController`
- Base path: `/api/catalog/public-products`
- Leitura: `GET /api/catalog/public-products/all`, `POST /api/catalog/public-products/filter`
- Escrita bloqueada: `POST /api/catalog/public-products` (→ 405), `PUT /api/catalog/public-products/{id}` (→ 405), `DELETE ...` (→ 405)
- Schema: `/schemas/filtered?path=/api/catalog/public-products/all` inclui `readOnly=true` nos metadados do link

Perfis
- Dev (default): H2 in‑memory, H2 Console em `/h2-console` (JDBC: `jdbc:h2:mem:seeddb`)

## Auditoria guiada (do starter)

- Índice: praxis/backend-libs/praxis-metadata-starter/docs/audit/README.md:1
- Checklist consolidado: praxis/backend-libs/praxis-metadata-starter/docs/audit/CHECKLIST-GERAL.md:1
- Fases recomendadas:
  - Fase 1 — Build e Dependências: praxis/backend-libs/praxis-metadata-starter/docs/audit/fases/FASE-01-BUILD-E-DEPENDENCIAS.md:1
  - Fase 2 — Controllers e Grupos: praxis/backend-libs/praxis-metadata-starter/docs/audit/fases/FASE-02-CONTROLLERS-E-GRUPOS-OPENAPI.md:1
  - Fase 3 — DTOs, Validação e @UISchema: praxis/backend-libs/praxis-metadata-starter/docs/audit/fases/FASE-03-DTOS-VALIDACAO-UISCHEMA.md:1
  - Fase 4 — Services, Options e Sort: praxis/backend-libs/praxis-metadata-starter/docs/audit/fases/FASE-04-SERVICES-REPOS-OPTIONS-SORT.md:1
  - Fase 5 — Filtros, Paginação e Opções: praxis/backend-libs/praxis-metadata-starter/docs/audit/fases/FASE-05-FILTROS-PAGINACAO-OPCOES.md:1
  - Fase 6 — Erros, HATEOAS e ETag: praxis/backend-libs/praxis-metadata-starter/docs/audit/fases/FASE-06-ERROS-HATEOAS-OPENAPI-ETAG.md:1
  - Fase 7 — MapStruct (Fail-fast): praxis/backend-libs/praxis-metadata-starter/docs/audit/fases/FASE-07-MAPSTRUCT-FAIL-FAST.md:1
  - Fase 8 — Auto-config e Grupos fixos: praxis/backend-libs/praxis-metadata-starter/docs/audit/fases/FASE-08-AUTO-CONFIG-GRUPOS-FALLBACK.md:1

## Auditoria e Boas Práticas (extraído do starter)

- Build e dependências: Java 21 + Maven Wrapper; starter presente no POM.
- Controllers e grupos: `@ApiResource` obrigatório; `@ApiGroup` cria contextos agregados coesos.
- DTOs e validação: anote campos críticos; `@Valid` em create/update (no controller base já coberto).
- Services e opções: herde `AbstractBaseCrudService`; use `@OptionLabel`; ordene com `@DefaultSortColumn`.
- Filtros e paginação: `@Filterable` por campo; `relation` para joins; limites de `size` e `by-ids` aplicados.
- Erros e HATEOAS: payload padronizado via `GlobalExceptionHandler`; links automáticos; `X-Data-Version` quando aplicável.
- MapStruct (fail‑fast): mappers com `CorporateMapperConfig` e unmapped como erro.
- Auto‑config e grupos fixos: `praxis-metadata-infra` e `application`; BigDecimal → `number/decimal`.

Leituras recomendadas (no starter):
- Auto‑configuração: praxis/backend-libs/praxis-metadata-starter/docs/technical/AUTO-CONFIGURACAO.md:1
- Estratégia de Grupos OpenAPI: praxis/backend-libs/praxis-metadata-starter/docs/technical/ESTRATEGIA-DUPLA-GRUPOS-OPENAPI.md:1
- Validação @ApiResource: praxis/backend-libs/praxis-metadata-starter/docs/technical/VALIDACAO-API-RESOURCE.md:1

## Nomes & Pacote

- Pacote base padrão deste seed: `io.github.codexrodrigues.praxis.seed`.
- `groupId` padrão atualizado para `io.github.codexrodrigues.praxis.seed` no `pom.xml`.
- Você pode substituir por seu domínio (ex.: `com.suaorg.catalog`) e ajustar a classe principal (`PraxisBackendSeedApp`).
- Convenções sugeridas:
  - Pacotes: `com.suaorg.<domínio>.(controller|service|repository|domain|dto)`
  - Recursos REST: nomes no plural, base paths prefixados (ex.: `/api/catalog/products`).
  - Grupos OpenAPI via `@ApiGroup` curtos e consistentes (ex.: `catalog`, `catalog-public`).

## Licença e Políticas

- Licença: Apache-2.0 (arquivo `LICENSE`).
- Código de Conduta: `CODE_OF_CONDUCT.md` (Contributor Covenant).
- Contribuição: `CONTRIBUTING.md`.
- Segurança (vulnerabilidades): `SECURITY.md`.
