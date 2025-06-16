CREATE TABLE person.pacients(
    id bigint primary key,
    nome varchar(255) not null,
    cpf varchar(11) not null,
    data_nascimento date not null,
    genero varchar(100) not null,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null,
    telefone varchar(50) not null,
    email varchar(100) not null,
    ativo boolean not null,
    cid jsonb,
    alergia jsonb
);