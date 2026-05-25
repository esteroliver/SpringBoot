CREATE TABLE public.usuario(
    id UUID PRIMARY KEY,
    email VARCHAR,
    nome VARCHAR,
    keycloak_id VARCHAR,
    papel VARCHAR
);