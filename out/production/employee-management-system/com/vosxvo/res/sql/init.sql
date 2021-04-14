-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
-- Generation Time: Apr 10, 2021 at 03:21 PM

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vo`
--
CREATE DATABASE IF NOT EXISTS `vo` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `vo`;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
                         `id` int NOT NULL,
                         `username` varchar(255) NOT NULL,
                         `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Truncate table before insert `users`
--

TRUNCATE TABLE `users`;
-- --------------------------------------------------------

--
-- Table structure for table `vo_departments`
--

DROP TABLE IF EXISTS `vo_departments`;
CREATE TABLE `vo_departments` (
                                  `dep_no` int NOT NULL,
                                  `dep_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Truncate table before insert `vo_departments`
--

TRUNCATE TABLE `vo_departments`;
-- --------------------------------------------------------

--
-- Table structure for table `vo_employee`
--

DROP TABLE IF EXISTS `vo_employee`;
CREATE TABLE `vo_employee` (
                               `id` int NOT NULL,
                               `first_name` varchar(255) DEFAULT NULL,
                               `last_name` varchar(255) DEFAULT NULL,
                               `birthday` date DEFAULT NULL,
                               `gender` varchar(5) DEFAULT NULL,
                               `hire_date` date DEFAULT NULL,
                               `department` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Truncate table before insert `vo_employee`
--

TRUNCATE TABLE `vo_employee`;
-- --------------------------------------------------------

--
-- Table structure for table `vo_manager`
--

DROP TABLE IF EXISTS `vo_manager`;
CREATE TABLE `vo_manager` (
                              `id` int NOT NULL,
                              `dep_no` int NOT NULL,
                              `from_date` date DEFAULT NULL,
                              `to_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Truncate table before insert `vo_manager`
--

TRUNCATE TABLE `vo_manager`;
-- --------------------------------------------------------

--
-- Table structure for table `vo_salaries`
--

DROP TABLE IF EXISTS `vo_salaries`;
CREATE TABLE `vo_salaries` (
                               `id` int NOT NULL,
                               `salary` int DEFAULT NULL,
                               `from_date` date DEFAULT NULL,
                               `to_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Truncate table before insert `vo_salaries`
--

TRUNCATE TABLE `vo_salaries`;
--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
    ADD PRIMARY KEY (`username`),
  ADD KEY `usr_fk` (`id`);

--
-- Indexes for table `vo_departments`
--
ALTER TABLE `vo_departments`
    ADD PRIMARY KEY (`dep_no`);

--
-- Indexes for table `vo_employee`
--
ALTER TABLE `vo_employee`
    ADD PRIMARY KEY (`id`),
  ADD KEY `emp_fk2` (`department`);

--
-- Indexes for table `vo_manager`
--
ALTER TABLE `vo_manager`
    ADD PRIMARY KEY (`id`,`dep_no`),
  ADD KEY `mg_fk2` (`dep_no`);

--
-- Indexes for table `vo_salaries`
--
ALTER TABLE `vo_salaries`
    ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
    MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `vo_departments`
--
ALTER TABLE `vo_departments`
    MODIFY `dep_no` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `vo_employee`
--
ALTER TABLE `vo_employee`
    MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `users`
--
ALTER TABLE `users`
    ADD CONSTRAINT `usr_fk` FOREIGN KEY (`id`) REFERENCES `vo_employee` (`id`);

--
-- Constraints for table `vo_employee`
--
ALTER TABLE `vo_employee`
    ADD CONSTRAINT `emp_fk2` FOREIGN KEY (`department`) REFERENCES `vo_departments` (`dep_no`);

--
-- Constraints for table `vo_manager`
--
ALTER TABLE `vo_manager`
    ADD CONSTRAINT `mg_fk` FOREIGN KEY (`id`) REFERENCES `vo_employee` (`id`),
  ADD CONSTRAINT `mg_fk2` FOREIGN KEY (`dep_no`) REFERENCES `vo_departments` (`dep_no`);

--
-- Constraints for table `vo_salaries`
--
ALTER TABLE `vo_salaries`
    ADD CONSTRAINT `sal_fk` FOREIGN KEY (`id`) REFERENCES `vo_employee` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;