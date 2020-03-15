INSERT INTO `roles` VALUES (1,'client'),(2,'admin');

INSERT INTO `users` VALUES (1,'Tom','tom@mail.ru',1),(2,'Kate','kate@mail.com',1),(3,'Ben','bene@gmail.com',1),(4,'Peter','peter@gmail.com',2);

INSERT INTO `producers` VALUES (1,'Samsung','samsung@gmail.com','China'),(2,'Apple','apple@gmail.com','China'),(3,'Nokia','nokia@gmail.com','China'),(4,'Nokia','nokia@gmail.org','Sweden');

INSERT INTO `products` VALUES (1,'samsung',355,2),(2,'apple',700,2),(3,'nokia',500,3);

INSERT INTO `products_producers` VALUES (1,1),(2,2),(3,3),(3,4);
