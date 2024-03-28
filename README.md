<h1 align="center">API Rest</h1>

<p align="left">Tecnologias Utilizadas</p>

 <a href="https://nodejs.org/en/" align="center"><img src = "https://img.shields.io/badge/node.js-6DA55F?style=for-the-badge&logo=node.js&logoColor=white"/></a>
 <a href="https://www.typescriptlang.org/"><img src = "https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white"/></a>
 <a href="https://www.prisma.io/docs"><img src = "https://img.shields.io/badge/Prisma-3982CE?style=for-the-badge&logo=Prisma&logoColor=white"/></a>
 <a href="https://fastify.dev/docs/latest/"><img src = "https://img.shields.io/badge/fastify-%23000000.svg?style=for-the-badge&logo=fastify&logoColor=white"/></a>
 <a href="https://www.mongodb.com/docs/"><img src = "https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white"/></a>
 <a href="https://jwt.io/introduction"><img src = "https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens"/></a>
 


<p align="center">Tem como objetivo desenvolver uma API para um suposto back-end simples de clientes cadastrados utilizando <b>Fastfy</b>, <b>Prisma</b>, <b>MongoDB</b> e <b>JWT</b></p>

## Instalação de dependências e Iniciar Projeto

- Dependências do projeto

        npm install

- Executar projeto

        npm run dev
   

# Rotas da aplicação

**Clientes**

- POST /login
    - Realiza o login, retornando o token `JWT` para uso nas autenticações
        - Obrigatório o envio dos dados `email` pelo body da aplicação

- POST /cliente
    - Cadastra um cliente no sistema
        - Obrigatório o envio dos dados `name` e `email` pelo body da aplicação

- GET /cliente
    - Lista todos os clientes cadastrados no sistema
    

- PUT /cliente/:id
    - Altera os dados do usuário
        - Obrigatório o envio de ao menos um dos dados: `name` e `email` pelo body da aplicação
            - Caso não seja passado nenhum dos dados, retorna um erro
        - Obrigatória a validação do token JWT nos headers da aplicação

- DELETE /cliente/:id
    - Exclui o cliente informado nos parametros da rota
        - Deve ser informado no parametro da rota o `id` do usuário que deve ser excluído
        - Obrigatória a validação do token JWT nos headers da aplicação

## Features

**Clientes**

- [x] Deve ser possível criar um usuário

- [x] Deve ser possível realizar o Login do usuário e gerar o token JWT

- [x] Deve ser possível alterar os dados do usuário

- [x] Deve ser possível listar todos os usuários

- [x] Deve ser possível excluir um usuário
