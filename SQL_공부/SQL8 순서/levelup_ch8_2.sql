-- SQL에서 자연수의 특징을 활용하면 '양쪽 끝부터 숫자 세기'를 할 수 있다.
-- 홀수의 경우에는 hi = lo
-- 짝수의 경우에는 hi = lo+1 과 hi = lo-1의 두 개가 존재한다.
SELECT AVG(weight) AS median
FROM (SELECT weight,
    ROW_NUMBER() OVER (ORDER BY weight ASC, student_id ASC) AS hi,
    ROW_NUMBER() OVER (ORDER BY weight DESC, student_id DESC) AS lo
    FROM Weights) TMP
WHERE hi IN (lo, lo+1, lo-1);


SELECT AVG(weight)
FROM (SELECT weight,
    2 * ROW_NUMBER() OVER(ORDER BY weight) - COUNT(*) OVER() AS diff
    FROM Weights) TMP
WHERE diff BETWEEN 0 AND 2;



-- 순번 테이블 정의
CREATE TABLE Numbers ( num INTEGER PRIMARY KEY);
INSERT INTO Numbers VALUES(1);
INSERT INTO Numbers VALUES(3);
INSERT INTO Numbers VALUES(4);
INSERT INTO Numbers VALUES(7);
INSERT INTO Numbers VALUES(8);
INSERT INTO Numbers VALUES(9);
INSERT INTO Numbers VALUES(12);


-- 집합 지향 - 집합의 경계선
SELECT (N1.num +1) AS gap_start, '~', (MIN(N2.num) - 1) AS gap_end
FROM Numbers N1 INNER JOIN Numbers N2
    ON N2.num > N1.num
GROUP BY N1.num
HAVING (N1.num+1) < MIN(N2.num);


-- 절차지향 - 다음레코드
--  next_num : MAX(num) OVER (ORDER BY num ROWS BETWEEN 1 FOLLOWING  AND 1 FOLLOWING) 
SELECT  num+1 AS gap_start,  '~' , (num+diff-1) AS gap_end
FROM (SELECT num, 
                    MAX(num) OVER (ORDER BY num ROWS BETWEEN 1 FOLLOWING 
                        AND 1 FOLLOWING)  - num AS diff
                FROM Numbers) TMP
WHERE diff <>1;  -- (다음수 - 현재수)가 1이 아니라면 -> gap 발생한 경우


