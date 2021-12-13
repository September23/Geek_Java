
/* 用户表 */
/* id 名字 密码 邮箱 电话 */

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


/* 商品表 */
/* 用户id 商品id 商品名字 价格 库存 创建时间 更新时间 */

CREATE TABLE `product` (
  `id` int NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  `crete_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

/* 订单表 */
/* id 订单number 用户id 支付价格 支付时间 运送id 运送时间 送达时间 订单创建时间 订单关闭时间*/

CREATE TABLE `order` (
  `id` int NOT NULL,
  `order_number` bigint NOT NULL,
  `user_id` int NOT NULL,
  `payment` decimal(10,0) NOT NULL,
  `payment_time` datetime NOT NULL,
  `send_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `shipping_id` int NOT NULL,
  `create_time` datetime NOT NULL,
  `close_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci