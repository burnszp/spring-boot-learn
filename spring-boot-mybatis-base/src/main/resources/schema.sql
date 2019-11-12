/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2019-10-25 17:13:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mybatis_say
-- ----------------------------
DROP TABLE IF EXISTS `mybatis_say`;
CREATE TABLE `mybatis_say` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `say` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mybatis_say
-- ----------------------------
INSERT INTO `mybatis_say` VALUES ('1', 'Hi Mybatis!');
