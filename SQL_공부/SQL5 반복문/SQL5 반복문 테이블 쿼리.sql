create table Sales (
    company varchar2(8),
    year number(4),
    sale number(4),
    constraint SalesPK primary key (company, year)
);
create table Sales2 (
    company varchar2(8),
    year number(4),
    sale number(4),
    var varchar2(2),
    constraint Sales2PK primary key (company, year)
);

insert into Sales values ('A', 2002, 50);
insert into Sales values ('A', 2003, 52);
insert into Sales values ('A', 2004, 55);
insert into Sales values ('A', 2007, 55);
insert into Sales values ('B', 2001, 27);
insert into Sales values ('B', 2005, 28);
insert into Sales values ('B', 2006, 28);
insert into Sales values ('B', 2009, 30);
insert into Sales values ('C', 2001, 40);
insert into Sales values ('C', 2005, 39);
insert into Sales values ('C', 2006, 38);
insert into Sales values ('C', 2010, 35);


create table postalcode (
    pcode char(7),
    district_name varchar(256),
    constraint PK_postalcode primary key(pcode)
);

insert into postalcode values ('4130001', '서울시 마포구 경리단길 1');
insert into postalcode values ('4130002', '서울시 마포구 경리단길 2');
insert into postalcode values ('4130103', '서울시 마포구 경리단길 3');
insert into postalcode values ('4130041', '서울시 마포구 경리단길 4');
insert into postalcode values ('4103213', '서울시 마포구 경리단길 5');
insert into postalcode values ('4380824', '서울시 마포구 경리단길 6');


create table postalHistory(
    name varchar(64),
    pcode char(7),
    new_pcode char(7),
    constraint pk_name_pcode primary key (name, pcode)
);

insert into postalHistory values ('지용운', '4130001', '4130002');
insert into postalHistory values ('지용운', '4130002', '4130003');
insert into postalHistory(name, pcode) values ('지용운', '4130103');
insert into postalHistory(name, pcode) values ('김수정', '4130041');
insert into postalHistory values ('최현웅', '4103213', '4380824');
insert into postalHistory(name, pcode) values ('최현웅', '4180824');

create table PostalHistory2(
    name varchar2(64),
    pcode char(7),
    lft real not null,
    rgt real not null,
    constraint pk_name_pcode2 primary key(name, pcode),
    constraint uq_name_lft unique (name, lft),
    constraint uq_name_rgt unique (name, rgt),
    check (lft < rgt)
);

insert into PostalHistory2 values ('김중수', '4130001', 0, 27);
insert into PostalHistory2 values ('김중수', '4130002', 9, 18);
insert into PostalHistory2 values ('김중수', '4130003', 12, 15);
insert into PostalHistory2 values ('한장희', '4130041', 0, 27);
insert into PostalHistory2 values ('김정수', '4103213', 0, 27);
insert into PostalHistory2 values ('김정수', '4380824', 9, 18);

