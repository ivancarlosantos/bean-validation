# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [Unreleased]

### Fixed

- Validações de `CEP`, `CPF`, `CNPJ`, `email`, `login`, `password` e `phone` agora lançam `VerifyFieldsException` quando a entrada não corresponde ao padrão definido em `RegexPatterns`.
- A validação passou a ocorrer antes da aplicação das máscaras internas, evitando erros de índice em entradas inválidas.

---

## [1.0.0] - 2026-03-11

### Added

- **`BeanValidationApplication`** — classe principal da aplicação Spring Boot com logging via Lombok `@Slf4j` e log de inicialização com status HTTP 200 OK.
- **Interface `Valid`** — contrato base para todos os validadores, definindo o método `execute(String value)`.
- **`VerifyFieldsException`** — exceção customizada do tipo `RuntimeException` lançada quando um campo não passa na validação.
- **`RegexPatterns`** — classe utilitária `final` com constantes de expressões regulares para:
  - `LOGIN` — permite caracteres alfanuméricos, pontos, underscores e hífens (3 a 20 caracteres).
  - `EMAIL` — valida formato padrão de endereço de e-mail.
  - `CPF` — valida o formato `###.###.###-##`.
  - `PHONE` — valida números de telefone brasileiros com ou sem código de país `+55`.
  - `CEP` — valida o formato `#####-###`.
  - `PASSWORD` — exige ao menos uma letra maiúscula, uma minúscula, um dígito, um caractere especial e mínimo de 8 caracteres.
- **`CPFValidator`** — implementação de `Valid` para validação de CPF com regex e tamanho máximo de 11 dígitos.
- **`CEPValidator`** — implementação de `Valid` para validação de CEP com regex e tamanho máximo de 9 caracteres.
- **`EmailValidator`** — implementação de `Valid` para validação de e-mail com regex.
- **`LoginValidator`** — implementação de `Valid` para validação de login com regex.
- **`PasswordValidator`** — implementação de `Valid` para validação de senha com regex e restrição de comprimento (8 a 12 caracteres).
- **`PhoneValidator`** — implementação de `Valid` para validação de número de telefone com regex e tamanho máximo de 10 dígitos.
- **`BeanValidationApplicationTests`** — teste de contexto Spring Boot para verificar a inicialização correta da aplicação.
- **Spring Boot Starter Web** — suporte a aplicações web com Spring MVC.
- **Spring Boot Starter Actuator** — endpoints de monitoramento e gerenciamento da aplicação.
- **SpringDoc OpenAPI (`springdoc-openapi-starter-webmvc-ui` 2.8.16)** — documentação interativa da API via Swagger UI.
- **Lombok** — geração automática de código boilerplate (getters, setters, logging, etc.).
- **Jackson Core (2.21.1)** — processamento de dados JSON.
- **Apache Commons Lang3 (3.20.0)** — utilitários adicionais para manipulação de Strings e objetos Java.
- **JUnit Jupiter (5.10.2)** — framework de testes unitários.
- **Mockito Core (5.11.0)** — framework de mocking para testes unitários.
- **Spring Boot Starter Test** — suporte completo a testes com Spring Boot.
- **PiTest (1.22.1) + pitest-junit5-plugin (1.2.1)** — framework de testes de mutação para avaliação da qualidade dos testes.
- **`maven-source-plugin` (3.3.1)** — geração e publicação do JAR de fontes (`sources.jar`).
- **`maven-javadoc-plugin` (3.11.3)** — geração e publicação do JAR de Javadoc (`javadoc.jar`).
- **`maven-gpg-plugin` (3.1.0)** — assinatura GPG dos artefatos publicados.
- **`central-publishing-maven-plugin` (0.6.0)** — publicação automatizada dos artefatos no Maven Central (Sonatype).
- Licença **Apache License 2.0** adicionada ao projeto.
- Configuração SCM apontando para o repositório `github.com/ivancarlosantos/bean-validation`.

### Changed

- Empacotamento definido como `jar` no `pom.xml`.
- Nome final do artefato gerado definido como `bean-validation` via `<finalName>` no `pom.xml`.
- Versão do Java configurada para **Java 21** (`maven.compiler.release=21`).
- Codificação do projeto definida como **UTF-8** (`project.build.sourceEncoding`).
- Spring Boot atualizado para a versão **3.5.11**.

### Deprecated

- Nenhuma funcionalidade obsoleta nesta versão.

### Removed

- Nenhuma funcionalidade removida nesta versão.

### Fixed

- Nenhuma correção de bug nesta versão (release inicial).

### Security

- Uso de expressões regulares robustas em `RegexPatterns` para evitar validações permissivas que possam aceitar entradas maliciosas (e.g., injeção em campos de CPF, e-mail e senha).
- `PasswordValidator` exige senha forte com obrigatoriedade de caracteres especiais, letras maiúsculas, minúsculas e dígitos, reduzindo a superfície de ataque por senhas fracas.
- Artefatos publicados no Maven Central assinados digitalmente com GPG via `maven-gpg-plugin`, garantindo integridade e autenticidade dos binários distribuídos.
- Lombok excluído do empacotamento final (`spring-boot-maven-plugin`), evitando exposição desnecessária de dependências de compile-time no artefato de produção.

---

[1.0.0]: https://github.com/ivancarlosantos/bean-validation/releases/tag/v1.0.0

