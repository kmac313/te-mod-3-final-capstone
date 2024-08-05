--BEGIN TRANSACTION;

DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS invoice CASCADE;
DROP TABLE IF EXISTS specialty_pizza CASCADE;
DROP TABLE IF EXISTS pizza CASCADE;
DROP TABLE IF EXISTS drink;
DROP TABLE IF EXISTS pizza_product CASCADE;
DROP TABLE IF EXISTS pizza_size CASCADE;
DROP TABLE IF EXISTS drink_size CASCADE;

CREATE TABLE product (
	product_id SERIAL,
	product_category_id int NOT NULL, 
	price numeric(4,2),
	description varchar(50),
	CONSTRAINT PK_product_id PRIMARY KEY (product_id)
);

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL
);

CREATE TABLE customer(
	customer_id SERIAL,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
	street_address varchar(50),
	city varchar(50),
	zip_code numeric(5,0),
	state_abbreviation char(2),
	phone_number numeric(10,0) NOT NULL,
	email varchar(50) NOT NULL,
	username varchar(50) NOT NULL,
	CONSTRAINT PK_customer_id  PRIMARY KEY (customer_id)
);

CREATE TABLE invoice (
	invoice_id SERIAL,
	customer_id int NOT NULL, 
	total numeric(5,2) NOT NULL,
	is_delivery boolean NOT NULL,
	CONSTRAINT PK_invoice_id PRIMARY KEY (invoice_id)
);
CREATE TABLE specialty_pizza (
	specialty_pizza_id SERIAL,
	specialty_pizza_name varchar(50) NOT NULL,
	crust varchar(20) NOT NULL, 
	sauce varchar(20) NOT NULL,
	premium numeric (4,2)
	
	-- SELECT ... SUM(sp.premium, ps.base_price) AS price FROM specialty_pizza sp
	-- JOIN pizza_size ps ON sp.specialty_pizza_id = ps.specialty_pizza_id
	-- WHERE sp.specialty_pizza_id = ?
	
);

CREATE TABLE pizza (
	pizza_id SERIAL,
	invoice_id int NOT NULL,
	product_id int NOT NULL,
	size_id varchar(2) NOT NULL,
	pizza_name varchar(20),
	crust varchar(20) NOT NULL, 
	sauce varchar(20) NOT NULL,
	price numeric (4,2) NOT NULL,
	additional_instruction varchar(200), 
	CONSTRAINT PK_pizza_id PRIMARY KEY (pizza_id)
);

CREATE TABLE drink(
	drink_id SERIAL, 
	drink_size_id int NOT NULL,
	invoice_id int NOT NULL,
	product_id int NOT NULL,
	flavor varchar(50) NOT NULL,
	price numeric (3,2) NOT NULL, 
	CONSTRAINT PK_drink_id PRIMARY KEY (drink_id)
);

CREATE TABLE pizza_size (
	pizza_size_id varchar(2),
	pizza_size_description varchar (20) NOT NULL, 
	diameter numeric(2,0),
	base_price numeric (4,2), 
	CONSTRAINT PK_pizza_size_id PRIMARY KEY (pizza_size_id)
);
CREATE TABLE drink_size(
	drink_size_id SERIAL,
	drink_size_description varchar (10) NOT NULL, 
	drink_size_price numeric(4,2), 
	CONSTRAINT PK_drink_size_id PRIMARY KEY (drink_size_id)
);

CREATE TABLE pizza_product (
	pizza_id int NOT NULL,
	product_id int NOT NULL,
	CONSTRAINT FK_pizza_product_pizza_id FOREIGN KEY (pizza_id) REFERENCES pizza(pizza_id),
	CONSTRAINT FK_pizza_product_product_id FOREIGN KEY (product_id) REFERENCES product(product_id)
);

CREATE TABLE product_category (
	product_category_id SERIAL,
	product_category_description varchar (50) NOT NULL,
	CONSTRAINT PK_product_catgory_id PRIMARY KEY (product_category_id)
);

ALTER TABLE pizza 
	ADD CONSTRAINT FK_pizza_invoice FOREIGN KEY (invoice_id) REFERENCES invoice(invoice_id),
	ADD CONSTRAINT FK_pizza_size FOREIGN KEY (size_id) REFERENCES pizza_size(pizza_size_id),
	ADD CONSTRAINT FK_product_pizza FOREIGN KEY (product_id) REFERENCES product(product_id)
	
;
AlTER TABLE invoice 
	ADD CONSTRAINT FK_invoice_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
;

ALTER TABLE drink 
	ADD CONSTRAINT FK_drink_size FOREIGN KEY (drink_size_id) REFERENCES drink_size(drink_size_id),
	ADD CONSTRAINT FK_drink_invoice FOREIGN KEY (invoice_id) REFERENCES invoice(invoice_id),
	ADD CONSTRAINT FK_product_drink FOREIGN KEY (product_id) REFERENCES product(product_id)
;

ALTER TABLE customer
	ADD CONSTRAINT FK_customer_user_username FOREIGN KEY (username) REFERENCES users(username)
;
ALTER TABLE product
	ADD CONSTRAINT FK_product_category_id FOREIGN KEY (product_category_id) REFERENCES product_category(product_category_id)
;

--ROLLBACK TRANSACTION;
