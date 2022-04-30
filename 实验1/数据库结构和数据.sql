/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : stc

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 30/04/2022 19:36:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `c_id` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `c_name` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hours` int NULL DEFAULT NULL,
  `credit` float NULL DEFAULT NULL,
  `semester` int NULL DEFAULT NULL,
  PRIMARY KEY (`c_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('001', '数据库', 40, 6, 1);
INSERT INTO `course` VALUES ('002', '数据结构', 40, 6, 3);
INSERT INTO `course` VALUES ('003', '编译原理', 40, 6, 7);
INSERT INTO `course` VALUES ('004', 'C语言', 30, 4.5, 6);
INSERT INTO `course` VALUES ('005', '高等数学', 80, 12, 2);
INSERT INTO `course` VALUES ('006', '计算机网络', 20, 3, 4);

-- ----------------------------
-- Table structure for sc
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc`  (
  `s_id` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `c_id` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `score` float NULL DEFAULT NULL,
  PRIMARY KEY (`s_id`, `c_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc
-- ----------------------------
INSERT INTO `sc` VALUES ('9802101', '001', 80);
INSERT INTO `sc` VALUES ('9802101', '005', 95);
INSERT INTO `sc` VALUES ('9802102', '005', 85);
INSERT INTO `sc` VALUES ('9803101', '001', 90);
INSERT INTO `sc` VALUES ('9803101', '002', 86);
INSERT INTO `sc` VALUES ('9803101', '006', 62);
INSERT INTO `sc` VALUES ('9803102', '001', 82);
INSERT INTO `sc` VALUES ('9803102', '002', 78);
INSERT INTO `sc` VALUES ('9803102', '004', 66);
INSERT INTO `sc` VALUES ('9803102', '005', 92);
INSERT INTO `sc` VALUES ('9803102', '006', 50);
INSERT INTO `sc` VALUES ('9803103', '002', 68);
INSERT INTO `sc` VALUES ('9803103', '006', 62);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `s_id` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `s_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `d_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `class` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`s_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('98020101', '李三', '女', 18, '能源', '980201');
INSERT INTO `student` VALUES ('98020102', '李四', '男', 19, '能源', '980201');
INSERT INTO `student` VALUES ('98030101', '张三', '男', 20, '计算机', '980301');
INSERT INTO `student` VALUES ('98030102', '张四', '女', 20, '计算机', '980301');
INSERT INTO `student` VALUES ('98030103', '张五', '男', 19, '计算机', '980301');
INSERT INTO `student` VALUES ('98040201', '王三', '男', 20, '自动控制', '980402');
INSERT INTO `student` VALUES ('98040202', '王四', '男', 21, '自动控制', '980402');
INSERT INTO `student` VALUES ('98040203', '王五', '女', 19, '自动控制', '980402');

SET FOREIGN_KEY_CHECKS = 1;
