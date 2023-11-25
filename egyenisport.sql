-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 23, 2023 at 09:21 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `egyenisport`
--
CREATE DATABASE IF NOT EXISTS `egyenisport` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_hungarian_ci;
USE `egyenisport`;

-- --------------------------------------------------------

--
-- Table structure for table `bajnoksag`
--

DROP TABLE IF EXISTS `bajnoksag`;
CREATE TABLE `bajnoksag` (
  `nev` varchar(32) NOT NULL,
  `kezdodatum` date NOT NULL,
  `vegzodatum` date NOT NULL,
  `helyszin` varchar(32) NOT NULL,
  `nyilt` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `felhasznalo`
--

DROP TABLE IF EXISTS `felhasznalo`;
CREATE TABLE `felhasznalo` (
  `felhasznalonev` varchar(32) NOT NULL,
  `jelszo` varchar(64) NOT NULL,
  `nev` varchar(32) DEFAULT NULL,
  `admin` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `hozzaadta`
--

DROP TABLE IF EXISTS `hozzaadta`;
CREATE TABLE `hozzaadta` (
  `felhasznalonev` varchar(32) DEFAULT NULL,
  `versenyzoid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `merkozes`
--

DROP TABLE IF EXISTS `merkozes`;
CREATE TABLE `merkozes` (
  `merkozesid` int(11) NOT NULL,
  `eredmeny` tinyint(1) NOT NULL,
  `datum` date NOT NULL,
  `helyszin` varchar(32) NOT NULL,
  `nev` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `resztvesz`
--

DROP TABLE IF EXISTS `resztvesz`;
CREATE TABLE `resztvesz` (
  `versenyzoid` int(11) NOT NULL,
  `merkozesid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

-- --------------------------------------------------------

--
-- Table structure for table `versenyzo`
--

DROP TABLE IF EXISTS `versenyzo`;
CREATE TABLE `versenyzo` (
  `versenyzoid` int(11) NOT NULL,
  `nev` varchar(32) NOT NULL,
  `szuletesidatum` date NOT NULL,
  `szuletesihely` varchar(32) NOT NULL,
  `allampolgarsag` varchar(10) NOT NULL,
  `aktiv` tinyint(1) NOT NULL,
  `gyesvarany` double NOT NULL,
  `felhasznalonev` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bajnoksag`
--
ALTER TABLE `bajnoksag`
  ADD PRIMARY KEY (`nev`);

--
-- Indexes for table `felhasznalo`
--
ALTER TABLE `felhasznalo`
  ADD PRIMARY KEY (`felhasznalonev`);

--
-- Indexes for table `hozzaadta`
--
ALTER TABLE `hozzaadta`
  ADD UNIQUE KEY `felhasznalonev` (`felhasznalonev`),
  ADD UNIQUE KEY `versenyzoid` (`versenyzoid`);

--
-- Indexes for table `merkozes`
--
ALTER TABLE `merkozes`
  ADD PRIMARY KEY (`merkozesid`),
  ADD UNIQUE KEY `nev` (`nev`);

--
-- Indexes for table `resztvesz`
--
ALTER TABLE `resztvesz`
  ADD UNIQUE KEY `versenyzoid` (`versenyzoid`),
  ADD UNIQUE KEY `merkozesid` (`merkozesid`);

--
-- Indexes for table `versenyzo`
--
ALTER TABLE `versenyzo`
  ADD PRIMARY KEY (`versenyzoid`),
  ADD UNIQUE KEY `felhasznalonev` (`felhasznalonev`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `merkozes`
--
ALTER TABLE `merkozes`
  MODIFY `merkozesid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `versenyzo`
--
ALTER TABLE `versenyzo`
  MODIFY `versenyzoid` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `hozzaadta`
--
ALTER TABLE `hozzaadta`
  ADD CONSTRAINT `hozzaadta_ibfk_1` FOREIGN KEY (`felhasznalonev`) REFERENCES `felhasznalo` (`felhasznalonev`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `hozzaadta_ibfk_2` FOREIGN KEY (`versenyzoid`) REFERENCES `versenyzo` (`versenyzoid`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `merkozes`
--
ALTER TABLE `merkozes`
  ADD CONSTRAINT `merkozes_ibfk_1` FOREIGN KEY (`nev`) REFERENCES `bajnoksag` (`nev`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `resztvesz`
--
ALTER TABLE `resztvesz`
  ADD CONSTRAINT `resztvesz_ibfk_1` FOREIGN KEY (`versenyzoid`) REFERENCES `versenyzo` (`versenyzoid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `resztvesz_ibfk_2` FOREIGN KEY (`merkozesid`) REFERENCES `merkozes` (`merkozesid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `versenyzo`
--
ALTER TABLE `versenyzo`
  ADD CONSTRAINT `versenyzo_ibfk_1` FOREIGN KEY (`felhasznalonev`) REFERENCES `felhasznalo` (`felhasznalonev`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
