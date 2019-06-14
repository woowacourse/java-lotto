CREATE TABLE lotto (
	round INT NOT NULL,
    number_1 TINYINT NOT NULL,
    number_2 TINYINT NOT NULL,
    number_3 TINYINT NOT NULL,
    number_4 TINYINT NOT NULL,
    number_5 TINYINT NOT NULL,
    number_6 TINYINT NOT NULL
);

CREATE TABLE winninglotto (
	round INT NOT NULL,
    number_1 TINYINT NOT NULL,
    number_2 TINYINT NOT NULL,
    number_3 TINYINT NOT NULL,
    number_4 TINYINT NOT NULL,
    number_5 TINYINT NOT NULL,
    number_6 TINYINT NOT NULL,
    bonus TINYINT NOT NULL,
    PRIMARY KEY (round)
);

CREATE TABLE result (
	round INT NOT NULL,
    first INT,
    second INT,
    third INT,
    fourth INT,
    fifth INT,
    miss INT,
    PRIMARY KEY (round)
);