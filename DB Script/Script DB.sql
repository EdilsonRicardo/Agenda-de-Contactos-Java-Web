create database dbagenda;
show databases;
use dbagenda;
create table contactos(
idcon int primary key auto_increment,
nome varchar(50) not null,
telefone varchar(15) not null,
email varchar(50)
);

show tables;
describe contactos;