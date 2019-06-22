CREATE DATABASE lotto DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE winningLotto (
    round int NOT NULL PRIMARY KEY,
    numbers VARCHAR(64) NOT NULL,
    bonus VARCHAR(64) NOT NULL
);

CREATE TABLE lotto (
    lotto_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    round int NOT NULL,
    numbers VARCHAR(64) NOT NULL
);

CREATE TABLE statistics (
    round int NOT NULL PRIMARY KEY,
    first_count int NOT NULL,
    second_count int NOT NULL,
    third_count int NOT NULL,
    fourth_count int NOT NULL,
    fifth_count int NOT NULL,
    miss_count int NOT NULL,
    return_of_rate VARCHAR(64) NOT NULL
);