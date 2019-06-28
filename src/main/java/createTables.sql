CREATE TABLE issued_lotto (
    lotto_id TINYINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    lotto_numbers VARCHAR(50) NOT NULL,
    trial_number TINYINT UNSIGNED NOT NULL
)

CREATE TABLE statistics (
    trial_number TINYINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    winning_numbers VARCHAR(50) NOT NULL,
    bonus_number TINYINT UNSIGNED NOT NULL,
    counts_of_first TINYINT UNSIGNED NOT NULL,
    counts_of_second TINYINT UNSIGNED NOT NULL,
    counts_of_third TINYINT UNSIGNED NOT NULL,
    counts_of_fourth TINYINT UNSIGNED NOT NULL,
    counts_of_fifth TINYINT UNSIGNED NOT NULL,
    counts_of_miss TINYINT UNSIGNED NOT NULL,
    earning_rates FLOAT UNSIGNED NOT NULL
)
