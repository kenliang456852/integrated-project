/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : integrated

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 26/07/2018 10:30:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for acct_role
-- ----------------------------
DROP TABLE IF EXISTS `acct_role`;
CREATE TABLE `acct_role`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `acct_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of acct_role
-- ----------------------------
INSERT INTO `acct_role` VALUES ('08b1d58d-b21d-4dff-8aed-8459f9041ff2', 'dd76a1a6-6e23-4e41-8ff8-115c0d22cf8f', '35429b41-f591-42e6-bf4b-df51210edda5');
INSERT INTO `acct_role` VALUES ('0d0ae198-9745-4343-9a7b-425d7a796361', 'dd76a1a6-6e23-4e41-8ff8-115c0d22cf8f', '2acd3305-d273-4dc5-8de9-39eac01a45e0');

-- ----------------------------
-- Table structure for busi_acct_info
-- ----------------------------
DROP TABLE IF EXISTS `busi_acct_info`;
CREATE TABLE `busi_acct_info`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `acct_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of busi_acct_info
-- ----------------------------
INSERT INTO `busi_acct_info` VALUES ('dd76a1a6-6e23-4e41-8ff8-115c0d22cf8f', 'admin', 'a66abb5684c45962d887564f08346e8d');

-- ----------------------------
-- Table structure for permission_info
-- ----------------------------
DROP TABLE IF EXISTS `permission_info`;
CREATE TABLE `permission_info`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `permission_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission_info
-- ----------------------------
INSERT INTO `permission_info` VALUES ('1921c9d3-128a-46ec-a41e-9cc78afcdfb5', 'user:select');
INSERT INTO `permission_info` VALUES ('3169cb32-9908-49a8-a817-02f6871efe7b', 'user:delete');
INSERT INTO `permission_info` VALUES ('51731ac2-9b51-4607-89ff-d91d68ad4a63', 'user:insert');
INSERT INTO `permission_info` VALUES ('bed3d5a5-0ee7-41ef-a66f-604ab0dc0bca', 'user:update');

-- ----------------------------
-- Table structure for role_info
-- ----------------------------
DROP TABLE IF EXISTS `role_info`;
CREATE TABLE `role_info`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_info
-- ----------------------------
INSERT INTO `role_info` VALUES ('2acd3305-d273-4dc5-8de9-39eac01a45e0', 'admin');
INSERT INTO `role_info` VALUES ('35429b41-f591-42e6-bf4b-df51210edda5', 'user');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `permission_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('2d5b895b-bcaf-47fc-af62-31cb9b4cee03', '35429b41-f591-42e6-bf4b-df51210edda5', '1921c9d3-128a-46ec-a41e-9cc78afcdfb5');
INSERT INTO `role_permission` VALUES ('4117b624-09d1-4847-a405-c937fa23841b', '35429b41-f591-42e6-bf4b-df51210edda5', '51731ac2-9b51-4607-89ff-d91d68ad4a63');
INSERT INTO `role_permission` VALUES ('637e165e-ea0f-4cc2-935f-b1180eb445e8', '35429b41-f591-42e6-bf4b-df51210edda5', 'bed3d5a5-0ee7-41ef-a66f-604ab0dc0bca');
INSERT INTO `role_permission` VALUES ('655896de-d37a-495f-9253-c418063b6f8b', '2acd3305-d273-4dc5-8de9-39eac01a45e0', '51731ac2-9b51-4607-89ff-d91d68ad4a63');
INSERT INTO `role_permission` VALUES ('9d8ca334-1662-4e94-bb65-ff7a4edb98da', '2acd3305-d273-4dc5-8de9-39eac01a45e0', '3169cb32-9908-49a8-a817-02f6871efe7b');
INSERT INTO `role_permission` VALUES ('a10be885-86fe-4bec-83d0-9c75be6ac9e1', '2acd3305-d273-4dc5-8de9-39eac01a45e0', 'bed3d5a5-0ee7-41ef-a66f-604ab0dc0bca');
INSERT INTO `role_permission` VALUES ('a6b8a950-4e22-412d-9152-2bccb0047b09', '35429b41-f591-42e6-bf4b-df51210edda5', '3169cb32-9908-49a8-a817-02f6871efe7b');
INSERT INTO `role_permission` VALUES ('a95caef9-beb4-4865-b7c4-f0033fbb5a2a', '2acd3305-d273-4dc5-8de9-39eac01a45e0', '1921c9d3-128a-46ec-a41e-9cc78afcdfb5');

SET FOREIGN_KEY_CHECKS = 1;
