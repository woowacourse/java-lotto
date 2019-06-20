create user 'kjmlotto'@'localhost' identified by 'kjmlotto';
grant all privileges on *.* to 'kjmlotto'@'localhost';
flush privileges;
CREATE DATABASE lotto_db DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

select * from lotto_result_info;
CREATE TABLE lotto_result_info(
	round INT NOT NULL DEFAULT 0,
    result MEDIUMTEXT,
    profit_rate MEDIUMTEXT,
	prize_money MEDIUMTEXT,
    PRIMARY KEY (round)
);

select * from winning_lotto_info;
CREATE TABLE winning_lotto_info(
	round INT NOT NULL DEFAULT 0,
    first INT NOT NULL DEFAULT 0,
    second INT NOT NULL DEFAULT 0,
    third INT NOT NULL DEFAULT 0,
    fourth INT NOT NULL DEFAULT 0,
    fifth INT NOT NULL DEFAULT 0,
    sixth INT NOT NULL DEFAULT 0,
    bonus_number INT NOT NULL DEFAULT 0
);

select * from lotto_numbers_info;
CREATE TABLE lotto_numbers_info (
	round INT NOT NULL DEFAULT 0,
    first INT NOT NULL DEFAULT 0,
    second INT NOT NULL DEFAULT 0,
    third INT NOT NULL DEFAULT 0,
    fourth INT NOT NULL DEFAULT 0,
    fifth INT NOT NULL DEFAULT 0,
    sixth INT NOT NULL DEFAULT 0
    );
