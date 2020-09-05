-- 체중테이블 정의
create table Weights
(student_id char(4) primary key,
weight integer);

insert into Weights values ('A100', 50);
insert into Weights values ('A101', 55);
insert into Weights values ('A124', 55);
insert into Weights values ('B343', 60);
insert into Weights values ('B346', 72);
insert into Weights values ('C563', 72);
insert into Weights values ('C345', 72);

-- 윈도우 함수 사용
-- 학생 ID를 오름차순으로 순번 정의
SELECT student_id, ROW_NUMBER() OVER (ORDER BY student_id) AS seq
FROM Weights;

-- 상관 서브쿼리를 사용
-- MySQL처럼 ROW_NUMBER 함수를 사용할 수 없는 환경에서는 상관서브쿼리를 사용해야 한다.
-- 이 서브쿼리는 재귀 집합을 만들고 요소 수를 COUNT 함수로 센다.
-- 기본키 student_id를 비교 사용하므로 재귀집합의 요소가 한 개씩 증가한다.
-- 순번을 생성할 때 자주 사용하는 트릭이다.
 SELECT student_id, 
    (SELECT COUNT(*)
    FROM Weights W2
    WHERE W2.student_id <= W1.student_id) AS seq
FROM Weights W1;

-- 두 가지 방법은 기능적으로는 동일하지만, 성능 측면에서 윈도우 함수를 사용하는 편이 좋다.
-- 윈도우 함수에서는 스캔 횟수가 1회.
-- 또 인덱스 온리 스캔을 사용하므로 테이블에 직접적인 접근을 회피한다.
-- 한편 상관 서브쿼리를 사용하는 방법에서는 2회(w1, w2)의 스캔이 실행된다.


-- 체중테이블2 정의
CREATE TABLE Weights2
(class INTEGER NOT NULL,
student_id CHAR(4) NOT NULL,
weight INTEGER NOT NULL,
    PRIMARY KEY(class, student_id));
    
INSERT INTO Weights2 VALUES(1, '100', 50);
INSERT INTO Weights2 VALUES(1, '101', 55);
INSERT INTO Weights2 VALUES(1, '102', 56);
INSERT INTO Weights2 VALUES(2, '100', 60);
INSERT INTO Weights2 VALUES(2, '101', 72);
INSERT INTO Weights2 VALUES(2, '102', 73);
INSERT INTO Weights2 VALUES(2, '103', 73);

-- 윈도우 함수 사용
-- ROW_NUMBER를 사용하는 경우는 머리를 굴릴 필요가 전혀 없습니다.
-- ORDER BY의 키에 필드를 추가하기만 하면 됩니다.
SELECT class, student_id, weight, 
    ROW_NUMBER() OVER (ORDER BY class, student_id) AS seq
FROM Weights2;

-- 상관 서브쿼리를 이용
-- 다중 필드 비교를 사용
-- 다중 필드 비교: 이름 그대로 복합적인 필드를 하나의 값으로 연결하고 한꺼번에 비교하는 기능
-- 이 방법의 장점은 자료형을 원하는대로 지정할 수 있다는 것 이다.
-- 숫자와 문자열, 문자열과 숫자라도 가능하다.
-- 암묵적인 자료형 변환도 발행하지 않으므로 기본키 인덱스도 사용 가능, 필드 개수 제한 없다.
-- 다중 필드 비교 사용 불가 : 오라클, ...
SELECT class, student_id, weight,
    (SELECT COUNT (*)
    FROM Weights2 W2
    WHERE (W2.class, W2.student_id) <=  (W1.class, W1.student_id) ) AS seq
FROM Weights2 W1;





-- 그룹마다 순번을 붙이는 경우
-- 테이블을 그룹으로 나누고 그룹마다 내부 레코드에 순번을 붙이는 것이다.

-- 윈도우 함수 => PARTITION BY
SELECT class, student_id,
    ROW_NUMBER() OVER (PARTITION BY class ORDER BY student_id) AS seq
FROM Weights2;

-- 상관쿼리
SELECT class, student_id,
    (SELECT COUNT(*)
    FROM Weights2 W2
    WHERE W2.class = W1.class
        AND W2.student_id <= W1.student_id) AS seq
FROM Weights2 W1;




-- 순번과 갱신
-- 검색이 아니라 갱신에서 순번을 매기는 방법을 알아본다.
-- 순번을 갱신하는 UPDATE 구문을 만들어본다.

-- 체중테이블3 wjddml
CREATE TABLE Weights3
(class INTEGER NOT NULL,
student_id INTEGER NOT NULL,
weight INTEGER NOT NULL,
seq INTEGER NULL,
    PRIMARY KEY(class, student_id) );

INSERT INTO Weights3 VALUES(1,'100', 50, null);
INSERT INTO Weights3 VALUES(1,'101', 55, null);
INSERT INTO Weights3 VALUES(1,'102', 56, null);
INSERT INTO Weights3 VALUES(2,'100', 60, null);
INSERT INTO Weights3 VALUES(2,'101', 72, null);
INSERT INTO Weights3 VALUES(2,'102', 73, null);
INSERT INTO Weights3 VALUES(2,'103', 73, null);


-- 윈도우 함수 사용
-- 기본적으로 앞서 본 순번 할당 쿼리를 SET 구로 넣는 방법을 생각할 수 있다.
UPDATE Weights3
    SET seq = (SELECT seq
                        FROM (SELECT class, student_id,
                                                ROW_NUMBER() 
                                                    OVER(PARTITION BY class 
                                                        ORDER BY student_id) AS seq
                                       FROM Weights3) SeqTbl
                        WHERE Weights3.class = SeqTbl.class
                            AND Weights3.student_id = SeqTbl.student_id);

-- 상관쿼리
-- 그냥 넣어주면 된다. (MySQL은 안됌. 갱신시 자기참조 불가)
UPDATE Weights3
    SET seq = (SELECT COUNT(*)
                        FROM Weights3 W2
                        WHERE W2.class =Weights3.class
                            AND W2.student_id <= Weights3.student_id);
















