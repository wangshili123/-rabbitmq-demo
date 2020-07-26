/*
 Navicat Premium Data Transfer

 Source Server         : mariadb
 Source Server Type    : MySQL
 Source Server Version : 100307
 Source Host           : localhost:3306
 Source Schema         : rabbitmq_demo

 Target Server Type    : MySQL
 Target Server Version : 100307
 File Encoding         : 65001

 Date: 26/07/2020 21:29:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for kucun
-- ----------------------------
DROP TABLE IF EXISTS `kucun`;
CREATE TABLE `kucun`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kucun
-- ----------------------------
INSERT INTO `kucun` VALUES (1, 0);

-- ----------------------------
-- Table structure for qiangok
-- ----------------------------
DROP TABLE IF EXISTS `qiangok`;
CREATE TABLE `qiangok`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
