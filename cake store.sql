--first draft
DROP SCHEMA IF EXISTS cake_store CASCADE;
CREATE SCHEMA cake_store;

CREATE DOMAIN cake_store.gender CHAR(1) CHECK(value IN ('M', 'F', 'O'));

CREATE TABLE cake_store.user(
	email VARCHAR(100) PRIMARY KEY,
	password VARCHAR(100),
	f_name VARCHAR(100),
	l_name VARCHAR(100),
	birthday DATE,
	age INT,
	gender cake_store.gender,
	isEmployee boolean
);

CREATE TABLE cake_store.order(
	id SERIAL PRIMARY KEY,
	date DATE,
	email VARCHAR(100) REFERENCES cake_store.user(email)
);

CREATE TABLE cake_store.product(
	id SERIAL PRIMARY KEY,
    quantity INT,
    name VARCHAR(100),
    description VARCHAR(10000),
    price DECIMAL(5,2)
);

CREATE TABLE cake_store.producrOrder(
	orderId INT REFERENCES cake_store.order(id),
	productId INT REFERENCES cake_store.product(id),
	quantity INT,
	PRIMARY KEY(orderId, productId)
);