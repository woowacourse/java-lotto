-- create user 'aaaa'@'localhost' identified by 'aaaa';

-- grant all privileges on *.* to 'aaaa'@'localhost';

-- flush privileges;

-- CREATE DATABASE eunsukko_db DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE lotto (
	idx INT NOT NULL,
    no_1 INT NOT NULL,
    no_2 INT NOT NULL,
    no_3 INT NOT NULL,
    no_4 INT NOT NULL,
    no_5 INT NOT NULL,
    no_6 INT NOT NULL,
    token INT NOT NULL,
    is_auto BIT NOT NULL,
    PRIMARY KEY (idx, token)
);


set SQL_SAFE_UPDATES = 0;
delete from lotto;
set SQL_SAFE_UPDATES = 1;



CREATE TABLE winning_lotto (
	bonus_no INT NOT NULL,
	no_1 INT NOT NULL,
    no_2 INT NOT NULL,
    no_3 INT NOT NULL,
    no_4 INT NOT NULL,
    no_5 INT NOT NULL,
    no_6 INT NOT NULL,
    token INT NOT NULL,
    PRIMARY KEY (token)
);

select MAX(token) AS max_token from winning_lotto;
