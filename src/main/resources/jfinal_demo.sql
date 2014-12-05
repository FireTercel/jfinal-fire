# Host: localhost  (Version: 5.5.12)
# Date: 2014-12-06 06:06:25
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

INSERT INTO `clothes` VALUES (1,'cloth1',1.00,'sdfs','sdfs','2014-01-01 00:00:00','11'),(6,'撒旦法',111.00,'撒旦法','撒旦法','2014-01-02 00:00:00','12'),(7,'撒旦法',10.00,'撒旦法','撒旦法','2014-01-02 00:00:00','12');

#
# Structure for table "clothimage"
#

DROP TABLE IF EXISTS `clothimage`;
CREATE TABLE `clothimage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image_url` varchar(150) DEFAULT '' COMMENT '图片URL',
  `clothes_id` int(11) DEFAULT NULL COMMENT '外键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "clothimage"
#

INSERT INTO `clothimage` VALUES (1,'123',5),(2,'321',5);

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
