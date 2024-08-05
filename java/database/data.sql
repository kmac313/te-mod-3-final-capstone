--BEGIN TRANSACTION;

INSERT INTO users (username, password_hash, role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO pizza_size (pizza_size_id, pizza_size_description, diameter, base_price) VALUES ('S', 'Small', 12, 12.99);
INSERT INTO pizza_size (pizza_size_id, pizza_size_description, diameter, base_price) VALUES ('M', 'Medium', 14, 14.99);
INSERT INTO pizza_size (pizza_size_id, pizza_size_description, diameter, base_price) VALUES ('L', 'Large', 16, 16.99);
INSERT INTO pizza_size (pizza_size_id, pizza_size_description, diameter, base_price) VALUES ('XL' , 'Extra Large', 18, 18.99);

INSERT INTO product_category (product_category_description) VALUES ('Size');
INSERT INTO product_category (product_category_description) VALUES ('Crust');
INSERT INTO product_category (product_category_description) VALUES ('Sauce');
INSERT INTO product_category (product_category_description) VALUES ('Regular Topping');
INSERT INTO product_category (product_category_description) VALUES ('Premium Topping');

INSERT INTO product (description, price, product_category_id) VALUES ('Pepperoni', 1.25,1);
INSERT INTO product (description, price, product_category_id) VALUES ('Sausage', 1.25,1);
INSERT INTO product (description, price, product_category_id) VALUES ('Extra Cheese', 0.75,1);
INSERT INTO product (description, price, product_category_id) VALUES ('Green Peppers', 1.00,1);
INSERT INTO product (description, price, product_category_id) VALUES ('Parmesean', 0.75,1);
INSERT INTO product (description, price, product_category_id) VALUES ('Onions', 1.00,1);
INSERT INTO product (description, price, product_category_id) VALUES ('Mushrooms', 1.00,1);
INSERT INTO product (description, price, product_category_id) VALUES ('Chicken', 1.00,1);
INSERT INTO product (description, price, product_category_id) VALUES ('Bacon', 1.25,1);
INSERT INTO product (description, price, product_category_id) VALUES ('Anchovies', 2.00,1);
INSERT INTO product (description, price, product_category_id) VALUES ('Black Olives', 0.50,1);
INSERT INTO product (description, price, product_category_id) VALUES ('Pineapple', 1.00,1);
INSERT INTO product (description, price, product_category_id) VALUES ('Ham', 1.25,1);
INSERT INTO product (description, price, product_category_id) VALUES ('Tomatoes', 0.50,1);
INSERT INTO product (description, price, product_category_id) VALUES ('Jalapenos', 0.50,1);

UPDATE product SET product_category_id = (SELECT product_category_id FROM product_category WHERE product_category_description = 'Premium Topping')
	WHERE price > 1.00;
UPDATE product SET product_category_id = (SELECT product_category_id FROM product_category WHERE product_category_description = 'Regular Topping')
	WHERE price <= 1.00;
	


--COMMIT TRANSACTION;
