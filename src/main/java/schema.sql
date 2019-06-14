CREATE TABLE `lotto`.`round`
(
    `round` INT NOT NULL,
    PRIMARY KEY (`round`)
);

CREATE TABLE `lotto`.`lotto`
(
    `lotto_id` INT NOT NULL AUTO_INCREMENT,
    `num1`     INT NOT NULL,
    `num2`     INT NOT NULL,
    `num3`     INT NOT NULL,
    `num4`     INT NOT NULL,
    `num5`     INT NOT NULL,
    `num6`     INT NOT NULL,
    `round`    INT NOT NULL,
    PRIMARY KEY (`lotto_id`)
);

ALTER TABLE `lotto`.`lotto`
    ADD CONSTRAINT `round`
        FOREIGN KEY (`round`)
            REFERENCES `lotto`.`round` (`round`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION;


CREATE TABLE `lotto`.`result`
(
    `result_id` INT        NOT NULL AUTO_INCREMENT,
    `first`     INT        NOT NULL,
    `second`    INT        NOT NULL,
    `third`     INT        NOT NULL,
    `fourth`    INT        NOT NULL,
    `fifth`     INT        NOT NULL,
    `miss`      INT        NOT NULL,
    `yield`     DOUBLE     NOT NULL,
    `win_prize` BIGINT(11) NOT NULL,
    `round`     INT        NOT NULL,
    PRIMARY KEY (`result_id`)
);

ALTER TABLE `lotto`.`result`
    ADD CONSTRAINT `result_round`
        FOREIGN KEY (`round`)
            REFERENCES `lotto`.`round` (`round`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION;


CREATE TABLE `lotto`.`winning_lotto`
(
    `winning_lotto_id` INT NOT NULL AUTO_INCREMENT,
    `num1`             INT NOT NULL,
    `num2`             INT NOT NULL,
    `num3`             INT NOT NULL,
    `num4`             INT NOT NULL,
    `num5`             INT NOT NULL,
    `num6`             INT NOT NULL,
    `bonus`            INT NOT NULL,
    `round`            INT NOT NULL,
    PRIMARY KEY (`winning_lotto_id`)
);

ALTER TABLE `lotto`.`winning_lotto`
    ADD CONSTRAINT `winning_round`
        FOREIGN KEY (`round`)
            REFERENCES `lotto`.`round` (`round`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION;
