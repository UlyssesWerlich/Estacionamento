CREATE TABLE `entrada` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_hora_entrada` datetime(6) NOT NULL,
  `data_hora_saida` datetime(6) DEFAULT NULL,
  `cliente_id` bigint NOT NULL,
  `pagamento_id` bigint DEFAULT NULL,
  `veiculo_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgfasx2tg2dokk47bm05kboo82` (`cliente_id`),
  KEY `FKikoeipwaqy2ucln0smr7fwq9x` (`pagamento_id`),
  KEY `FKsklmk1e6y17d5jdld237kuyks` (`veiculo_id`),
  CONSTRAINT `FKgfasx2tg2dokk47bm05kboo82` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FKikoeipwaqy2ucln0smr7fwq9x` FOREIGN KEY (`pagamento_id`) REFERENCES `pagamento` (`id`),
  CONSTRAINT `FKsklmk1e6y17d5jdld237kuyks` FOREIGN KEY (`veiculo_id`) REFERENCES `veiculo` (`id`)
)