/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : mf

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-07-22 19:49:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for customer_info
-- ----------------------------
DROP TABLE IF EXISTS `customer_info`;
CREATE TABLE `customer_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` int(11) NOT NULL,
  `sex` varchar(255) NOT NULL,
  `age` int(11) NOT NULL,
  `marriage_status` int(11) NOT NULL,
  `idcard_type` int(11) NOT NULL,
  `idcard_number` int(11) NOT NULL,
  `child_age` int(11) DEFAULT NULL,
  `census_register` varchar(255) NOT NULL COMMENT '户籍所在地',
  `house_number` int(11) NOT NULL COMMENT '水电费户号',
  `house_holder` varchar(255) NOT NULL,
  `house_status` int(11) NOT NULL,
  `house_rent` float(11,2) DEFAULT NULL COMMENT '租金',
  `current_residence` varchar(255) NOT NULL COMMENT '现住地',
  `native_type` int(11) DEFAULT NULL COMMENT '是否本地',
  `current_residence_phone` int(11) DEFAULT NULL,
  `mobile_phone` int(11) NOT NULL,
  `mobile_phone_age` int(11) DEFAULT NULL COMMENT '使用年限',
  `mobile_phone_real_name` int(11) DEFAULT NULL COMMENT '号码是否实名制',
  `education` int(11) DEFAULT NULL,
  `graduate_school` varchar(255) DEFAULT NULL,
  `account_number` int(11) NOT NULL,
  `deposit_bank` varchar(255) NOT NULL,
  `customer_contacts_id` int(11) NOT NULL,
  `customer_company_id` int(11) NOT NULL,
  `customer_debt_id` int(11) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_info
-- ----------------------------
