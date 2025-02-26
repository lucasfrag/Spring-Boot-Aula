# 🚀 Spring Boot Exemplo: Web API com MySQL
Este repositório contém um projeto de biblioteca virtual desenvolvido com Spring Boot, com o objetivo de demonstrar como construir uma aplicação web com persistência de dados em MySQL e exposição de uma API REST. Este projeto é ideal para iniciantes que desejam aprender os conceitos básicos do Spring Boot e sua integração com banco de dados.

### 📁 Projeto Biblioteca
Na pasta **biblioteca**, temos o projeto Spring Boot com persistência no banco de dados e uma API REST separada para atender requisições externas. Esse projeto foi desenvolvido no **Apache NetBeans IDE** para exemplificar como realizar o CRUD completo em um projeto Java na web e como criar uma API REST. Os endpoints foram inicialmente testados no Postman.

### 📁 Projeto Web-Client
Já a pasta **web-client** possui um projeto separado construído para exemplificar como uma página externa se comunica com APIs REST através das requisições *GET*, *POST*, *PUT* e *DELETE*. Esse projeto foi construído usando o **Visual Studio Code**.

<img src="https://github.com/lucasfrag/Spring-Boot-Exemplo/blob/main/web-client/preview.png">



### ⚠️ ATENÇÃO!!! 
**Este projeto foi desenvolvido como parte de uma atividade educacional e tem fins exclusivamente didáticos.** Não é recomendado utilizá-lo em ambientes de produção.


## 🖥️ Tecnologias Utilizadas 
O projeto foi criado utilizando o [Spring Initialzr](https://start.spring.io) com as seguintes dependências:

- **Spring Boot** (Framework Java para aplicações web)
- **Spring Data JPA** (Integração com o banco de dados relacional)
- **MySQL Driver** (Conexão com banco de dados MySQL)
- **Spring Web** (Criação de APIs REST)
- **Thymeleaf** (Template engine para renderização de páginas HTML dinâmicas)
- **Lombok** (Redução de código boilerplate com anotações como *@Data*, *@Getter*, *@Setter*)

## ⚙️ Configuração do Ambiente 

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

## 📡 Endpoints da API REST
A API REST implementa operações CRUD para as entidades **Livro** e **Comentário**.

### **Endpoints para Livros** 📚
| Método | Endpoint | Descrição |
|---------|----------|-------------|
| GET | `/api/livros` | Lista todos os livros |
| GET | `/api/livros/{id}` | Obtém um livro pelo ID |
| POST | `/api/livros` | Adiciona um novo livro |
| PUT | `/api/livros/{id}` | Atualiza um livro existente |
| DELETE | `/api/livros/{id}` | Remove um livro pelo ID |

### **Endpoints para Comentários** 💬
| Método | Endpoint | Descrição |
|---------|----------|-------------|
| GET | `/api/comentarios/livro/{id}` | Lista todos os comentários de um livro |
| POST | `/api/comentarios` | Adiciona um novo comentário |
| PUT | `/api/comentarios/{id}` | Atualiza um comentário existente |
| DELETE | `/api/comentarios/{id}` | Remove um comentário pelo ID |


---
📌 **Dica**: Caso queira explorar mais funcionalidades do Spring Boot, recomendo consultar a [documentação oficial](https://spring.io/projects/spring-boot).

