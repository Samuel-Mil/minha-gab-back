
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

**Exemplo de dependências Maven:**

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

    <!-- JWT -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt</artifactId>
        <version>0.9.1</version>
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

---
