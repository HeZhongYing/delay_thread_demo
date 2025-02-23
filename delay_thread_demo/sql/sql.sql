-- 创建数据库
create schema db_user collate utf8mb4_general_ci;

-- 创建表
use db_user;
CREATE TABLE `user`
(
    `id`       int         NOT NULL AUTO_INCREMENT COMMENT '序列号',
    `username` varchar(20) NOT NULL COMMENT '用户名',
    `password` varchar(20) NOT NULL COMMENT '密码',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';