# Cardápio - Projeto Spring Boot com Flyway e Java17

Este projeto é uma aplicação Spring Boot que gerencia um cardápio de alimentos. Utiliza o Flyway para migrações de banco de dados e segue padrões RESTful.

## Estrutura do Projeto

- **Pasta `src/main/java/com/gsweb/demo/controller`**
  - Contém o controlador principal (`FoodController`) que gerencia as operações CRUD relacionadas aos alimentos.

- **Pasta `src/main/java/com/gsweb/demo/food`**
  - Contém as entidades relacionadas a alimentos (`Food`) e suas representações DTO (`FoodRequestDTO` e `FoodResponseDTO`).

- **Pasta `src/main/java/com/gsweb/demo/exception`**
  - Pode conter classes relacionadas ao tratamento de exceções, como `ResourceNotFoundException`.

- **Arquivo `pom.xml`**
  - Configuração do Maven, com dependências para o Spring Boot, Flyway, Lombok e PostgreSQL.

## Endpoints Principais

- **POST `/food`**
  - Salva um novo alimento com base nos dados fornecidos no corpo da requisição.

- **POST `/food/lista`**
  - Salva uma lista de alimentos com base nos dados fornecidos no corpo da requisição.

- **GET `/food`**
  - Retorna uma lista de todos os alimentos existentes.

- **GET `/food/{id}`**
  - Retorna um alimento com base no ID fornecido na URL.

- **DELETE `/food/{id}`**
  - Exclui um alimento com base no ID fornecido na URL.

- **DELETE `/food/all`**
  - Exclui todos os alimentos.

- **PUT `/food/{id}`**
  - Atualiza um alimento com base no ID fornecido na URL e nos dados fornecidos no corpo da requisição.

## Como Executar

Certifique-se de ter o ambiente Java e Maven configurados. Clone o repositório e execute:

```bash
mvn spring-boot:run
```
A aplicação estará disponível em http://localhost:8080.
