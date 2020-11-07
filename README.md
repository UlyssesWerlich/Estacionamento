# Estacionamento
Sistema REST para controle de fluxo de um estacionamento.

# O Sistema
O sistema é construído usando a linguagem Java com a ferramenta Spring, e banco de dados MySql.
A comunicação com o FrontEnd é feita por meio do formato JSON. O sistema conta também com a autenticação OAuth2.

# Como usar
O uso do sistema se constitui na manipulação de dados de cinco diferentes entidades, que são:
  - O gerenciamento dos Tipos de Veículos permitidos no sistema
  - O gerenciamento de Clientes
  - O gerenciamento de Veículos
  - O gerenciamento de Entradas
  - O gerenciamento de Pagamentos
  
O sistema no momento atual NÂO faz a contagem de vagas nem o bloqueio de entrada caso não haja mais vagas disponíveis. Mas essa possibilidade esta disponivel caso necessidade.

# O gerenciamento de Tipos de veículos
Campos:
  - Tipo de Veículo
  - Preço
  
Essa parte é responsável pelo registro e controle dos tipos de veículos permitidos no sistema, junto com o preço do estacionamento para cada tipo de veículo.
É por meio do preço associado com o tipo de veículo que mais pra frente é calculado o valor total do estacionamento.

## Rotas
O gerenciamento de Tipos de veículos possue as seguintes rotas:

### Listar 
   - Método: GET
   - Rota: "/tipos-veiculos"
   Retorna os tipos de veículos cadastrados

### Listar os nomes dos tipos de veículos 
   - Método: GET
   - Rota: "/tipos-veiculos/nomes"
   Retorna uma lista de nomes dos tipos de veículos

### Buscar
   - Método: GET
   - Rota: "/tipos-veiculos/{id}"
  Retorna o tipo de veículo especificado pelo ID

### Buscar por nome do tipo de veículo 
   - Método: GET
   - Rota: "/tipos-veiculos/nomes/{nome}"
   Retorna o tipo de veículo com o nome específico

### Cadastrar tipo de veículo 
   - Método: POST
   - Rota: "/tipos-veiculos"
   - Corpo da requisição:
          {
            "nome": "teste",
            "preco": 12.00
          }
    Grava no banco de dados o novo tipo de veículo, e retorna o tipo de veículo cadastrado junto com o ID, e a URL de consulta no header da resposta
    
### Alterar tipo de veículo 
   - Método: PUT
   - Rota: "/tipos-veiculos/{id}"
   - Corpo da requisição:
          {
            "nome": "teste",
            "preco": 12.00
          }
    Altera no banco de dados o tipo de veículo especificado pelo ID na URL, e retorna o tipo de veículo alterado junto com o ID
    
### Alterar tipo de veículo 
   - Método: DELETE
   - Rota: "/tipos-veiculos/{id}"
    Exclui no banco de dados o tipo de veículo especificado pelo ID na URL, e retorna o status 204 No Content
  
# O Gerenciamento de Clientes
Campos:
  - Tipo de Veículo
  - Preço
  
Essa parte é responsável pelo registro e controle dos tipos de veículos permitidos no sistema, junto com o preço do estacionamento para cada tipo de veículo.
É por meio do preço associado com o tipo de veículo que mais pra frente é calculado o valor total do estacionamento.

## Rotas
O gerenciamento de Tipos de veículos possue as seguintes rotas:

### Listar 
   - Método: GET
   - Rota: "/tipos-veiculos"
   Retorna os tipos de veículos cadastrados

### Listar os nomes dos tipos de veículos 
   - Método: GET
   - Rota: "/tipos-veiculos/nomes"
   Retorna uma lista de nomes dos tipos de veículos

### Buscar
   - Método: GET
   - Rota: "/tipos-veiculos/{id}"
  Retorna o tipo de veículo especificado pelo ID

### Buscar por nome do tipo de veículo 
   - Método: GET
   - Rota: "/tipos-veiculos/nomes/{nome}"
   Retorna o tipo de veículo com o nome específico

### Cadastrar tipo de veículo 
   - Método: POST
   - Rota: "/tipos-veiculos"
   - Corpo da requisição:
          {
            "nome": "teste",
            "preco": 12.00
          }
    Grava no banco de dados o novo tipo de veículo, e retorna o tipo de veículo cadastrado junto com o ID, e a URL de consulta no header da resposta
    
### Alterar tipo de veículo 
   - Método: PUT
   - Rota: "/tipos-veiculos/{id}"
   - Corpo da requisição:
          {
            "nome": "teste",
            "preco": 12.00
          }
    Altera no banco de dados o tipo de veículo especificado pelo ID na URL, e retorna o tipo de veículo alterado junto com o ID
    
### Alterar tipo de veículo 
   - Método: DELETE
   - Rota: "/tipos-veiculos/{id}"
    Exclui no banco de dados o tipo de veículo especificado pelo ID na URL, e retorna o status 204 No Content


# O gerenciamento de Veículos


