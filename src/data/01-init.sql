-- my_database.customer definition

CREATE TABLE `customer` (
  `id` bigint NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- my_database.customer_seq definition

CREATE TABLE `customer_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- my_database.home_seq definition

CREATE TABLE `home_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- my_database.orders_seq definition

CREATE TABLE `orders_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- my_database.product definition

CREATE TABLE `product` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- my_database.product_orders_seq definition

CREATE TABLE `product_orders_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- my_database.product_seq definition

CREATE TABLE `product_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- my_database.home definition

CREATE TABLE `home` (
  `id` bigint NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeh8t0lv87m2ewembncaswc7ch` (`customer_id`),
  CONSTRAINT `FKeh8t0lv87m2ewembncaswc7ch` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- my_database.orders definition

CREATE TABLE `orders` (
  `id` bigint NOT NULL,
  `customer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK624gtjin3po807j3vix093tlf` (`customer_id`),
  CONSTRAINT `FK624gtjin3po807j3vix093tlf` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- my_database.product_orders definition

CREATE TABLE `product_orders` (
  `id` bigint NOT NULL,
  `quantity` int NOT NULL,
  `orders_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9ydyx6pyhf84scldi46dxyw8y` (`orders_id`),
  KEY `FK5c959v5pr5pky28q88sbgg8hq` (`product_id`),
  CONSTRAINT `FK5c959v5pr5pky28q88sbgg8hq` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FK9ydyx6pyhf84scldi46dxyw8y` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

