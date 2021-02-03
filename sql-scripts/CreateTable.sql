CREATE DATABASE IF NOT EXISTS `user`;

CREATE TABLE IF NOT EXISTS `user`.`user` (
	`id` bigint PRIMARY KEY AUTO_INCREMENT, 
	`username` varchar(100) NOT NULL UNIQUE, 
	`password` varchar(100),
	`first_name` varchar(100), 
	`last_name` varchar(100), 
	`email` varchar(100),
	`birthday` date,
	`date_created` timestamp default now()
);