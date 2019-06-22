CREATE TABLE winning_numbers (
    round INT UNSIGNED NOT NULL CHECK(round > 0) PRIMARY KEY,
    first_num TINYINT UNSIGNED NOT NULL CHECK(first_num >= 1 AND first_num <=45),
    second_num TINYINT UNSIGNED NOT NULL CHECK(second_num >= 1 AND second_num <=45),
    third_num TINYINT UNSIGNED NOT NULL CHECK(third_num >= 1 AND third_num <=45),
    fourth_num TINYINT UNSIGNED NOT NULL CHECK(fourth_num >= 1 AND fourth_num <=45),
    fifth_num TINYINT UNSIGNED NOT NULL CHECK(fifth_num >= 1 AND fifth_num <=45),
    sixth_num TINYINT UNSIGNED NOT NULL CHECK(sixth_num >= 1 AND sixth_num <=45),
    bonus_num TINYINT UNSIGNED NOT NULL CHECK(bonus_num >= 1 AND bonus_num <=45)
);

CREATE TABLE purchase_history (
    no INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    round INT UNSIGNED NOT NULL CHECK(round > 0),
    lottos LONGTEXT NOT NULL,
    date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3)
);