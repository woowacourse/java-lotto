-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema lottodb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema lottodb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lottodb` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema testDatabase
-- -----------------------------------------------------
USE `lottodb` ;

-- -----------------------------------------------------
-- Table `lottodb`.`winningInfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lottodb`.`winningInfo` (
  `winning_number1` INT UNSIGNED NOT NULL,
  `winning_number2` INT UNSIGNED NOT NULL,
  `winning_number3` INT UNSIGNED NOT NULL,
  `winning_number4` INT UNSIGNED NOT NULL,
  `winning_number5` INT UNSIGNED NOT NULL,
  `winning_number6` INT UNSIGNED NOT NULL,
  `bonus` INT UNSIGNED NOT NULL,
  `round` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`round`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lottodb`.`lotto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lottodb`.`lotto` (
  `number1` INT UNSIGNED NOT NULL,
  `number2` INT UNSIGNED NOT NULL,
  `number3` INT UNSIGNED NOT NULL,
  `number4` INT UNSIGNED NOT NULL,
  `number5` INT UNSIGNED NOT NULL,
  `number6` INT UNSIGNED NOT NULL,
  `round` INT UNSIGNED NOT NULL,
  `id_lotto` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_lotto`),
  INDEX `fk_lotto_winningInfo_idx` (`round` ASC) VISIBLE,
  CONSTRAINT `fk_lotto_winningInfo`
    FOREIGN KEY (`round`)
    REFERENCES `lottodb`.`winningInfo` (`round`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
