CREATE TABLE `cliente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `celular` varchar(20) DEFAULT NULL,
  `cpf` varchar(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(80) DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
)