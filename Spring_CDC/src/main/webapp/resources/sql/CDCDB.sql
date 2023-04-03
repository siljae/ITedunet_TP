drop database if exists cdcdb;
create database cdcdb default character set utf8 collate utf8_general_ci;
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
    primary key(m_num),
    unique key(m_name)
);
drop table member;
select*from member;
delete from member where m_name='aaa';
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

create table commuboard
(
   cb_num int not null auto_increment,
    m_name varchar(6) not null,
    cb_board_type varchar(6) not null,
    cb_tagsrc varchar(12) not null,
    cb_tagvalue varchar(3) not null,
    cb_title varchar(100) not null,
    cb_content text not null,
    cb_regist_day varchar(30) not null,
    cb_hit int not null default 0,
    cb_recom int not null default 0,
    primary key(cb_num),
    foreign key(m_name) references member(m_name)
);
desc commuboard;
drop table commuboard;
select*from commuboard;
-- 테스트용 게시글 8개
insert into commuboard(m_name, cb_board_type, cb_animal_type, cb_title, cb_content, cb_regist_day) values('abc', 'commu', 'cat', '테스트용입니다1', 'test', '2023/03/14 12:12:12');
insert into commuboard(m_name, cb_board_type, cb_animal_type, cb_title, cb_content, cb_regist_day) values('abc', 'commu', 'cat', '테스트용입니다2', 'test', '2023/03/14 12:12:12');
insert into commuboard(m_name, cb_board_type, cb_animal_type, cb_title, cb_content, cb_regist_day) values('abc', 'commu', 'cat', '테스트용입니다3', 'test', '2023/03/14 12:12:12');
insert into commuboard(m_name, cb_board_type, cb_animal_type, cb_title, cb_content, cb_regist_day) values('abc', 'commu', 'cat', '테스트용입니다4', 'test', '2023/03/14 12:12:12');
insert into commuboard(m_name, cb_board_type, cb_animal_type, cb_title, cb_content, cb_regist_day) values('abc', 'commu', 'cat', '테스트용입니다5', 'test', '2023/03/14 12:12:12');
insert into commuboard(m_name, cb_board_type, cb_animal_type, cb_title, cb_content, cb_regist_day) values('abc', 'commu', 'cat', '테스트용입니다6', 'test', '2023/03/14 12:12:12');
insert into commuboard(m_name, cb_board_type, cb_animal_type, cb_title, cb_content, cb_regist_day) values('abc', 'commu', 'cat', '테스트용입니다7', 'test', '2023/03/14 12:12:12');
insert into commuboard(m_name, cb_board_type, cb_animal_type, cb_title, cb_content, cb_regist_day) values('abc', 'commu', 'cat', '테스트용입니다8', 'test', '2023/03/14 12:12:12');
insert into commuboard(m_name, cb_board_type, cb_animal_type, cb_title, cb_content, cb_regist_day) values('abc', 'commu', 'cat', '테스트용입니다9', 'test', '2023/03/14 12:12:12');
insert into commuboard(m_name, cb_board_type, cb_animal_type, cb_title, cb_content, cb_regist_day) values('abc', 'commu', 'cat', '테스트용입니다10', 'test', '2023/03/14 12:12:12');
insert into commuboard(m_name, cb_board_type, cb_animal_type, cb_title, cb_content, cb_regist_day) values('abc', 'commu', 'cat', '테스트용입니다11', 'test', '2023/03/14 12:12:12');
insert into commuboard(m_name, cb_board_type, cb_animal_type, cb_title, cb_content, cb_regist_day) values('abc', 'commu', 'cat', '테스트용입니다12', 'test', '2023/03/14 12:12:12');
insert into commuboard(m_name, cb_board_type, cb_animal_type, cb_title, cb_content, cb_regist_day) values('abc', 'commu', 'cat', '테스트용입니다13', 'test', '2023/03/14 12:12:12');

update commuboard set cb_recom= cb_recom+1 where cb_title= '33333';

delete from commuboard where m_name='abc';
select*from commuboard order by cb_num desc;
select*from commuboard where cb_title like '%스트%' or cb_content like '%es%' order by cb_num desc;
select*from commuboard where cb_title like '%스트%' or cb_content like '%es%' order by cb_num desc limit 0,10;
select*from commuboard where cb_title like '%19%' or cb_content like '%19%' order by cb_num desc limit 10,10;
select*from commuboard order by cb_hit desc;
select*from commuboard where cb_recom >= 10 limit 5;


select count(*) from commuboard where cb_animal_type='cat';
select count(*) from commuboard where cb_title like '%스트%' or cb_content like '%스트%';


update commuboard 
   set cb_board_type='commu',
    cb_animal_type='cat',
    m_name='abc',
    cb_title='애옹',
    cb_content='431',
    cb_regist_day='2023/03/10 14:26:10',
    cb_filename='holong1.jpg',
    cb_hit=1
    where cb_num=1;
alter table commuboard add cb_tag varchar(10) not null;
alter table commuboard alter cb_filename set default null;
alter table commuboard alter cb_filename drop default;
alter table commuboard add cb_recom int not null default 0;

SELECT table_schema "Database", ROUND(SUM(data_length+index_length)/1024/1024,1) "used_MB" FROM information_schema.TABLES GROUP BY 1;

-- 더미데이터 생성을 위한 스토어드 프로시저
delimiter $$
drop procedure if exists insertloop;
create procedure insertloop()
begin
   declare i int default 1;
    while i <= 100 do
      insert into commuboard(m_name, cb_board_type, cb_animal_type, cb_title, cb_content, cb_regist_day) values('abc','자랑해요', 'cat', concat('테스트용입니다1',i), concat('test',i), '2023/03/14 12:12:12');
      set i = i+1;
   end while;
end $$
delimiter ;

call insertloop;

-- 아래 리콤은 추천테이블이다 해당 테이블에 대한 로직을 작성하려면 아직 이해도가 부족하기때문에 나중에 작성하다 해당 테이블과 관련된 모든 sql문은 현재 사용해서는 안된다
create table recom
(
   recom_num int auto_increment,
    m_name varchar(6) not null,
    cb_num int not null,
    recom_chk boolean default false,
    primary key(recom_num),
    foreign key (cb_num) references commuboard(cb_num),
   foreign key (m_name) references member(m_name) 
);

insert into recom(m_name, cb_num, recom_chk) values('admin',38,true);
update commuboard as cb
   set cb.cb_recom = 
      (
         select count(*)
         from recom as r
         where r.cb_num = cb.cb_num and r.recom_chk =true
      );

select*from recom;
drop table recom;
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

create table qnaboard(
   qb_num int not null auto_increment,
    m_name varchar(6) not null,
    qb_board_type varchar(6) not null,
    qb_tagsrc varchar(12) not null,
    qb_tagvalue varchar(3) not null,
    qb_title varchar(100) not null,
    qb_content text not null,
    qb_regist_day varchar(30) not null,
    qb_hit int not null default 0,
    qb_recom int not null default 0,
    primary key(qb_num),
    foreign key(m_name) references member(m_name)
);
drop table qnaboard;
select*from qnaboard;
create table noticeboard(
   nb_num int not null auto_increment,
    m_name varchar(6) not null,
    nb_board_type varchar(6) not null,
    nb_title varchar(100) not null,
    nb_content text not null,
    nb_regist_day varchar(30) not null,
    nb_hit int not null default 0,
    primary key(nb_num),
    foreign key(m_name) references member(m_name)
);
drop table noticeboard;
create table eventboard(
   eb_num int not null auto_increment,
    m_name varchar(6) not null,
    eb_board_type varchar(6) not null,
    eb_title varchar(100) not null,
    eb_content text not null,
    eb_regist_day varchar(30) not null,
    eb_hit int not null default 0,
    eb_recom int not null default 0,
    primary key(eb_num),
    foreign key(m_name) references member(m_name)
);

create table hosreviewboard(
   hvb_num int not null auto_increment,
    m_name varchar(6) not null,
    hvb_board_type varchar(6) not null,
    hvb_tagsrc varchar(12) not null,
    hvb_tagvalue varchar(3) not null,
    hvb_title varchar(100) not null,
    hvb_content text not null,
    hvb_regist_day varchar(30) not null,
    hvb_hit int not null default 0,
    hvb_recom int not null default 0,
    primary key(hvb_num),
    foreign key(m_name) references member(m_name)
);
drop table hosreviewboard;

CREATE TABLE boardfile (
  bf_num INT NOT NULL AUTO_INCREMENT,
  board_type VARCHAR(50) NOT NULL,
  cb_num INT DEFAULT NULL,
  qb_num INT DEFAULT NULL,
  nb_num INT DEFAULT NULL,
  eb_num INT DEFAULT NULL,
  hvb_num INT DEFAULT NULL,
  filename VARCHAR(255) NOT NULL,
  PRIMARY KEY (bf_num),
  FOREIGN KEY (cb_num) REFERENCES commuboard(cb_num) on delete cascade,
  FOREIGN KEY (qb_num) REFERENCES qnaboard(qb_num) on delete cascade,
  FOREIGN KEY (nb_num) REFERENCES noticeboard(nb_num) on delete cascade,
  FOREIGN KEY (eb_num) REFERENCES eventboard(eb_num) on delete cascade,
  FOREIGN KEY (hvb_num) REFERENCES hosreviewboard(hvb_num) on delete cascade
);
drop table boardfile;
select*from boardfile;
alter table boardfile change column file_name filename varchar(255) not null;

insert into boardfile(board_type, cb_num, file_name) values('자랑해요',302,'naver.jpg');

create table tc
(
	c_num int not null auto_increment primary key,
    m_name varchar(20) not null,
    c_bt varchar(10) not null,
    c_title varchar(100) not null
);
create table tq
(
	q_num int not null auto_increment primary key,
    m_name varchar(20) not null,
    q_bt varchar(10) not null,
    q_title varchar(100) not null
);
insert into tc(m_name, c_bt, c_title) values('abc','자랑해요','자랑테스트1');
insert into tc(m_name, c_bt, c_title) values('abc','자랑해요','자랑테스트2');
insert into tc(m_name, c_bt, c_title) values('abc','자랑해요','자랑테스트3');
insert into tq(m_name, q_bt, q_title) values('abc','Q&A','Q&A테스트1');
insert into tq(m_name, q_bt, q_title) values('abc','Q&A','Q&A테스트2');
insert into tq(m_name, q_bt, q_title) values('abc','Q&A','Q&A테스트3');

SELECT c_num, m_name, c_bt, c_title FROM tc
UNION ALL
SELECT q_num, m_name, q_bt, q_title FROM tq;

SELECT c_num AS id, m_name, c_bt AS boardtype, c_title AS title FROM tc
UNION ALL
SELECT q_num AS id, m_name, q_bt AS boardtype, q_title AS title FROM tq;

SELECT c_num AS 'c_num', NULL AS q_num, m_name, c_bt AS boardtype, c_title AS title FROM tc
UNION ALL
SELECT NULL AS c_num, q_num AS 'c_num or q_num', m_name, q_bt AS boardtype, q_title AS title FROM tq;

select cb_num, null as qb_num, m_name, cb_board_type as boardtype, cb_tagsrc as tagsrc, cb_tagvalue as tagvalue, cb_title as title, cb_content as content, cb_regist_day as regist_day, cb_hit as hit, cb_recom as recom from commuboard
union all
select null as cb_num, qb_num, m_name, qb_board_type as boardtype, qb_tagsrc as tagsrc, qb_tagvalue as tagvalue, qb_title as title, qb_content as content, qb_regist_day as regist_day, qb_hit as hit, qb_recom as recom from qnaboard
order by regist_day desc limit 10;

 select * from boardfile where board_type=? and cb_num=?;

create table file(
	f_num int not null auto_increment primary key,
    c_num int null,
    q_num int null,
    boardtype varchar(5) not null,
    filename varchar(100) not null,
    foreign key(c_num) references tc(c_num),
    foreign key(q_num) references tq(q_num)
);

insert into file(c_num, boardtype, filename) values(1,'자랑해요','catface.png');
insert into file(q_num, boardtype, filename) values(3,'Q&A','catface.png');

SELECT f.*
FROM file f
INNER JOIN (
    SELECT c_num, c_bt as boardtype, c_title FROM tc
    UNION ALL
    SELECT q_num, q_bt as boardtype, q_title FROM tq
) t ON (f.c_num = t.c_num OR f.q_num = t.c_num)
    AND f.boardtype = t.boardtype;
    
select cb_num, null as qb_num, m_name, cb_board_type as boardtype, cb_tagsrc as tagsrc, cb_tagvalue as tagvalue, cb_title as title, cb_content as content, cb_regist_day as regist_day, cb_hit as hit, cb_recom as recom from commuboard 
union all
select null as cb_num, qb_num, m_name, qb_board_type as boardtype, qb_tagsrc as tagsrc, qb_tagvalue as tagvalue, qb_title as title, qb_content as content, qb_regist_day as regist_day, qb_hit as hit, qb_recom as recom from qnaboard
order by regist_day desc limit 0,10;

select cb_num, null as qb_num, m_name, cb_board_type as boardtype, cb_tagsrc as tagsrc, cb_tagvalue as tagvalue, cb_title as title, cb_content as content, cb_regist_day as regist_day, cb_hit as hit, cb_recom as recom from commuboard
where cb_title like '%우리%' or cb_content like '%우리%' 
union all
select null as cb_num, qb_num, m_name, qb_board_type as boardtype, qb_tagsrc as tagsrc, qb_tagvalue as tagvalue, qb_title as title, qb_content as content, qb_regist_day as regist_day, qb_hit as hit, qb_recom as recom from qnaboard
where qb_title like '%우리%' or qb_content like '%우리%' 
order by regist_day desc limit 0,10;

select cb_num, null as qb_num, m_name, cb_board_type as boardtype, cb_tagsrc as tagsrc, cb_tagvalue as tagvalue, cb_title as title, cb_content as content, cb_regist_day as regist_day, cb_hit as hit, cb_recom as recom from commuboard
where cb_recom >= 10 and cb_title like '%스트%' or cb_content like '%스트%' 
union all
select null as cb_num, qb_num, m_name, qb_board_type as boardtype, qb_tagsrc as tagsrc, qb_tagvalue as tagvalue, qb_title as title, qb_content as content, qb_regist_day as regist_day, qb_hit as hit, qb_recom as recom from qnaboard
where qb_recom >= 10 and qb_title like '%스트%' or qb_content like '%스트%' 
order by regist_day desc;

select count(*) from commuboard where cb_recom >= 10;

select*from qnaboard;