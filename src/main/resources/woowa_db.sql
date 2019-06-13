CREATE TABLE `winning_lotto` (
  `round` int(100) NOT NULL AUTO_INCREMENT,
  `first_number` int(10) NOT NULL,
  `second_number` int(10) NOT NULL,
  `third_number` int(10) NOT NULL,
  `fourth_number` int(10) NOT NULL,
  `fifth_number` int(10) NOT NULL,
  `sixth_number` int(10) NOT NULL,
  `bonus_ball` int(10) NOT NULL,
  PRIMARY KEY (`round`)
);

CREATE TABLE `user_lotto` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `first_number` int(10) NOT NULL,
  `second_number` int(10) NOT NULL,
  `third_number` int(10) NOT NULL,
  `fourth_number` int(10) NOT NULL,
  `fifth_number` int(10) NOT NULL,
  `sixth_number` int(10) NOT NULL,
  `winning_lotto_fk` int(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `winning_lotto_and_user_lotto` (`winning_lotto_fk`),
  CONSTRAINT `winning_lotto_and_user_lotto` FOREIGN KEY (`winning_lotto_fk`) REFERENCES `winning_lotto` (`round`)
);

CREATE TABLE `lotto_result` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `first_rank` int(100) DEFAULT NULL,
  `second_rank` int(100) DEFAULT NULL,
  `third_rank` int(100) DEFAULT NULL,
  `fourth_rank` int(100) DEFAULT NULL,
  `fifth_rank` int(100) DEFAULT NULL,
  `winning_lotto_fk` int(100) DEFAULT NULL,
  `sum` bigint(100) DEFAULT NULL,
  `earning_rate` bigint(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `winning_lotto_and_lotto_result` (`winning_lotto_fk`),
  CONSTRAINT `winning_lotto_and_lotto_result` FOREIGN KEY (`winning_lotto_fk`) REFERENCES `winning_lotto` (`round`)
);
