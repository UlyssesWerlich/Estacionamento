create table pagamento (
  id bigint not null auto_increment,
  entrada_id bigint not null,
  quantidade_intervalo_de_tempo bigint default null,
  tempo_estacionamento bigint default null,
  valor_pagamento decimal(19,2) not null,
  valor_total_entrada decimal(19,2) default null,
  primary key (id)
) 