create database boardtest charset=utf8;
use boardtest;

create table board(
	num int not null auto_increment primary key,
    title varchar(100) not null,
    content varchar(100) not null,
    filename varchar(100)
);

select*from board;

insert into board(title, content) values('제목1','내용1');
insert into board(title, content) values('제목2','내용2');
insert into board(title, content) values('제목3','내용3');
insert into board(title, content) values('제목4','내용4');
insert into board(title, content) values('제목5','내용5');