CREATE TABLE IF NOT EXISTS product (
	id serial PRIMARY KEY,
	name varchar(255) NOT NULL,
	price FLOAT NOT NULL
);

CREATE INDEX product_id_index ON product(id);
CREATE INDEX product_name_index ON product(name);