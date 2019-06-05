CREATE TABLE round (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id)
);

CREATE TABLE lotto (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  numbers VARCHAR(30) NOT NULL,
  round int NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE winning_lotto (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  numbers VARCHAR(30) NOT NULL,
  bonus_no INT NOT NULL,
  round int NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE result (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    round INT NOT NULL,
    first INT DEFAULT 0,
    second  INT DEFAULT 0,
    third   INT DEFAULT 0,
    fourth   INT DEFAULT 0,
    fifth   INT DEFAULT 0,
    miss   INT DEFAULT 0,
    PRIMARY KEY (id)
);

CREATE TABLE win_prize (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    round INT NOT NULL,
    total_prize BIGINT NOT NULL,
    rate_of_profit FLOAT NOT NULL,
    PRIMARY KEY (id)
);