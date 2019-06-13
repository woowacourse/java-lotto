DROP TABLE IF EXISTS payment_info CASCADE;
DROP TABLE IF EXISTS lotto_ticket CASCADE;
DROP TABLE IF EXISTS lotto_result CASCADE;
DROP TABLE IF EXISTS winning_lotto CASCADE;
DROP TABLE IF EXISTS round CASCADE;
DROP TABLE IF EXISTS user CASCADE;

CREATE TABLE IF NOT EXISTS user(
	id INT(10) AUTO_INCREMENT,
    name VARCHAR(20),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS payment_info(
	id INT(10) AUTO_INCREMENT,
    payment INT(10) NOT NULL,
    user_id INT(10) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS round(
	id INT(10) AUTO_INCREMENT,
    manual INT(7) NOT NULL,
    auto INT(7) NOT NULL,
    user_id INT(10) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS lotto_ticket(
	id INT(10) AUTO_INCREMENT,
    lotto VARCHAR(20) NOT NULL,
    is_auto BOOLEAN NOT NULL,
    round_id INT(10) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (round_id) REFERENCES round (id)
);

CREATE TABLE IF NOT EXISTS winning_lotto(
	round_id INT(10),
    winning_lotto VARCHAR(20) NOT NULL,
    bonus_ball VARCHAR(2) NOT NULL,    
    PRIMARY KEY (round_id),
    FOREIGN KEY (round_id) REFERENCES round (id)
);

CREATE TABLE IF NOT EXISTS lotto_result(
	id INT(10) AUTO_INCREMENT,
    first INT(5) DEFAULT 0,
    second INT(5) DEFAULT 0,
    third INT(5) DEFAULT 0,
    fourth INT(5) DEFAULT 0,
    fifth INT(5) DEFAULT 0,
    miss INT(5) DEFAULT 0,
    round_id INT(10) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (round_id) REFERENCES round (id)
);

---------------
-- Dummy --
---------------
-- User
INSERT INTO user(name) VALUES('김고래');
INSERT INTO user(name) VALUES('whale');

-- Payment_info
INSERT INTO payment_info(payment, user_id) VALUES(5000, 1);
INSERT INTO payment_info(payment, user_id) VALUES(1000, 1);
INSERT INTO payment_info(payment, user_id) VALUES(1000, 2);

-- Round
INSERT INTO round(manual, auto, user_id) VALUES(1, 4, 1);
INSERT INTO round(manual, auto, user_id) VALUES(1, 0, 1);
INSERT INTO round(manual, auto, user_id) VALUES(1, 0, 2);

-- Lotto_ticket
INSERT INTO lotto_ticket(lotto, is_auto, round_id) VALUES('1,2,3,4,5,6', 0, 1);
INSERT INTO lotto_ticket(lotto, is_auto, round_id) VALUES('1,2,3,4,5,7', 1, 1);
INSERT INTO lotto_ticket(lotto, is_auto, round_id) VALUES('1,2,3,4,5,8', 1, 1);
INSERT INTO lotto_ticket(lotto, is_auto, round_id) VALUES('1,12,23,34,35,45', 1, 1);
INSERT INTO lotto_ticket(lotto, is_auto, round_id) VALUES('10,20,30,40,43,45', 1, 1);
INSERT INTO lotto_ticket(lotto, is_auto, round_id) VALUES('1,3,5,7,9,11', 1, 2);
INSERT INTO lotto_ticket(lotto, is_auto, round_id) VALUES('1,3,5,7,9,11', 1, 3);

-- Winning_lotto
INSERT INTO winning_lotto(round_id, winning_lotto, bonus_ball) VALUES(1, '1,2,3,4,5,6', '7');
INSERT INTO winning_lotto(round_id, winning_lotto, bonus_ball) VALUES(2, '1,2,3,4,5,6', '7');
INSERT INTO winning_lotto(round_id, winning_lotto, bonus_ball) VALUES(3, '1,2,3,4,5,6', '7');

-- Lotto_result
INSERT INTO lotto_result (first, second, third, fourth, fifth, miss, round_id) VALUES(1, 1, 1, 0, 0, 2, 1);
INSERT INTO lotto_result (first, second, third, fourth, fifth, miss, round_id) VALUES(0, 0, 0, 0, 1, 0, 2);
INSERT INTO lotto_result (first, second, third, fourth, fifth, miss, round_id) VALUES(0, 0, 0, 0, 1, 0, 3);

-----------------
-- test code --
-----------------
-- 전체 데이터 조회
SELECT round.id, name, first, second, third, fourth, fifth, miss, payment
FROM user
INNER JOIN round ON round.user_id = user.id
INNER JOIN payment_info ON payment_info.user_id = user.id
INNER JOIN lotto_result ON lotto_result.round_id = round.id
GROUP BY round.id
LIMIT 10;

