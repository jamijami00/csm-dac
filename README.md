# Projeto e Implementação de API
Desenvolvido por Victor Verdan Braga, esse projeto implementa todas as classes de entidade descritas no projeto, implementa todos os endpoints necessários para realizar todos os casos de uso e também implementa outros endpoints de apoio, com no mínimo um CRUD para cada classe.

A estrutura básica do projeto incluindo as dependências foram criadas usando o Spring Initializr (https://start.spring.io/).

A documentação Swagger foi gerada automaticamente pela dependencia do springdoc. O banco de dados para persistência que eu usei foi o H2, por isso é necessário mockar os dados toda a vez que executar o trabalho, mas eu enviei uma coleção com todos os meus testes separados para poderem ser reproduzidos no Postman.

## Especificações
- Spring Web
- Spring Data
- Lombok
- H2 Database

## Acesso
- **Documentação**: http://localhost:8080/swagger-ui/index.html
- **Banco de Dados**: http://localhost:8080/h2

![image](https://github.com/jamijami00/csm-dac/assets/37881485/87bd2e35-4a0f-40c8-a401-a461a4542c22)

## Endpoints
Aqui está escrito cada um dos endpoints, além disso o código nos controladores foi comentado para descrever o que cada endpoint faz.

### Usuário
- GET: /api/v1/users -> Retorna todos usuários
- GET: /api/v1/users/{id} -> Retorna um usuário pelo ID
- GET: /api/v1/users/{id}/favorites -> Retorna todos as atividades favoritas do Usuário
- POST: /api/v1/users -> Cadastra um usuário
- PUT: /api/v1/users/{id} -> Atualiza um usuário
- PUT: /api/v1/users/{userId}/favorites/{atividadeId} -> Adiciona um favorito a um usuário passando apenas o ID
- DELETE: /api/v1/users/{id} -> Deleta um usuário
- DELETE: /api/v1/users/{userId}/favorites/{atividadeId} -> Remove um favorito de um usuário passando o ID da atividade

### Evento
- GET: /api/v1/events -> Retorna todos eventos
- GET: /api/v1/events/{id} -> Retorna um evento pelo ID
- POST: /api/v1/events -> Cadastra um evento
- PUT: /api/v1/events/{id} -> Atualiza um evento
- DELETE: /api/v1/events/{id} -> Deleta um evento

### Edição
- GET: /api/v1/editions -> Retorna todas edições
- GET: /api/v1/events/{eventId}/editions -> Retorna todas edições do evento
- GET: /api/v1/events/{eventId}/{editionId} -> Retorna a edição de um evento
- POST: /api/v1/events/{eventId}/editions -> Cria uma edição do evento informado
- PUT: /api/v1/editions/{id} -> Atualiza uma edição
- PUT: /api/v1/editions/{editionId}/organizer/{userId} -> Atualiza o organizador da edição
- PUT: /api/v1/editions/{editionId}/activities/{activityId} -> Atualiza as atividades da edição
- DELETE: /api/v1/editions/{id} -> Deleta uma edição

### Espaço
- GET: /api/v1/places -> Retorna todos os espaços
- GET: /api/v1/places/{id} -> Retorna um espaço pelo ID
- POST: /api/v1/places -> Cadastra um espaço
- PUT: /api/v1/places/{id} -> Atualiza um espaço
- DELETE: /api/v1/places/{id} -> Deleta um espaço

### Atividade
- GET: /api/v1/activities -> Retorna todas atividades
- GET: /api/v1/activities/{id} -> Retorna uma atividade pelo ID
- GET: /api/v1/places/{placeId}/activities -> Retorna as atividades do Espaço informado
- POST: /api/v1/places/{placeId}/activities -> Cria uma atividade no espaço
- PUT: /api/v1/activities/{id} -> Edita uma atividade
- DELETE: /api/v1/activities/{id} -> Deleta uma atividade
- DELETE: /api/v1/places/{placeId}/activities -> Deleta todas atividades do Espaço informado

## Estrutura da Aplicação
A aplicação é dividida em seis pacotes:
- **config**: Onde é realizado algumas configurações para o Swagger
- **entity**: Aqui estão todas as entidades da Api
- **enums**: Aqui é onde está armazenado o enum para o Tipo de Atividade
- **http.controller**: Aqui onde estão todos os controladores contendo os endpoints.
- **repository**: Aqui estão todos os repositórios de cada um dos modelos, servindo como uma interface que estende JpaRepository (ou CrudRepository, mas eu fui com o primeiro). Filtros customizados de pesquisa se encontram aqui.
- **service**: Aqui está as interfaces usadas para estabelecer as regras de negócio e outro pacote para a implementação de tais interfaces. O controlador não tem acesso direto ao repositório, ao invés disso ele tem acesso ao serviço que tem acesso ao repositório.

## Considerações
Durante o desenvolvimento, como eu não consegui formar um grupo pela minha dificuldade de entrar em contato com outros alunos na faculdade, eu tive que tomar algumas concessões. Do método que está implementado atualmente, ele não propaga as deleções e alterações no banco de dados, mas ele ainda preserva a integridade do banco de dados.

Eu também não sabia se era para implementar apenas uma das classes ou todas elas, mas para não ficar com algo muito simples, eu acabei por implementar tudo.
