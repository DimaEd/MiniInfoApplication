DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`
(
    `id`       bigint(8)   NOT NULL AUTO_INCREMENT,
    `roleName` varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `id`      bigint(8)   NOT NULL AUTO_INCREMENT,
    `name`    varchar(45) NOT NULL,
    `email`   varchar(45) NOT NULL,
    `role_id` bigint(8)   NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_Users_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
);

CREATE TABLE `producers`
(
    `id`          bigint(8)   NOT NULL AUTO_INCREMENT,
    `companyName` varchar(45) NOT NULL,
    `email`       varchar(45) NOT NULL,
    `country`     varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`
(
    `id`          bigint(8)   NOT NULL AUTO_INCREMENT,
    `productName` varchar(45) NOT NULL,
    `cost`        double      NOT NULL,
    `user_id`     bigint(8)   NOT NULL,
    PRIMARY KEY (`id`, `user_id`),
    CONSTRAINT `fk_products_Users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

DROP TABLE IF EXISTS `products_producers`;

CREATE TABLE `products_producers`
(
    `product_id`  bigint(8) NOT NULL,
    `producer_id` bigint(8) NOT NULL,
    foreign key (`producer_id`) references `producers` (`id`),
    foreign key (`product_id`) references `products` (`id`)
);
