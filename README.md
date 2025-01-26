# PlannerRocket API (Back-End)

Este projeto foi desenvolvido durante o NLW 16 da Rocketseat, com o objetivo de criar uma API REST utilizando o framework Spring Boot, banco de dados H2 em memória, e Flyway para controle de migrações. O **PlannerRocket** é um gerenciador de tarefas simples e eficaz, focado em organização pessoal e produtividade.

## Tecnologias Utilizadas

- **Java**: Linguagem principal do projeto.
- **Spring Framework**:
  - **Spring Boot 3**: Para configuração e inicialização simplificada do projeto.
  - **Spring Web**: Para criação de endpoints RESTful.
  - **Spring Data JPA**: Para integração com o JPA e manipulação de dados.
- **Banco de Dados**:
  - **H2 Database**: Banco de dados em memória para desenvolvimento e testes.
  - **Flyway**: Para controle de versionamento e migração do banco de dados.
- **Maven**: Gerenciador de dependências e build.

## Funcionalidades

- **Tarefas**:
  - Criação, leitura, atualização e exclusão (CRUD).
  - Organização por categorias e datas.
  - Marcar tarefas como concluídas.
- **Categorias**:
  - CRUD de categorias para organizar as tarefas.

## Estrutura do Projeto

O projeto segue uma estrutura organizada baseada no padrão MVC (Model-View-Controller), facilitando a manutenção e escalabilidade.

```
src/main/java/
|-- com.uisgabrieo.plannerrocket
    |-- activity/          # Configurações iniciais do projeto
    |-- link/              # Controladores REST para endpoints
    |-- participant/       # Definição das entidades do banco de dados
    |-- trip/              # Interfaces para acesso aos dados
```

## Endpoints Principais

### Viagens (Trips)
- **POST /trips/create**: Cria uma nova viagem, registrando participantes a partir dos e-mails fornecidos.
- **GET /trips/{id}**: Retorna os detalhes de uma viagem específica pelo ID.
- **PUT /trips/{id}**: Atualiza uma viagem existente, como data de início, data de término e destino.
- **GET /trips/{id}/confirm**: Confirma uma viagem, alterando seu status para "confirmada" e enviando e-mails de confirmação para os participantes.
- **POST /trips/{id}/invite**: Convida um participante para uma viagem, enviando um e-mail de confirmação caso a viagem já esteja confirmada.
- **GET /trips/{id}/participants**: Retorna todos os participantes de uma viagem.
- **POST /trips/{id}/activity**: Cria uma nova atividade dentro de uma viagem.
- **GET /trips/{id}/activities**: Retorna todas as atividades registradas para uma viagem.
- **POST /trips/{id}/links**: Registra um novo link relacionado a uma viagem.
- **GET /trips/{id}/links**: Retorna todos os links registrados para uma viagem.

### Participantes (Participants)
- **POST /participants/{id}/confirm**: Confirma a participação de um participante em uma viagem e atualiza seus dados.

## Aprendizados

Durante o desenvolvimento do **PlannerRocket**, foram aprofundados conhecimentos em:
- Configuração de projetos Spring Boot.
- Implementação de APIs RESTful.
- Uso de Spring Data JPA para persistência de dados.
- Configuração e uso do banco de dados H2 em memória.
- Versionamento do banco de dados com Flyway.
- Organização e boas práticas no desenvolvimento back-end.

## Considerações Finais

O **PlannerRocket** é um projeto introdutório que consolidou conceitos essenciais no desenvolvimento back-end com Java e Spring. Ele oferece uma base sólida para criação de sistemas RESTful e um aprendizado significativo sobre o ecossistema Spring. 

Este projeto foi uma experiência desafiadora e enriquecedora que fortaleceu habilidades que serão aplicadas em projetos futuros.
