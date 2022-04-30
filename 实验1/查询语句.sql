SELECT * FROM student;

SELECT * FROM sc WHERE score >= 80 and score <= 90;

SELECT DISTINCT d_name FROM student;

SELECT COUNT(*) as '姓王的同学的数量' FROM student WHERE s_name LIKE '王%';

SELECT MAX(score) FROM sc;

SELECT * FROM sc ORDER BY (c_id *1000 - score);

SELECT s_id, COUNT(*) as '选课数量' FROM sc GROUP BY s_id;