INSERT INTO users (username, password_hash, role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
--Test Users
INSERT INTO users (username, password_hash, role) VALUES ('1','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('2','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('3','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('4','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('5','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('6','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('7','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('8','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('9','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('10','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');

INSERT INTO product_category (product_category_description) --PRODUCT CATEGORIES
	VALUES
	('Size'),('Crust'),('Sauce'), ('Regular Topping'), 
	('Premium Topping'), ('Salad'), ('Appetizer'), ('Drink'), ('Dessert');

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
INSERT INTO product (product_category_id, price, description, quantity) --ADD SAUCES
	VALUES
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Sauce'), 0.00, 'Tomato Sauce - A classic sauce made from tomatoes, often used in pasta dishes and pizzas.', 10),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Sauce'), 0.00, 'Barbecue Sauce - A tangy and sweet sauce used for grilling and barbecuing meats, also popular on pizzas.', 10),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Sauce'), 0.00, 'Pesto Sauce - A green sauce made from basil, garlic, pine nuts, Parmesan cheese, and olive oil, sometimes used on pizzas.', 10),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Sauce'), 0.00, 'Alfredo Sauce - A creamy white sauce made from butter, heavy cream, and Parmesan cheese, occasionally used on pizzas.', 10),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Sauce'), 0.00, 'Garlic Sauce - A creamy sauce made from garlic, often used as a base for pizzas.', 10),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Sauce'), 0.00, 'Buffalo Sauce - A spicy and tangy sauce made from hot sauce and butter, sometimes used on pizzas.', 10),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Sauce'), 0.00, 'Ranch Sauce - A creamy sauce made from buttermilk, garlic, and herbs, sometimes used as a pizza topping.', 10),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Sauce'), 0.00, 'Marinara Sauce - A simple tomato sauce with garlic and herbs, commonly used on pizzas.', 10),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Sauce'), 0.00, 'White Sauce - A creamy sauce made from milk, butter, and flour, used as a base for white pizzas.', 10),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Sauce'), 0.00, 'Chipotle Sauce - A smoky and spicy sauce made from chipotle peppers, sometimes used on pizzas.', 10)
;

INSERT INTO product (product_category_id, price, description, quantity) 
	VALUES
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Dessert'), 5.50, 'Chocolate Cake - A rich and moist chocolate cake.', 20),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Dessert'), 4.75, 'Apple Pie - A traditional apple pie with a flaky crust.', 15),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Dessert'), 6.00, 'Cheesecake - A creamy cheesecake with a graham cracker crust.', 10),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Dessert'), 3.50, 'Brownie - A fudgy chocolate brownie.', 25),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Dessert'), 4.00, 'Ice Cream - A scoop of vanilla ice cream.', 30),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Drink'), 2.50, 'Lemonade - A refreshing lemonade.', 50),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Drink'), 2.25, 'Iced Tea - A chilled iced tea.', 40),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Drink'), 1.75, 'Coffee - A hot cup of coffee.', 60),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Drink'), 3.50, 'Smoothie - A fruit smoothie.', 35),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Drink'), 1.50, 'Soda - A can of soda.', 70),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Appetizer'), 6.75, 'Mozzarella Sticks - Fried mozzarella sticks with marinara sauce.', 20),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Appetizer'), 7.00, 'Nachos - Tortilla chips with cheese and toppings.', 15),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Appetizer'), 5.50, 'Spring Rolls - Crispy spring rolls with dipping sauce.', 25),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Appetizer'), 4.25, 'Garlic Bread - Toasted garlic bread.', 30),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Appetizer'), 6.00, 'Stuffed Mushrooms - Mushrooms stuffed with cheese and herbs.', 20),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Salad'), 7.50, 'Caesar Salad - Romaine lettuce with Caesar dressing and croutons.', 20),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Salad'), 8.00, 'Greek Salad - Mixed greens with feta cheese, olives, and vinaigrette.', 15),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Salad'), 6.50, 'Garden Salad - Mixed greens with assorted vegetables.', 25),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Salad'), 9.00, 'Cobb Salad - Mixed greens with chicken, bacon, and blue cheese.', 10),
((SELECT product_category_id FROM product_category WHERE product_category_description = 'Salad'), 7.75, 'Spinach Salad - Fresh spinach with bacon and eggs.', 20)
;
INSERT INTO customer(first_name, last_name, street_address, city, zip_code, state_abbreviation, phone_number, email, username)
VALUES
  ('Christen','Allen','2247 Ultrices, Av.','Columbus','11911','GA','1122537848','ligula.consectetuer.rhoncus@aol.couk','1'),
  ('Kane','Santos','Ap #984-6233 Justo Avenue','Rochester','67832','MN','5721723281','non.enim@outlook.couk','2'),
  ('Margaret','Kidd','Ap #237-3935 Molestie Road','Newark','72181','DE','5419438506','turpis.non@outlook.couk','3'),
  ('Molly','Fitzpatrick','788-2105 Nulla Rd.','Honolulu','96433','HI','6273616656','porttitor.interdum@yahoo.com','4'),
  ('Lane','Dean','9512 Commodo Ave','Lafayette','37660','LA','1575319229','interdum.sed.auctor@yahoo.edu','5'),
  ('Benedict','Savage','P.O. Box 717, 9204 Metus. Av.','Chandler','85126','AZ','5622864289','egestas@aol.com','6'),
  ('Urielle','Dodson','Ap #152-1135 Vitae Rd.','Grand Rapids','36238','MI','8758145014','praesent.luctus.curabitur@yahoo.com','7'),
  ('Troy','Jensen','Ap #517-703 Nec, Street','Akron','98045','OH','6529485741','facilisis@outlook.edu','8'),
  ('Yuli','Espinoza','Ap #333-353 Nulla. Ave','Salem','38762','OR','8275065667','nam.porttitor.scelerisque@outlook.edu','9'),
  ('Barclay','Guy','263-1235 Eros. Avenue','New Haven','67758','CT','3617524327','sed.pharetra@hotmail.ca','10')
;

INSERT INTO invoice (customer_id, total, is_delivery, is_complete, timestamp) 
	VALUES
		(1, 150.75, true, false, '2022-01-15 10:30:00'),
		(2, 200.50, false, true, '2022-02-20 11:45:00'),
		(3, 75.00, true, true, '2022-03-25 14:20:00'),
		(4, 120.00, false, false, '2022-04-10 09:15:00'),
		(5, 300.25, true, true, '2022-05-05 16:00:00'),
		(6, 50.00, false, true, '2022-06-12 12:30:00'),
		(7, 180.00, true, false, '2022-07-18 13:45:00'),
		(8, 220.75, false, true, '2022-08-22 15:00:00'),
		(9, 90.50, true, true, '2022-09-30 17:30:00'),
		(10, 110.00, false, false, '2022-10-10 08:45:00'),
		(1, 130.75, true, true, '2022-11-15 10:30:00'),
		(2, 210.50, false, false, '2022-12-20 11:45:00'),
		(3, 85.00, true, true, '2023-01-25 14:20:00'),
		(4, 140.00, false, false, '2023-02-10 09:15:00'),
		(5, 310.25, true, true, '2023-03-05 16:00:00'),
		(6, 60.00, false, true, '2023-04-12 12:30:00'),
		(7, 190.00, true, false, '2023-05-18 13:45:00'),
		(8, 230.75, false, true, '2023-06-22 15:00:00'),
		(9, 100.50, true, true, '2023-07-30 17:30:00'),
		(10, 120.00, false, false, '2023-08-10 08:45:00'),
		(1, 140.75, true, true, '2023-09-15 10:30:00'),
		(2, 220.50, false, false, '2023-10-20 11:45:00'),
		(3, 95.00, true, true, '2023-11-25 14:20:00'),
		(4, 160.00, false, false, '2023-12-10 09:15:00'),
		(5, 320.25, true, true, '2023-12-25 16:00:00'),
		(6, 70.00, false, true, '2024-01-12 12:30:00'),
		(7, 200.00, true, false, '2024-02-18 13:45:00'),
		(8, 240.75, false, true, '2024-03-22 15:00:00'),
		(9, 110.50, true, true, '2024-04-30 17:30:00'),
		(10, 130.00, false, false, '2024-05-10 08:45:00')
;
	
