# 🚀 Spring Boot Exemplo: Web API com MySQL

Este repositório contém um projeto de biblioteca virtual desenvolvido com Spring Boot, com o objetivo de demonstrar como construir uma aplicação web com persistência de dados em MySQL e exposição de uma API REST. Este projeto é ideal para iniciantes que desejam aprender os conceitos básicos do Spring Boot e sua integração com banco de dados.

## ⚠️ ATENÇÃO!!!
**Este projeto foi desenvolvido como parte de uma atividade educacional e tem fins exclusivamente didáticos.** Não é recomendado utilizá-lo em ambientes de produção.


## Tecnologias Utilizadas 🖥️
O projeto foi criado utilizando o [Spring Initialzr](https://start.spring.io) com as seguintes dependências:

- MySQL Driver
- Spring Web
- Spring Data JPA
- Validation
- Lombok
- Thymeleaf

## Configuração do Ambiente ⚙️

### 1. Pré-requisitos ✅
Certifique-se de ter instalado em seu sistema:
- [Java 23](https://www.oracle.com/br/java/technologies/downloads/) (ou superior)
- [Apache NetBeans IDE](https://netbeans.apache.org/front/main/index.html)
- [MySQL Server](https://www.mysql.com)
- [Postman](https://www.postman.com) (ou outra ferramenta para testes de API)

### 2. Configuração do Banco de Dados 🛢️
Crie um banco de dados no MySQL com o seguinte comando:
```sql
CREATE DATABASE biblioteca;
```

Atualize as credenciais do banco no arquivo **application.properties**:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

## Endpoints da API 📡

A API contém endpoints CRUD para cada entidade criada (por exemplo, `livro`).

| Método | Endpoint       | Descrição                     |
|--------|---------------|--------------------------------|
| GET    | `/livro`   | Lista todos os livros       |
| GET    | `/livro/{id}` | Obtém um livro por ID     |
| POST   | `/livro`   | Adiciona um novo livro      |
| PUT    | `/livro/{id}` | Atualiza um livro existente |
| DELETE | `/livro/{id}` | Remove um livro pelo ID    |


---
📌 **Dica**: Caso queira explorar mais funcionalidades do Spring Boot, recomendo consultar a [documentação oficial](https://spring.io/projects/spring-boot).

