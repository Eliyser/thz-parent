/*
SQLyog Ultimate v8.32 
MySQL - 5.5.49 : Database - thzdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`thzdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
/**/

USE `thzdb`;

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `ID` bigint(32) NOT NULL,
  `tem` varchar(32) DEFAULT NULL COMMENT '温度',
  `hum` varchar(32) DEFAULT NULL COMMENT '湿度',
  `name` varchar(32) DEFAULT NULL COMMENT '物质名', //查询
  `number` bigint(32) DEFAULT NULL COMMENT '次数', //查询
  `type` varchar(32) DEFAULT NULL COMMENT '类型', //查询
  `Report Date Time` varchar(32) DEFAULT NULL COMMENT '时间', //查询
  `Vertical axis` varchar(32) DEFAULT NULL,
  `Horizontal axis` varchar(32) DEFAULT NULL,
  `Frequency resolution` varchar(32) DEFAULT NULL COMMENT '频率',
  `DeltaTime` double DEFAULT NULL,
  `Cumulated number` bigint(32) DEFAULT NULL,
  `Master seed laser [A]` double DEFAULT NULL,
  `Slave seed laser [A]` double DEFAULT NULL,
  `Bias voltage [V]` double DEFAULT NULL,
  `Bias gain [MOhm]` bigint(32) DEFAULT NULL,
  `Sample thickness [mm]` double DEFAULT NULL,
  `XMIN` double DEFAULT NULL,
  `XMAX` double DEFAULT NULL,
  `YMIN` double DEFAULT NULL,
  `YMAX` double DEFAULT NULL,
  ` XAxis` varchar(100) DEFAULT NULL COMMENT 'x轴数据，字符串存储',
  `Result` varchar(100) DEFAULT NULL COMMENT 'y轴数据，字符串存储',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`ID`,`tem`,`hum`,`name`,`number`,`type`,`Report Date Time`,`Vertical axis`,`Horizontal axis`,`Frequency resolution`,`DeltaTime`,`Cumulated number`,`Master seed laser [A]`,`Slave seed laser [A]`,`Bias voltage [V]`,`Bias gain [MOhm]`,`Sample thickness [mm]`,`XMIN`,`XMAX`,`YMIN`,`YMAX`,` XAxis`,`Result`) values (1,'30.0','30.0','食用油',1,'液体','2018/11/08 21:19:08','Time Domain [V]','Time [psec]','7.6GHz',0.002,256,0.225,0.19,12.83686,30,2.4,0,131.07,-0.07942627,0.1119652,'1,2,3,4,5,6','1,2,3,4,5,6'),(2,'28.5','28.6','西瓜刀',1,'固体','2018/11/08 21:19:08','Time Domain [V]','Time [psec]','7.6GHz',0.002,256,0.225,0.19,12.83686,30,2.4,0,131.07,-0.07942627,0.1119652,'1,2,3,4,5,6',NULL);

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`username`,`password`) values (1,'1','123456'),(2,'admin','123456');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
