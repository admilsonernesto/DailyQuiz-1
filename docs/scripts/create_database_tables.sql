delimiter ;

drop schema if exists `dailyquiz1`;

CREATE DATABASE `dailyquiz1`
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_unicode_ci;

use dailyquiz1;

CREATE TABLE `dailyquiz1`.`membro` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `username` VARCHAR(50) NOT NULL,
  `senha` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`id`));
