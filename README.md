<h1 align="center">API RESTFul</h1>

<p align="left">Tecnologias Utilizadas</p>

 <a href="https://docs.oracle.com/en/java/javase/index.html" align="center"><img src = "https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white"/></a>
 <a href="https://springdoc.org/#Introduction"><img src = "https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white"/></a>
 <a href="https://www.postgresql.org/docs/"><img src = "https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white"/></a>

 




  
# Rotas da aplicação

**Products**

- POST /products
    - Realiza o cadastro de um produto na aplicação
        - Obrigatório o envio dos dados `name` e `value`  pelo body da aplicação

- GET /products
    - Lista todos os produtos cadastrados na aplicação
    

- PUT /products/:id
    - Altera os dados do produto
        - Obrigatório o envio de todos os dados pelo Body da aplicação.
            - Caso não seja passado nenhum dos dados, retorna um erro

- DELETE /products/:id
    - Exclui o produto informado nos parametros da rota
        - Deve ser informado no parametro da rota o `id` do produto que deve ser excluído
     
**Users**

- POST /users
    - Realiza o cadastro de um user na aplicação
        - Obrigatório o envio dos dados `name`, `email` e `password`  pelo body da aplicação

- GET /users
    - Lista todos os users cadastrados na aplicação
    

- PUT /users/:id
    - Altera os dados do usuário
        - Obrigatório o envio de todos os dados pelo Body da aplicação.
            - Caso não seja passado nenhum dos dados, retorna um erro

- DELETE /users/:id
    - Exclui o user informado nos parametros da rota
        - Deve ser informado no parametro da rota o `id` do user que deve ser excluído

