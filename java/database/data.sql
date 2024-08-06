INSERT INTO users (username, password_hash, role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO product_category (product_category_description) --PRODUCT CATEGORIES
	VALUES ('Size'),('Crust'),('Sauce'), ('Regular Topping'), ('Premium Topping');

INSERT INTO product (description, price, product_category_id) --TOPPINGS
	VALUES ('Pepperoni', 1.25,1), ('Sausage', 1.25,1), ('Extra Cheese', 0.75,1), ('Green Peppers', 1.00,1),
	 ('Parmesean', 0.75,1), ('Onions', 1.00,1), ('Mushrooms', 1.00,1),('Chicken', 1.00,1),('Bacon', 1.25,1),
	 ('Anchovies', 2.00,1), ('Black Olives', 0.50,1), ('Pineapple', 1.00,1),('Ham', 1.25,1),('Tomatoes', 0.50,1),
	 ('Jalapenos', 0.50,1);

UPDATE product SET product_category_id = (SELECT product_category_id FROM product_category WHERE product_category_description = 'Premium Topping')
	WHERE price > 1.00;
UPDATE product SET product_category_id = (SELECT product_category_id FROM product_category WHERE product_category_description = 'Regular Topping')
	WHERE price <= 1.00;
	
INSERT INTO product (description, price, product_category_id) --ADD SIZES
	VALUES ('12 inch Small Pizza', 12.99, (SELECT product_category_id FROM product_category WHERE product_category_description = 'Size')),
	 ('14 inch Medium Pizza', 14.99, (SELECT product_category_id FROM product_category WHERE product_category_description = 'Size')),
	  ('16 inch Large Pizza', 16.99, (SELECT product_category_id FROM product_category WHERE product_category_description = 'Size')),
	   ('18 inch Extra Large Pizza', 18.99, (SELECT product_category_id FROM product_category WHERE product_category_description = 'Size'))
;

INSERT INTO product (description, price, product_category_id) --ADD CRUSTS
	VALUES ('Hand Tossed', 0.00,  (SELECT product_category_id FROM product_category WHERE product_category_description = 'Crust')),
	('Thin', 0,  (SELECT product_category_id FROM product_category WHERE product_category_description = 'Crust')),
	('Deep Dish', 0.00,  (SELECT product_category_id FROM product_category WHERE product_category_description = 'Crust')),
	('Stuffed', 2.00,  (SELECT product_category_id FROM product_category WHERE product_category_description = 'Crust')),
	('Pretzel Crust', 0,  (SELECT product_category_id FROM product_category WHERE product_category_description = 'Crust'))
	;
INSERT INTO product (product_category_id, price, description, quantity) VALUES --ADD SAUCES
(1, 0.00, 'Tomato Sauce - A classic sauce made from tomatoes, often used in pasta dishes and pizzas.', 10),
(1, 0.00, 'Barbecue Sauce - A tangy and sweet sauce used for grilling and barbecuing meats, also popular on pizzas.', 10),
(1, 0.00, 'Pesto Sauce - A green sauce made from basil, garlic, pine nuts, Parmesan cheese, and olive oil, sometimes used on pizzas.', 10),
(1, 0.00, 'Alfredo Sauce - A creamy white sauce made from butter, heavy cream, and Parmesan cheese, occasionally used on pizzas.', 10),
(1, 0.00, 'Garlic Sauce - A creamy sauce made from garlic, often used as a base for pizzas.', 10),
(1, 0.00, 'Buffalo Sauce - A spicy and tangy sauce made from hot sauce and butter, sometimes used on pizzas.', 10),
(1, 0.00, 'Ranch Sauce - A creamy sauce made from buttermilk, garlic, and herbs, sometimes used as a pizza topping.', 10),
(1, 0.00, 'Marinara Sauce - A simple tomato sauce with garlic and herbs, commonly used on pizzas.', 10),
(1, 0.00, 'White Sauce - A creamy sauce made from milk, butter, and flour, used as a base for white pizzas.', 10),
(1, 0.00, 'Chipotle Sauce - A smoky and spicy sauce made from chipotle peppers, sometimes used on pizzas.', 10);

INSERT INTO customer(first_name,last_name,street_address,city,zip_code,state_abbreviation,phone_number,email,username)
VALUES
  ('Christen','Allen','2247 Ultrices, Av.','Columbus','11911','GA','1122537848','ligula.consectetuer.rhoncus@aol.couk','Nullam'),
  ('Kane','Santos','Ap #984-6233 Justo Avenue','Rochester','67832','MN','5721723281','non.enim@outlook.couk','mauris'),
  ('Margaret','Kidd','Ap #237-3935 Molestie Road','Newark','72181','DE','5419438506','turpis.non@outlook.couk','mauris,'),
  ('Molly','Fitzpatrick','788-2105 Nulla Rd.','Honolulu','96433','HI','6273616656','porttitor.interdum@yahoo.com','Proin'),
  ('Lane','Dean','9512 Commodo Ave','Lafayette','37660','LA','1575319229','interdum.sed.auctor@yahoo.edu','tristique'),
  ('Benedict','Savage','P.O. Box 717, 9204 Metus. Av.','Chandler','85126','AZ','5622864289','egestas@aol.com','enim.'),
  ('Urielle','Dodson','Ap #152-1135 Vitae Rd.','Grand Rapids','36238','MI','8758145014','praesent.luctus.curabitur@yahoo.com','rhoncus.'),
  ('Troy','Jensen','Ap #517-703 Nec, Street','Akron','98045','OH','6529485741','facilisis@outlook.edu','et'),
  ('Yuli','Espinoza','Ap #333-353 Nulla. Ave','Salem','38762','OR','8275065667','nam.porttitor.scelerisque@outlook.edu','libero.'),
  ('Barclay','Guy','263-1235 Eros. Avenue','New Haven','67758','CT','3617524327','sed.pharetra@hotmail.ca','Ut');
	
