# Biblioteca API

API REST de gerenciamento de biblioteca desenvolvida com Spring Boot e PostgreSQL.

## Tecnologias

- Java 17
- Spring Boot 3.2
- Spring Data JPA
- PostgreSQL
- Maven

## Como rodar

### Pré-requisitos
- Java 17+
- PostgreSQL instalado e rodando
- Maven

### Configuração do banco

Crie um banco chamado `biblioteca` no PostgreSQL e configure
um arquivo `src/main/resources/application.properties`:

```properties
server.port=8080
spring.datasource.url=jdbc:postgresql://localhost:5432/biblioteca
spring.datasource.username=postgres
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Rodando o projeto

```bash
mvn spring-boot:run
```

## Endpoints

| Método | URL | Descrição |
|--------|-----|-----------|
| GET | /livros | Lista todos os livros |
| GET | /livros/{id} | Busca livro por ID |
| GET | /livros/disponiveis | Lista livros disponíveis |
| GET | /livros/genero/{genero} | Lista livros por gênero |
| POST | /livros | Cadastra novo livro |
| DELETE | /livros/{id} | Remove livro |

## Arquitetura

```
Controller → Service → Repository → PostgreSQL
```