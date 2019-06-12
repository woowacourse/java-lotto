USE JAVA_LOTTO;

-- result Table Create SQL
CREATE TABLE result
(
    `id`      INT    NOT NULL    AUTO_INCREMENT COMMENT '기본키', 
    `miss`    INT    NOT NULL    COMMENT '꽝 횟수', 
    `fifth`   INT    NOT NULL    COMMENT '5등 횟수', 
    `fourth`  INT    NOT NULL    COMMENT '4등 횟수', 
    `third`   INT    NOT NULL    COMMENT '3등 횟수', 
    `second`  INT    NOT NULL    COMMENT '2등 횟수', 
    `first`   INT    NOT NULL    COMMENT '1등 횟수', 
    PRIMARY KEY (id)
);

ALTER TABLE result COMMENT '당첨 결과';


-- winningLotto Table Create SQL
CREATE TABLE winningLotto
(
    `id`     INT    NOT NULL    AUTO_INCREMENT COMMENT '기본키', 
    `no1`    INT    NOT NULL    COMMENT '첫번째 번호', 
    `no2`    INT    NOT NULL    COMMENT '두번째 번호', 
    `no3`    INT    NOT NULL    COMMENT '세번째 번호', 
    `no4`    INT    NOT NULL    COMMENT '네번째 번호', 
    `no5`    INT    NOT NULL    COMMENT '다섯번째 번호', 
    `no6`    INT    NOT NULL    COMMENT '여섯번째 번호', 
    `bonus`  INT    NOT NULL    COMMENT '보너스 번호', 
    PRIMARY KEY (id)
);

ALTER TABLE winningLotto COMMENT '당첨 로또';


-- round Table Create SQL
CREATE TABLE round
(
    `id`             INT       NOT NULL    AUTO_INCREMENT COMMENT '기본키', 
    `wid`            INT       NOT NULL    COMMENT '당첨 로또를 가리키는 외래키', 
    `re_id`          INT       NOT NULL    COMMENT '결과를 가리키는 외래키', 
    `prize`          INT       NOT NULL    COMMENT '상금', 
    `interest_rate`  DOUBLE    NOT NULL    COMMENT '수익률', 
    PRIMARY KEY (id)
);

ALTER TABLE round COMMENT '로또의 한 회차';

ALTER TABLE round
    ADD CONSTRAINT FK_round_re_id_result_id FOREIGN KEY (re_id)
        REFERENCES result (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE round
    ADD CONSTRAINT FK_round_wid_winningLotto_id FOREIGN KEY (wid)
        REFERENCES winningLotto (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- lotto Table Create SQL
CREATE TABLE lotto
(
    `id`     INT    NOT NULL    AUTO_INCREMENT COMMENT '기본키', 
    `ro_id`  INT    NOT NULL    COMMENT '회차를 가리키는 외래키', 
    `no1`    INT    NOT NULL    COMMENT '첫번째 번호', 
    `no2`    INT    NOT NULL    COMMENT '두번째 번호', 
    `no3`    INT    NOT NULL    COMMENT '세번째 번호', 
    `no4`    INT    NOT NULL    COMMENT '네번째 번호', 
    `no5`    INT    NOT NULL    COMMENT '다섯번째 번호', 
    `no6`    INT    NOT NULL    COMMENT '여섯번째 번호', 

    PRIMARY KEY (id)
);

ALTER TABLE lotto COMMENT '로또';

ALTER TABLE lotto
    ADD CONSTRAINT FK_lotto_ro_id_round_id FOREIGN KEY (ro_id)
        REFERENCES round (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


