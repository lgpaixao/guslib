CREATE TABLE customer(
    customer_id int not null,
    roles varchar(50) not null,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
)