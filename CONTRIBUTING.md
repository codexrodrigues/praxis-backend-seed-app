# Contribuindo

Obrigado por considerar uma contribuição! Este guia ajuda você a começar.

## Pré‑requisitos

- Java 21+
- Maven 3.9+

## Build e execução

```bash
mvn -B -DskipTests -T 1C package
java -jar target/praxis-backend-seed-1.0.0-SNAPSHOT.jar
```

Swagger UI: `http://localhost:8080/swagger-ui.html`

## Estilo e padrões

- Java: siga o estilo existente no projeto.
- Commits: mensagens claras, no imperativo curto (ex.: "Add product filter by price").
- PRs: descreva o problema, a solução e impactos; inclua testes quando aplicável.

## Abrindo issues

- Descreva o comportamento atual e o esperado.
- Inclua passos de reprodução e contexto (versões, logs, SO).

## Processos de PR

- Vincule a issue (se houver) e detalhe escopo/limitações.
- Mantenha PRs pequenos e focados.
- Aguarde a revisão de pelo menos 1 mantenedor.

## Segurança

Não relate vulnerabilidades publicamente. Siga `SECURITY.md`.
