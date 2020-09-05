drop table items;

create table items(
    item_id varchar2(10),
    year number(4),
    item_name varchar2(50),
    price_tax_ex number(10),
    price_tax_in number(10),
    constraint items_PK primary key(item_id, year)
);

select * from items;

insert into items values ('100', 2000, '머그컵', 500, 525);
insert into items values ('100', 2001, '머그컵', 520, 546);
insert into items values ('100', 2002, '머그컵', 600, 630);
insert into items values ('100', 2003, '머그컵', 600, 630);
insert into items values ('101', 2000, '티스푼', 500, 525);
insert into items values ('101', 2001, '티스푼', 500, 525);
insert into items values ('101', 2002, '티스푼', 500, 525);
insert into items values ('101', 2003, '티스푼', 500, 525);
insert into items values ('102', 2000, '나이프', 600, 630);
insert into items values ('102', 2001, '나이프', 550, 577);
insert into items values ('102', 2002, '나이프', 550, 577);
insert into items values ('102', 2003, '나이프', 400, 420);

-- 문제1
-- 2001년 까지는 세금이 포함되지 않은 가격을, 2002년 부터는 세금이 포함된 가격을 'price'필드로 표시해라.
-- 표시할 데이터:  item_name, year, price
select * from items;

-- union을 사용한 조건분기
select * from (
    select item_name, year, price_tax_ex as price
    from items
    where year<=2001
    union all
    select item_name, year, price_tax_in as price
    from items
    where year>2001
) order by item_name, year
;

-- case를 사용한 조건분기
select item_name, year,
    case when year<=2001 then price_tax_ex 
        when year>2001 then price_tax_in
        end as price
from items;


drop table population;

create table population(
    prefecture varchar2(20),
    sex number(1),
    pop number(10),
    constraint population_PK primary key (prefecture, sex)
);

insert into population values ('성남', 1, 60);
insert into population values ('성남', 2, 40);
insert into population values ('수원', 1, 90);
insert into population values ('수원', 2, 100);
insert into population values ('광명', 1, 100);
insert into population values ('광명', 2, 50);
insert into population values ('일산', 1, 100);
insert into population values ('일산', 2, 100);
insert into population values ('용인', 1, 20);
insert into population values ('용인', 2, 200);

-- 문제2
-- 지역별로 남녀 인구를 표시 (지역, 남자인구, 여자인구)
select * from population;

-- UNION을 사용한 방법
select prefecture, sum(pop_men) as pop_men, sum(pop_wom) as pop_wom
from(
    select prefecture, pop as pop_men, null as pop_wom
    from population
    where sex=1
    union
    select prefecture, null as pop_men, pop as pop_wom
    from population
    where sex=2
) TMP
group by prefecture;

-- 서브쿼리
select prefecture, pop as pop_men, null as pop_wom
from population
where sex=1
union
select prefecture, null as pop_men, pop as pop_wom
from population
where sex=2;

-- CASE를 사용한 방법
select prefecture,
    sum(case when sex=1 then pop else null end) as pop_men,
    sum(case when sex=2 then pop else null end) as pop_wom
from population
group by prefecture;



drop table Employees;

create table Employees(
    emp_id varchar2(10),
    team_id varchar2(10),
    emp_name varchar2(20),
    team varchar2(20),
    constraint Employees_PK primary key (emp_id, team_id)
);

insert into Employees values ('201', '1', '지용운', '상품기획');
insert into Employees values ('201', '2', '지용운', '개발');
insert into Employees values ('201', '3', '지용운', '영업');
insert into Employees values ('202', '2', '김수정', '개발');
insert into Employees values ('203', '3', '이경호', '영업');
insert into Employees values ('204', '1', '송미정', '상품기획');
insert into Employees values ('204', '2', '송미정', '개발');
insert into Employees values ('204', '3', '송미정', '영업');
insert into Employees values ('204', '4', '송미정', '관리');
insert into Employees values ('205', '1', '한장희', '상품기획');
insert into Employees values ('205', '2', '한장희', '개발');

-- 문제3
-- 소속팀이 1개라면 해당 직원은 팀의 이름을 그대로 출력,
-- 2개라면 '2개를 겸무', 3개 이상이라면 '3개 이상을 겸무'라는 문자열을 'team'필드로 표시.
-- 출력대상: emp_name, team
select * from employees;

-- UNION을 사용
select emp_name, max(team) as team
from employees
group by emp_name
having count(*)=1
union all
select emp_name, '2개의 업무를 겸비' as team
from employees
group by emp_name
having count(*)=2
union all
select emp_name, '3개 이상의 업무를 겸비' as team
from employees
group by emp_name
having count(*)>=3;

-- CASE를 사용
select emp_name,
    case when count(*)=1 then max(team)
            when count(*)=2 then '2개의 업무를 겸비'
            when count(*)>=3 then '3개 이상의 업무를 겸비'
            end as team
from employees
group by emp_name;


select col_1
from TABLE_A
union
select col_2
from TABLE_B