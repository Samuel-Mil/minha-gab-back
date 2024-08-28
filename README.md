# MinhaGab API

O MinhaGab é um gerenciador de documentos chamados GABs, que são basicamente guias laboratoriais emitidos de uma clínica para um cliente/paciente.

Este é o projeto de uma API REST desenvolvida com Spring Boot, Java 17 e Maven. A API está conectada a um banco de dados MySQL e utiliza as principais dependências necessárias para o desenvolvimento de uma aplicação robusta e segura.

## Configurações do Projeto

- **Java**: 17
- **Spring Boot**: 3.3.3
- **Maven**: 3.8.1 ou superior
- **Banco de Dados**: MySQL

## Dependências

As principais dependências utilizadas neste projeto são:

- **Spring Web**
- **Spring Security**
- **Lombok**
- **MySQL Driver**
- **Spring Data JPA**
- **Spring Boot DevTools**

Aqui estão as configurações das dependências necessárias no arquivo `pom.xml`:

```xml
<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot Starter Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.24</version>
        <scope>provided</scope>
    </dependency>

    <!-- MySQL Driver -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.29</version>
    </dependency>

    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Spring Boot DevTools -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>

```
## Configuração de Conexão com o Banco de Dados

As configurações para conectar a API ao banco de dados MySQL estão definidas em `application.properties` da seguinte forma:

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

## Instalação e Configuração

Siga os passos abaixo para configurar o projeto localmente:

1. **Clone o Repositório**

   ```bash
   git clone https://github.com/Samuel-Mil/minha-gab-back
   ```

2. **Instale as Dependências**

   Execute o comando abaixo para instalar as dependências necessárias:

   ```bash
   mvn clean install
   ```

3. **Configure o Banco de Dados**

   Certifique-se de que você tenha um servidor MySQL rodando localmente e crie um banco de dados com o nome `BancoGAB`. As credenciais do banco de dados devem estar configuradas no arquivo `application.properties`.

4. **Execute a Aplicação**

   Para rodar a aplicação, utilize o comando:

   ```bash
   mvn spring-boot:run
   ```

5. **Acesse a Aplicação**

   A aplicação estará disponível no seguinte endereço:

   ```
   http://localhost:8080
   ```

## Links Úteis

- [Documentação do Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Documentação do Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Documentação do Spring Security](https://docs.spring.io/spring-security/site/docs/current/reference/html5/)
- [Documentação do Lombok](https://projectlombok.org/)
- [Documentação do MySQL Connector](https://dev.mysql.com/doc/connector-j/8.0/en/)
```