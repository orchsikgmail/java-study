--3.1 Select all warehouses.
--  모든 창고의 모든 정보
SELECT * FROM warehouses;


--3.2 Select all boxes with a value larger than $150.
-- value가 150달러 초과하는 박스의 모든 정보
SELECT   * FROM BOXES WHERE VALUE > 150;


--3.3 Select all distinct contents in all the boxes.
-- Boxes테이블의 중복되지 않는 contents
SELECT  DISTINCT contents FROM BOXES;

--3.4 Select the average value of all the boxes.
-- Boxex의 value의 평균값
SELECT AVG(value) FROM Boxes;


--3.5 Select the warehouse code and the average value of the boxes in each warehouse.
-- 창고별 Boxes의 평균 value값과 warehouse번호
-- GROUP BY
SELECT warehouse, AVG(value) FROM boxes GROUP BY warehouse;

-- OVER Function
SELECT distinct warehouse, AVG(value) OVER(PARTITION BY warehouse)  FROM Boxes;


--3.6 Same as previous exercise, but select only those warehouses where the average value of the boxes is greater than 150.
-- 위와 동일한데 평균 value150달러 초과인 warehouse만
SELECT warehouse, AVG(value) FROM boxes GROUP BY warehouse HAVING AVG(value) >150;


--3.7 Select the code of each box, along with the name of the city the box is located in.
-- 박스별로 박스코드랑 박스가 위치하는 location 정보 
SELECT B.code, location
FROM Boxes B JOIN Warehouses W
ON B.warehouse = W.code;


--3.8 Select the warehouse codes, along with the number of boxes in each warehouse. 
    -- Optionally, take into account that some warehouses are empty (i.e., the box count should show up as zero, instead of omitting the warehouse from the result).
-- warehouse의 코드와 boxe개수
SELECT warehouse, NVL(COUNT(*),0)
FROM Boxes
GROUP BY warehouse;


--3.9 Select the codes of all warehouses that are saturated (a warehouse is saturated if the number of boxes in it is larger than the warehouse's capacity).
-- capacity가 box개수보다 작은 warehouse의 코드
SELECT W.code
FROM Warehouses W JOIN (
    SELECT warehouse, COUNT(*) cob
    FROM Boxes
    GROUP BY warehouse) J
ON W.code = J.warehouse
WHERE W.capacity < cob;

-- Subquery에서 상위쿼리 접근
SELECT Code
   FROM Warehouses
   WHERE Capacity <
   (
     SELECT COUNT(*)
       FROM Boxes
       WHERE Warehouse = Warehouses.Code
   );
   

--3.10 Select the codes of all the boxes located in Chicago.
-- Chicago에 위치한 box의 코드
SELECT B.code
FROM Boxes B JOIN Warehouses W
ON B.warehouse = W.code
WHERE location = 'Chicago';

 /* With a subquery */
 SELECT Code
   FROM Boxes
   WHERE Warehouse IN
   (
     SELECT Code
       FROM Warehouses
       WHERE Location = 'Chicago'
   );
   
   
--3.11 Create a new warehouse in New York with a capacity for 3 boxes.
--  capacity 3, location New Your 의 새로운 warehouse 추가
INSERT INTO Warehouses VALUES (6, 'New York',3);


--3.12 Create a new box, with code "H5RT", containing "Papers" with a value of $200, and located in warehouse 2.
-- code:'H5RT', contents: 'Papers', value: 200, warehouse:2 인 box 데이터 추가
INSERT INTO Boxes VALUES ('H5RT', 'Paper', 200, 2);


--3.13 Reduce the value of all boxes by 15%.
-- 모든 박스의 value를 15프로 감소
UPDATE Boxes SET value = value*(1-.15);


--3.14 Remove all boxes with a value lower than $100.
-- value가 100미만인 박스 제거
DELETE FROM Boxes Where value < 100;


-- 3.15 Remove all boxes from saturated warehouses.
-- 수용량 넘어선 박스 제거
--  1
DELETE FROM boxes
WHERE warehouse IN
    (
    SELECT Code
       FROM Warehouses
       WHERE Capacity <
       (
         SELECT COUNT(*)
           FROM Boxes
           WHERE Warehouse = Warehouses.Code
       )
    );

-- 2
REMOVE FROM Boxes 
WHERE warehouse IN (
    SELECT code, capacity 
    FROM Warehouses W JOIN (
        SELECT warehouse, COUNT(*) cnt
        FROM Boxes
        GROUP BY warehouse) B
        ON W.code = B.warehouse
    WHERE W.capacity < B.cnt);


-- 3.16 Add Index for column "Warehouse" in table "boxes"
-- Boxes테이블에 warehouse컬럼으로 인덱스 생성
CREATE INDEX Index_Wharehouse ON Boxes (warehouse);  


-- 3.17 Print all the existing indexes
-- 존재하는 모든 인덱스 출력
SELECT * FROM SYS.ALL_INDEXES;
-- 현재 접속유저의 인덱스
SELECT * FROM user_indexes;    


-- 3.18 Remove (drop) the index you added just
-- 인덱스 삭제
DROP INDEX Index_WareHouse;