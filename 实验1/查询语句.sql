SELECT * FROM student;

SELECT * FROM sc WHERE score >= 80 and score <= 90;

SELECT DISTINCT d_name FROM student;

SELECT COUNT(*) as '������ͬѧ������' FROM student WHERE s_name LIKE '��%';

SELECT MAX(score) FROM sc;

SELECT * FROM sc ORDER BY (c_id *1000 - score);

SELECT s_id, COUNT(*) as 'ѡ������' FROM sc GROUP BY s_id;