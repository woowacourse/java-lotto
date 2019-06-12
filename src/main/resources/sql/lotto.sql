CREATE TABLE round (
    round INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(round)
);

CREATE TABLE money (
	id INT NOT NULL AUTO_INCREMENT, 
	round INT NOT NULL,
    money INT NOT NULL,
    available_count INT NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE lotto (
	id INT NOT NULL AUTO_INCREMENT,
    round INT NOT NULL,
    first_num INT NOT NULL,
    second_num INT NOT NULL,
    third_num INT NOT NULL,
    forth_num INT NOT NULL,
    fifth_num INT NOT NULL,
    sixth_num INT NOT NULL,
    primary key(id)
);

CREATE TABLE winning_lotto (
	id INT NOT NULL AUTO_INCREMENT,
	round INT NOT NULL,
    first_num INT NOT NULL,
    second_num INT NOT NULL,
    third_num INT NOT NULL,
    forth_num INT NOT NULL,
    fifth_num INT NOT NULL,
    sixth_num INT NOT NULL,
    bonus_num INT NOT NULL,
    primary key(id)
);

CREATE TABLE result (
	id INT NOT NULL AUTO_INCREMENT,
    round INT NOT NULL,
    first_prize INT NOT NULL,
    second_prize INT NOT NULL,
    third_prize INT NOT NULL,
    forth_prize INT NOT NULL,
    fifth_prize INT NOT NULL,
    income_rate double NOT NULL,
    primary key(id)
);

ALTER TABLE money ADD FOREIGN KEY (round) REFERENCES round(round);
ALTER TABLE lotto ADD FOREIGN KEY (round) REFERENCES round(round);
ALTER TABLE winning_lotto ADD FOREIGN KEY (round) REFERENCES round(round);
ALTER TABLE result ADD FOREIGN KEY (round) REFERENCES round(round);

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS money;
DROP TABLE IF EXISTS lotto;
DROP TABLE IF EXISTS winning_lotto;
DROP TABLE IF EXISTS result;
DROP TABLE IF EXISTS round;