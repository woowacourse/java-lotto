CREATE DATABASE  IF NOT EXISTS `lotto_db`;
USE `lotto_db`;

SET NAMES utf8 ;

DROP TABLE IF EXISTS `lottos`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `lottos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numbers` varchar(20) NOT NULL,
  `round` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `round`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `round` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `stat`;
SET character_set_client = utf8mb4 ;
CREATE TABLE `stat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first` int(11) NOT NULL,
  `second` int(11) NOT NULL,
  `third` int(11) NOT NULL,
  `fourth` int(11) NOT NULL,
  `fifth` int(11) NOT NULL,
  `miss` int(11) NOT NULL,
  `profit` double NOT NULL,
  `round` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `winning_lotto`;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `winning_lotto` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `numbers` varchar(20) NOT NULL,
  `bonus_number` int(11) NOT NULL,
  `round` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
