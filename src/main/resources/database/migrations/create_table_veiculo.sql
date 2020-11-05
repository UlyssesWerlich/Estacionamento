create table veiculo (
	id bigint primary key unique not null auto_increment,
	placa varchar(10) unique not null,
	cor varchar(15) not null,
	modelo varchar(20),
	tipo_veiculo_id varchar(30) not null
);

alter table veiculo add constraint tipo_veiculo_id
foreign key (tipo_veiculo_id) references tipo_veiculo (id);