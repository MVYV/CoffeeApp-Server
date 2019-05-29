-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Час створення: Трв 29 2019 р., 16:55
-- Версія сервера: 5.7.24
-- Версія PHP: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База даних: `march_eleven`
--

-- --------------------------------------------------------

--
-- Структура таблиці `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Дамп даних таблиці `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(7),
(7);

-- --------------------------------------------------------

--
-- Структура таблиці `news`
--

DROP TABLE IF EXISTS `news`;
CREATE TABLE IF NOT EXISTS `news` (
  `news_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(60) NOT NULL,
  `sub_text` varchar(150) NOT NULL,
  `text` text NOT NULL,
  PRIMARY KEY (`news_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Дамп даних таблиці `news`
--

INSERT INTO `news` (`news_id`, `title`, `sub_text`, `text`) VALUES
(1, 'Chelsea\'s loan players', 'We round up news involving our loan players following a play-off in the Netherlands and mixed fortunes from the Italian top flight…\r\n', 'Having finished fifth in Eredivisie table, Vitesse were involved in a play-off with Utrecht for a place in next season’s Europa League. They were unable to select Jake Clarke-Salter for last week’s first leg due to a knee problem, the on-loan Chelsea defender having been a mainstay of the Vitesse defence this season, and longer-term casualty Charly Musonda was unavailable again having returned to the pitch earlier in the month.\r\n\r\nThat game away at Utrecht ended 1-1 so hopes must have been high at Vitesse ahead of Tuesday’s home leg, but their visitors ran out 2-0 winners on the night. Again Clarke-Salter could not play.\r\n\r\nThe Serie A season concluded at the weekend and Mario Pasalic (pictured top centre) was on the scoresheet, netting the third in Atalanta’s 3-1 home win over Sassuolo which meant the Bergamo side ended the season in a very impressive third place behind Juventus and Napoli, earning them a place in the Champions League for the first time in their history.');

-- --------------------------------------------------------

--
-- Структура таблиці `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(30) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Дамп даних таблиці `roles`
--

INSERT INTO `roles` (`role_id`, `role`) VALUES
(1, 'ADMIN'),
(4, 'ADMIN'),
(5, 'USER'),
(6, 'USER');

-- --------------------------------------------------------

--
-- Структура таблиці `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `active` tinyint(4) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

--
-- Дамп даних таблиці `users`
--

INSERT INTO `users` (`user_id`, `email`, `password`, `name`, `last_name`, `active`) VALUES
(1, 'yura@gmail.com', '$2a$10$2mkMpMK.p/GMiPMjiUb4ROAQpRrnEuMCn8Kp6DPU1XFEIa2lsRlhS', 'yura', 'yura', 1),
(24, 'palmer@mail.com', '$2a$10$iriuqrDPPxu1HoTufSf8wei16k32iaW36mCmaL2sI31zCUelPhpO2', 'Meredith', 'Without nothing3', 1);

-- --------------------------------------------------------

--
-- Структура таблиці `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FKt7e7djp752sqn6w22i6ocqy6q` (`role_id`),
  KEY `FKj345gk1bovqvfame88rcx7yyx` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Дамп даних таблиці `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 1),
(3, 4),
(24, 6);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
