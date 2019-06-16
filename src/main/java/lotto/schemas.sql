CREATE DATABASE lotto DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE winningLotto (
    round int NOT NULL PRIMARY KEY,
    numbers VARCHAR(64) NOT NULL,
    bonus VARCHAR(64) NOT NULL
);

CREATE TABLE lotto (
    lotto_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    round int NOT NULL,
    numbers text(64) NOT NULL,
    FOREIGN KEY (round) REFERENCES winningLotto (round)
);

CREATE TABLE statistics (
    round int NOT NULL PRIMARY KEY,
    result VARCHAR(256) NOT NULL,
    return_of_rate VARCHAR(64) NOT NULL,
    FOREIGN KEY (round) REFERENCES winningLotto (round)
);