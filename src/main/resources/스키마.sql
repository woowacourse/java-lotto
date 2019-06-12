CREATE TABLE winning_numbers (
    round INT UNSIGNED NOT NULL CHECK(round > 0) PRIMARY KEY,
    first TINYINT UNSIGNED NOT NULL CHECK(first >= 1 AND first <=45),
    second TINYINT UNSIGNED NOT NULL CHECK(second >= 1 AND second <=45),
    third TINYINT UNSIGNED NOT NULL CHECK(third >= 1 AND third <=45),
    fourth TINYINT UNSIGNED NOT NULL CHECK(fourth >= 1 AND fourth <=45),
    fifth TINYINT UNSIGNED NOT NULL CHECK(fifth >= 1 AND fifth <=45),
    sixth TINYINT UNSIGNED NOT NULL CHECK(sixth >= 1 AND sixth <=45),
    bonus TINYINT UNSIGNED NOT NULL CHECK(bonus >= 1 AND bonus <=45)
);

CREATE TABLE purchase_history (
    no INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    round INT UNSIGNED NOT NULL CHECK(round > 0),
    lottos LONGTEXT NOT NULL,
    date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3)
);