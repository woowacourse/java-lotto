CREATE TABLE IF NOT EXISTS winning_lotto (
	id INT AUTO_INCREMENT primary key NOT NULL,
	lotto_numbers VARCHAR(17) NOT NULL,
	bonus_number TINYINT NOT NULL
);

CREATE TABLE IF NOT EXISTS lottos (
	round INT NOT NULL,
	lotto_numbers VARCHAR(17) NOT NULL,
	id INT AUTO_INCREMENT primary key NOT NULL
);