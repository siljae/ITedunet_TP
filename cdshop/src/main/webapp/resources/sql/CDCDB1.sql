drop database if exists cdcdb;
create database cdcdb;
use cdcdb;
create table member
(
	m_num int not null auto_increment,
    m_email varchar(50) not null,
    m_name varchar(6) not null,
    m_pw varchar(20) not null,
    m_phone varchar(20) not null,
    m_post varchar(5) not null,
    m_addr1 varchar(100) not null,
    m_addr2 varchar(100) not null,
    m_level int default 1,
    primary key(m_name),
    unique key(m_name)
) default charset=utf8;

select * from member;

create table admin
(
	a_num int not null auto_increment,
    a_email varchar(50) not null,
    a_name varchar(6) not null,
    a_pw varchar(20) not null,
    a_phone varchar(11) not null,
    primary key(a_num)
);

create table product
(
    p_num int not null auto_increment,
	p_id varchar(10) not null,
    p_name varchar(30) not null,
    p_category varchar(10) not null,
    p_titlement varchar(30) not null,
    p_simpledescription varchar(50) not null,
    p_manufacturer varchar(10) not null,
    p_unitprice int not null,
    p_unitsinstock long not null,
    p_detailfilename varchar(100) not null,
    p_titlefilename varchar(100) not null,
    p_date date,
    p_hit int default 1,
    primary key(p_num),
    unique key(p_id)
);

create table buy
(
	b_num int not null auto_increment,
    p_id varchar(10) not null,
    m_name varchar(6) not null,
    b_amount int not null,
    b_date date not null,
    b_total_price int not null,
    foreign key(p_id) references product(p_id),
    foreign key(n_name) references member(m_name)
);


create table commuboard
(
	cb_num int not null auto_increment,
    m_name varchar(6) not null,
    cb_board_type varchar(6) not null,
    cb_animal_type varchar(10) not null,
    cb_title varchar(100) not null,
    cb_content text not null,
    cb_regist_day varchar(30) not null,
    cb_filename varchar(100),
    cb_hit int not null default 0,
    primary key(cb_num),
    foreign key(m_name) references member(m_name)
);

create table recom
(
	recom_num int auto_increment,
    m_name varchar(6) not null,
    cb_num int not null,
    recom_chk int default 0,
    primary key(recom_num),
    foreign key(m_name) references member(m_name) on delete cascade,
    foreign key(cb_num) references commuboard(cd_num) on delete cascade
);

select * from recom;
drop table recom;

create table reply
(
	r_num int not null auto_increment,
    m_name varchar(6) not null,
    r_content text not null,
    r_date date not null,
    primary key(r_num),
    foreign key(m_name) references member(m_name)
);