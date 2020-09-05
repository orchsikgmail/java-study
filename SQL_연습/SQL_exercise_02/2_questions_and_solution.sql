-- 2.15 Select the name and last name of employees working for departments with second lowest budget.
-- 두번째로 가장 적은 예산의 부서에 속한 직원의 이름과 마지막 이름
/* rownum이용 */
SELECT name, lastname 
FROM employees
WHERE department = (
    SELECT code FROM (
        SELECT ROW_NUMBER() OVER(ORDER BY budget) rnum, code
        FROM departments
        ORDER BY budget
    ) WHERE rnum = 2
);     

-- 2.18 Reassign all employees from the Research department (code 77) to the IT department (code 14).
-- 77번 Research부서의 모든 직원을 14번 IT 부서로 옮겨라
update employees set department =14 where department =77;


-- 2.1 Select the last name of all employees.
-- 직원들의 마지막 이름
select LastName from Employees;


-- 2.2 Select the last name of all employees, without duplicates. 
-- 중복없이 직원들 마지막 이름
select distinct LastName from Employees;


-- 2.3 Select all the data of employees whose last name is "Smith".
--마지막이름이 Smith인 직원의 모든 정보
select * from employees where lastname = 'Smith';


-- 2.4 Select all the data of employees whose last name is "Smith" or "Doe".
-- 마지막이름이 Smith 또는 Doe인 직원의 모든 정보
select * from Employees where lastname in ('Smith', 'Doe');

select * from Employees where lastname = 'Smith' or lastname = 'Doe';


-- 2.5 Select all the data of employees that work in department 14.
-- 14번 부서에서 일하는 모든 직원의 정보
select * from Employees where department = 14;


-- 2.6 Select all the data of employees that work in department 37 or department 77.
-- 37번 또는 77번 부서에서 일하는 직원들의 정보
select * from employees where department = 37 or department = 77;

select * from employees where department in (37, 77);


-- 2.7 Select all the data of employees whose last name begins with an "S".
-- 마지막이름이 "S"로 시작하는 직원의 모든 정보
select * from employees where substr(LastName, 0, 1)= 'S';

select * from employees where LastName like 'S%';


-- 2.8 Select the sum of all the departments' budgets.
-- 모든 부서의 예산의 합
select sum(budget) from Departments;


-- 2.9 Select the number of employees in each department (you only need to show the department code and the number of employees).
-- 각 부서의 직원 수(부서코드, 직원수 출력)
select Department, count(*) from employees group by department;


-- 2.10 Select all the data of employees, including each employee's department's data.
-- 부서데이터를 포함한 모든 직원들의 정보
select *
from employees e
join departments d on e.department = d.code;

select a.*, b.* 
from employees a
join departments b on a.department = b.code ;


-- 2.11 Select the name and last name of each employee, along with the name and budget of the employee's department.
-- 개별 직원의 이름과 마지막이름, 소속부서명, 예산 
select a.name, a.lastname, b.name Department_name, b.Budget
from employees a join departments b
on a.department = b.code;


-- 2.12 Select the name and last name of employees working for departments with a budget greater than $60,000.
-- 예산  $60,000 초과인 부서에 속한 개별 직원의 이름과 마지막이름
/* 조인 */
select a.name, a.lastname
from employees a join departments b
on a.department = b.code
where b.budget>60000;

/* With subquery */
SELECT Name, LastName FROM Employees
  WHERE Department IN
  (SELECT Code FROM Departments WHERE Budget > 60000);

/* Without subquery */
SELECT Employees.Name, LastName
FROM Employees INNER JOIN Departments
ON Employees.Department = Departments.Code
AND Departments.Budget > 60000;


-- 2.13 Select the departments with a budget larger than the average budget of all the departments.
-- 평균부서예산 보다 큰 부서의 모든 정보
select * from departments 
where budget > (
select avg(budget) from departments
);


-- 2.14 Select the names of departments with more than two employees.
-- 직원이 2명 초과인 부서의 이름
/* With subquery */
SELECT Name FROM Departments
  WHERE Code IN
  (
    SELECT Department
      FROM Employees
      GROUP BY Department
      HAVING COUNT(*) > 2
  );

/* With UNION. This assumes that no two departments have
   the same name */
SELECT Departments.Name
FROM Employees INNER JOIN Departments
ON Department = Code
GROUP BY Departments.Name
HAVING COUNT(*) > 2;


-- 2.15 Select the name and last name of employees working for departments with second lowest budget.
-- 두번째로 가장 적은 예산의 부서에 속한 직원의 이름과 마지막 이름
/* rownum이용 */
select * from
    (
    select rownum rnum, temp.* from
        (
        select * from departments order by budget
        ) temp
    )
where rnum=2;

/* 분석함수 */
select * from
    (
    select rank() over(order by budget) rank, code, name, budget from departments
    )
where rank=2 ;


-- 2.16  Add a new department called "Quality Assurance", with a budget of $40,000 and departmental code 11. And Add an employee called "Mary Moore" in that department, with SSN 847219811.
-- "Quality Assurance"라는 부서 등록, 예산: 40,000, 코드: 11, 직원이름:  "Mary Moore", SSN: 847219811.
insert into departments values(11, 'Quality Assurance', 40000);
insert into employees values(847219811, 'Mary', 'Moore', 11);


-- 2.17 Reduce the budget of all departments by 10%.
update departments set budget = budget*0.9;


-- 2.18 Reassign all employees from the Research department (code 77) to the IT department (code 14).
-- 77번 Research부서의 모든 직원을 14번 IT 부서로 옮겨라
update employees set department =14 where department =77;


-- 2.19 Delete from the table all employees in the IT department (code 14).
-- 14번 IT 부서의 모든 직원정보를 삭제
delete from employees where department=14;


-- 2.20 Delete from the table all employees who work in departments with a budget greater than or equal to $60,000.
-- 60,000.이상의 예산을 가진 부서의 모든 직원 정보 삭제
delete from employees
where department in (
    select code 
    from departments
    where budget>=60000
);


-- 2.21 Delete from the table all employees.
delete from employees;