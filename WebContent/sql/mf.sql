/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : mf

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-08-11 17:39:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `company_info`
-- ----------------------------
DROP TABLE IF EXISTS `company_info`;
CREATE TABLE `company_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company_info
-- ----------------------------
INSERT INTO `company_info` VALUES ('1', 'xxx公司');

-- ----------------------------
-- Table structure for `customer_info`
-- ----------------------------
DROP TABLE IF EXISTS `customer_info`;
CREATE TABLE `customer_info` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(11) NOT NULL,
  `sex` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  `marriage_status` int(11) NOT NULL,
  `idcard_type` int(11) NOT NULL,
  `idcard_number` varchar(18) NOT NULL,
  `child_age` varchar(3) DEFAULT NULL,
  `census_register` varchar(255) NOT NULL COMMENT '户籍所在地',
  `cenuse_register_detail` varchar(255) NOT NULL,
  `house_number` varchar(255) NOT NULL COMMENT '水电费户号',
  `house_status` int(11) NOT NULL,
  `house_rent` varchar(11) DEFAULT NULL COMMENT '租金',
  `house_holder` varchar(255) NOT NULL,
  `current_residence` varchar(255) NOT NULL COMMENT '现住地',
  `native_type` int(12) NOT NULL COMMENT '是否本地',
  `current_residence_phone` varchar(11) DEFAULT NULL,
  `mobile_phone` varchar(11) NOT NULL,
  `mobile_phone_age` varchar(11) DEFAULT NULL COMMENT '使用年限',
  `mobile_phone_real_name` int(11) DEFAULT NULL COMMENT '号码是否实名制',
  `education` int(11) DEFAULT NULL,
  `graduate_school` varchar(255) DEFAULT NULL,
  `account_number` varchar(255) NOT NULL,
  `deposit_bank` varchar(255) NOT NULL,
  `remark` text,
  `sales_account_manager` varchar(255) NOT NULL COMMENT '客户经理',
  `create_time` datetime DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_info
-- ----------------------------
INSERT INTO `customer_info` VALUES ('1', '测试用户', '1', '18', '0', '0', '431003199408136018', '18', '1', '', '1', '1', '1.00', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '1', '2', '1', '2017-08-07 17:10:16', '2017-08-07 17:10:16');
INSERT INTO `customer_info` VALUES ('2', '吴一凡', '0', '22', '1', '1', '431003199408136018', '23', '安徽省-安庆市-大观区', '家里', '431003199408136018', '1', '', '431003199408136018', '安徽省-安庆市-大观区-家里', '0', '', '13762567348', '', '0', '1', '家里', '家里', '家里', '', '1', '2017-08-08 16:47:42', '2017-08-08 16:55:19');
INSERT INTO `customer_info` VALUES ('3', '吴一凡', '0', '22', '1', '1', '431003199408136018', '23', '安徽省-安庆市-大观区', '家里', '431003199408136018', '1', '', '431003199408136018', '安徽省-安庆市-大观区-家里', '0', '', '13762567348', '', '0', '1', '家里', '家里', '家里', '', '1', '2017-08-08 16:47:42', null);
INSERT INTO `customer_info` VALUES ('4', '吴一凡', '0', '22', '1', '1', '431003199408136018', '23', '安徽省-安庆市-大观区', '家里', '431003199408136018', '1', '', '431003199408136018', '安徽省-安庆市-大观区-家里', '0', '', '13762567348', '', '0', '1', '家里', '家里', '家里', 'qigua', '1', '2017-08-08 16:51:17', '2017-08-08 16:55:08');
INSERT INTO `customer_info` VALUES ('5', '吴一凡', '0', '22', '1', '1', '431003199408136018', '23', '安徽省-安庆市-大观区', '家里', '431003199408136018', '1', '', '431003199408136018', '安徽省-安庆市-大观区-家里', '0', '', '13762567348', '', '0', '1', '家里', '家里', '家里', 'nihao', '1', '2017-08-08 16:54:45', '2017-08-08 16:55:03');
INSERT INTO `customer_info` VALUES ('6', '吴一凡', '0', '22', '1', '1', '431003199408136018', '', '安徽省-安庆市-大观区', '安徽省-安庆市-大观区', '6018', '1', '', '6018', '安徽省-安庆市-大观区', '0', '', '13762567348', '', '0', '1', '', '6018', '6018', '', '1', '2017-08-08 17:08:01', null);
INSERT INTO `customer_info` VALUES ('7', '吴一凡', '0', '22', '1', '1', '431003199408136018', '100', '安徽省-安庆市-大观区', '431003199408136018', '431003199408136018', '1', '666.5', '431003199408136018', '431003199408136018', '0', '', '13762567348', '1', '0', '1', '431003199408136018', '431003199408136018', '431003199408136018', '431003199408136018', '1', '2017-08-08 17:10:46', null);
INSERT INTO `customer_info` VALUES ('8', '吴一凡', '0', '22', '1', '1', '431003199408136018', '', '安徽省-安庆市-大观区', '431003199408136018', '431003199408136018', '1', '', '431003199408136018', '431003199408136018', '0', '', '13762567348', '1', '0', '1', '', '431003199408136018', '431003199408136018', '', '1', '2017-08-08 17:16:25', null);
INSERT INTO `customer_info` VALUES ('9', '吴一凡', '0', '22', '1', '1', '431003199408136018', '', '安徽省-安庆市-大观区', '431003199408136018', '431003199408136018', '1', '', '431003199408136018', '431003199408136018', '0', '', '13762567348', '', '0', '1', '', '431003199408136018', '431003199408136018', '', '1', '2017-08-08 17:19:52', null);
INSERT INTO `customer_info` VALUES ('10', '吴一凡', '0', '22', '1', '1', '431003199408136018', '', '安徽省-安庆市-大观区', '431003199408136018', '431003199408136018', '1', '', '431003199408136018', '431003199408136018', '0', '', '13762567348', '', '0', '1', '', '431003199408136018', '431003199408136018', null, '1', '2017-08-11 16:29:56', '2017-08-11 16:29:58');
INSERT INTO `customer_info` VALUES ('11', '测试用户', '0', '22', '1', '1', '431003199408136018', '23', '安徽省-安庆市-大观区', '测试用户', '23', '1', '', '23', '安徽省-安庆市-大观区-测试用户', '0', '', '13762567348', '6', '0', '1', '测试用户', '测试用户', '测试用户', '测试用户---', '1', '2017-08-11 17:19:24', null);
INSERT INTO `customer_info` VALUES ('12', '测试', '0', '22', '1', '1', '431003199408136018', '', '安徽省-安庆市-大观区', '测试', '66', '1', '', '66', '安徽省-安庆市-大观区-测试', '0', '', '13762567348', '', '0', '1', '', '测试', '测试', '测试', '1', '2017-08-11 17:23:40', null);
INSERT INTO `customer_info` VALUES ('13', '测试', '0', '22', '2', '1', '431003199408136018', '', '安徽省-安庆市-大观区', '测试', '666', '1', '', '测试', '安徽省-安庆市-大观区-测试', '0', '', '13762567348', '', '0', '1', '', '测试', '测试', '', '1', '2017-08-11 17:28:01', null);
INSERT INTO `customer_info` VALUES ('14', '测试', '0', '22', '1', '1', '431003199408136018', '', '安徽省-安庆市-大观区', '测试', '66', '1', '', '66', '安徽省-安庆市-大观区-测试', '0', '', '13762567348', '', '0', '1', '', '测试', '测试', '测试', '1', '2017-08-11 17:34:57', null);

-- ----------------------------
-- Table structure for `customer_info_company`
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
  `customer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_info_company
-- ----------------------------

-- ----------------------------
-- Table structure for `customer_info_contact`
-- ----------------------------
DROP TABLE IF EXISTS `customer_info_contact`;
CREATE TABLE `customer_info_contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `relationship` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `mobile_phone` varchar(255) NOT NULL,
  `company` varchar(255) NOT NULL,
  `customer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_info_contact
-- ----------------------------
INSERT INTO `customer_info_contact` VALUES ('1', '1', '6666', '666', '66', '1');
INSERT INTO `customer_info_contact` VALUES ('2', '1', '6666', '666', '66', '1');
INSERT INTO `customer_info_contact` VALUES ('3', '1', '6666', '666', '66', '1');
INSERT INTO `customer_info_contact` VALUES ('4', '1', '6666', '666', '66', '1');
INSERT INTO `customer_info_contact` VALUES ('5', '1', '6666', '666', '66', '1');
INSERT INTO `customer_info_contact` VALUES ('6', '1', '666', '66', '666', '2');
INSERT INTO `customer_info_contact` VALUES ('7', '', '', '', '', '2');
INSERT INTO `customer_info_contact` VALUES ('8', '', '', '', '', '2');
INSERT INTO `customer_info_contact` VALUES ('9', '', '', '', '', '2');
INSERT INTO `customer_info_contact` VALUES ('10', '1', '6666', '666', '66', '2');
INSERT INTO `customer_info_contact` VALUES ('11', '1', '666', '66', '666', '3');
INSERT INTO `customer_info_contact` VALUES ('12', '', '', '', '', '3');
INSERT INTO `customer_info_contact` VALUES ('13', '', '', '', '', '3');
INSERT INTO `customer_info_contact` VALUES ('14', '', '', '', '', '3');
INSERT INTO `customer_info_contact` VALUES ('15', '1', '6666', '666', '66', '3');
INSERT INTO `customer_info_contact` VALUES ('16', '1', '666', '66', '666', '4');
INSERT INTO `customer_info_contact` VALUES ('17', '', '', '', '', '4');
INSERT INTO `customer_info_contact` VALUES ('18', '', '', '', '', '4');
INSERT INTO `customer_info_contact` VALUES ('19', '', '', '', '', '4');
INSERT INTO `customer_info_contact` VALUES ('20', '1', '6666', '666', '66', '4');
INSERT INTO `customer_info_contact` VALUES ('21', '1', '666', '66', '666', '5');
INSERT INTO `customer_info_contact` VALUES ('22', '', '', '', '', '5');
INSERT INTO `customer_info_contact` VALUES ('23', '', '', '', '', '5');
INSERT INTO `customer_info_contact` VALUES ('24', '', '', '', '', '5');
INSERT INTO `customer_info_contact` VALUES ('25', '1', '6666', '666', '66', '5');
INSERT INTO `customer_info_contact` VALUES ('26', '1', '666', '66', '666', '6');
INSERT INTO `customer_info_contact` VALUES ('27', '1', '6666', '666', '66', '6');
INSERT INTO `customer_info_contact` VALUES ('28', '1', '666', '66', '666', '6');
INSERT INTO `customer_info_contact` VALUES ('29', '1', '6666', '666', '66', '6');
INSERT INTO `customer_info_contact` VALUES ('30', '1', '666', '66', '666', '6');
INSERT INTO `customer_info_contact` VALUES ('31', '1', '6666', '66', '666', '7');
INSERT INTO `customer_info_contact` VALUES ('32', '2', '66', '666', '6666', '7');
INSERT INTO `customer_info_contact` VALUES ('33', '1', '5555', '555', '55', '7');
INSERT INTO `customer_info_contact` VALUES ('34', '1', '4444', '444', '44', '7');
INSERT INTO `customer_info_contact` VALUES ('35', '1', '3333', '333', '333', '7');
INSERT INTO `customer_info_contact` VALUES ('36', '1', '6666', '66', '666', '8');
INSERT INTO `customer_info_contact` VALUES ('37', '2', '66', '666', '6666', '8');
INSERT INTO `customer_info_contact` VALUES ('38', '1', '5555', '555', '55', '8');
INSERT INTO `customer_info_contact` VALUES ('39', '1', '4444', '444', '44', '8');
INSERT INTO `customer_info_contact` VALUES ('40', '1', '3333', '333', '333', '8');
INSERT INTO `customer_info_contact` VALUES ('41', '1', '6666', '66', '666', '9');
INSERT INTO `customer_info_contact` VALUES ('42', '2', '66', '666', '6666', '9');
INSERT INTO `customer_info_contact` VALUES ('43', '1', '5555', '555', '55', '9');
INSERT INTO `customer_info_contact` VALUES ('44', '1', '4444', '444', '44', '9');
INSERT INTO `customer_info_contact` VALUES ('45', '1', '3333', '333', '333', '9');
INSERT INTO `customer_info_contact` VALUES ('46', '1', '6666', '66', '666', '10');
INSERT INTO `customer_info_contact` VALUES ('47', '2', '66', '666', '6666', '10');
INSERT INTO `customer_info_contact` VALUES ('48', '1', '5555', '555', '55', '10');
INSERT INTO `customer_info_contact` VALUES ('49', '1', '4444', '444', '44', '10');
INSERT INTO `customer_info_contact` VALUES ('50', '1', '3333', '333', '333', '10');
INSERT INTO `customer_info_contact` VALUES ('51', '1', '测试', '6', '测试', '14');
INSERT INTO `customer_info_contact` VALUES ('52', '1', '测试', '6', '测试', '14');
INSERT INTO `customer_info_contact` VALUES ('53', '1', '测试', '6', '测试', '14');
INSERT INTO `customer_info_contact` VALUES ('54', '1', '测试', '6', '测试', '14');
INSERT INTO `customer_info_contact` VALUES ('55', '1', '测试', '6', '测试', '14');

-- ----------------------------
-- Table structure for `customer_info_contact_other`
-- ----------------------------
DROP TABLE IF EXISTS `customer_info_contact_other`;
CREATE TABLE `customer_info_contact_other` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `customer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_info_contact_other
-- ----------------------------
INSERT INTO `customer_info_contact_other` VALUES ('1', '5555', '9');
INSERT INTO `customer_info_contact_other` VALUES ('2', '5555', '10');

-- ----------------------------
-- Table structure for `navs`
-- ----------------------------
DROP TABLE IF EXISTS `navs`;
CREATE TABLE `navs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `spread` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of navs
-- ----------------------------
INSERT INTO `navs` VALUES ('1', null, '账号管理', 'fa-stop-circle', '#', 'false');
INSERT INTO `navs` VALUES ('2', null, '客户管理', 'fa-stop-circle', '#', 'false');
INSERT INTO `navs` VALUES ('3', null, '产品管理', 'fa-stop-circle', '#', 'false');
INSERT INTO `navs` VALUES ('4', null, '短信管理', 'fa-stop-circle', '#', 'false');
INSERT INTO `navs` VALUES ('5', null, '数据管理', 'fa-stop-circle', '#', 'false');
INSERT INTO `navs` VALUES ('6', null, '通讯录管理', 'fa-stop-circle', '#', 'false');
INSERT INTO `navs` VALUES ('7', null, '催收管理', 'fa-stop-circle', '#', 'false');
INSERT INTO `navs` VALUES ('8', null, '对账管理', 'fa-stop-circle', '#', 'false');
INSERT INTO `navs` VALUES ('9', '2', '客户列表', 'fa-github', 'customer_list.html', 'false');
INSERT INTO `navs` VALUES ('10', '2', '添加客户', 'fa-github', 'customer_add.html', 'false');
INSERT INTO `navs` VALUES ('11', '3', '产品列表', 'fa-github', '#', 'false');
INSERT INTO `navs` VALUES ('12', '3', '添加产品', 'fa-github', '#', 'false');
INSERT INTO `navs` VALUES ('13', '4', '自动发送', 'fa-github', '#', 'false');
INSERT INTO `navs` VALUES ('14', '4', '手动发送', 'fa-github', '#', 'false');
INSERT INTO `navs` VALUES ('15', '5', '数据统计', 'fa-github', '#', 'false');
INSERT INTO `navs` VALUES ('16', '5', '客户经理统计', 'fa-github', '#', 'false');
INSERT INTO `navs` VALUES ('17', '6', '通讯录列表', 'fa-github', '#', 'false');
INSERT INTO `navs` VALUES ('18', '6', '添加通讯录', 'fa-github', '#', 'false');
INSERT INTO `navs` VALUES ('19', '1', '管理员列表', 'fa-github', 'admin_list.html', 'false');
INSERT INTO `navs` VALUES ('20', '1', null, null, null, null);

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `stauts` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `company_id` int(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '6666', '666', '测试管理员', '', '0', '1', '1');
INSERT INTO `user_info` VALUES ('2', '55555', '555', '555', '', '0', '3', '1');
INSERT INTO `user_info` VALUES ('7', '66666', '6666', '测试管理员2', '', '0', '2', '1');

-- ----------------------------
-- View structure for `manager_relation`
-- ----------------------------
DROP VIEW IF EXISTS `manager_relation`;
CREATE ALGORITHM=UNDEFINED DEFINER=`skip-grants user`@`skip-grants host` SQL SECURITY DEFINER VIEW `manager_relation` AS select `customer_info`.`idcard_number` AS `idcard_number`,`user_info`.`company_id` AS `company_id`,`user_info`.`id` AS `sales_account_manager_id` from ((`user_info` join `company_info` on((`user_info`.`company_id` = `company_info`.`id`))) join `customer_info` on((`customer_info`.`sales_account_manager` = `user_info`.`id`))) ;
