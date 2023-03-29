drop database if exists cdcdb;
create database cdcdb default character set utf8 collate utf8_general_ci;
use cdcdb;
create table member
(
	m_num int not null auto_increment,
    m_email varchar(50) not null,
    m_name varchar(6) not null ,
    m_pw varchar(20) not null,
    m_phone varchar(20) not null,
    m_post varchar(5) not null,
    m_addr1 varchar(100) not null,
    m_addr2 varchar(100) not null,
    m_level int default 1,
    primary key(m_num),
    unique key(m_name)
); 

select * from member;
insert into member values(null, "admin@gmail.com", "관리자", "1234", "010-1111-1111", "11111", "창원시", "의창구", 2);
insert into member values(null, "seoh@naver.com", "회원", "1234", "010-3012-5225", "22222", "창원시", "의창구", 1);


create table product
(
    p_num int not null auto_increment,
	p_id varchar(10) not null,
    p_name varchar(30) not null,
    p_category varchar(10) not null,
    p_titlement varchar(30) not null,
    p_simpledescription varchar(100) not null,
    p_manufacturer varchar(10) not null,
    p_unitprice int not null,
    p_unitsinstock long not null,
    p_dfilename varchar(100) not null,
    p_tfilename varchar(100) not null,
    p_hit int not null default 1,
    primary key(p_num),
    unique key(p_id)
);

insert into product (p_id, p_name, p_category, p_titlement, p_simpledescription, p_manufacturer, 
p_unitprice, p_unitsinstock, p_dfilename, p_tfilename) values("DF001", "개사료", "dogfood", "이거는 개사료양", "이거 비슷하게하면 돼", "김서현", 10, 1, "몰라.jpg", "몰라2.jpg");
drop table product;
select * from product;

create table buy
(
	b_num int not null auto_increment,
    p_id varchar(10) not null,
    m_name varchar(6) not null,
    b_amount int not null,
    b_date date not null,
    b_total_price int not null,
    primary key(b_num),
    foreign key(p_id) references product(p_id),
    foreign key(m_name) references member(m_name)
);

create table cart
(
	ca_id int not null auto_increment,
    m_name varchar(6) not null,
    p_id varchar(10) not null,
    p_unitprice int not null,
    ca_qnt int not null,
    p_tfilename varchar(100) not null,
    primary key(ca_id),
    foreign key(m_name) references member(m_name)
);

select * from cart;
drop table cart;

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
    foreign key(cb_num) references commuboard(cb_num) on delete cascade
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

create table chat
(
	c_num int not null auto_increment,
    m_name varchar(6) not null,
    c_content text not null,
    c_date date not null,
    primary key(c_num),
    foreign key(m_name) references member(m_name)
);