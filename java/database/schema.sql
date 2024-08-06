--BEGIN TRANSACTION;

DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS invoice CASCADE;
DROP TABLE IF EXISTS specialty_pizza CASCADE;
DROP TABLE IF EXISTS pizza CASCADE;
DROP TABLE IF EXISTS pizza_product CASCADE;
DROP TABLE IF EXISTS invoice_product CASCADE;
DROP TABLE IF EXISTS product_category CASCADE;

CREATE TABLE product (
	product_id SERIAL,
	product_category_id int NOT NULL, 
	price numeric(4,2),
	description varchar(50),
	quantity int NOT NULL DEFAULT 10, 
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
	username varchar(50), --NOT NULL--
	CONSTRAINT PK_customer_id  PRIMARY KEY (customer_id)
);

CREATE TABLE invoice (
	invoice_id SERIAL,
	customer_id int NOT NULL, 
	total numeric(5,2) NOT NULL,
	is_delivery boolean NOT NULL,
	is_complete boolean NOT NULL,
	timestamp timestamp NOT NULL, 
	CONSTRAINT PK_invoice_id PRIMARY KEY (invoice_id)
);
CREATE TABLE specialty_pizza (
	specialty_pizza_id SERIAL,
	specialty_pizza_name varchar(50) NOT NULL,
	crust varchar(20) NOT NULL, 
	sauce varchar(20) NOT NULL,
	premium numeric (4,2)
	
);

CREATE TABLE pizza (
	pizza_id SERIAL,
	invoice_id int NOT NULL,
	pizza_name varchar(20),
	total numeric (4,2) NOT NULL, --UPDATE to SUM(*join product table using pizza_product to get price*)
	additional_instruction varchar(200), 
	CONSTRAINT PK_pizza_id PRIMARY KEY (pizza_id)
);

CREATE TABLE invoice_product(
	invoice_id int NOT NULL,
	product_id int NOT NULL 
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
	ADD CONSTRAINT FK_pizza_invoice FOREIGN KEY (invoice_id) REFERENCES invoice(invoice_id) ON DELETE CASCADE
	
;

AlTER TABLE invoice 
	ADD CONSTRAINT FK_invoice_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
;

--ALTER TABLE customer
--	ADD CONSTRAINT FK_customer_user_username FOREIGN KEY (username) REFERENCES users(username)
--;
ALTER TABLE product
	ADD CONSTRAINT FK_product_category_id FOREIGN KEY (product_category_id) REFERENCES product_category(product_category_id)
;

--ROLLBACK TRANSACTION;
