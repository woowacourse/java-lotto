CREATE TABLE round
(
    id_   INT,
    price INT,
    PRIMARY KEY (id_)
);

CREATE TABLE lotto
(
    id_      INT AUTO_INCREMENT,
    num_1    INT,
    num_2    INT,
    num_3    INT,
    num_4    INT,
    num_5    INT,
    num_6    INT,
    round_id INT,
    PRIMARY KEY (id_)
);

CREATE TABLE winninglotto
(
    id_      INT AUTO_INCREMENT,
    win_1    INT,
    win_2    INT,
    win_3    INT,
    win_4    INT,
    win_5    INT,
    win_6    INT,
    bonus    INT,
    round_id INT,
    PRIMARY KEY (id_)
);
