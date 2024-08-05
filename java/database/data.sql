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
	


--COMMIT TRANSACTION;
