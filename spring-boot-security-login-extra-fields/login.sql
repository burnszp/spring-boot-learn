/*
Navicat MySQL Data Transfer
Target Server Type    : MYSQL
Target Server Version : 50641
File Encoding         : 65001

Date: 2019-08-26 13:37:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for privilege
-- ----------------------------
DROP TABLE IF EXISTS `privilege`;
CREATE TABLE `privilege` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of privilege
-- ----------------------------
INSERT INTO `privilege` VALUES ('1', 'READ_PRIVILEGE');
INSERT INTO `privilege` VALUES ('2', 'WRITE_PRIVILEGE');
INSERT INTO `privilege` VALUES ('3', 'CHANGE_PASSWORD_PRIVILEGE');
INSERT INTO `privilege` VALUES ('4', 'DELETE_USER');
INSERT INTO `privilege` VALUES ('5', 'GET_USER');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('4', 'ROLE_ADMIN');
INSERT INTO `role` VALUES ('5', 'ROLE_USER');

-- ----------------------------
-- Table structure for roles_privileges
-- ----------------------------
DROP TABLE IF EXISTS `roles_privileges`;
CREATE TABLE `roles_privileges` (
  `role_id` bigint(20) NOT NULL,
  `privilege_id` bigint(20) NOT NULL,
  KEY `FKp0x1d9k5aksyqd1akwwfkh0ki` (`privilege_id`),
  KEY `FK2rfl694fu6ls2f2mqcxesqc6p` (`role_id`),
  CONSTRAINT `FK2rfl694fu6ls2f2mqcxesqc6p` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKp0x1d9k5aksyqd1akwwfkh0ki` FOREIGN KEY (`privilege_id`) REFERENCES `privilege` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles_privileges
-- ----------------------------
INSERT INTO `roles_privileges` VALUES ('4', '1');
INSERT INTO `roles_privileges` VALUES ('4', '2');
INSERT INTO `roles_privileges` VALUES ('4', '3');
INSERT INTO `roles_privileges` VALUES ('5', '1');
INSERT INTO `roles_privileges` VALUES ('5', '3');
INSERT INTO `roles_privileges` VALUES ('4', '4');
INSERT INTO `roles_privileges` VALUES ('4', '5');

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `is_using2fa` bit(1) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  `secret` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_account
-- ----------------------------
INSERT INTO `user_account` VALUES ('6', 'test@test.com', '', 'Test', '\0', 'Test', '$2a$10$8uTb6Q6761F46cNmgixuPO6IOlDgvDUNFC9irdVJG0giOXPGhzGH6', 'JNKTCKAQS26J7ILE');
INSERT INTO `user_account` VALUES ('7', 'test2@test.com', '', 'feng', '\0', 'zhang', '$2a$10$8uTb6Q6761F46cNmgixuPO6IOlDgvDUNFC9irdVJG0giOXPGhzGH6', 'JNKTCKAQS26J7ILE');

-- ----------------------------
-- Table structure for users_roles
-- ----------------------------
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKa9r8g5hiyy57ts5u4tkf0lbab` (`role_id`),
  KEY `FKci4mdvg1fmo9eqmwno1y9o0fa` (`user_id`),
  CONSTRAINT `FKa9r8g5hiyy57ts5u4tkf0lbab` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKci4mdvg1fmo9eqmwno1y9o0fa` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users_roles
-- ----------------------------
INSERT INTO `users_roles` VALUES ('6', '4');
INSERT INTO `users_roles` VALUES ('7', '5');
