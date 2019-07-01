# Host: localhost  (Version 5.5.15)
# Date: 2019-06-29 22:49:46
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "tb_booktype"
#

CREATE TABLE `tb_booktype` (
  `btId` int(12) NOT NULL AUTO_INCREMENT,
  `btName` varchar(40) NOT NULL,
  `btDescription` varchar(1000) NOT NULL,
  PRIMARY KEY (`btId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

#
# Data for table "tb_booktype"
#

INSERT INTO `tb_booktype` VALUES (2,'文学','这些书都是与文学相关的书籍'),(5,'金瓶梅','高数是一棵神奇的树。\t\t\t'),(6,'外语','学会一门外语是很有用处的。'),(7,'科幻','对未来的期望。');

#
# Structure for table "tb_book"
#

CREATE TABLE `tb_book` (
  `bId` int(11) NOT NULL AUTO_INCREMENT,
  `bBookName` varchar(40) NOT NULL,
  `bAuthor` varchar(20) NOT NULL,
  `bSex` varchar(10) NOT NULL,
  `bPrice` float NOT NULL,
  `bBookDescription` varchar(1000) NOT NULL,
  `btId` int(12) NOT NULL,
  PRIMARY KEY (`bId`),
  KEY `btId` (`btId`),
  CONSTRAINT `tb_book_ibfk_1` FOREIGN KEY (`btId`) REFERENCES `tb_booktype` (`btId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Data for table "tb_book"
#

INSERT INTO `tb_book` VALUES (2,'书名','作者名字','男',33,'描述',1),(5,'西游记','吴承恩','男',50,'西游记是一部神魔小说。',2),(9,'三体','刘慈欣','男',100,'科幻巨著。',7),(10,'醉花阴','李清照','女',5,'词。',2);
