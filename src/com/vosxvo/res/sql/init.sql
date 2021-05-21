-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: May 21, 2021 at 06:03 AM
-- Server version: 8.0.24
-- PHP Version: 7.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `employee_management_system`
--
CREATE DATABASE IF NOT EXISTS `employee_management_system` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `employee_management_system`;

-- --------------------------------------------------------

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
CREATE TABLE IF NOT EXISTS `departments` (
                                             `dept_no` int NOT NULL AUTO_INCREMENT,
                                             `dept_name` varchar(128) DEFAULT NULL,
    PRIMARY KEY (`dept_no`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Table structure for table `dept_emp`
--

DROP TABLE IF EXISTS `dept_emp`;
CREATE TABLE IF NOT EXISTS `dept_emp` (
                                          `emp_no` int NOT NULL,
                                          `dept_no` int NOT NULL,
                                          `from_date` date DEFAULT NULL,
                                          `to_date` date DEFAULT NULL,
                                          PRIMARY KEY (`emp_no`,`dept_no`),
    KEY `dept_emp_fk1` (`dept_no`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Table structure for table `dept_manager`
--

DROP TABLE IF EXISTS `dept_manager`;
CREATE TABLE IF NOT EXISTS `dept_manager` (
                                              `dept_no` int NOT NULL,
                                              `emp_no` int NOT NULL,
                                              `from_date` date DEFAULT NULL,
                                              `to_date` date DEFAULT NULL,
                                              PRIMARY KEY (`dept_no`,`emp_no`),
    KEY `dept_manager_fk` (`emp_no`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
CREATE TABLE IF NOT EXISTS `employees` (
                                           `emp_no` int NOT NULL AUTO_INCREMENT,
                                           `first_name` varchar(32) DEFAULT NULL,
    `last_name` varchar(32) DEFAULT NULL,
    `birthday` date DEFAULT NULL,
    `gender` varchar(8) DEFAULT NULL,
    `hire_date` date DEFAULT NULL,
    PRIMARY KEY (`emp_no`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Table structure for table `salaries`
--

DROP TABLE IF EXISTS `salaries`;
CREATE TABLE IF NOT EXISTS `salaries` (
                                          `emp_no` int NOT NULL,
                                          `salary` int DEFAULT NULL,
                                          `from_date` date DEFAULT NULL,
                                          `to_date` date DEFAULT NULL,
                                          PRIMARY KEY (`emp_no`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Table structure for table `titles`
--

DROP TABLE IF EXISTS `titles`;
CREATE TABLE IF NOT EXISTS `titles` (
                                        `emp_no` int NOT NULL,
                                        `title` varchar(64) DEFAULT NULL,
    `from_date` date DEFAULT NULL,
    `to_date` date DEFAULT NULL,
    PRIMARY KEY (`emp_no`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
                                       `emp_no` int NOT NULL,
                                       `username` varchar(255) NOT NULL,
    `password` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`emp_no`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dept_emp`
--
ALTER TABLE `dept_emp`
    ADD CONSTRAINT `dept_emp_fk` FOREIGN KEY (`emp_no`) REFERENCES `employees` (`emp_no`),
  ADD CONSTRAINT `dept_emp_fk1` FOREIGN KEY (`dept_no`) REFERENCES `departments` (`dept_no`);

--
-- Constraints for table `dept_manager`
--
ALTER TABLE `dept_manager`
    ADD CONSTRAINT `dept_manager_fk` FOREIGN KEY (`emp_no`) REFERENCES `employees` (`emp_no`),
  ADD CONSTRAINT `dept_manager_fk1` FOREIGN KEY (`dept_no`) REFERENCES `departments` (`dept_no`);

--
-- Constraints for table `salaries`
--
ALTER TABLE `salaries`
    ADD CONSTRAINT `salaries_fk` FOREIGN KEY (`emp_no`) REFERENCES `employees` (`emp_no`);

--
-- Constraints for table `titles`
--
ALTER TABLE `titles`
    ADD CONSTRAINT `titles_fk` FOREIGN KEY (`emp_no`) REFERENCES `employees` (`emp_no`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
    ADD CONSTRAINT `users_fk` FOREIGN KEY (`emp_no`) REFERENCES `employees` (`emp_no`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;