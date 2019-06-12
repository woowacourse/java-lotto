-- round Table Create SQL
CREATE TABLE round
(
    `id`             INT       NOT NULL    AUTO_INCREMENT, 
    `prize`          INT       NOT NULL, 
    `interest_rate`  DOUBLE    NOT NULL, 
    PRIMARY KEY (id)
);

ALTER TABLE round;


-- result Table Create SQL
CREATE TABLE result
(
    `id`      INT    NOT NULL    AUTO_INCREMENT, 
    `ro_id`   INT    NOT NULL, 
    `miss`    INT    NOT NULL, 
    `fifth`   INT    NOT NULL, 
    `fourth`  INT    NOT NULL, 
    `third`   INT    NOT NULL, 
    `second`  INT    NOT NULL, 
    `first`   INT    NOT NULL, 
    PRIMARY KEY (id)
);

ALTER TABLE result COMMENT '당첨 결과';

ALTER TABLE result
    ADD CONSTRAINT FK_result_ro_id_round_id FOREIGN KEY (ro_id)
        REFERENCES round (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- winningLotto Table Create SQL
CREATE TABLE winningLotto
(
    `id`     INT    NOT NULL    AUTO_INCREMENT, 
    `ro_id`  INT    NOT NULL, 
    `no1`    INT    NOT NULL, 
    `no2`    INT    NOT NULL, 
    `no3`    INT    NOT NULL, 
    `no4`    INT    NOT NULL, 
    `no5`    INT    NOT NULL, 
    `no6`    INT    NOT NULL, 
    `bonus`  INT    NOT NULL, 
    PRIMARY KEY (id)
);

ALTER TABLE winningLotto;

ALTER TABLE winningLotto
    ADD CONSTRAINT FK_winningLotto_ro_id_round_id FOREIGN KEY (ro_id)
        REFERENCES round (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- lotto Table Create SQL
CREATE TABLE lotto
(
    `id`     INT    NOT NULL    AUTO_INCREMENT, 
    `ro_id`  INT    NOT NULL, 
    `no1`    INT    NOT NULL, 
    `no2`    INT    NOT NULL, 
    `no3`    INT    NOT NULL, 
    `no4`    INT    NOT NULL, 
    `no5`    INT    NOT NULL, 
    `no6`    INT    NOT NULL, 
    PRIMARY KEY (id)
);

ALTER TABLE lotto;

ALTER TABLE lotto
    ADD CONSTRAINT FK_lotto_ro_id_round_id FOREIGN KEY (ro_id)
        REFERENCES round (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


