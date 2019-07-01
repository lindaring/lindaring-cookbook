--SQL SERVER
CREATE TABLE activity (
    id int IDENTITY(1,1) PRIMARY KEY,
    [text] varchar(255),
    dateAdded datetime,
    [user] varchar(50)
);

INSERT INTO activity([text], [dateAdded], [visitor])
VALUES('This is activity 1', CURRENT_TIMESTAMP, 'the usern');

/* MYSQL */
USE db_lobola_calculator;
CREATE TABLE activity (
    `id` int NOT NULL AUTO_INCREMENT,
    `text` varchar(255),
    `dateAdded` datetime,
    `visitor` varchar(50),
	PRIMARY KEY (`id`)
);

INSERT INTO activity(`text`, `dateAdded`, `visitor`)
VALUES('This is activity 1', CURRENT_TIMESTAMP, 'the usern');

select * from activity;

drop table activity;

truncate table activity;