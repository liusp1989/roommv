/*
Navicat MySQL Data Transfer

Source Server         : roommv
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : roommvserver

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-01-23 17:58:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `visitinfo`
-- ----------------------------
DROP TABLE IF EXISTS `visitinfo`;
CREATE TABLE `visitinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(36) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `referUrl` varchar(1024) DEFAULT NULL,
  `currentUrl` varchar(1024) DEFAULT NULL,
  `createDate` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of visitinfo
-- ----------------------------
