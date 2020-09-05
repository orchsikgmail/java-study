--1) 여러개의 레코드를 한 개의 레코드로 집약
create table NonAggTbl (
    id varchar2(32) not null,
    data_type char(1) not  null,
    data_1 integer,
    data_2 integer,
    data_3 integer,
    data_4 integer,
    data_5 integer,
    data_6 integer,
    constraint NonAggTbl_PK primary key(id, data_type)
);

insert into NonAggTbl (id, data_type, data_1, data_2 , data_3, data_4, data_5) values ('지용운', 'A', 100, 10, 34, 346, 54);
insert into NonAggTbl values ('지용운', 'B', 45, 2, 167, 77, 90, 157);
insert into NonAggTbl (id, data_type, data_2, data_3 , data_4, data_5, data_6) values ('지용운', 'C', 3, 687, 1355, 324, 457);
insert into NonAggTbl (id, data_type, data_1, data_2 , data_3, data_4, data_6) values ('김수정', 'A', 78, 5, 724, 457, 1);
insert into NonAggTbl values ('김수정', 'B', 123, 12, 178, 346, 85, 235);
insert into NonAggTbl (id, data_type, data_1, data_3, data_4, data_5,  data_6) values ('김수정', 'C', 45, 23, 46, 687, 33);
insert into NonAggTbl (id, data_type, data_1, data_2 , data_3, data_4, data_5) values ('이경호', 'A', 75, 0, 190, 25, 356);
insert into NonAggTbl (id, data_type, data_1, data_2 , data_3, data_5, data_6) values ('이경호', 'B', 435, 0, 183, 4, 325);
insert into NonAggTbl (id, data_type, data_1, data_2 , data_4, data_5, data_6 ) values ('이경호', 'C', 96, 128, 0, 0, 12);

-- 비집약 테이블
select * from NonAggTbl;

--	문제] 이와 같이 한 사람에 대한 필요한 정보가 여러 개의 레코드에 분산되어 있을 때, 사람마다 필요한 정보만 집약한 테이블을 만들어라.
select id,
    max(case when data_type='A' then data_1 else null end) as data_1,
    max(case when data_type='A' then data_2 else null end) as data_2,
    max(case when data_type='B' then data_3 else null end) as data_3,
    max(case when data_type='B' then data_4 else null end) as data_4,
    max(case when data_type='B' then data_5 else null end) as data_5,
    max(case when data_type='C' then data_6 else null end) as data_6
from NonAggTbl
group by id;

-- 위에서 사용한 쿼리 (집약하기 전)
select id,
    case when data_type='A' then data_1 else null end as data_1,
    case when data_type='A' then data_2 else null end as data_2,
    case when data_type='B' then data_3 else null end as data_3,
    case when data_type='B' then data_4 else null end as data_4,
    case when data_type='B' then data_5 else null end as data_5,
    case when data_type='C' then data_6 else null end as data_6
from NonAggTbl;



-- 2) 합쳐서 하나
create table PriceByAge(
    product_id varchar2(32) not null,
    low_age integer not null,
    high_age integer not null,
    price integer not null,
    constraint PriceByAge_PK primary key (product_id, low_age),
        check (low_age < high_age)
);

insert into PriceByAge values ('제품1', 0, 50, 2000);
insert into PriceByAge values ('제품1', 51, 100, 3000);
insert into PriceByAge values ('제품2', 0, 100, 4200);
insert into PriceByAge values ('제품3', 0, 20, 500);
insert into PriceByAge values ('제품3', 31, 70, 800);
insert into PriceByAge values ('제품3', 71, 100, 1000);
insert into PriceByAge values ('제품4', 0, 99, 8900);

-- 제품의 대상 연령별 가격을 관리하는 테이블.
select * from PriceByAge;

-- 여러 개의 레코드로 한 개의 범위를 커버
-- 문제1] 이런 제품 중 0~100세까지 모든 연령이 가지고 놀 수 있는 제품을 구해라.
select product_id
from PriceByAge
group by product_id
having sum(high_age - low_age +1 ) =101;

-- 참고
select product_id, sum(1), sum(low_age), sum(high_age),  sum(high_age - low_age + 1)
from PriceByAge
group by product_id
order by product_id;



-- 문제2
create table HotelRooms(
    room_nbr integer,
    start_date date,
    end_date date,
    constraint HotelRooms_PK primary key (room_nbr, start_date)
);

insert into HotelRooms values (101, '2008-02-01', '2008-02-06');
insert into HotelRooms values (101, '2008-02-06', '2008-02-08');
insert into HotelRooms values (101, '2008-02-10', '2008-02-13');
insert into HotelRooms values (202, '2008-02-05', '2008-02-08');
insert into HotelRooms values (202, '2008-02-08', '2008-02-11');
insert into HotelRooms values (202, '2008-02-11', '2008-02-12');
insert into HotelRooms values (303, '2008-02-03', '2008-02-17');

-- 문제2] 이 테이블에서 사람들이 숙박한 날이 10일 이상인 방을 선택한다.
-- 숙박한 날의 수는 도착일이 2월 1일, 출발일이 2월 6일이라면 5박이므로 5일 이다.
select * from HotelRooms;

-- 정답
select room_nbr, sum(end_date - start_date) as working_days
from HotelRooms
group by room_nbr
having sum(end_date - start_date) >= 10
order by room_nbr;




-- 2. 자르기
-- 1)  자르기와 파티션
create table Persons (
    name varchar(32) not null,
    age integer not null,
    height float not null,
    weight float not null,
    constraint Persons_PK primary key (name)
);

insert into Persons values ('지용운', 30, 188, 90);
insert into Persons values ('김수정', 21, 167, 55);
insert into Persons values ('최현웅', 87, 158, 48);
insert into Persons values ('이경호', 54, 187, 70);
insert into Persons values ('송미정', 39, 177, 120);
insert into Persons values ('이동건', 90, 175, 48);
insert into Persons values ('김중수', 12, 160, 55);
insert into Persons values ('한장희', 25, 182, 90);
insert into Persons values ('김현창', 30, 176, 53);


-- 문제 1-1] 이름 첫 글자를 사용해 특정한 글자로 시작하는 이름을 가진 사람이 몇 명인지 집계해라.
select * from Persons;

-- 정답
select  substr(name, 1, 1) as label , count(*)
from Persons
group by substr(name, 1, 1);


--문제 1-2] 나이를 기준으로 어린이(20세미만), 성인(20~69세), 노인(70세 이상)으로 나눠라
-- 출력: 나이구분, 명수

-- 정답]
select  case  when age<20 then '어린이'
        when age between 20 and 69 then '성인'
        when age>=70 then '노인'
        else null  end as age_class,
    count(*)
from persons
group by  case  when age<20 then '어린이'
        when age between 20 and 69 then '성인'
        when age>=70 then '노인'
        else null end;

-- 문제1-3] BMI 수치를 바탕으로 저체중(18.5 미만), 정상(18.5 이상 25 미만), 과체중(25 이상)으로 분류해라.
-- BMI  = 체중 / { (키/100)^2 }
-- 출력: 이름, BMI, 분류
select name, round(weight/power(height/100, 2), 1) as BMI,
    case when weight/power(height/100, 2) <18.5 then '저체중'
        when weight/power(height/100, 2)>=18.5 and weight/power(height/100, 2)<25  then '정상'
         when weight/power(height/100, 2) >=25 then '과체중' else null end as 분류
from persons;


-- 문제 1-4] 저체중, 정상, 과체중으로 분류하고 명수를 구하라
-- 출력: 체중분류, 명수
select 
    case when weight/power(height/100, 2) <18.5 then '저체중'
        when weight/power(height/100, 2)>=18.5 and weight/power(height/100, 2)<25  then '정상'
         when weight/power(height/100, 2) >=25 then '과체중' else null end as 분류,
         count(*)
from persons
group by case when weight/power(height/100, 2) <18.5 then '저체중'
        when weight/power(height/100, 2)>=18.5 and weight/power(height/100, 2)<25  then '정상'
         when weight/power(height/100, 2) >=25 then '과체중' else null end;




-- 문제] 이전에 살펴 봤던 연령 범위 테이블(Persons)에 파티션 자르기를 사용해보자. 
-- 출력: 이름, 나이, age_class, age_class별 나이 등수
select name,
    age,
    case when age<20 then '어린이'
        when age between 20 and 69 then '성인'
        when age>=70 then '노인'
        else null end as age_class,
    rank() over(partition by 
        case when age<20 then '어린이'
            when age between 20 and 69 then '성인'
            when age>=70 then '노인'
            else null end
        order by age) as age_rank_in_class
from persons
order by age_class, age_rank_in_class;

