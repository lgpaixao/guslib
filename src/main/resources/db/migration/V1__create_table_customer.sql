CREATE TABLE customer(
    id SERIAL PRIMARY KEY,
    nome varchar(255) not null,
    email varchar(255) not null unique
)