delimiter ;

drop schema if exists `dailyquiz1`;

CREATE DATABASE `dailyquiz1`
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_unicode_ci;

use dailyquiz1;

CREATE TABLE `dailyquiz1`.`membro` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `senha` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `dataNascimento` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

create table alternativa (id bigint not null auto_increment, descricao varchar(255), ordem integer not null, idQuestao bigint, primary key (id)) ;
create table questao (id bigint not null auto_increment, enunciado varchar(255), ordem integer not null, idAlternativa bigint, idQuestionario bigint, primary key (id)) ;
create table questionario (id bigint not null auto_increment, dataDisponibilidadeFinal datetime, dataDisponibilidadeInicial datetime, nome varchar(255), idMembro bigint, primary key (id)) ;
create table respostaQuestao (id bigint not null auto_increment, idAlternativa bigint, idQuestao bigint, idRespostaQuestionario bigint, primary key (id)) ;
create table respostaQuestionario (id bigint not null auto_increment, data datetime, idMembro bigint, idQuestionario bigint, primary key (id)) ;


