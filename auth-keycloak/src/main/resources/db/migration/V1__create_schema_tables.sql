CREATE SCHEMA IF NOT EXISTS loja;

CREATE TABLE loja.cliente(
    id UUID PRIMARY KEY,
    nome VARCHAR,
    telefone VARCHAR,
    email VARCHAR
);

CREATE TABLE loja.compra(
    id UUID PRIMARY KEY,
    itens VARCHAR,
    id_cliente UUID NOT NULL REFERENCES loja.cliente(id)
);
