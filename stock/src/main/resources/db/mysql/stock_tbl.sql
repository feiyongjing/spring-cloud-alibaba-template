CREATE TABLE `stock_tbl` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `product_id` bigint NOT NULL,
                             `count` int NOT NULL,
                             PRIMARY KEY (`id`),
    UNIQUE KEY `product_id_index` (`product_id`) USING BTREE
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;