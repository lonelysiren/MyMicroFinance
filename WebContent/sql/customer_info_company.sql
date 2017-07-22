/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : mf

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-07-22 19:50:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for customer_info_company
-- ----------------------------
DROP TABLE IF EXISTS `customer_info_company`;
CREATE TABLE `customer_info_company` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `phone_number` int(11) DEFAULT NULL,
  `phone_number_extension` int(11) DEFAULT NULL,
  `phone_number_hr` int(11) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  `salary` decimal(10,0) DEFAULT NULL,
  `accumulation_fund_account` varchar(255) DEFAULT NULL,
  `accumulation_fund_password` varchar(255) DEFAULT NULL,
  `company_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_info_company
-- ----------------------------
