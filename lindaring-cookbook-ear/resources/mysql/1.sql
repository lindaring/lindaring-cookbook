----------------------------->MYSQL
SHOW databases;

USE LINDARING_COOKBOOK;

SHOW tables;	
desc answers;

select * from answers;

----------------------------->SQL SERVER

--List tables
SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE='BASE TABLE' 

/*
	activity id
	text
	date & time
	user
*/
CREATE TABLE activity (
    id int,
    [text] varchar(255),
    dateAdded datetime,
    [user] varchar(50)
);