create table cliente (
  id bigint not null auto_increment,
  celular varchar(20) default null,
  cpf varchar(11) default null,
  email varchar(255) default null,
  nome varchar(80) default null,
  telefone varchar(20) default null,
  primary key (id)
)