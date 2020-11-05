create table cliente (
	cpf varchar(14) primary key unique not null,
    nome varchar(60) not null unique,
    telefone varchar(20) not null,
    celular varchar(20),
    email varchar(80)
)