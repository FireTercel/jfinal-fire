# Host: localhost  (Version: 5.5.12)
# Date: 2014-12-03 05:49:31
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

#
# Data for table "blog"
#

INSERT INTO `blog` VALUES (1,'JFinal Demo Title here','JFinal Demo Content here'),(2,'test 1','test 1'),(3,'test 2','test 2'),(4,'test 3','test 3'),(5,'test 4','test 4'),(7,'hello','你好'),(8,'hello','撒旦法'),(9,'sdfsd','撒旦法'),(15,'sdf ','sdf '),(16,'zxczx','行政村在线程sdf');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "clothes"
#

INSERT INTO `clothes` VALUES (1,'cloth1',0.00,'sdfs','sdfs','2014-01-01 00:00:00','11'),(6,'撒旦法',111.00,'撒旦法','撒旦法','2014-01-02 00:00:00','12');

#
# Structure for table "clothimage"
#

DROP TABLE IF EXISTS `clothimage`;
CREATE TABLE `clothimage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image_url` varchar(150) DEFAULT '' COMMENT '图片URL',
  `clothes_id` int(11) DEFAULT NULL COMMENT '外键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "clothimage"
#

INSERT INTO `clothimage` VALUES (1,'123',5),(2,'321',5);
