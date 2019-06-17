-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 17, 2019 at 11:27 AM
-- Server version: 5.7.21
-- PHP Version: 7.1.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_lobola_calculator`
--
CREATE DATABASE IF NOT EXISTS `db_lobola_calculator` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `db_lobola_calculator`;

-- --------------------------------------------------------

--
-- Table structure for table `answers`
--

CREATE TABLE `answers` (
  `id` int(5) NOT NULL,
  `text` varchar(255) NOT NULL,
  `dateAdded` datetime NOT NULL,
  `actived` int(1) NOT NULL,
  `question_id` int(5) NOT NULL,
  `point` decimal(8,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `answers`
--

INSERT INTO `answers` (`id`, `text`, `dateAdded`, `actived`, `question_id`, `point`) VALUES
(1, 'this is answer 1', '2019-02-11 00:00:00', 1, 1, '100.00'),
(2, 'this is answer 2', '2019-02-11 00:00:00', 1, 1, '100.00'),
(3, 'this is answer 3', '2019-02-11 00:00:00', 1, 1, '100.00'),
(4, 'this is answer 4', '2019-02-11 00:00:00', 1, 1, '100.00'),
(5, 'this is answer 5', '2019-05-28 18:07:05', 1, 2, '100.00'),
(6, 'this is answer 6', '2019-05-28 18:07:05', 1, 2, '100.00'),
(7, 'this is answer 7', '2019-05-28 18:07:05', 1, 2, '100.00'),
(8, 'this is answer 8', '2019-05-28 18:07:05', 1, 2, '100.00'),
(9, 'this is answer 9', '2019-05-28 18:07:05', 1, 3, '100.00'),
(10, 'this is answer 10', '2019-05-28 18:07:05', 1, 3, '100.00'),
(11, 'sdf', '2019-06-04 20:41:07', 1, 81, '100.00'),
(12, 'easd', '2019-06-04 20:41:07', 1, 81, '100.00'),
(13, 'sdf', '2019-06-04 20:41:07', 1, 81, '100.00'),
(14, 'sdf', '2019-06-04 20:41:07', 1, 81, '100.00'),
(15, 'x1', '2019-06-04 20:44:45', 1, 82, '100.00'),
(16, 'x2', '2019-06-04 20:44:45', 1, 82, '100.00'),
(17, 'x3', '2019-06-04 20:44:45', 1, 82, '100.00'),
(18, 'x4', '2019-06-04 20:44:45', 1, 82, '100.00'),
(19, 'y1', '2019-06-04 20:50:16', 1, 83, '100.00'),
(20, 'y2', '2019-06-04 20:50:16', 1, 83, '100.00'),
(21, 'y3', '2019-06-04 20:50:16', 1, 83, '100.00'),
(22, 'y4', '2019-06-04 20:50:16', 1, 83, '100.00'),
(23, 'h1', '2019-06-04 20:51:04', 1, 84, '100.00'),
(24, 'h12', '2019-06-04 20:51:04', 1, 84, '100.00'),
(25, 'h123', '2019-06-04 20:51:04', 1, 84, '100.00'),
(26, 'h1234', '2019-06-04 20:51:04', 1, 84, '100.00'),
(31, 'yo 1', '2019-06-06 00:47:20', 1, 86, '100.00'),
(32, 'go 1', '2019-06-06 00:47:20', 1, 86, '100.00'),
(33, 'me too', '2019-06-06 00:47:20', 1, 86, '100.00'),
(34, 'go teeth', '2019-06-06 00:47:20', 1, 86, '100.00'),
(35, '111', '2019-06-10 19:52:26', 1, 87, '100.00'),
(36, '112', '2019-06-10 19:52:26', 1, 87, '100.00'),
(37, '113', '2019-06-10 19:52:26', 1, 87, '100.00'),
(38, '114', '2019-06-10 19:52:26', 1, 87, '100.00');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `id` int(5) NOT NULL,
  `description` text NOT NULL,
  `dateAdded` datetime NOT NULL,
  `activated` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`id`, `description`, `dateAdded`, `activated`) VALUES
(1, 'How old are u1?', '2019-01-09 00:00:00', 0),
(2, 'What is your highest level of education?', '2019-05-12 00:00:00', 1),
(3, 'Are you a virgin?', '2019-05-12 00:00:00', 1),
(4, 'Do you have a kid?', '2019-05-12 00:00:00', 1),
(5, 'How many guys have you slept with?', '2019-05-12 00:00:00', 1),
(6, 'Have you been married before?', '2019-05-12 00:00:00', 1),
(7, 'Do you have a job?', '2019-05-12 00:00:00', 1),
(8, 'Do you have a car?', '2019-05-12 00:00:00', 0),
(9, 'Do you have a house?', '2019-05-12 00:00:00', 1),
(10, 'How much does your boyfriend earn?', '2019-05-12 00:00:00', 1),
(50, 'Which School did you go to?', '2019-05-12 00:00:00', 1),
(51, 'Can you cook?', '2019-05-12 00:00:00', 1),
(52, 'What\'s your complexion?', '2019-05-12 00:00:00', 1),
(53, 'Are you from a rich family?', '2019-05-12 00:00:00', 1),
(54, 'How many children do you have?', '2019-05-12 00:00:00', 1),
(55, 'How many boyfriends have you had in the past?', '2019-05-12 00:00:00', 1),
(56, 'Have you been married before?', '2019-05-12 00:00:00', 1),
(57, 'Do you own a car?', '2019-05-12 00:00:00', 1),
(58, 'Rate your performance in the bedroom?', '2019-05-12 00:00:00', 1),
(59, 'Are you a socialite? Uthanda izinto? (clubbing,shisanyamas,parties are your thing)', '2019-05-12 00:00:00', 1),
(60, 'Are you planning to be a housewife?', '2019-05-12 00:00:00', 1),
(61, 'Are you gonna ask for your hair money? Imali yekhanda?', '2019-05-12 00:00:00', 1),
(62, 'Do you drink?', '2019-05-12 00:00:00', 1),
(65, 'testing 123 ccc', '2019-05-23 00:00:00', 1),
(67, 'This is a new question', '2019-05-28 05:59:49', 1),
(68, 'This is a new question 2', '2019-05-28 06:01:09', 1),
(69, 'I am trying my luck', '2019-05-28 06:02:19', 1),
(70, 'Why are we doing this?', '2019-05-28 06:03:31', 1),
(71, 'Let\'s try this', '2019-05-28 06:03:48', 1),
(81, 'asdf', '2019-06-04 20:41:07', 1),
(82, 'xxxxxxxxxxxxxxx', '2019-06-04 20:44:45', 1),
(83, 'yyyyyyyyyyyyyyyyy', '2019-06-04 20:50:16', 1),
(84, 'hhhhhhhhhhhhhhh', '2019-06-04 20:51:04', 1),
(86, 'this is a new question', '2019-06-06 00:47:20', 1),
(87, 'my sk', '2019-06-10 19:52:26', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answers`
--
ALTER TABLE `answers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `question_id` (`question_id`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answers`
--
ALTER TABLE `answers`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=88;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answers`
--
ALTER TABLE `answers`
  ADD CONSTRAINT `answers_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
