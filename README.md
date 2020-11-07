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

# Autenticação
O sistema conta com a autenticação OAuth2, o que se faz necessário uso de um token de autenticação para ter acesso as rotas do sistema.
Para ter acesso ao token de autenticação, primeiro é necessário preencher as seguintes informações no POSTMAN (ou qualquer outro software de mesma finalidade):

## Na aba Authorization:

  - No campo TYPE, selecione a opção "Basic Auth".
  - No campo Username, preencha com "estacionamento".
  - No campo Password, preencha com "123".

## Na aba Body
  - Selecione a opção form-data.
  - Na tabela de KEYs, adicione as seguintes linhas:
  KEY: grant_type     VALUE: password
  KEY: username       VALUE: ulysses
  KEY: password       VALUE: 123

Feito isso, basta acessar a seguinte rota:

        - /oauth/token

O sistema irá retornar as seguintes informações:

        {
            "access_token": "VlLlrDipAAqLK/5JscQImC7yGJw=",
            "token_type": "bearer",
            "refresh_token": "bGI7devF7295HdhS/h3Hk4jJQDw=",
            "expires_in": 43199,
            "scope": "password"
        }
        
Copie o access_token e nas rotas do sistema, dentro da aba Authorization, selecione no campo TYPE a opção Bearer Token e no campo Token, cole o access_token que foi gerado anteriormente. 

Copie o access_token sempre quando for usar uma nova rota.

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
   
   Retorna os tipos de veículos cadastrados.

### Listar os nomes dos tipos de veículos 
   - Método: GET
   - Rota: "/tipos-veiculos/nomes"
   
   Retorna uma lista de nomes dos tipos de veículos.

### Buscar
   - Método: GET
   - Rota: "/tipos-veiculos/{id}"
   
  Retorna o tipo de veículo especificado pelo ID

### Buscar por nome do tipo de veículo 
   - Método: GET
   - Rota: "/tipos-veiculos/nomes/{nome}"
   
   Retorna o tipo de veículo com o nome específico.

### Cadastrar tipo de veículo 
   - Método: POST
   - Rota: "/tipos-veiculos"
   - Corpo da requisição:
   
          {
            "nome": "teste",
            "preco": 12.00
          }
          
    Grava no banco de dados o novo tipo de veículo, e retorna o tipo de veículo cadastrado junto com o ID, e a URL de consulta no header da resposta,
    
### Alterar tipo de veículo 
   - Método: PUT
   - Rota: "/tipos-veiculos/{id}"
   - Corpo da requisição:
   
          {
            "nome": "teste",
            "preco": 12.00
          }
          
    Altera no banco de dados o tipo de veículo especificado pelo ID na URL, e retorna o tipo de veículo alterado junto com o ID.
    
### Excluir tipo de veículo 
   - Método: DELETE
   - Rota: "/tipos-veiculos/{id}"
   
    Exclui no banco de dados o tipo de veículo especificado pelo ID na URL, e retorna o status 204 No Content.
  
# O Gerenciamento de Clientes
Campos:
  - CPF
  - Nome do cliente
  - Telefone 
  - Celular
  - E-mail
  
Essa parte é responsável pelo registro e controle dos clientes do estacionamento.
Os campos Celular e E-mail são opcionais. O campo CPF deve ser único para cada cliente.

## Rotas
O gerenciamento de clientes é feito pelas seguintes rotas:

### Listar 
   - Método: GET
   - Rota: "/clientes"
   
   Retorna os clientes cadastrados no sistema.

### Listar os clientes por nome
   - Método: GET
   - Rota: "/clientes/nome"
   - Corpo da requisição: 
   
          {
            "nome" : "Insira o trecho do nome ou sobrenome aqui"
          }
   
   Retorna os clientes cujo o nome e/ou o sobrenome satisfaça a pesquisa feita no corpo da requisição.  

### Buscar
   - Método: GET
   - Rota: "/clientes/{id}"
   
  Retorna o cliente especificado pelo ID na URL.

### Buscar o cliente por CPF
   - Método: GET
   - Rota: "/clientes/cpf/{CPF}"
   
   Retorna o cliente com o CPF específicado na URL.

### Cadastrar o cliente
   - Método: POST
   - Rota: "/clientes"
   - Corpo da requisição:
   
        {
            "cpf": "08926004500",
            "nome": "Jose Dos Santos",
            "telefone": "48 996120819", 
            "celular": "",
            "email": "santos@gmail.com"
        }
        
    Grava no banco de dados um novo cliente, e retorna o cliente cadastrado junto com o ID, e a URL de consulta no header da resposta.
    
### Alterar dados do cliente 
   - Método: PUT
   - Rota: "/clientes/{id}"
   - Corpo da requisição:

        {
            "cpf": "08926004500",
            "nome": "Jose Dos Santos",
            "telefone": "48 996120819", 
            "celular": "",
            "email": "santos@gmail.com"
        }

    Altera no banco de dados o cliente especificado pelo ID na URL, e retorna o cliente alterado junto com o ID.
    
### Excluir cliente 
   - Método: DELETE
   - Rota: "/clientes/{id}"
    Exclui no banco de dados o cliente especificado pelo ID na URL, e retorna o status 204 No Content.

# O gerenciamento de Veículos
Campos:
  - Placa do veículo
  - Cor do veículo
  - Modelo do veículo
  - Tipo do veículo
  
Essa parte é responsável pelo registro e controle dos veículos.
O campo modelo do veículo é opcional. O campo placa deve ser único para cada veículo cadastrado.

## Rotas
O gerenciamento de veículos é feito pelas seguintes rotas:

### Listar 
   - Método: GET
   - Rota: "/veiculos"
   
   Retorna os veiculos cadastrados no sistema.

### Buscar
   - Método: GET
   - Rota: "/veiculos/{id}"
   
  Retorna o veículo especificado pelo ID na URL.

### Buscar o veículo por placa
   - Método: GET
   - Rota: "/veiculos/placa/{placa}"
   
   Retorna o veículo com a placa específicada na URL.

### Cadastrar o veículo
   - Método: POST
   - Rota: "/veiculos"
   - Corpo da requisição:
   
        {
            "placa": "maz1254",
            "cor": "Rosa",
            "modelo": "Clio",
            "tipoVeiculoId": "2"
        }
        
    Grava no banco de dados um novo veículo, e retorna o veículo cadastrado junto com o ID, e a URL de consulta no header da resposta.
    
### Alterar tipo de veículo 
   - Método: PUT
   - Rota: "/veiculos/{id}"
   - Corpo da requisição:
   
        {
            "placa": "maz1254",
            "cor": "Rosa",
            "modelo": "Clio",
            "tipoVeiculoId": "2"
        }

    Altera no banco de dados o veículo especificado pelo ID na URL, e retorna o veículo alterado junto com o ID.
    
### Excluir o veículo
   - Método: DELETE
   - Rota: "/veiculos/{id}"
   
    Exclui no banco de dados o veículo especificado pelo ID na URL, e retorna o status 204 No Content.

# O gerenciamento de Entradas
Campos:
  - Data e hora de entrada o veículo
  - Data e hora de saída o veículo
  - ID do cliente
  - ID do veículo
  - ID do pagamento
  
Essa parte é responsável pelo registro e controle de entradas.
A data e hora tanto de saída quanto entrada são gerados automaticamente pelo sistema. A data e hora de entrada é gerado quando é feito o cadastro da entrada. Os campos data e hora de saída e ID do pagamento são gerados quando é registrado o pagamento.

## Rotas
O gerenciamento de entradas é feito pelas seguintes rotas:

### Listar 
   - Método: GET
   - Rota: "/entradas"
   
   Retorna as entradas registradas no sistema.

### Listar por entradas abertas
   - Método: GET
   - Rota: "/entradas/abertos"
   
  Retorna as entradas abertas (sem o registro de saída e de pagamento) registradas no sistema.

### Listar entradas por cliente
   - Método: GET
   - Rota: "/entradas/cliente/{id do cliente}"
   
   Retorna as entradas registradas no sistema que pertencem ao cliente específicado pelo ID na URL.
   
### Listar entradas por veículo
   - Método: GET
   - Rota: "/entradas/veículo/{id do veículo}"
   
   Retorna as entradas registradas no sistema que pertencem ao veículo específicado pelo ID na URL.

### Cadastrar a entrada
   - Método: POST
   - Rota: "/entradas"
   - Corpo da requisição:
   
        {
            "veiculoId": "11",
            "clienteId": "9"
        }
        
    Grava no banco de dados uma nova entrada, e retorna a entrada cadastrada junto com o ID, e a URL de consulta no header da resposta.
    
### Alterar entrada já feita
   - Método: PUT
   - Rota: "/entradas/{id}"
   - Corpo da requisição:
   
        {
            "veiculoId": "11",
            "clienteId": "9"
        }

    Altera no banco de dados a entrada especificado pelo ID na URL, e retorna a entrada alterada junto com o ID.
    
### Excluir a entrada
   - Método: DELETE
   - Rota: "/entradas/{id}"
   
    Exclui no banco de dados a entrada especificada pelo ID na URL, e retorna o status 204 No Content.

# O gerenciamento de pagamento
Campos:
  - ID da entrada a ser paga
  - Unidades de intervalo de tempo a serem pagas
  - Tempo estacionado em minutos
  - Valor total do estacionamento
  - Valor que foi pago
  
Essa parte é responsável pelo registro de pagamentos.
O pagamento é registrado fornecendo o ID da entrada junto com o valor que foi pago pelo cliente (não necessariamente o valor gerado automaticamente pelo sistema).
O valor total do estacionamento é gerado usando o preço de estacionamento do tipo do veículo estacionado multiplicado pela quantidade de horas (o termo "horas" foi usado para facilitar o entendimento. O sistema dá a liberdade de alterar o intevalo de tempo de cobrança do estacionamento) em que o veículo ficou estacionado.
No momento que é registrado o pagamento da entrada, o sistema atualiza automaticamente o registro da entrada, preenchendo os campos ID do pagamento e a data e hora da saída.

## Rotas
O gerenciamento de pagamento é feito pelas seguintes rotas:

### Consultar preço do estacionamento 
   - Método: GET
   - Rota: "/pagamentos/consulta/{ID da entrada}"
   
   Retorna o valor total do estacionamento a ser pago pela entrada referenciada pelo ID na URL.

### Fazer o pagamento da entrada aberta
   - Método: POST
   - Rota: "/pagamentos/{ID da entrada}"
   - Corpo da requisição:
   
    {
        "valorPagamento" : "5.00"
    }
   
  Gera o registro do pagamento para a entrada referenciada pelo ID na URL. Retorna o pagamento feito, e a URL de consulta do pagamento no header.

### Buscar pagamento
   - Método: GET
   - Rota: "/pagamentos/{id do pagamento}"

   Retorna o pagamento específicado pelo ID na URL.
   
