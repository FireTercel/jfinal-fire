# Host: localhost  (Version: 5.5.12)
# Date: 2014-12-14 17:17:49
# Generator: MySQL-Front 5.3  (Build 4.156)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "blog"
#

DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `content` mediumtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

#
# Data for table "blog"
#

INSERT INTO `blog` VALUES (1,'JFinal Demo Title here','JFinal Demo Content here'),(2,'test 1','test 1'),(3,'test 2','test 2'),(4,'test 3','test 3'),(5,'test 4','test 4'),(7,'hello','你好'),(8,'hello','撒旦法'),(9,'sdfsd','撒旦法'),(15,'sdf ','sdf '),(16,'zxczx','行政村在线程sdf'),(17,'sdf ','sdf '),(18,'sdf ','sdf ');

#
# Structure for table "clothes"
#

DROP TABLE IF EXISTS `clothes`;
CREATE TABLE `clothes` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `clothname` varchar(10) NOT NULL DEFAULT '' COMMENT '名称',
  `price` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '价格',
  `description` text NOT NULL COMMENT '描述',
  `commant` mediumtext NOT NULL COMMENT '评论、意见',
  `uploadtime` datetime NOT NULL DEFAULT '2014-01-01 00:00:00' COMMENT '上传时间',
  `flag` varchar(5) NOT NULL DEFAULT '' COMMENT '标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Data for table "clothes"
#

INSERT INTO `clothes` VALUES (1,'cloth1',1.00,'sdfs','sdfs','2014-01-01 00:00:00','11'),(5,'撒旦法',111.00,'撒旦法','撒旦法','2014-01-02 00:00:00','12'),(7,'撒旦法',10.00,'撒旦法','撒旦法','2014-01-02 00:00:00','12');

#
# Structure for table "clothimage"
#

DROP TABLE IF EXISTS `clothimage`;
CREATE TABLE `clothimage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imageurl` varchar(150) DEFAULT '' COMMENT '图片URL',
  `clothesid` int(11) DEFAULT NULL COMMENT '外键',
  `timeme` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `timememem` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "clothimage"
#

INSERT INTO `clothimage` VALUES (1,'123',5,'2014-12-14 16:52:21',NULL),(2,'321',5,'2014-12-14 16:52:21',NULL);

#
# Structure for table "sec_permission"
#

DROP TABLE IF EXISTS `sec_permission`;
CREATE TABLE `sec_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '名称',
  `value` varchar(50) NOT NULL COMMENT '值',
  `url` varchar(255) DEFAULT NULL COMMENT 'url地址',
  `intro` varchar(255) DEFAULT NULL COMMENT '简介',
  `pid` bigint(20) DEFAULT '0' COMMENT '父级id',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='权限';

#
# Data for table "sec_permission"
#

INSERT INTO `sec_permission` VALUES (1,'管理员目录','P_D_ADMIN','/admin/**','',0,'2014-12-14 17:17:01',NULL,NULL),(2,'角色权限管理','P_ROLE','/admin/role/**','',1,'2014-12-14 17:17:01',NULL,NULL),(3,'用户管理','P_USER','/admin/user/**','',1,'2014-12-14 17:17:01',NULL,NULL),(4,'总部目录','P_D_MEMBER','/member/**','',0,'2014-12-14 17:17:01',NULL,NULL),(5,'分部目录','P_D_USER','/user/**','',0,'2014-12-14 17:17:01',NULL,NULL),(6,'用户处理','P_USER_CONTROL','/user/branch**','',5,'2014-12-14 17:17:01',NULL,NULL),(7,'订单','P_ORDER','/order/**','',0,'2014-12-14 17:17:01',NULL,NULL),(8,'订单处理','P_ORDER_CONTROL','/order/deliver**','',7,'2014-12-14 17:17:01',NULL,NULL),(9,'订单更新','P_ORDER_UPDATE','/order/update**','',7,'2014-12-14 17:17:01',NULL,NULL),(10,'支部订单','P_ORDER_BRANCH','/order/branch**','',7,'2014-12-14 17:17:01',NULL,NULL),(11,'区域支行处理','P_REGION_CONTROL','/order/region**','',7,'2014-12-14 17:17:01',NULL,NULL),(12,'收货地址','P_Address','/address/**','',0,'2014-12-14 17:17:01',NULL,NULL);

#
# Structure for table "sec_role"
#

DROP TABLE IF EXISTS `sec_role`;
CREATE TABLE `sec_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '名称',
  `value` varchar(50) NOT NULL COMMENT '值',
  `intro` varchar(255) DEFAULT NULL COMMENT '简介',
  `pid` bigint(20) DEFAULT '0' COMMENT '父级id',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色';

#
# Data for table "sec_role"
#

INSERT INTO `sec_role` VALUES (1,'超级管理员','R_ADMIN','',0,'2014-12-14 17:17:01',NULL,NULL),(2,'系统管理员','R_MANAGER','',1,'2014-12-14 17:17:01',NULL,NULL),(3,'总部','R_MEMBER','',2,'2014-12-14 17:17:01',NULL,NULL),(4,'分部','R_USER','',2,'2014-12-14 17:17:01',NULL,NULL);

#
# Structure for table "sec_role_permission"
#

DROP TABLE IF EXISTS `sec_role_permission`;
CREATE TABLE `sec_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='角色权限';

#
# Data for table "sec_role_permission"
#

INSERT INTO `sec_role_permission` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,1,11),(12,1,12),(13,2,1),(14,2,3),(15,2,4),(16,2,5),(17,2,6),(18,2,7),(19,2,8),(20,2,9),(21,2,10),(22,2,11),(23,2,12),(24,3,4),(25,3,5),(26,3,6),(27,3,11),(28,4,5),(29,4,7),(30,4,9),(31,4,12);

#
# Structure for table "sec_user"
#

DROP TABLE IF EXISTS `sec_user`;
CREATE TABLE `sec_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '登录名',
  `providername` varchar(50) NOT NULL COMMENT '提供者',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `password` varchar(200) NOT NULL COMMENT '密码',
  `hasher` varchar(200) NOT NULL COMMENT '加密类型',
  `salt` varchar(200) NOT NULL COMMENT '加密盐',
  `avatar_url` varchar(255) DEFAULT NULL COMMENT '头像',
  `first_name` varchar(10) DEFAULT NULL COMMENT '名字',
  `last_name` varchar(10) DEFAULT NULL COMMENT '姓氏',
  `full_name` varchar(20) DEFAULT NULL COMMENT '全名',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户';

#
# Data for table "sec_user"
#

INSERT INTO `sec_user` VALUES (1,'admin','shengmu','wangrenhui1990@gmail.com','15611434500','$shiro1$SHA-256$500000$iLqsOFPx5bjMGlB0JiNjQQ==$1cPTj9gyPGmYcKGQ8aw3shybrNF1ixdMCm/akFkn71o=','default_hasher','','','管理员','圣牧','圣牧.管理员','2014-12-14 17:17:01',NULL,NULL);

#
# Structure for table "sec_user_info"
#

DROP TABLE IF EXISTS `sec_user_info`;
CREATE TABLE `sec_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建者id',
  `gender` int(11) DEFAULT '0' COMMENT '性别0男，1女',
  `province_id` bigint(20) DEFAULT NULL COMMENT '省id',
  `city_id` bigint(20) DEFAULT NULL COMMENT '市id',
  `county_id` bigint(20) DEFAULT NULL COMMENT '县id',
  `street` varchar(500) DEFAULT NULL COMMENT '街道',
  `zip_code` varchar(50) DEFAULT NULL COMMENT '邮编',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户信息';

#
# Data for table "sec_user_info"
#

INSERT INTO `sec_user_info` VALUES (1,1,0,0,1,2,3,'人民大学',NULL,'2014-12-14 17:17:01',NULL,NULL);

#
# Structure for table "sec_user_role"
#

DROP TABLE IF EXISTS `sec_user_role`;
CREATE TABLE `sec_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户角色';

#
# Data for table "sec_user_role"
#

INSERT INTO `sec_user_role` VALUES (1,1,1);

#
# Structure for table "workmate"
#

DROP TABLE IF EXISTS `workmate`;
CREATE TABLE `workmate` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '主键',
  `workid` varchar(8) DEFAULT NULL COMMENT '工号',
  `name` varchar(5) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `telephone` varchar(15) DEFAULT NULL COMMENT '手机号码',
  `short_phone` varchar(6) DEFAULT NULL COMMENT '短号',
  `workclass` varchar(10) DEFAULT NULL COMMENT '所属班组',
  `workname` varchar(10) DEFAULT NULL COMMENT '岗位名称',
  `cometime` datetime DEFAULT NULL COMMENT '入职时间',
  `integral` int(10) DEFAULT NULL COMMENT '积分点',
  `status` varchar(5) DEFAULT NULL COMMENT '状态',
  `content` varchar(30) DEFAULT NULL COMMENT '备注',
  `worklevel` varchar(5) DEFAULT NULL COMMENT '岗位级别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

#
# Data for table "workmate"
#

INSERT INTO `workmate` VALUES (1,'021121','李四','0','15800203923','11111','05','写码','2014-05-12 00:00:00',0,'in','20031','04'),(2,'111','王五','1','13909986677','223212','03','开拆','2014-05-13 00:00:00',0,'relax','20031','04'),(3,'020009','叶旻','0','18924928885','3434','01','质监岗','2013-04-10 00:00:00',0,'in','20031','02'),(4,'20031','赵青','0','12354578956','2312','01','装车','2013-05-04 00:00:00',0,'relax','无','01'),(5,'123','123','0','12354578956','123','01','123','2013-05-04 00:00:00',0,'in','20031','01'),(6,'20031','123','0','12354578956','2312','10','123','2013-05-04 00:00:00',0,'in','20031','04'),(7,'06560','啊啊啊','0','1564654','2132','01','123','2013-05-04 00:00:00',0,'in','20031','01'),(8,'20031','123','0','12354578956','2312','01','123','2013-05-04 00:00:00',0,'in','20031','01'),(9,'8888','123','1','12354578956','2312','01','123','2014-06-09 00:00:00',0,'in','20031','04'),(10,'555','123','0','12354578956','2312','01','123','2013-05-04 00:00:00',0,'in','20031','01'),(11,'020037','张三','0','159000880000','88880','07','设备系统','2013-07-01 00:00:00',0,'in','20031','04');
