# SuperPetCare Application

Esta é uma aplicação Spring Boot para gerenciamento de um PetShop.
A aplicação permite o cadastro de tutores (guardians), pets e serviços oferecidos pelo petshop,
além de possuir funcionalidades para o carrinho (cart) e pagamento (payment).

## Funcionalidades

- CRUD de tutores (guardians)
- CRUD de pets
- CRUD de serviços oferecidos pelo petshop
- Carrinho (cart) para adicionar serviços
- Registro de pagamento (payment) para os serviços adicionados ao carrinho

## Estrutura do Banco de Dados

A aplicação utiliza um banco de dados relacional Postgres e usa o Flyway para gerenciar as migrations:

## Relacionamentos

- Um guardian pode ter N pets.
- Um pet pode ter apenas um guardian.
- Um carrinho está relacionado a um pet.
- Um pet pode ter N carrinhos.
- Um pagamento está relacionado a um carrinho.
- Um carrinho está relacionado a um pagamento.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- Postgres
- Flyway
- JPA

### Configuração do Banco de Dados

1. Crie um banco de dados PostgreSQL com o nome `super_pet_care`.
2. Edite o arquivo `application.properties` em `src/main/resources` e configure as propriedades.

## Acesse a documentação Swagger
http://localhost:8080/swagger-ui/index.html#/

# Autenticação padrão
- user: administrator
- password: marmite