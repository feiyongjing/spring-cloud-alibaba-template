CREATE TABLE `order_tbl` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `product_id` bigint NOT NULL,
                             `total_amount` decimal(20,2) NOT NULL,
    `status` varchar(10) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=365 DEFAULT CHARSET=utf8;