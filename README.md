
---

# MinhaGab

O **MinhaGab** é um gerenciador de documentos chamados GABs, que são basicamente guias laboratoriais emitidos de uma clínica para um cliente/paciente. Este projeto é uma API REST desenvolvida com Spring Boot, Java 17 e Maven, conectada a um banco de dados MySQL.

## Índice

- [Descrição do Projeto](#descrição-do-projeto)
- [Configurações do Projeto](#configurações-do-projeto)
- [Dependências](#dependências)
- [Instalação e Configuração](#instalação-e-configuração)

## Descrição do Projeto

O **MinhaGab** foi desenvolvido para facilitar a gestão de documentos clínicos, permitindo registrar e consultar GABs (guias laboratoriais). O sistema oferece funcionalidades para cadastro, visualização e gerenciamento de GABs de maneira segura e eficiente.

## Configurações do Projeto

- **Java**: 17
- **Spring Boot**: 3.3.3
- **Maven**: 3.8.1 ou superior
- **Banco de Dados**: MySQL

## Dependências

O projeto utiliza as seguintes dependências:

- **Spring Boot**: Framework para construção da aplicação.
- **Spring Security**: Para segurança da aplicação.
- **Spring Data JPA**: Para acesso ao banco de dados.
- **Lombok**: Biblioteca para reduzir a verbosidade do código Java.
- **MySQL Driver**: Conector para o banco de dados MySQL.
- **Spring Boot DevTools**: Ferramenta para facilitar o desenvolvimento.
- **JWT**: Para gerar e validar tokens JWT.
- **Hibernate Validator**: Para validação de dados.
- **SLF4J**: Para logging.

**Exemplo de dependências Maven:**

```xml
<dependencies>
    <!-- Spring Boot Starter Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Spring Boot Starter Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot DevTools -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>

    <!-- MySQL Driver -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.31</version>
    </dependency>

    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.24</version>
        <optional>true</optional>
    </dependency>

    <!-- Spring Boot Starter Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

    <!-- Spring Security Test -->
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-test</artifactId>
        <scope>test</scope>
    </dependency>

    <!-- JWT -->
    <dependency>
        <groupId>com.auth0</groupId>
        <artifactId>java-jwt</artifactId>
        <version>4.4.0</version>
    </dependency>

    <!-- Hibernate Validator -->
    <dependency>
        <groupId>org.hibernate.validator</groupId>
        <artifactId>hibernate-validator</artifactId>
        <version>8.0.1.Final</version>
    </dependency>

    <!-- Validation API -->
    <dependency>
        <groupId>javax.validation</groupId>
        <artifactId>validation-api</artifactId>
        <version>2.0.1.Final</version>
    </dependency>

    <!-- SLF4J API -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>2.0.0</version>
    </dependency>

    <!-- SLF4J Simple -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-simple</artifactId>
        <version>2.0.0</version>
    </dependency>
</dependencies>
```

## Instalação e Configuração

Siga os passos abaixo para configurar o projeto localmente:

### Clone o Repositório

```bash
git clone https://github.com/Samuel-Mil/minha-gab-back
```

### Instale as Dependências

Execute o comando abaixo para instalar as dependências necessárias:

```bash
mvn clean install
```

### Configure o Banco de Dados

Certifique-se de que você tenha um servidor MySQL rodando localmente e crie um banco de dados com o nome `BancoGAB`. As credenciais do banco de dados devem estar configuradas no arquivo `application.properties`:

```properties
spring.application.name=MinhaGab
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/BancoGAB?createDatabaseIfNotExist=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
```

### Execute a Aplicação

Para rodar a aplicação, utilize o comando:

```bash
mvn spring-boot:run
```

### Acesse a Aplicação

A aplicação estará disponível no seguinte endereço:

```
http://localhost:8080
```

### Links Úteis

- [Baixar Java JDK](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Documentação do Spring Boot](https://spring.io/projects/spring-boot)
- [Documentação do Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Documentação do Spring Security](https://spring.io/projects/spring-security)
- [Documentação do Lombok](https://projectlombok.org/)
- [Documentação do MySQL Connector](https://dev.mysql.com/doc/connector-j/8.0/en/)
- [Documentação do JWT](https://github.com/jwtk/jjwt)

### Verifique a Instalação do Java

Após instalar o Java, verifique se ele foi instalado corretamente executando o seguinte comando no terminal:

```bash
java -version
```

Este comando deve exibir a versão do Java instalada.


## Endpoints da API

## 🔐 Autenticação e Registro de Usuário
## 🛡️ Autorização
`Rotas protegidas exigem que o cliente envie um token JWT válido no cabeçalho da requisição HTTP, grande parte é necessário as Roles Financeiro ou Clinica.

### ✅ Registro de Usuário

Cria um novo usuário no sistema.

- **Endpoint:** `POST /auth/register`
- **Descrição:** Cria um novo usuário com (role) especificado.

#### 🧾 Request Body

```json
{
  "name": "string",
  "cpfcnpj": "string",
  "phone": "string",
  "email": "string",
  "password": "string",
  "role": "CLINICA | PACIENTE | FINANCEIRO"
}
```

#### ✅ Resposta - Sucesso

- **Código:** `200 OK`

```json
{
  "mensagem": "Usuário registrado com sucesso!"
}
```

#### ❌  Resposta - Erro de Validação

- **Código:** `400 Bad Request`

```json
{
  "mensagem": "Erro de Validação (exemplo, pode ser erro de credencial inválida, etc)"
}
```

### 🔐 Login usuário

Realiza a autenticação de um usuário registrado e retorna o token JWT de acesso.

- **Endpoint:** `POST /auth/login`
- **Descrição:** Retorna tokens de acesso e refresh token para o usuário autenticado.

#### 🧾 Request Body

```json
{
  "cpfcnpj": "String",
  "password": "string",
  "role": "CLINICA PACIENTE FINANCEIRO"
}
```

#### ✅ Resposta - Sucesso

- **Código:** `200 OK`

```json
{
  "accessToken": "accessToken",
  "refreshToken": "RefreshToken",
  "message": "Login bem-sucedido"
}
```
#### ❌  Resposta - Credenciais Inválidas

- **Código:** `401 Unauthorized (caso tenha alguma credencial inválida)`

```json
{
  "accessToken": null,
  "refreshToken": null,
  "message": "Credenciais inválidas"
}
```

### 💬 Comentários
#### ✏️ Criar Comentário

Permite a criação de comentários por usuários autenticados.

- **Endpoint:** `POST /comentarios/criar`
- **Descrição:** Adiciona um novo comentário ao sistema.
#### 🧾 Request Body

```json
{
  "userId": "integer",
  "comentario": "string",
  "status": "AGUARDANDO | RESPONDIDO"
}
```
#### ✅ Resposta - Sucesso

- **Código:** `201 CREATED`

```json
{
  "message": "Comentário criado com sucesso"
}
```
#### ❌  Resposta - Acesso Negado (para criar um comentário, é necessário ter a Role FINANCEIRO OU CLINICA)

- **Código:** `403 Forbbiden`

```json
{
  "timestamp": "2025-05-20T18:23:15.484+00:00",
  "status": 403,
  "error": "Forbidden",
  "message": "Access Denied",
  "path": "/comentarios/criar"
}
```
- **Código:** `500 Server internal Error`

```json
{
  "timestamp": "2025-05-20T18:25:19.494+00:00",
  "status": 500,
  "error": "Internal Server Error",
  "trace": "java.lang.RuntimeException: Usuário não encontrado com ID: {id}",
  "message": "Usuário não encontrado com ID: {id}",
  "path": "/comentarios/criar"
}
```

#### ✏️ Buscar Comentários

Endpoints que permitem a busca por comentários

- **Endpoint:** `GET /comentarios/todos`
- **Descrição:** Busca e lista todos os comentários cadastrados no banco.
- **Endpoint:** `GET /comentarios/{id}`
- **Descrição:** Busca um comentário pelo seu id cadastrado no banco.
#### 🧾 Basta enviar a requisição com a role permitida (FINANCEIRO | CLINICA)

#### ✅ Resposta - Sucesso

- **Código:** `200 OK`
- **Retorna um array em json com o(s) comentário(s)**

#### ❌  Resposta - Nenhum comentário encontrado

- **Código:** `404 NOT FOUND`

#### ✏️ Atualizar um comentário

Permite a atualização de um comentário passando o seu id

- **Endpoint:** `PUT /comentarios/{id}`
- **Descrição:** Atualiza um comentário.
#### 🧾 Request Body

```json
{
  "userId": "integer",
  "comentario": "string",
  "status": "AGUARDANDO | RESPONDIDO"
}
```

#### ✅ Resposta - Sucesso

- **Código:** `200 OK`
- **Retorna um array em json com o comentário atualizado**

#### ❌ Resposta - Nenhum comentário encontrado

- **Código:** `500 SERVER INTERNAL ERROR`

```json
{
  "timestamp": "2025-05-22T10:53:28.848+00:00",
  "status": 500,
  "error": "Internal Server Error",
  "trace": "java.lang.RuntimeException: Comentário não encontrado com ID: {id}"
}
```
#### ❌ Deletar um comentário

Endpoint para deletar um comentário

- **Endpoint:** `DELETE /comentarios/deletar/{id}`
- **Descrição:** Deleta um comentário pelo seu ID.

#### ✅ Resposta - Sucesso

- **Código:** `204 NO CONTENT`

#### ✏️ Buscar um comentário pelo ID do usuário

Endpoint para buscar um comentário pelo ID do usuário

- **Endpoint:** `GET /comentarios/usuario/{userId}`
- **Descrição:** Busca um comentário pelo ID do usuário que o criou.


#### ✅ Resposta - Sucesso

- **Código:** `200 OK`
- **Retorna um array em Json com todos os comentários criados pelo id passado do usuário**


#### ❌ Resposta - Nenhum comentário encontrado

- **Código:** `204 NO CONTENT`
- 
#### ✏️ Responder um comentário

- **Endpoint:** `POST /comentarios/responder`
- **Descrição:** Endpoint para responder um comentário pelo ID.

#### 🧾 Request Body
```Json
{
   "comentarioId": "Long",
   "resposta": "String"
}
```


#### ✅ Resposta - Sucesso

- **Código:** `201 CREATED`

```json
{
    "id": 0,
    "conteudo": "String"
}
```


#### ❌ Resposta - Comentário não encontrado

- **Código:** `500 SERVER INTERNAL ERROR`

```json
{
  "timestamp": "2025-05-22T11:12:17.808+00:00",
  "status": 500,
  "error": "Internal Server Error",
  "trace": "java.lang.RuntimeException: Comentário não encontrado",
  "message": "Comentário não encontrado",
  "path": "/comentarios/responder"
}
```
### 📤 Criar GabRequest

- **EndPoint:** `POST /gab_requests/create`
- **Descrição:** Cria uma nova solicitação (GabRequest) sem anexo de arquivo.

#### 🔸 Request Body

```json
{
  "descricao": "string",
  "status": "string",
  "user": {
    "id": 1
  }
}
```

#### ✅ Resposta - Sucesso

- **Código:** `200 OK`

```json
{
  "id": 123,
  "descricao": "string",
  "status": "string",
  "user": {
    "id": 1,
    "name": "João"
  }
}
```

#### ❌ Erro Interno

- **Código:** `500 Internal Server Error`
- **Response Body:**

```json
{
  "message": "Erro ao salvar a GabRequest"
}
```

---

### 📎 Upload de PDF para GabRequest

- **EndPoint:** `POST /gab_requests/upload`
- **Descrição:** Faz upload de um arquivo PDF e cria uma GabRequest vinculada a um usuário.

#### 🔸 Parâmetros

- `userId` (Integer): ID do usuário.
- `pdf_file` (MultipartFile): Arquivo PDF a ser enviado.

#### 📝 Exemplo de requisição `form-data`

```
Key: userId         Value: 1
Key: pdf_file       Value: <arquivo.pdf>
```

#### ✅ Resposta - Sucesso

- **Código:** `200 OK`

```text
GabRequest criada com sucesso: 123
```

#### ❌ Erro de Requisição

- **Código:** `400 Bad Request`

```text
O arquivo 'pdf_file' não foi enviado.
```

#### ❌ Erro Interno

- **Código:** `500 Internal Server Error`

```text
Erro ao processar o upload: <mensagem de erro>
```

---

### 🔍 Buscar GABs por CPF ou Nome

- **EndPoint:** `GET /gab_requests/search`
- **Descrição:** Retorna uma lista de GABs relacionadas a um nome ou CPF.

#### 🔸 Parâmetros

- `cpfOrName` (String): Nome ou CPF a ser pesquisado.

#### 🧪 Exemplo

```
GET /gab_requests/search?cpfOrName=12345678900
```

#### ✅ Resposta - Sucesso

- **Código:** `200 OK`

```json
[
  {
    "id": 1,
    "nome": "String",
    "cpf": "String",
    "descricao": "Atendimento clínico"
  },
  {
    "id": 2,
    "nome": "String",
    "cpf": "String",
    "descricao": "Retorno"
  }
]
```

#### ❌ Erro Interno

- **Código:** `500 Internal Server Error`

```json
{
  "message": "Erro ao buscar GABs"
}
```

---

