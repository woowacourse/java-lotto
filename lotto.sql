CREATE TABLE purchased_lotto (
	round INTEGER(11) UNSIGNED NOT NULL,
	purchased_lotto_no INTEGER(11) UNSIGNED,
    first_num TINYINT(1) UNSIGNED NOT NULL,
    second_num TINYINT(1) UNSIGNED NOT NULL,
    third_num TINYINT(1) UNSIGNED NOT NULL,
    fourth_num TINYINT(1) UNSIGNED NOT NULL,
    fifth_num TINYINT(1) UNSIGNED NOT NULL,
    sixth_num TINYINT(1) UNSIGNED NOT NULL,
    FOREIGN KEY (round) REFERENCES lotto_result (round),
    PRIMARY KEY (round, purchased_lotto_no)
);

CREATE TABLE winning_lotto (
	round INTEGER(11) UNSIGNED PRIMARY KEY,
	first_num TINYINT(1) UNSIGNED NOT NULL,
    second_num TINYINT(1) UNSIGNED NOT NULL,
    third_num TINYINT(1) UNSIGNED NOT NULL,
    fourth_num TINYINT(1) UNSIGNED NOT NULL,
    fifth_num TINYINT(1) UNSIGNED NOT NULL,
    sixth_num TINYINT(1) UNSIGNED NOT NULL,
    bonus_ball TINYINT(1) UNSIGNED NOT NULL,
    FOREIGN KEY (round) REFERENCES lotto_result (round)
);

CREATE TABLE lotto_result (
	round INTEGER(11) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	counts_of_first_rank TINYINT(1) UNSIGNED DEFAULT(0),
    counts_of_second_rank TINYINT(1) UNSIGNED DEFAULT(0),
    counts_of_third_rank TINYINT(1) UNSIGNED DEFAULT(0),
    counts_of_fourth_rank TINYINT(1) UNSIGNED DEFAULT(0),
    counts_of_fifth_rank TINYINT(1) UNSIGNED DEFAULT(0),
    counts_of_sixth_rank TINYINT(1) UNSIGNED DEFAULT(0),
    profit_ratio float UNSIGNED DEFAULT(0)
);