INSERT INTO my_database.customer (id,username) VALUES
	 (1,'Marko Musica'),
	 (2,'Ivo Ivic');
INSERT INTO my_database.customer_seq (next_val) VALUES
	 (101);
INSERT INTO my_database.home (id,address,name,customer_id) VALUES
	 (1,'Varazdin, Ul. Julija Merlica 9','WORKPLACE',1),
	 (2,'Varazdin, Ul. Petra Krešimira IV. 42','PRIMARY_HOME',2);
INSERT INTO my_database.home_seq (next_val) VALUES
	 (101);
INSERT INTO my_database.orders (id,customer_id) VALUES
	 (1,1);
INSERT INTO my_database.orders_seq (next_val) VALUES
	 (51);
INSERT INTO my_database.product (id,name,price) VALUES
	 (1,'ASUS Monitor',100.00),
	 (2,'Wireshark keyboard',10.00),
	 (3,'Razer mouse',20.00);
INSERT INTO my_database.product_orders (id,quantity,orders_id,product_id) VALUES
	 (1,1,1,1),
	 (2,2,1,2);
INSERT INTO my_database.product_orders_seq (next_val) VALUES
	 (101);
INSERT INTO my_database.product_seq (next_val) VALUES
	 (101);
