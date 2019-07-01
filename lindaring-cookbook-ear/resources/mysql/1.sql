----------------------------->MYSQL
SHOW databases;

USE LINDARING_COOKBOOK;

SHOW tables;	
desc answers;

select * from answers;

----------------------------->SQL SERVER

--List tables
SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE='BASE TABLE' 

SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = 'activity'
/*
	activity id
	text
	date & time
	user
*/
DROP TABLE activity

CREATE TABLE activity (
    id int IDENTITY(1,1) PRIMARY KEY,
    [text] varchar(255),
    dateAdded datetime,
    [visitor] varchar(50)
);

INSERT INTO activity([text], [dateAdded], [visitor])
VALUES('This is activity 1', CURRENT_TIMESTAMP, 'the usern')

SELECT * FROM activity