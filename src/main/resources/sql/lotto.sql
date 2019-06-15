DROP TABLE IF EXISTS lotto_ticket CASCADE;
DROP TABLE IF EXISTS lotto_result CASCADE;
DROP TABLE IF EXISTS winning_lotto CASCADE;
DROP TABLE IF EXISTS payment_info CASCADE;
DROP TABLE IF EXISTS user CASCADE;

CREATE TABLE IF NOT EXISTS user(
	id INT(10) AUTO_INCREMENT,
    name VARCHAR(20),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS payment_info(
	round INT(10) AUTO_INCREMENT,
    payment INT(10) NOT NULL,
    manual INT(7) NOT NULL,
    auto INT(7) NOT NULL,
    user_id INT(10) NOT NULL,
    PRIMARY KEY (round),
    FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS lotto_ticket(
	id INT(10) AUTO_INCREMENT,
    lotto VARCHAR(30) NOT NULL,
    is_auto BOOLEAN NOT NULL,
    round INT(10) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (round) REFERENCES payment_info (round)
);

CREATE TABLE IF NOT EXISTS winning_lotto(
	round INT(10),
    winning_lotto VARCHAR(30) NOT NULL,
    bonus_ball VARCHAR(2) NOT NULL,    
    PRIMARY KEY (round),
    FOREIGN KEY (round) REFERENCES payment_info (round)
);

CREATE TABLE IF NOT EXISTS lotto_result(
	id INT(10) AUTO_INCREMENT,
    first INT(5) DEFAULT 0,
    second INT(5) DEFAULT 0,
    third INT(5) DEFAULT 0,
    fourth INT(5) DEFAULT 0,
    fifth INT(5) DEFAULT 0,
    miss INT(5) DEFAULT 0,
    round INT(10) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (round) REFERENCES payment_info (round)
);

---------------
-- Dummy --
---------------
-- User
INSERT INTO user(name) VALUES('김고래');
INSERT INTO user(name) VALUES('whale');

-- Payment_info
INSERT INTO payment_info(payment, manual, auto, user_id) VALUES(5000, 1, 4, 1);
INSERT INTO payment_info(payment, manual, auto, user_id) VALUES(1000, 1, 0, 1);
INSERT INTO payment_info(payment, manual, auto, user_id) VALUES(1000, 1, 0, 2);

-- Lotto_ticket
INSERT INTO lotto_ticket(lotto, is_auto, round) VALUES('1,2,3,4,5,6', 0, 1);
INSERT INTO lotto_ticket(lotto, is_auto, round) VALUES('1,2,3,4,5,7', 1, 1);
INSERT INTO lotto_ticket(lotto, is_auto, round) VALUES('1,2,3,4,5,8', 1, 1);
INSERT INTO lotto_ticket(lotto, is_auto, round) VALUES('1,12,23,34,35,45', 1, 1);
INSERT INTO lotto_ticket(lotto, is_auto, round) VALUES('10,20,30,40,43,45', 1, 1);
INSERT INTO lotto_ticket(lotto, is_auto, round) VALUES('1,3,5,7,9,11', 1, 2);
INSERT INTO lotto_ticket(lotto, is_auto, round) VALUES('1,3,5,7,9,11', 1, 3);

-- Winning_lotto
INSERT INTO winning_lotto(round, winning_lotto, bonus_ball) VALUES(1, '1,2,3,4,5,6', '7');
INSERT INTO winning_lotto(round, winning_lotto, bonus_ball) VALUES(2, '1,2,3,4,5,6', '7');
INSERT INTO winning_lotto(round, winning_lotto, bonus_ball) VALUES(3, '1,2,3,4,5,6', '7');

-- Lotto_result
INSERT INTO lotto_result (first, second, third, fourth, fifth, miss, round) VALUES(1, 1, 1, 0, 0, 2, 1);
INSERT INTO lotto_result (first, second, third, fourth, fifth, miss, round) VALUES(0, 0, 0, 0, 1, 0, 2);
INSERT INTO lotto_result (first, second, third, fourth, fifth, miss, round) VALUES(0, 0, 0, 0, 1, 0, 3);

-----------------
-- test code --
-----------------
-- 전체 데이터 조회
SELECT payment_info.round, name, first, second, third, fourth, fifth, miss, payment
FROM user
INNER JOIN payment_info ON payment_info.user_id = user.id
INNER JOIN lotto_result ON lotto_result.round = payment_info.round
LIMIT 10;

-- round로 Lotto result 조회
SELECT payment_info.round, name, first, second, third, fourth, fifth, miss, payment
FROM user
INNER JOIN payment_info ON payment_info.user_id = user.id
INNER JOIN lotto_result ON lotto_result.round = payment_info.round
WHERE lotto_result.round = 3;

-- 유저 등록 (없을 경우)
INSERT INTO user (name) 
  SELECT 'pobi' FROM DUAL
WHERE NOT EXISTS 
  (SELECT name FROM user WHERE name = 'pobi');

-- 이름에 일치하는 id를 찾아 payment 입력
INSERT INTO payment_info(payment, user_id, manual, auto) VALUES(5000, 1, 1, (SELECT id
FROM user
WHERE name = '김고래'));

-- 로또 입력
INSERT INTO lotto_ticket(lotto, is_auto, round) VALUES(?, ?, ?);

SELECT *
FROM payment_info
WHERE round = (SELECT last_insert_id());



SELECT * FROM user;
select * from payment_info;
select * from lotto_ticket;
SELECT * FROM lotto_result;
select * from winning_lotto;

select * from lotto_ticket
WHERE round = 1;

SELECT winning_lotto, bonus_ball
FROM winning_lotto
WHERE round = 10;

SELECT name, SUM(payment) as sum
FROM user
INNER JOIN payment_info ON payment_info.user_id = user.id
GROUP BY name
ORDER BY sum DESC;
