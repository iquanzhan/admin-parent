/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.4.188
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : 192.168.4.188:3306
 Source Schema         : xxadmin

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 25/02/2020 16:31:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '资源名称',
  `type` tinyint(11) NOT NULL COMMENT '资源类型 目录：1，菜单：2，按钮：3',
  `scource_key` varchar(128) NOT NULL COMMENT '资源KEY',
  `http_method` varchar(16) DEFAULT NULL,
  `source_url` varchar(256) NOT NULL COMMENT '资源URL',
  `descript` varchar(1024) NOT NULL COMMENT '资源描述',
  `icon` varchar(64) DEFAULT NULL COMMENT '资源图标',
  `is_show` tinyint(32) DEFAULT NULL COMMENT '是否显示',
  `sort` int(32) DEFAULT NULL COMMENT '排序',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父ID',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_status` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统资源表 ';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `role_key` varchar(64) DEFAULT NULL COMMENT '角色KEY',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `descript` varchar(1024) DEFAULT NULL COMMENT '角色描述',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父级角色',
  `sort` tinyint(11) DEFAULT NULL COMMENT '排序',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `delete_status` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表 ';

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `resource_id` varchar(32) DEFAULT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源表 ';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `user_name` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `telephone` char(11) DEFAULT NULL COMMENT '手机号码',
  `nick_name` varchar(64) DEFAULT NULL COMMENT '昵称',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `email` varchar(128) DEFAULT NULL COMMENT '电子邮箱',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别0代表女1代表男',
  `address` varchar(128) DEFAULT NULL COMMENT '地址',
  `descript` varchar(1024) DEFAULT NULL COMMENT '描述信息',
  `is_locked` tinyint(1) NOT NULL COMMENT '是否锁定',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `delete_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表 ';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关系表 ';

SET FOREIGN_KEY_CHECKS = 1;
