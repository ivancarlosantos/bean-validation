# bean-validation

Biblioteca Java para validação de campos comuns com foco em **simples uso**, **retorno da própria entrada validada** e **padronização de formatos**.

O projeto oferece uma fachada (`ValidationFacade`) para validar dados como:

- `CEP`
- `CPF`
- `CNPJ`
- `email`
- `login`
- `password`
- `phone`

Além disso, o projeto expõe padrões regex reutilizáveis e utilitários de máscara para ocultar dados sensíveis em logs.

---

## Visão geral

A biblioteca foi organizada em alguns blocos principais:

- `facade/ValidationFacade` — ponto de entrada mais simples para uso externo.
- `validator/*` — implementações individuais de validação.
- `pattern/RegexPatterns` — expressões regulares reutilizáveis.
- `masks/MaskedFields` — mascaramento de campos sensíveis antes de exibir valores.
- `valid/Valid` e `valid/Masquerade` — contratos das operações de validação e máscara.

Em termos de comportamento, os validadores:

1. recebem uma `String`;
2. verificam o formato esperado;
3. retornam a própria string quando o valor é válido;
4. lançam `VerifyFieldsException` quando o formato é inválido.

> Observação: a validação acontece antes da aplicação das máscaras internas, evitando erros de índice em entradas inválidas e garantindo uma mensagem de domínio consistente.

---

## Requisitos

- Java 21+
- Maven

---

## Como usar

### 1. Dependência Maven

Se você publicar/consumir o artefato em outro projeto Maven, adicione a dependência correspondente ao seu repositório/versão.

```xml
<dependency>
	<groupId>io.github.ivancarlosantos</groupId>
	<artifactId>bean-validation</artifactId>
	<version>1.2.7</version>
</dependency>
```

### 2. Uso pela fachada

A maneira mais simples é usar a `ValidationFacade`:

```java
import io.github.ivancarlosantos.bean_validation.facade.ValidationFacade;

public class Example {
	public static void main(String[] args) {
		String cep = ValidationFacade.cep("12345-678");
		String cpf = ValidationFacade.cpf("123.456.789-09");
		String cnpj = ValidationFacade.cnpj("59.456.277/0001-76");
		String email = ValidationFacade.email("test@example.com");
		String login = ValidationFacade.login("user123");
		String password = ValidationFacade.password("Password1@");
		String phone = ValidationFacade.phone("+55 (11) 99999-9999");

		System.out.println(cep);
		System.out.println(cpf);
		System.out.println(cnpj);
		System.out.println(email);
		System.out.println(login);
		System.out.println(password);
		System.out.println(phone);
	}
}
```

### 3. Uso direto dos validadores

Se você quiser chamar a classe concreta, também pode:

```java
import io.github.ivancarlosantos.bean_validation.validator.EmailValidator;

public class Example {
	public static void main(String[] args) {
		String email = new EmailValidator().execute("user.name@domain.org");
		System.out.println(email);
	}
}
```

---

## Exemplo de tratamento de erro

```java
try {
	String cpf = ValidationFacade.cpf("BAD");
	System.out.println(cpf);
} catch (io.github.ivancarlosantos.bean_validation.exception.VerifyFieldsException ex) {
	System.out.println("CPF inválido: " + ex.getMessage());
}
```

---

## Formatos aceitos

| Campo | Formato aceito | Exemplo válido |
| --- | --- | --- |
| CEP | `12345-678` ou `12345678` | `01310-100` |
| CPF | `123.456.789-09` | `987.654.321-00` |
| CNPJ | `59.456.277/0001-76` | `12.345.678/9012-34` |
| Email | padrão com `@` e domínio válido | `user.name+tag@domain.org` |
| Login | 3 a 20 caracteres: letras, números, `.`, `_`, `-` | `user_name.ok` |
| Password | ao menos 1 maiúscula, 1 minúscula, 1 número, 1 caractere especial e mínimo de 8 caracteres | `Password1@` |
| Phone | Brasil com `55`, com ou sem `+`, com ou sem espaços/parênteses/hífen | `+55 (11) 99999-9999` |

---

## Validação por campo

### CEP

Aceita CEP com ou sem hífen:

```java
ValidationFacade.cep("12345-678");
ValidationFacade.cep("12345678");
```

### CPF

Aceita CPF no formato pontuado:

```java
ValidationFacade.cpf("123.456.789-09");
```

### CNPJ

Aceita CNPJ no formato tradicional:

```java
ValidationFacade.cnpj("59.456.277/0001-76");
```

### Email

Aceita emails comuns, inclusive com subdomínios e `+tag`:

```java
ValidationFacade.email("user+tag@example.co.uk");
```

### Login

Aceita logins curtos e médios com letras, números e separadores simples:

```java
ValidationFacade.login("user123");
ValidationFacade.login("User_Name");
ValidationFacade.login("user-name");
```

### Password

Exige senha forte com letras maiúsculas, minúsculas, número e caractere especial:

```java
ValidationFacade.password("S3cur3P@ss");
```

### Phone

Aceita telefone brasileiro com variações de formatação:

```java
ValidationFacade.phone("5511999999999");
ValidationFacade.phone("+5511999999999");
ValidationFacade.phone("55(11)99999-9999");
ValidationFacade.phone("+55 (11) 99999-9999");
```

---

## Aplicação prática

Esta biblioteca é útil quando você quer:

- validar campos de entrada antes de persistir em banco;
- proteger regras de formato em APIs REST, serviços e formulários;
- reaproveitar validações em vários pontos do sistema;
- centralizar formatos aceitos em um único pacote;
- padronizar mensagens e comportamento de validação.

### Cenários comuns

- cadastro de usuários;
- cadastro de clientes e empresas;
- validação de contatos;
- validação de formulários internos;
- pré-validação antes de integrar com outros sistemas.

---

## Padrões regex disponíveis

Se você quiser validar manualmente com `String.matches(...)`, os padrões ficam em `RegexPatterns`:

```java
import io.github.ivancarlosantos.bean_validation.pattern.RegexPatterns;

boolean ok = "test@example.com".matches(RegexPatterns.EMAIL);
```

Constantes disponíveis:

- `RegexPatterns.LOGIN`
- `RegexPatterns.EMAIL`
- `RegexPatterns.CPF`
- `RegexPatterns.CNPJ`
- `RegexPatterns.PHONE`
- `RegexPatterns.CEP`
- `RegexPatterns.PASSWORD`

---

## Máscaras de campos

O pacote `masks/MaskedFields` fornece máscaras para ocultar dados ao exibir valores no console ou em logs:

- `cepMask`
- `cpfMask`
- `cnpjMask`
- `emailMask`
- `loginMask`
- `passwordMask`
- `phoneMask`

Exemplo:

```java
import io.github.ivancarlosantos.bean_validation.masks.MaskedFields;

MaskedFields masks = new MaskedFields();
System.out.println(masks.emailMask("user@example.com"));
```

Essas máscaras são usadas internamente pelos validadores para evitar imprimir o valor completo durante a validação.

---

## Contratos da biblioteca

### `Valid`

Interface base para qualquer validador:

```java
public interface Valid {
	String execute(String value);
}
```

### `Masquerade`

Interface base para classes de máscara:

```java
public interface Masquerade {
	String cepMask(String cep);
	String cpfMask(String cpf);
	String cnpjMask(String cnpj);
	String emailMask(String email);
	String loginMask(String login);
	String passwordMask(String password);
	String phoneMask(String phone);
}
```

---

## Boas práticas de uso

- valide entradas logo na borda da aplicação;
- trate exceções de validação em controllers, serviços ou handlers;
- use `ValidationFacade` quando quiser simplicidade;
- use os validadores individuais quando quiser controle explícito;
- reutilize `RegexPatterns` se precisar validar fora da biblioteca.

---

## Observações importantes

- Os métodos retornam a própria string validada, o que facilita encadeamento.
- A biblioteca utiliza exceções de runtime para sinalizar erro.
- Os testes cobrem formatos válidos e inválidos para cada campo, servindo como referência prática de uso.

---

## Estrutura do projeto

```text
src/main/java/io/github/ivancarlosantos/bean_validation/
├── facade/
├── masks/
├── pattern/
├── valid/
└── validator/
```

---

## Licença

Apache License 2.0


