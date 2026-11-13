# API Lista de Tarefas

API REST desenvolvida em **Java 21** utilizando **Spring Boot**, com o objetivo de praticar o desenvolvimento Back-end e a criação de serviços que recebem e respondem a requisições HTTP.

O projeto consiste em uma API para gerenciamento de tarefas, permitindo realizar operações de criação, consulta, atualização e exclusão de tarefas.

## Tecnologias utilizadas

- **Java 21** – Linguagem utilizada no desenvolvimento.
- **Spring Boot** – Framework utilizado para construção da API REST.
- **Maven** – Gerenciamento de dependências e execução do projeto.
- **Springdoc OpenAPI** – Documentação e visualização dos endpoints.
- **Lombok** – Redução de código repetitivo.

## Pré-requisitos

- Java 21 ou superior.
- Maven instalado.
- Git.

## Como executar o projeto

### 1. Clone o repositório

```bash
git clone https://github.com/Cauasl/APIListaTarefas.git
```

### 2. Entre na pasta do projeto

```bash
cd APIListaTarefas
```

### 3. Execute a aplicação

Com o Maven instalado, execute:

```bash
mvn spring-boot:run
```

Após iniciar, a aplicação estará disponível em `http://localhost:8080`.

## Endpoints

A rota base da API é:

```text
http://localhost:8080/v1/listatarefas/
```

| Método HTTP | Endpoint | Descrição |
|---|---|---|
| GET | `/v1/listatarefas/` | Lista todas as tarefas. |
| GET | `/v1/listatarefas/{id}` | Busca uma tarefa pelo ID. |
| POST | `/v1/listatarefas/` | Cria uma nova tarefa. |
| PUT | `/v1/listatarefas/{id}` | Atualiza o título e a descrição de uma tarefa. |
| PATCH | `/v1/listatarefas/{id}/titulo` | Atualiza o título de uma tarefa. |
| PATCH | `/v1/listatarefas/{id}/descricao` | Atualiza a descrição de uma tarefa. |
| PATCH | `/v1/listatarefas/{id}/status` | Atualiza o status de uma tarefa. |
| DELETE | `/v1/listatarefas/{id}` | Exclui uma tarefa pelo ID. |

Nas requisições que possuem corpo, envie os dados no formato JSON e utilize o cabeçalho `Content-Type: application/json`.

### 1. Listar tarefas

**GET** `/v1/listatarefas/`

Retorna todas as tarefas cadastradas. Não é necessário enviar parâmetros ou corpo na requisição.

### 2. Buscar tarefa por ID

**GET** `/v1/listatarefas/{id}`

Retorna uma tarefa específica utilizando seu ID.

Exemplo:

```http
GET http://localhost:8080/v1/listatarefas/123456
```

### 3. Criar tarefa

**POST** `/v1/listatarefas/`

Cria uma nova tarefa. Envie no corpo da requisição um JSON com o título e, opcionalmente, a descrição.

Exemplo de corpo:

```json
{
  "titulo": "Estudar Java",
  "descricao": "Estudar Spring Boot"
}
```

O campo `titulo` é obrigatório. O campo `descricao` é opcional. Quando a descrição não é enviada, a tarefa é criada com uma descrição vazia. O status inicial é definido automaticamente como `PENDENTE`.

### 4. Atualizar tarefa

**PUT** `/v1/listatarefas/{id}`

Atualiza o título e a descrição de uma tarefa existente.

Exemplo de corpo:

```json
{
  "titulo": "Estudar Java e Spring",
  "descricao": "Praticar a criação de APIs REST"
}
```

**Atenção:** envie os dois campos (`titulo` e `descricao`). Embora a camada de serviço trate campos não informados, o repositório atualmente exige que ambos estejam preenchidos para concluir a atualização.

### 5. Atualizações específicas (PATCH)

Os endpoints PATCH permitem alterar uma informação específica da tarefa. Cada rota espera um JSON com o campo correspondente.

**Atualizar título**

`PATCH /v1/listatarefas/{id}/titulo`

```json
{
  "titulo": "Novo título"
}
```

**Atualizar descrição**

`PATCH /v1/listatarefas/{id}/descricao`

```json
{
  "descricao": "Nova descrição da tarefa"
}
```

**Atualizar status**

`PATCH /v1/listatarefas/{id}/status`

```json
{
  "status": "CONCLUIDA"
}
```

Os valores aceitos para `status` são:

- `PENDENTE`
- `CONCLUIDA`

Os valores devem ser enviados exatamente como estão escritos, respeitando as letras maiúsculas.

### 6. Excluir tarefa

**DELETE** `/v1/listatarefas/{id}`

Exclui uma tarefa utilizando seu ID. Não é necessário enviar corpo na requisição.

**Observação sobre o status HTTP:** atualmente, o método do Controller está anotado para responder com `404 NOT_FOUND`, mesmo quando a chamada de exclusão é realizada. Vale ajustar essa anotação no código para retornar um status adequado, como `204 NO_CONTENT`.

## Tratamento de erros

A API possui tratamento global para erros relacionados a tarefas não encontradas e campos inválidos. As respostas são enviadas no formato JSON.

Exemplo de tarefa não encontrada:

```json
{
  "message": "A tarefa de id 123456 não encontrada",
  "status": 404
}
```

Exemplo de campo obrigatório ausente:

```json
{
  "message": "O corpo da requisição veio sem titulo",
  "status": 400
}
```

## Documentação interativa

O projeto utiliza Springdoc OpenAPI para disponibilizar uma interface de documentação dos endpoints. Com a aplicação em execução, acesse:

```text
http://localhost:8080/swagger-ui/index.html
```

## Objetivo do projeto

Este projeto foi desenvolvido como parte dos meus estudos em Java e Back-end, com o intuito de colocar em prática os conhecimentos adquiridos sobre Spring Boot, desenvolvimento de APIs REST, manipulação de requisições HTTP e tratamento de exceções.
