-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema phonebook
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `phonebook` ;

-- -----------------------------------------------------
-- Schema phonebook
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `phonebook` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `phonebook` ;

-- -----------------------------------------------------
-- Table `phonebook`.`entries`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`entries` ;

CREATE TABLE IF NOT EXISTS `phonebook`.`entries` (
  `id` BIGINT(45) NULL AUTO_INCREMENT COMMENT '',
  `secondName` VARCHAR(255) NOT NULL COMMENT '',
  `firstName` VARCHAR(255) NOT NULL COMMENT '',
  `patronymic` VARCHAR(255) NOT NULL COMMENT '',
  `mobile` VARCHAR(255) NOT NULL COMMENT '',
  `tel` VARCHAR(255) NULL COMMENT '',
  `address` VARCHAR(255) NULL COMMENT '',
  `email` VARCHAR(255) NULL COMMENT '',
  `username` VARCHAR(255) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `phonebook`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phonebook`.`users` ;

CREATE TABLE IF NOT EXISTS `phonebook`.`users` (
  `username` VARCHAR(255) NOT NULL COMMENT '',
  `password` VARCHAR(255) NOT NULL COMMENT '',
  `email` VARCHAR(255) NOT NULL COMMENT '',
  `fullname` VARCHAR(255) NOT NULL COMMENT '',
  `enabled` TINYINT(1) NOT NULL COMMENT '',
  `authority` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL COMMENT '',
  PRIMARY KEY (`username`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
