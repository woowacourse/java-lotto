CREATE DATABASE wtc_lotto_db DEFAULT CHARACTER SET UTF8MB4 COLLATE UTF8MB4_GENERAL_CI;

USE wtc_lotto_db;

CREATE TABLE lotto(
	id INT NOT NULL AUTO_INCREMENT,
	number_0 TINYINT NOT NULL,
	number_1 TINYINT NOT NULL,
	number_2 TINYINT NOT NULL,
	number_3 TINYINT NOT NULL,
	number_4 TINYINT NOT NULL,
	number_5 TINYINT NOT NULL,
	price INT NOT NULL,
	reg_date TIMESTAMP NOT NULL DEFAULT NOW(),
	PRIMARY KEY(id)
);

CREATE TABLE winning_lotto(
	id INT NOT NULL AUTO_INCREMENT,
	winning_number_0 TINYINT NOT NULL,
	winning_number_1 TINYINT NOT NULL,
	winning_number_2 TINYINT NOT NULL,
	winning_number_3 TINYINT NOT NULL,
	winning_number_4 TINYINT NOT NULL,
	winning_number_5 TINYINT NOT NULL,
	winning_number_bonus TINYINT NOT NULL,
	reg_date TIMESTAMP NOT NULL DEFAULT NOW(),
	PRIMARY KEY(id)
);

CREATE TABLE aggregation(
	id INT NOT NULL AUTO_INCREMENT,
	lotto_round INT NOT NULL UNIQUE,
	cnt_first INT NOT NULL,
	cnt_second INT NOT NULL,
	cnt_third INT NOT NULL,
	cnt_fourth INT NOT NULL,
	cnt_fifth INT NOT NULL,
	cnt_none INT NOT NULL,
	prize_money_sum BIGINT NOT NULL,
	reg_date TIMESTAMP NOT NULL DEFAULT NOW(),
	PRIMARY KEY(id)
);

CREATE TABLE lotto_aggregated(
	id INT NOT NULL AUTO_INCREMENT,
	lotto_id INT NOT NULL,
	aggregation_id INT NULL,
	reg_date TIMESTAMP NOT NULL DEFAULT NOW(),
	PRIMARY KEY(id),
	FOREIGN KEY(lotto_id) REFERENCES lotto(id) ON DELETE CASCADE,
	FOREIGN KEY(aggregation_id) REFERENCES aggregation(id) ON DELETE CASCADE
);

CREATE TABLE winning_lotto_aggregated(
	id INT NOT NULL AUTO_INCREMENT,
	winning_lotto_id INT NOT NULL,
	aggregation_id INT NOT NULL,
	reg_date TIMESTAMP NOT NULL DEFAULT NOW(),
	PRIMARY KEY(id),
	FOREIGN KEY(winning_lotto_id) REFERENCES winning_lotto(id) ON DELETE CASCADE,
	FOREIGN KEY(aggregation_id) REFERENCES aggregation(id) ON DELETE CASCADE
);


-- DB for test

CREATE DATABASE wtc_lotto_test_db DEFAULT CHARACTER SET UTF8MB4 COLLATE UTF8MB4_GENERAL_CI;

USE wtc_lotto_test_db;

CREATE TABLE lotto(
	id INT NOT NULL AUTO_INCREMENT,
	number_0 TINYINT NOT NULL,
	number_1 TINYINT NOT NULL,
	number_2 TINYINT NOT NULL,
	number_3 TINYINT NOT NULL,
	number_4 TINYINT NOT NULL,
	number_5 TINYINT NOT NULL,
	price INT NOT NULL,
	reg_date TIMESTAMP NOT NULL DEFAULT NOW(),
	PRIMARY KEY(id)
);

CREATE TABLE winning_lotto(
	id INT NOT NULL AUTO_INCREMENT,
	winning_number_0 TINYINT NOT NULL,
	winning_number_1 TINYINT NOT NULL,
	winning_number_2 TINYINT NOT NULL,
	winning_number_3 TINYINT NOT NULL,
	winning_number_4 TINYINT NOT NULL,
	winning_number_5 TINYINT NOT NULL,
	winning_number_bonus TINYINT NOT NULL,
	reg_date TIMESTAMP NOT NULL DEFAULT NOW(),
	PRIMARY KEY(id)
);

CREATE TABLE aggregation(
	id INT NOT NULL AUTO_INCREMENT,
	lotto_round INT NOT NULL UNIQUE,
	cnt_first INT NOT NULL,
	cnt_second INT NOT NULL,
	cnt_third INT NOT NULL,
	cnt_fourth INT NOT NULL,
	cnt_fifth INT NOT NULL,
	cnt_none INT NOT NULL,
	prize_money_sum BIGINT NOT NULL,
	reg_date TIMESTAMP NOT NULL DEFAULT NOW(),
	PRIMARY KEY(id)
);

CREATE TABLE lotto_aggregated(
	id INT NOT NULL AUTO_INCREMENT,
	lotto_id INT NOT NULL,
	aggregation_id INT NULL,
	reg_date TIMESTAMP NOT NULL DEFAULT NOW(),
	PRIMARY KEY(id),
	FOREIGN KEY(lotto_id) REFERENCES lotto(id) ON DELETE CASCADE,
	FOREIGN KEY(aggregation_id) REFERENCES aggregation(id) ON DELETE CASCADE
);

CREATE TABLE winning_lotto_aggregated(
	id INT NOT NULL AUTO_INCREMENT,
	winning_lotto_id INT NOT NULL,
	aggregation_id INT NOT NULL,
	reg_date TIMESTAMP NOT NULL DEFAULT NOW(),
	PRIMARY KEY(id),
	FOREIGN KEY(winning_lotto_id) REFERENCES winning_lotto(id) ON DELETE CASCADE,
	FOREIGN KEY(aggregation_id) REFERENCES aggregation(id) ON DELETE CASCADE
);