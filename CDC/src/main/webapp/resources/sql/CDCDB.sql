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
    m_level int,
    primary key(m_num),
    unique key(m_name)
);

select*from member;
-- 만들려고 했는데 member 테이블에서 관리자를 주고 m_level로 회원과 관리자를 분리하기로 하자
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
    p_name varchar(30) not null,
    p_category varchar(10) not null,
    P_titlement varchar(30) not null,
    p_simpledescription varchar(50) not null,
    p_manufacturer varchar(10) not null,
    p_unitprice int not null,
    p_untisinstock long not null,
    p_filename varchar(100) not null,
    primary key(p_num)
);

create table mychildboard
(
	mb_num int not null auto_increment,
    m_name varchar(6) not null,
    a_name varchar(6) not null,
    mb_title varchar(100) not null,
    mb_content text not null,
    mb_date date not null,
    mb_filename varchar(100),
    mb_push int,
    mb_reply int,
    primary key(mb_num),
    foreign key(m_name) references member(m_name),
    foreign key(mb_reply) references reply(r_num)
);
create table reply
(
	r_num int not null auto_increment,
    m_name varchar(6) not null,
    r_content text not null,
    r_date date not null,
    primary key(r_num),
    foreign key(m_name) references member (m_name)
);

create table chat
(
	c_num int not null auto_increment,
    m_name varchar(6) not null,
    c_content text not null,
    c_date date not null,
    primary key(c_num),
    foreign key(m_name) references member(m_name)
);