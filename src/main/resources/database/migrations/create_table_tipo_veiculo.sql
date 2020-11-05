create table tipo_veiculo (
	id bigint primary key unique not null,
	nome varchar(30) not null,
    preco decimal(3,2) not null
)