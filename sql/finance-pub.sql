/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : finance-pub

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2020-05-31 11:19:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for fund_budget
-- ----------------------------
DROP TABLE IF EXISTS `fund_budget`;
CREATE TABLE `fund_budget` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fund_type_id` int(8) NOT NULL,
  `fund_type_name` varchar(16) CHARACTER SET utf8 NOT NULL,
  `money` decimal(8,2) NOT NULL,
  `month_num` tinyint(4) NOT NULL,
  `year_num` smallint(8) NOT NULL,
  `sys_user_id` bigint(11) DEFAULT NULL,
  `sys_user_name` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of fund_budget
-- ----------------------------
INSERT INTO `fund_budget` VALUES ('1', '9', '餐饮买菜', '343.00', '7', '2019', '1', 'admin', '2019-07-11 16:25:23', '2019-07-13 11:41:11');
INSERT INTO `fund_budget` VALUES ('2', '10', '零食饮料', '2232.00', '7', '2019', '1', 'admin', '2019-07-11 16:26:16', '2019-07-13 11:41:12');
INSERT INTO `fund_budget` VALUES ('3', '9', '餐饮买菜', '343.00', '7', '2019', '1', 'admin', '2019-07-11 17:06:40', '2019-07-13 11:41:13');
INSERT INTO `fund_budget` VALUES ('4', '9', '餐饮买菜', '234.00', '3', '2019', '1', 'admin', '2019-07-11 17:07:23', '2019-07-13 11:41:14');
INSERT INTO `fund_budget` VALUES ('5', '9', '餐饮买菜', '343.00', '1', '2019', '1', 'admin', '2019-07-11 17:08:37', '2019-07-17 10:12:48');
INSERT INTO `fund_budget` VALUES ('6', '10', '零食饮料', '300.00', '2', '2019', '2', '金双齐', '2019-07-17 09:45:36', '2019-07-17 10:12:51');

-- ----------------------------
-- Table structure for fund_family
-- ----------------------------
DROP TABLE IF EXISTS `fund_family`;
CREATE TABLE `fund_family` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sys_user_id` bigint(11) NOT NULL,
  `fund_family_name` varchar(100) CHARACTER SET utf8 NOT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of fund_family
-- ----------------------------
INSERT INTO `fund_family` VALUES ('1', '1', 'admin', '2019-07-18 10:03:59');
INSERT INTO `fund_family` VALUES ('2', '2', 'admin', '2019-07-17 11:33:34');

-- ----------------------------
-- Table structure for fund_income
-- ----------------------------
DROP TABLE IF EXISTS `fund_income`;
CREATE TABLE `fund_income` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fund_type_id` int(8) NOT NULL,
  `fund_type_name` varchar(16) CHARACTER SET utf8 NOT NULL,
  `content` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `money` decimal(8,2) NOT NULL,
  `sys_user_id` bigint(11) NOT NULL,
  `sys_user_name` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `address` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `remark` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `pic_url` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `month_num` tinyint(4) DEFAULT NULL,
  `year_num` smallint(16) DEFAULT NULL,
  `day_num` tinyint(4) DEFAULT NULL,
  `income_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of fund_income
-- ----------------------------
INSERT INTO `fund_income` VALUES ('1', '1', '工资', '工资', '200000.00', '1', 'admin', '', '', '', '5', '2020', '11', '2020-05-11 11:17:58', '2020-05-31 11:18:04', null, '1');

-- ----------------------------
-- Table structure for fund_pay
-- ----------------------------
DROP TABLE IF EXISTS `fund_pay`;
CREATE TABLE `fund_pay` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fund_type_id` int(8) NOT NULL,
  `fund_type_name` varchar(16) CHARACTER SET utf8 NOT NULL,
  `sys_user_id` bigint(11) NOT NULL,
  `sys_user_name` varchar(32) CHARACTER SET utf8 NOT NULL,
  `content` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `money` decimal(8,2) DEFAULT NULL,
  `pay_type` varchar(16) CHARACTER SET utf8 DEFAULT NULL,
  `remark` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `pic_url` varchar(100) DEFAULT NULL,
  `month_num` tinyint(4) DEFAULT NULL,
  `year_num` smallint(16) DEFAULT NULL,
  `day_num` tinyint(4) DEFAULT NULL,
  `pay_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of fund_pay
-- ----------------------------
INSERT INTO `fund_pay` VALUES ('1', '9', '餐饮买菜', '1', 'admin', '吃饭', '200.00', '支付宝', '', '', '5', '2020', '13', '2020-05-13 11:17:41', '2020-05-31 11:17:45', null, '1');
INSERT INTO `fund_pay` VALUES ('2', '15', '休闲娱乐', '1', 'admin', '旅游', '40000.00', '支付宝', '', '', '5', '2020', '31', '2020-05-31 11:18:30', '2020-05-31 11:18:31', null, '1');

-- ----------------------------
-- Table structure for fund_type
-- ----------------------------
DROP TABLE IF EXISTS `fund_type`;
CREATE TABLE `fund_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) NOT NULL,
  `name` varchar(16) CHARACTER SET utf8 NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of fund_type
-- ----------------------------
INSERT INTO `fund_type` VALUES ('1', '0', '工资', '2019-07-11 11:19:59', '2019-07-11 11:22:39');
INSERT INTO `fund_type` VALUES ('2', '0', '生活费', '2019-07-11 11:19:59', '2019-07-11 11:22:39');
INSERT INTO `fund_type` VALUES ('3', '0', '红包', '2019-07-11 11:19:59', '2019-07-11 11:22:39');
INSERT INTO `fund_type` VALUES ('4', '0', '兼职外快', '2019-07-11 11:19:59', '2019-07-11 11:22:39');
INSERT INTO `fund_type` VALUES ('5', '0', '投资收入', '2019-07-11 11:19:59', '2019-07-11 11:22:39');
INSERT INTO `fund_type` VALUES ('6', '0', '奖金', '2019-07-11 11:19:59', '2019-07-11 11:22:39');
INSERT INTO `fund_type` VALUES ('7', '0', '福利', '2019-07-11 11:19:59', '2019-07-11 11:22:39');
INSERT INTO `fund_type` VALUES ('8', '0', '其他收入', '2019-07-11 11:19:59', '2019-07-11 11:22:39');
INSERT INTO `fund_type` VALUES ('9', '1', '餐饮买菜', '2019-07-11 11:19:59', '2019-07-11 11:22:39');
INSERT INTO `fund_type` VALUES ('10', '1', '零食饮料', '2019-07-11 11:19:59', '2019-07-11 11:22:39');
INSERT INTO `fund_type` VALUES ('11', '1', '交通', '2019-07-11 11:19:59', '2019-07-11 11:22:39');
INSERT INTO `fund_type` VALUES ('12', '1', '衣服鞋帽', '2019-07-11 11:19:59', '2019-07-11 11:22:39');
INSERT INTO `fund_type` VALUES ('13', '1', '日用品', '2019-07-11 11:19:59', '2019-07-11 11:22:39');
INSERT INTO `fund_type` VALUES ('14', '1', '通讯网费', '2019-07-11 11:19:59', '2019-07-11 11:22:39');
INSERT INTO `fund_type` VALUES ('15', '1', '休闲娱乐', '2019-07-11 11:19:59', '2019-07-11 11:22:39');
INSERT INTO `fund_type` VALUES ('16', '1', '医疗', '2019-07-11 11:19:59', '2019-07-11 11:22:39');
INSERT INTO `fund_type` VALUES ('17', '1', '学习', '2019-07-11 11:19:59', '2019-07-11 11:22:39');
INSERT INTO `fund_type` VALUES ('18', '1', '烟酒', '2019-07-11 11:19:59', '2019-07-11 11:22:39');
INSERT INTO `fund_type` VALUES ('19', '1', '家居', '2019-07-11 11:19:59', '2019-07-11 11:22:39');
INSERT INTO `fund_type` VALUES ('20', '1', '护肤彩妆', '2019-07-11 11:19:59', '2019-07-11 11:22:39');
INSERT INTO `fund_type` VALUES ('21', '1', '住房', '2019-07-11 11:19:59', '2019-07-11 11:22:39');
INSERT INTO `fund_type` VALUES ('22', '1', '数码', '2019-07-11 11:19:59', '2019-07-11 11:22:39');
INSERT INTO `fund_type` VALUES ('23', '1', '宠物', '2019-07-11 11:19:59', '2019-07-11 11:22:39');
INSERT INTO `fund_type` VALUES ('24', '1', '其他支出', '2019-07-11 11:19:59', '2019-07-11 11:22:39');

-- ----------------------------
-- Table structure for fund_year_budget
-- ----------------------------
DROP TABLE IF EXISTS `fund_year_budget`;
CREATE TABLE `fund_year_budget` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `year_num` int(8) NOT NULL,
  `fund_year_income` decimal(10,2) NOT NULL,
  `fund_year_pay` decimal(10,2) NOT NULL,
  `sys_user_id` bigint(11) NOT NULL,
  `sys_user_name` varchar(11) CHARACTER SET utf8 NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of fund_year_budget
-- ----------------------------

-- ----------------------------
-- Table structure for my_consume
-- ----------------------------
DROP TABLE IF EXISTS `my_consume`;
CREATE TABLE `my_consume` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sys_user_id` int(11) NOT NULL COMMENT '添加人ID',
  `sys_user_name` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `consume_type` tinyint(4) DEFAULT NULL,
  `consume_money` decimal(10,2) DEFAULT NULL COMMENT '消费额度',
  `consume_name` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '消费名称',
  `consume_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '消费时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enabled` tinyint(4) DEFAULT NULL COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of my_consume
-- ----------------------------

-- ----------------------------
-- Table structure for my_creditcard_consume
-- ----------------------------
DROP TABLE IF EXISTS `my_creditcard_consume`;
CREATE TABLE `my_creditcard_consume` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `card_no` varchar(32) NOT NULL,
  `card_name` varchar(32) CHARACTER SET utf8 NOT NULL,
  `sys_user_id` int(11) NOT NULL,
  `sys_user_name` varchar(16) CHARACTER SET utf8 NOT NULL,
  `repay_time` datetime DEFAULT NULL,
  `repay_money` decimal(10,2) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of my_creditcard_consume
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('RenrenScheduler', 'Dk1590893103315', '1590895147292', '15000');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- ----------------------------
-- Records of schedule_job
-- ----------------------------

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`),
  KEY `job_id` (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha` (
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统验证码';

-- ----------------------------
-- Records of sys_captcha
-- ----------------------------
INSERT INTO `sys_captcha` VALUES ('022b8760-9b6d-4dbb-8029-5483da9c25f4', 'xcpn5', '2019-07-09 16:46:57');
INSERT INTO `sys_captcha` VALUES ('0242e3a5-108f-4cde-8df5-ca7169c4db9b', 'f5yyg', '2019-05-24 18:00:52');
INSERT INTO `sys_captcha` VALUES ('0622fdf3-9275-4076-8cd4-a0a51e65f572', '4d6nx', '2019-07-02 11:50:46');
INSERT INTO `sys_captcha` VALUES ('23b30dbb-c163-4f08-88f6-f5c809596112', '38y5m', '2019-07-11 18:05:26');
INSERT INTO `sys_captcha` VALUES ('24f0c35c-fd3a-427a-8400-fcc85e2c8dec', '26egc', '2019-05-25 11:02:41');
INSERT INTO `sys_captcha` VALUES ('2d9100f0-1e95-4967-87f5-9e69e85971b2', 'ywy6c', '2019-07-11 18:05:25');
INSERT INTO `sys_captcha` VALUES ('6596a327-1cff-430f-8d64-f32219fff44c', 'bbna4', '2019-05-08 14:33:50');
INSERT INTO `sys_captcha` VALUES ('67e99530-a760-45be-862b-fd1321b24689', 'c67p5', '2019-05-17 10:22:18');
INSERT INTO `sys_captcha` VALUES ('74ff8f74-a0bb-4dc0-8e62-99f3fe17c33f', '7egew', '2019-06-04 19:49:42');
INSERT INTO `sys_captcha` VALUES ('765bb7e9-a69c-4d6a-876c-7af9c60f0273', 'mwdnc', '2019-06-04 19:11:44');
INSERT INTO `sys_captcha` VALUES ('7cbe6ecb-c1b6-48eb-816b-f14ffee3f2ac', '8mg8w', '2019-06-24 13:56:38');
INSERT INTO `sys_captcha` VALUES ('95d3130f-f676-4f3f-8848-1129a167c283', 'yn2my', '2020-05-27 22:59:34');
INSERT INTO `sys_captcha` VALUES ('96ca95d6-2561-4db3-8d2a-ea25a47edd52', '25b84', '2019-06-04 18:25:55');
INSERT INTO `sys_captcha` VALUES ('9e5852e3-aa9f-46dc-80ad-2d0fdb554e7a', '8cn85', '2019-06-04 18:20:16');
INSERT INTO `sys_captcha` VALUES ('a4cb79c7-d4b0-4aba-824a-35dab900dea3', 'nenc2', '2020-04-18 11:37:03');
INSERT INTO `sys_captcha` VALUES ('a90aa238-5c3d-480b-80e2-7cf64ee0942d', '68ae8', '2020-05-27 23:02:17');
INSERT INTO `sys_captcha` VALUES ('af5fa864-1375-4564-8298-1a670758b699', '26xad', '2019-06-24 17:02:41');
INSERT INTO `sys_captcha` VALUES ('b1a091a4-5f5a-43d4-8e45-71bc0a6f2d0c', 'yaf22', '2019-05-22 16:42:35');
INSERT INTO `sys_captcha` VALUES ('b328072a-84c6-44a6-8820-150c60196bab', '6bg2y', '2019-05-25 10:59:16');
INSERT INTO `sys_captcha` VALUES ('c2af20c0-8652-43e5-8382-1b37e48db82b', 'eeapn', '2019-07-11 21:04:42');
INSERT INTO `sys_captcha` VALUES ('cdfc2b62-f5da-495f-8149-0a26e231fa0b', '3neyx', '2019-04-19 18:33:38');
INSERT INTO `sys_captcha` VALUES ('d23a02dd-f83e-4262-86db-653901e6a933', 'xnnxg', '2019-05-24 18:01:08');
INSERT INTO `sys_captcha` VALUES ('d6555bc3-dac3-42e9-89cc-d71bd665da03', '56pa6', '2019-05-12 15:09:14');
INSERT INTO `sys_captcha` VALUES ('e3f88e89-b873-4ce4-8401-b816a22aff42', '7bdpb', '2019-05-17 10:21:44');
INSERT INTO `sys_captcha` VALUES ('ec7bbfe8-8f57-4eed-8bd0-3272677c81f2', 'n2876', '2019-07-11 20:11:20');
INSERT INTO `sys_captcha` VALUES ('ef26c5cd-9146-4181-8450-b7d713276638', '42gb5', '2019-07-11 19:15:25');
INSERT INTO `sys_captcha` VALUES ('fbdc2558-05f7-49bc-83b7-76c6830b853e', 'ng6m7', '2020-05-27 22:57:40');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_key` (`param_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', 'CLOUD_STORAGE_CONFIG_KEY', '{\"type\":2,\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"qiniuBucketName\":\"ios-app\",\"aliyunDomain\":\"https://url.com\",\"aliyunPrefix\":\"\",\"aliyunEndPoint\":\"oss-xxxcom\",\"aliyunAccessKeyId\":\"xxx\",\"aliyunAccessKeySecret\":\"xxx\",\"aliyunBucketName\":\"xxx\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qcloudBucketName\":\"\"}', '0', '云存储配置信息');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'system', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '管理员列表', 'sys/user', null, '1', 'admin', '1');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'sys/role', null, '1', 'role', '2');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'sys/menu', null, '1', 'menu', '3');
INSERT INTO `sys_menu` VALUES ('5', '1', 'SQL监控', 'http://localhost:8080/renren-fast/druid/sql.html', null, '1', 'sql', '4');
INSERT INTO `sys_menu` VALUES ('6', '1', '定时任务', 'job/schedule', null, '1', 'job', '5');
INSERT INTO `sys_menu` VALUES ('7', '6', '查看', null, 'sys:schedule:list,sys:schedule:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('8', '6', '新增', null, 'sys:schedule:save', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('9', '6', '修改', null, 'sys:schedule:update', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('10', '6', '删除', null, 'sys:schedule:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('11', '6', '暂停', null, 'sys:schedule:pause', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('12', '6', '恢复', null, 'sys:schedule:resume', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('13', '6', '立即执行', null, 'sys:schedule:run', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('14', '6', '日志列表', null, 'sys:schedule:log', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:list', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:list', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('27', '1', '参数管理', 'sys/config', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', '1', 'config', '6');
INSERT INTO `sys_menu` VALUES ('29', '1', '系统日志', 'sys/log', 'sys:log:list', '1', 'log', '7');
INSERT INTO `sys_menu` VALUES ('30', '1', '文件上传', 'oss/oss', 'sys:oss:all', '1', 'oss', '6');
INSERT INTO `sys_menu` VALUES ('41', '0', '账户管理', '', '', '0', 'bianji', '0');
INSERT INTO `sys_menu` VALUES ('42', '41', '消费管理', 'my/consume/myconsume', 'my:myconsume:list', '1', 'bianji', '0');
INSERT INTO `sys_menu` VALUES ('43', '42', '新增', '', 'my:myconsume:save', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('44', '42', '修改', '', 'my:myconsume:update,my:myconsume:info', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('45', '41', '当月消费统计', 'my/consume/perdayconsume', 'my:myconsume:statisPerDayUser', '1', 'bianji', '0');
INSERT INTO `sys_menu` VALUES ('46', '41', '信用卡开支管理', 'my/consume/mycreditcardconsume', 'creditcard:mycreditcardconsume:list', '1', 'bianji', '0');
INSERT INTO `sys_menu` VALUES ('47', '46', '新增', '', 'creditcard:mycreditcardconsume:save', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('48', '46', '修改', '', 'creditcard:mycreditcardconsume:update,creditcard:mycreditcardconsume:info', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('49', '46', '删除', '', 'creditcard:mycreditcardconsume:delete', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('70', '41', '账户类型管理', 'fund/fundtype', 'fund:fundtype:list', '1', 'bianji', '0');
INSERT INTO `sys_menu` VALUES ('71', '70', '增加', '', 'fund:fundtype:save,fund:fundtype:info', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('72', '70', '修改', '', 'fund:fundtype:update,fund:fundtype:info', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('73', '70', '删除', '', 'fund:fundtype:delete', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('74', '41', '收入管理', 'fund/fundincome', 'fund:fundincome:list', '1', 'bianji', '0');
INSERT INTO `sys_menu` VALUES ('75', '74', '新增', '', 'fund:fundincome:save', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('76', '74', '修改', '', 'fund:fundincome:update,fund:fundincome:info', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('77', '74', '删除', '', 'fund:fundincome:delete', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('78', '41', '支出管理', 'fund/fundpay', 'fund:fundpay:list', '1', 'bianji', '0');
INSERT INTO `sys_menu` VALUES ('79', '78', '新增', '', 'fund:fundpay:save', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('80', '78', '修改', '', 'fund:fundpay:update,fund:fundpay:info', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('81', '78', '删除', '', 'fund:fundpay:delete', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('82', '41', '预算管理', 'fund/fundbudget', 'fund:fundbudget:list', '1', 'bianji', '0');
INSERT INTO `sys_menu` VALUES ('83', '82', '新增', '', 'fund:fundbudget:save', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('84', '82', '修改', '', 'fund:fundbudget:update,fund:fundbudget:info', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('85', '82', '删除', '', 'fund:fundbudget:delete', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('94', '41', '年度预算管理', 'fund/fundyearbudget', 'fund:fundyearbudget:list', '1', 'bianji', '5');
INSERT INTO `sys_menu` VALUES ('95', '94', '新增', '', 'fund:fundyearbudget:save', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('96', '94', '修改', '', 'fund:fundyearbudget:update,fund:fundyearbudget:info', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('97', '94', '删除', '', 'fund:fundyearbudget:delete', '2', '', '0');
INSERT INTO `sys_menu` VALUES ('98', '41', '账户主页', 'fund/fund-home', '', '1', 'bianji', '0');
INSERT INTO `sys_menu` VALUES ('99', '0', '优惠活动', '', '', '0', 'job', '3');
INSERT INTO `sys_menu` VALUES ('100', '99', '阿里云1核2G1M 99元/年', 'https://www.aliyun.com/minisite/goods?userCode=52ms1zt1&share_source=copy_link', '', '1', 'job', '0');
INSERT INTO `sys_menu` VALUES ('101', '99', '腾讯云1核2G1M 99元/年', 'https://cloud.tencent.com/act/cps/redirect?redirect=1054&cps_key=5e9a1eaf9c3a8ed04026c92a8c2bda00&from=console', '', '1', 'job', '0');
INSERT INTO `sys_menu` VALUES ('102', '99', '华为云免费试用，99/年', 'https://activity.huaweicloud.com/discount_area_v5/index.html?bpName=089da2d15e8025840f05c01d523bdb00&bindType=1&salesID=hanjingjing', '', '1', 'job', '0');

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='文件上传';

-- ----------------------------
-- Records of sys_oss
-- ----------------------------
INSERT INTO `sys_oss` VALUES ('1', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190420/06a09fe99f964f23b73ff42dad031158.jpg', '2019-04-20 11:58:33');
INSERT INTO `sys_oss` VALUES ('2', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190420/9f9c472e315e463c9e50a55d05bf69ad.js', '2019-04-20 18:07:31');
INSERT INTO `sys_oss` VALUES ('4', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190420/c56f337fda9249b49cae1f363a58db4c.js', '2019-04-20 18:21:59');
INSERT INTO `sys_oss` VALUES ('7', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/E:\\dk-platform\\dkServer\\target\\dkServer\\WEB-INF\\classes\\mapper\\jingjia\\jsFile\\b988a0bccfdb4d7385ce3dc312e70c39.js', '2019-04-20 18:30:34');
INSERT INTO `sys_oss` VALUES ('8', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/896561710cc1449abcd1a10f3f4e4fbf.js', '2019-04-20 18:36:45');
INSERT INTO `sys_oss` VALUES ('9', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/E:\\dk-platform\\dkServer\\target\\dkServer\\WEB-INF\\classes\\mapper\\jingjia\\jsFile\\676752d64def4b4598a51e7ba5296bbf.js', '2019-04-20 21:43:50');
INSERT INTO `sys_oss` VALUES ('10', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190420/ddd059fafbcb46d089d17dac629333fc.js', '2019-04-20 21:53:54');
INSERT INTO `sys_oss` VALUES ('11', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190420/5b50145919e9406393ff5b5c2c81a757.js', '2019-04-20 22:02:58');
INSERT INTO `sys_oss` VALUES ('12', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190420/148651f515b249fc97fb352fe7c09ba8.js', '2019-04-20 23:14:05');
INSERT INTO `sys_oss` VALUES ('13', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190423/f05d9f7d5c524b458e89b9a3cdbc6bf6.js', '2019-04-23 14:16:41');
INSERT INTO `sys_oss` VALUES ('15', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190423/129eab7e3d904d86beebe609a4c15f1c.js', '2019-04-23 15:40:47');
INSERT INTO `sys_oss` VALUES ('19', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190424/9f82ad32d3864b8284142e9b5bd56480.js', '2019-04-24 10:13:00');
INSERT INTO `sys_oss` VALUES ('21', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190424/89cf21ca8f6c4923b37d7b6de311131c.js', '2019-04-24 14:10:01');
INSERT INTO `sys_oss` VALUES ('22', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190424/c6cac05b01f64d21a86cd9ef27319b13.js', '2019-04-24 14:19:10');
INSERT INTO `sys_oss` VALUES ('23', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190424/e764b6664aa84ad1b08a63a5c51c8dcb.js', '2019-04-24 17:17:52');
INSERT INTO `sys_oss` VALUES ('24', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190426/1e7b8d80d7894009869d32cf44109d1f.js', '2019-04-26 10:10:43');
INSERT INTO `sys_oss` VALUES ('25', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190426/f9089db7475644edad6c7f9c322003f5.js', '2019-04-26 10:14:48');
INSERT INTO `sys_oss` VALUES ('26', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190510/efe8156a703b45b98aa7ab4ee154aa64.js', '2019-05-10 20:58:17');
INSERT INTO `sys_oss` VALUES ('27', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190510/e322a8f39e9b4595bc2ab502b7cea28b.js', '2019-05-10 21:01:09');
INSERT INTO `sys_oss` VALUES ('28', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190513/cd4c4cc8679c4b56b7b5d835755c4774.js', '2019-05-13 18:51:37');
INSERT INTO `sys_oss` VALUES ('29', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190513/6d313c32f0e7439bb8ff6c6d94498454.js', '2019-05-13 18:58:08');
INSERT INTO `sys_oss` VALUES ('30', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190605/8b1681baa0ff47c9a281e7cd4b77ffdd.png', '2019-06-05 09:19:46');
INSERT INTO `sys_oss` VALUES ('31', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190709/88c9c4e44532497abbd3149f27b66296.png', '2019-07-09 16:42:40');
INSERT INTO `sys_oss` VALUES ('32', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190709/e5a6b9da42c14087bc6b61abea4b93f7.png', '2019-07-09 16:43:50');
INSERT INTO `sys_oss` VALUES ('33', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190709/2c37b5eb8aff491ea440e0821e50058f.png', '2019-07-09 16:43:57');
INSERT INTO `sys_oss` VALUES ('34', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190709/1c78b0fbc1604cdf897d0e34805916ca.png', '2019-07-09 16:45:28');
INSERT INTO `sys_oss` VALUES ('35', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190709/9926c6f5e2754cda9eeddee624c32d01.png', '2019-07-09 17:30:09');
INSERT INTO `sys_oss` VALUES ('36', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190709/85b587faf0c74cfbb874c392fa285248.png', '2019-07-09 17:30:14');
INSERT INTO `sys_oss` VALUES ('37', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190709/0fb6039e277b44e19de6911dec94be6b.jpg', '2019-07-09 17:30:19');
INSERT INTO `sys_oss` VALUES ('38', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190709/fb4334ee9d5349e1924db01193825a28.jpg', '2019-07-09 17:30:25');
INSERT INTO `sys_oss` VALUES ('39', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190709/d33204262d304e17a3f31a7fe022bcc0.jpg', '2019-07-09 17:30:29');
INSERT INTO `sys_oss` VALUES ('40', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190710/5d87f3ee5ab74e7298c000d862f5ab6a.png', '2019-07-10 12:59:00');
INSERT INTO `sys_oss` VALUES ('41', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20190710/3f47e1eefeb14a4fa5be3f051d0339e5.png', '2019-07-10 14:13:43');
INSERT INTO `sys_oss` VALUES ('42', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20200418/31d4c09cca61426cb0ccac9d4e452552.png', '2020-04-18 11:36:36');
INSERT INTO `sys_oss` VALUES ('43', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20200418/e9f39a618a5d4c589c9bb5278c5a19b9.mp4', '2020-04-18 11:41:35');
INSERT INTO `sys_oss` VALUES ('44', 'https://ourtaoliyuan.oss-cn-shenzhen.aliyuncs.com/20200418/ea475a8e4e4245e78337ec1fce334aa4.mp4', '2020-04-18 11:44:17');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('2', '账户管理', '', '1', '2019-07-17 10:07:20');
INSERT INTO `sys_role` VALUES ('3', '测试', '', '1', '2020-05-28 22:24:29');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('12', '2', '41');
INSERT INTO `sys_role_menu` VALUES ('13', '2', '42');
INSERT INTO `sys_role_menu` VALUES ('14', '2', '43');
INSERT INTO `sys_role_menu` VALUES ('15', '2', '44');
INSERT INTO `sys_role_menu` VALUES ('16', '2', '45');
INSERT INTO `sys_role_menu` VALUES ('17', '2', '46');
INSERT INTO `sys_role_menu` VALUES ('18', '2', '47');
INSERT INTO `sys_role_menu` VALUES ('19', '2', '48');
INSERT INTO `sys_role_menu` VALUES ('20', '2', '49');
INSERT INTO `sys_role_menu` VALUES ('21', '2', '70');
INSERT INTO `sys_role_menu` VALUES ('22', '2', '71');
INSERT INTO `sys_role_menu` VALUES ('23', '2', '72');
INSERT INTO `sys_role_menu` VALUES ('24', '2', '73');
INSERT INTO `sys_role_menu` VALUES ('25', '2', '74');
INSERT INTO `sys_role_menu` VALUES ('26', '2', '75');
INSERT INTO `sys_role_menu` VALUES ('27', '2', '76');
INSERT INTO `sys_role_menu` VALUES ('28', '2', '77');
INSERT INTO `sys_role_menu` VALUES ('29', '2', '78');
INSERT INTO `sys_role_menu` VALUES ('30', '2', '79');
INSERT INTO `sys_role_menu` VALUES ('31', '2', '80');
INSERT INTO `sys_role_menu` VALUES ('32', '2', '81');
INSERT INTO `sys_role_menu` VALUES ('33', '2', '82');
INSERT INTO `sys_role_menu` VALUES ('34', '2', '83');
INSERT INTO `sys_role_menu` VALUES ('35', '2', '84');
INSERT INTO `sys_role_menu` VALUES ('36', '2', '85');
INSERT INTO `sys_role_menu` VALUES ('37', '2', '94');
INSERT INTO `sys_role_menu` VALUES ('38', '2', '95');
INSERT INTO `sys_role_menu` VALUES ('39', '2', '96');
INSERT INTO `sys_role_menu` VALUES ('40', '2', '97');
INSERT INTO `sys_role_menu` VALUES ('41', '2', '-666666');
INSERT INTO `sys_role_menu` VALUES ('42', '3', '94');
INSERT INTO `sys_role_menu` VALUES ('43', '3', '95');
INSERT INTO `sys_role_menu` VALUES ('44', '3', '96');
INSERT INTO `sys_role_menu` VALUES ('45', '3', '97');
INSERT INTO `sys_role_menu` VALUES ('46', '3', '-666666');
INSERT INTO `sys_role_menu` VALUES ('47', '3', '41');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `home_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '默认主页类型',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'abc@qq.com', '13612345678', '1', '1', '2016-11-11 11:11:11', '2');
INSERT INTO `sys_user` VALUES ('4', 'test', '9ce7e7af843b3c5f39c01d74021d36a85a7809a3b344d9e4a3f5de6718361451', 'ksghnKbpAFh3VxIW07H1', 'test@qq.com', '15626475985', '1', '1', '2020-05-28 22:25:04', '2');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('3', '2', '2');
INSERT INTO `sys_user_role` VALUES ('8', '3', '2');
INSERT INTO `sys_user_role` VALUES ('11', '4', '2');
INSERT INTO `sys_user_role` VALUES ('12', '1', '2');

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户Token';

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES ('1', '19d0ec03e5e5294f2b3a016d2f234102', '2020-05-31 22:45:44', '2020-05-31 10:45:44');
INSERT INTO `sys_user_token` VALUES ('2', '045aae734e63e1efb52000533f7a9d40', '2019-07-18 06:27:45', '2019-07-17 18:27:45');
INSERT INTO `sys_user_token` VALUES ('3', 'b8f8c8c9f223c7b94ced5da0761694ce', '2019-07-19 22:03:09', '2019-07-19 10:03:09');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'mark', '13612345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2017-03-23 22:37:41');
SET FOREIGN_KEY_CHECKS=1;
