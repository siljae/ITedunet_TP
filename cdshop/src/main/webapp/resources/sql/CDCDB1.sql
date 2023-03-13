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

insert into product values (null, "DF001", "오리젠 독 6kg", "DOG", "강아지가 환장하는 맛! 인기순위 1위", "✔️완벽한 영양소 밸런스", "오리젠", 60000, 100, "orijendog1.png", "orijendog.png", "2023/03/13", null);
insert into product values (null, "CF001", "오리젠 캣앤키튼 1.8kg", "CAT", "고양이가 환장하는 맛! 인기순위 1위", "✔️완벽한 영양소 밸런스", "오리젠", 18000, 100, "origen1.png", "origen_catkitten.png", "2023/03/13", null);
insert into product values (null, "DF002", "오리젠 독 6kg", "DOG", "강아지가 환장하는 맛! 인기순위 1위", "✔️완벽한 영양소 밸런스", "오리젠", 60000, 100, "orijendog1.png", "orijendog.png", "2023/03/13", null);
insert into product values (null, "CF002", "오리젠 캣앤키튼 1.8kg", "CAT", "고양이가 환장하는 맛! 인기순위 1위", "✔️완벽한 영양소 밸런스", "오리젠", 18000, 100, "origen1.png", "origen_catkitten.png", "2023/03/13", null);
insert into product values (null, "DF003", "오리젠 독 6kg", "DOG", "강아지가 환장하는 맛! 인기순위 1위", "✔️완벽한 영양소 밸런스", "오리젠", 60000, 100, "orijendog1.png", "orijendog.png", "2023/03/13", null);
insert into product values (null, "CF003", "오리젠 캣앤키튼 1.8kg", "CAT", "고양이가 환장하는 맛! 인기순위 1위", "✔️완벽한 영양소 밸런스", "오리젠", 18000, 100, "origen1.png", "origen_catkitten.png", "2023/03/13", null);

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
	ca_id int not null,
    m_name varchar(6) not null,
    p_id varchar(10) not null,
    ca_qnt int not null,
    primary key(ca_id),
    foreign key(m_name) references member(m_name),
    foreign key(p_id) references product(p_id)
);

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