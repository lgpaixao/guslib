CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    nome varchar(255) not null,
    price decimal(10,2) not null,
    status varchar(255) not null,
    customer_id INT not null,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
)