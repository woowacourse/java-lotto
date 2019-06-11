CREATE TABLE winning_numbers (
        round INT UNSIGNED NOT NULL PRIMARY KEY,
        first TINYINT NOT NULL,
        second TINYINT NOT NULL,
        third TINYINT NOT NULL,
        fourth TINYINT NOT NULL,
        fifth TINYINT NOT NULL,
        sixth TINYINT NOT NULL,
        bonus TINYINT NOT NULL
);