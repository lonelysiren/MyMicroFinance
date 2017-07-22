/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : mf

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-07-22 19:50:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for customer_info_contact
-- ----------------------------
DROP TABLE IF EXISTS `customer_info_contact`;
CREATE TABLE `customer_info_contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `relationship` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `mobile_phone` varchar(255) NOT NULL,
  `company` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_info_contact
-- ----------------------------
