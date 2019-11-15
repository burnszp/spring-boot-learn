/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2019-11-15 14:46:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mybatis_generator
-- ----------------------------
DROP TABLE IF EXISTS `mybatis_generator`;
CREATE TABLE `mybatis_generator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mybatis_generator
-- ----------------------------
