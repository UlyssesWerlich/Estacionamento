create table entrada (
  id bigint primary key not null auto_increment,
  data_hora_entrada datetime(6) not null,
  data_hora_saida datetime(6) default null,
  cliente_id bigint not null,
  pagamento_id bigint default null,
  veiculo_id bigint not null,
  foreign key (cliente_id) references cliente (id),
  foreign key (pagamento_id) references pagamento (id),
  foreign key (veiculo_id) references veiculo (id)
)