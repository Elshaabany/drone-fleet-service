-- MySQL Workbench Forward Engineering

-- Set variables
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- Create schema
CREATE SCHEMA IF NOT EXISTS `fleet`;
USE `fleet`;

-- Table `Load`
DROP TABLE IF EXISTS `Load`;

CREATE TABLE IF NOT EXISTS `Load` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `weight` DECIMAL(5,2) NOT NULL DEFAULT 0 CHECK (`weight` > 0),
  `status` ENUM('PENDING', 'ASSIGNED', 'REJECTED', 'DELIVERED') NOT NULL DEFAULT 'PENDING',
  `message` VARCHAR(255) NULL, 
  `Drone_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Load_Drone`
    FOREIGN KEY (`Drone_id`)
    REFERENCES `fleet`.`Drone` (`id`)
) ENGINE = InnoDB;

CREATE INDEX `fk_Load_Drone_idx` ON `fleet`.`Load` (`Drone_id` ASC) VISIBLE;


-- Create the Drone_Model table
DROP TABLE IF EXISTS `Drone_Model`;

CREATE TABLE IF NOT EXISTS `fleet`.`Drone_Model` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `model` VARCHAR(50) NOT NULL,
  `max_weight` DECIMAL(5,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

-- Insert data into the Drone_Model table
INSERT INTO `Drone_Model` (`model`, `max_weight`)
VALUES
  ('LightWeight', 200),
  ('MiddleWeight', 300),
  ('CruiserWeight', 400),
  ('HeavyWeight', 500);

-- Table `Drone`
DROP TABLE IF EXISTS `Drone`;

CREATE TABLE IF NOT EXISTS `fleet`.`Drone` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `serial_number` VARCHAR(100) NOT NULL,
  `battery_capacity` INT NOT NULL DEFAULT 0 CHECK (`battery_capacity` >= 0 AND `battery_capacity` <= 100),
  `status` ENUM('IDLE', 'LOADING', 'LOADED', 'DELIVERING', 'DELIVERED', 'RETURNING') NOT NULL DEFAULT 'IDLE',
  `Drone_Model_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Drone_Drone_Model`
    FOREIGN KEY (`Drone_Model_id`)
    REFERENCES `fleet`.`Drone_Model` (`id`)
) ENGINE = InnoDB;

CREATE INDEX `fk_Drone_Drone_Model_idx` ON `fleet`.`Drone` (`Drone_Model_id` ASC) VISIBLE;
CREATE UNIQUE INDEX `serial_number_UNIQUE` ON `fleet`.`Drone` (`serial_number` ASC) VISIBLE;

-- Table `Medication`
DROP TABLE IF EXISTS `Medication`;

CREATE TABLE IF NOT EXISTS `Medication` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(50) NOT NULL DEFAULT '' CHECK (`code` REGEXP '^[A-Z0-9_]+$'),
  `name` VARCHAR(100) NOT NULL DEFAULT '' CHECK (`name` REGEXP '^[A-Za-z0-9_-]+$'),
  `weight` DECIMAL(5,2) NOT NULL DEFAULT 0 CHECK (`weight` > 0),
  `image_url` VARCHAR(255) NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE UNIQUE INDEX `code_UNIQUE` ON `fleet`.`Medication` (`code` ASC) VISIBLE;

-- Table `Battery_History`
DROP TABLE IF EXISTS `Battery_History`;

CREATE TABLE IF NOT EXISTS `Battery_History` (
  `Drone_id` INT NOT NULL,
  `recorded_at` TIMESTAMP(3) NOT NULL,
  `remaining_capacity` INT NOT NULL DEFAULT 0 CHECK (`remaining_capacity` >= 0 AND `remaining_capacity` <= 100),
  PRIMARY KEY (`Drone_id`, `recorded_at`),
  CONSTRAINT `fk_Battery_History_Drone`
    FOREIGN KEY (`Drone_id`)
    REFERENCES `fleet`.`Drone` (`id`)
) ENGINE = InnoDB;

CREATE INDEX `fk_Battery_History_Drone_idx` ON `fleet`.`Battery_History` (`Drone_id` ASC) VISIBLE;

-- Table `Load_has_Medication`
DROP TABLE IF EXISTS `Load_has_Medication`;

CREATE TABLE IF NOT EXISTS `Load_has_Medication` (
  `Load_id` INT NOT NULL,
  `Medication_id` INT NOT NULL,
  PRIMARY KEY (`Load_id`, `Medication_id`),
  CONSTRAINT `fk_Load_has_Medication_Load_id`
    FOREIGN KEY (`Load_id`)
    REFERENCES `fleet`.`Load` (`id`),
  CONSTRAINT `fk_Load_has_Medication_Medication_id`
    FOREIGN KEY (`Medication_id`)
    REFERENCES `fleet`.`Medication` (`id`)
) ENGINE = InnoDB;

CREATE INDEX `fk_Load_has_Medication_Medication_id_idx` ON `fleet`.`Load_has_Medication` (`Medication_id` ASC) VISIBLE;
CREATE INDEX `fk_Load_has_Medication_Load_id_idx` ON `fleet`.`Load_has_Medication` (`Load_id` ASC) VISIBLE;

-- Reset variables
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
