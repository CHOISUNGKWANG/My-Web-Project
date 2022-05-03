create table member(
	mid varchar(50) primary key not null,
	mpw varchar(50) not null,
	mnick varchar(50) not null,
	memail varchar(50) not null
);

create table board(
	bid int primary key not null,
	mid varchar(50) not null,
	mnick varchar(50) not null,
	btitle varchar(100) not null,
	bimg varchar(50) default null,
	bcontent varchar(500) not null,
	bhits int default 0,
	blike int default 0,
	bdate date default sysdate,
	foreign key (mid) references member(mid) on delete cascade
);

create table reply(
	rid int primary key not null,
	bid int not null,
	mid varchar(50) not null,
	mnick varchar(50) not null,
	rcontent varchar(500) not null,
	rdate date default sysdate,
	foreign key (bid) references board(bid) on delete cascade,
	foreign key (mid) references member(mid) on delete cascade
);

create table reply2(
	rrid int primary key not null,
	rid int not null,
	mid varchar(50) not null,
	mnick varchar(50) not null,
	rrcontent varchar(500) not null,
	rrdate date default sysdate,
	foreign key (rid) references reply(rid) on delete cascade,
	foreign key (mid) references member(mid) on delete cascade
);

create table blike(
	bid int not null,
	mid varchar(50) not null,
	foreign key (bid) references board(bid) on delete cascade,
	foreign key (mid) references member(mid) on delete cascade
);

create table stats(
	sposition varchar(50) not null,
	srank varchar(50) not null,
	schampsrc varchar(100) not null,
	schamp varchar(50) not null,
	stiersrc varchar(50) not null,
	swinrate varchar(50) not null,
	spickrate varchar(50) not null
);

drop table member;
drop table board;
drop table reply;
drop table reply2;
drop table blike;
drop table stats;

select * from member;
select * from board;
select * from reply;
select * from reply2;
select * from blike;
select * from stats;

insert into member values('tjdrhkd','성팡','1234','mail@naver.com');
select count(*) from blike where bid=1;
select count(*) from board;
select * from (select * from board order by bid desc) where rownum<=30;
select board from (select rownum rnum,bid from board order by bid) where rnum>=11 and rnum<=20;
select * from (select rownum r,a.* from(select * from board order by bid DESC) a) where r between 21 and 30;
select count(*) from board where btitle like '%'||3||'%';      
select * from (select rownum r,a.* from(select * from board order by blike desc) a) where r between 1 and 10;

insert into reply (rid,bid,mid,mnick,rcontent) values((select nvl(max(rid),0)+1 from reply),25,'tjdrhkd','성팡','개못해 ㅋ');
insert into reply2 (rrid,rid,mid,mnick,rrcontent) values((select nvl(max(rrid),0)+1 from reply2),1,'tjdrhkd','성팡','나 잘함 ㅋ');
update board set bhits='23' where bid='126';

insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');
insert into board(bid,mid,mnick,btitle,bimg,bcontent) values((select nvl(max(bid),0)+1 from board),'tjdrhkd1234a','최성광','더미글','darius.jpg','더미내용');


