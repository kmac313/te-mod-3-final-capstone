--BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO pizza_size (pizza_size_id, pizza_size_description, diameter, base_price) VALUES ('S', 'Small', 12, 12.99);
INSERT INTO pizza_size (pizza_size_id, pizza_size_description, diameter, base_price) VALUES ('M', 'Medium', 14, 14.99);
INSERT INTO pizza_size (pizza_size_id, pizza_size_description, diameter, base_price) VALUES ('L', 'Large', 16, 16.99);
INSERT INTO pizza_size (pizza_size_id, pizza_size_description, diameter, base_price) VALUES ('XL' , 'Extra Large', 18, 18.99);

INSERT INTO topping (topping_name, additional_price) VALUES ('Pepperoni', 1.25);
INSERT INTO topping (topping_name, additional_price) VALUES ('Sausage', 1.25);
INSERT INTO topping (topping_name, additional_price) VALUES ('Extra Cheese', 0.75);
INSERT INTO topping (topping_name, additional_price) VALUES ('Green Peppers', 1.00);
INSERT INTO topping (topping_name, additional_price) VALUES ('Parmesean', 0.75);
INSERT INTO topping (topping_name, additional_price) VALUES ('Onions', 1.00);
INSERT INTO topping (topping_name, additional_price) VALUES ('Mushrooms', 1.00);
INSERT INTO topping (topping_name, additional_price) VALUES ('Chicken', 1.00);
INSERT INTO topping (topping_name, additional_price) VALUES ('Bacon', 1.25);
INSERT INTO topping (topping_name, additional_price) VALUES ('Anchovies', 2.00);
INSERT INTO topping (topping_name, additional_price) VALUES ('Black Olives', 0.50);
INSERT INTO topping (topping_name, additional_price) VALUES ('Pineapple', 1.00);
INSERT INTO topping (topping_name, additional_price) VALUES ('Ham', 1.25);
INSERT INTO topping (topping_name, additional_price) VALUES ('Tomatoes', 0.50);
INSERT INTO topping (topping_name, additional_price) VALUES ('Jalapenos', 0.50);


--COMMIT TRANSACTION;
