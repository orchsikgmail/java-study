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

select * from Sales;
select * from Sales2;

-- 정답2
insert into Sales2
select company, year, sale,
    case sign(
            sale - max(sale) over (partition by company order by year
                    rows between 1 preceding and 1 preceding)
        )
    when 0 then '='
    when 1 then '+'
    when -1 then '-'
    else null end as var
from sales;

-- ROWS BETWEEN 참고
select company, year, sale, 
    max(sale) over (partition by company order by year
    rows between 1 preceding and 1 preceding) as preceding1,
    max(sale) over (partition by company order by year
    rows between 2 preceding and 2 preceding) as preceding2
 from sales;

-- 문제3  : 윈도우 함수로 ‘직전 회사명’과 ‘직전 매상’ 검색
-- 정답3
select * from sales;
select company, year, sale,
    max(company) over(partition by company order by year
        rows between 1 preceding and 1 preceding) as preCompany,
    max(sale) over(partition by company order by year 
        rows between 1 preceding and 1 preceding) as preSale
from sales;


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

select * from postalcode;

-- 문제4
select pcode, district_name
from postalCode
where case when pcode like '4130033' then 0
    when pcode like '413003%' then 1
    when pcode like '41300%%' then 2
    when pcode like '4130%%%' then 3
    when pcode like '413%%%%' then 4
    when pcode like '41%%%%%' then 5
    when pcode like '4%%%%%%' then 6
    else null end =
        ( select min(case when pcode like '4130033' then 0
                when pcode like '413003%' then 1
                when pcode like '41300%%' then 2
                when pcode like '4130%%%' then 3
                when pcode like '413%%%%' then 4
                when pcode like '41%%%%%' then 5
                when pcode like '4%%%%%%' then 6
                else null end ) from postalCode
        );

-- 참고
select pcode, district_name,
    case when pcode like '4130033' then 0
        when pcode like '413003%' then 1
        when pcode like '41300%%' then 2
        when pcode like '4130%%%' then 3
        when pcode like '413%%%%' then 4
        when pcode like '41%%%%%' then 5
        when pcode like '4%%%%%%' then 6
       else null end as rank
from postalCode;


-- 문제5
select pcode, district_name
from ( select pcode, district_name,
    case when pcode like '4130033' then 0
        when pcode like '413003%' then 1
        when pcode like '41300%%' then 2
        when pcode like '4130%%%' then 3
        when pcode like '413%%%%' then 4
        when pcode like '41%%%%%' then 5
        when pcode like '4%%%%%%' then 6
        else null end as rank,
    min( case when pcode like '4130033' then 0
        when pcode like '413003%' then 1
        when pcode like '41300%%' then 2
        when pcode like '4130%%%' then 3
        when pcode like '413%%%%' then 4
        when pcode like '41%%%%%' then 5
        when pcode like '4%%%%%%' then 6
        else null end
        ) over ( order by case when pcode like '4130033' then 0
                when pcode like '413003%' then 1
                when pcode like '41300%%' then 2
                when pcode like '4130%%%' then 3
                when pcode like '413%%%%' then 4
                when pcode like '41%%%%%' then 5
                when pcode like '4%%%%%%' then 6
                else null end) as min_rank
      from postalCode) Foo
where rank = min_rank;

-- 문제6
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

select * from postalHistory;

-- 문제6 (PostgreSQL)
with recursive explosion (name, pcode, new_pcode, depth)
as
(select name, pcode, new_pcode, 1
from postalHistory
where name ='지용운'
    and new_pcode is null
union
select child.name, child.pcode, child.new_pcode, depth+1
from explosion as parent, postalHistory as child
where parent.code = child.new_pcode
    and parent.name = child.name)
select name, pcode, new_pcode
from explosion
where depth = (select max(depth) from explosion);

-- 문제6 (Oralcle)
with explosion (name, pcode, new_pcode, depth)
as
    (select name, pcode, new_pcode, 1
    from postalHistory
    where name = '지용운'
        and new_pcode is null
    union all
    select child.name, child.pcode, child.new_pcode, depth+1
    from explosion parent, postalHistory child
    where parent.pcode = child.new_pcode
        and parent.name = child.name)
select name, pcode, new_pcode
from explosion
where depth = (select max(depth) from explosion);


-- 문제7
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

select * from postalHistory2;

-- 정답7
select name, pcode
from postalhistory2 ph1
where name = '김중수'
    and not exists ( 
        select * from postalhistory2 ph2
        where ph2.name = '김중수'
            and ph1.lft > ph2.lft);


