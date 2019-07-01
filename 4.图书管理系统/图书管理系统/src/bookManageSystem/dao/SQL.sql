create database db_bookSystem;

use db_bookSystem;

-- 创建图书表tb_book
create table tb_book(
  bId int not null auto_increment,-- 图书编号
  bBookName varchar(40) not null,-- 图书名称
  bAuthor varchar(20) not null,-- 图书作者
  bSex varchar(10) not null,-- 图书作者性别
  bPrice float not null,-- 图书价格
  bBookDescription varchar(1000) not null,-- 图书描述
  btId int(12) not null,-- 图书类型编号
  primary key(bId),-- 设置主键
  foreign key(btId) references tb_bookType(btId)-- 设置外键
);

-- 给图书表tb_book插入一些测试数据
insert into tb_book(bBookName, bAuthor, bSex, bPrice, bBookDescription, btId) values("计算机组成原理","张功萱","男",49.9,"这是一本描述计算机组成原理的书",1);
insert into tb_book(bBookName, bAuthor, bSex, bPrice, bBookDescription, btId) values("C语言程序设计","谭浩强","男",59.9,"这是一本C语言入门的书籍",1);

-- 创建图书类型表tb_bookType
create table tb_bookType(
  btId int(12) not null auto_increment,-- 图书类型编号
  btName varchar(40) not null,-- 图书类型名称
  btDescription varchar(1000) not null,-- 图书类型描述
  primary key(btId)-- 设置主键
);

-- 给图书类型表tb_bookType插入一些测试数据
insert into tb_bookType (btName, btDescription) values ("计算机","这些书都是与计算机相关的书籍");
insert into tb_bookType (btName, btDescription) values ("文学","这些书都是与文学相关的书籍");
