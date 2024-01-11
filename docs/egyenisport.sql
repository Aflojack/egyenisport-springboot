-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 26, 2023 at 10:09 PM
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

--
-- Dumping data for table `bajnoksag`
--

INSERT INTO `bajnoksag` (`nev`, `kezdodatum`, `vegzodatum`, `helyszin`, `nyilt`) VALUES
('Futárosok 2022', '2022-10-10', '2022-12-10', 'Szolnok', 1),
('Futárosok 2023', '2023-11-26', '2023-12-23', 'Szeged', 1),
('Szeged vagyok 2022', '2023-09-01', '2023-10-01', 'Szeged', 0),
('Tavaszváró 2011', '2011-02-01', '2011-03-01', 'Paks', 1),
('Tavaszváró 2012', '2012-02-01', '2012-03-01', 'Paks', 1),
('Tavaszváró 2013', '2013-02-01', '2013-03-01', 'Paks', 1),
('Tenisz 2022', '2022-10-10', '2022-12-10', 'Szeged', 1);

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

--
-- Dumping data for table `felhasznalo`
--

INSERT INTO `felhasznalo` (`felhasznalonev`, `jelszo`, `nev`, `admin`) VALUES
('admin', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Nagy Admin', 1),
('fabatka723', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Fa Batka', 0),
('felhasznalo', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Felhasználó Béla', 0),
('futáros5000', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Futár Béla', 0),
('futokár1099', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Kis Ákos', 0),
('kiscica3000', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Kis Cica', 0),
('kispeter77', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Kis Péter', 0),
('laci', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Holtig László', 0),
('lépdelek', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Lép Elek', 1),
('nagyadmin', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Nagy Admin', 1),
('tiptoprunner', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Ágnes Péter', 0),
('verselek', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Vers Elek', 1),
('woodenred5', '3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 'Nagy Sándor', 0);

-- --------------------------------------------------------

--
-- Table structure for table `merkozes`
--

DROP TABLE IF EXISTS `merkozes`;
CREATE TABLE `merkozes` (
  `merkozesid` int(11) NOT NULL,
  `datum` date NOT NULL,
  `helyszin` varchar(32) NOT NULL,
  `nev` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- Dumping data for table `merkozes`
--

INSERT INTO `merkozes` (`merkozesid`, `datum`, `helyszin`, `nev`) VALUES
(3, '2022-10-10', 'Szolnok Repülőtér utca 22.', 'Futárosok 2022'),
(6, '2022-10-15', 'Szolnok Repülőtér utca 21.', 'Futárosok 2022'),
(7, '2022-10-20', 'Szolnok Előtér út 50.', 'Futárosok 2022'),
(8, '2022-11-10', 'Szolnok Tiszaliget sportpálya', 'Futárosok 2022'),
(9, '2022-10-10', 'Szeged Nagyvásár csarnok', 'Tenisz 2022'),
(10, '2023-09-01', 'Szeged Irinyi', 'Szeged vagyok 2022'),
(11, '2023-09-10', 'Szeged TIK', 'Szeged vagyok 2022');

-- --------------------------------------------------------

--
-- Table structure for table `resztvesz`
--

DROP TABLE IF EXISTS `resztvesz`;
CREATE TABLE `resztvesz` (
  `resztveszid` int(11) NOT NULL,
  `versenyzoid` int(11) DEFAULT NULL,
  `merkozesid` int(11) DEFAULT NULL,
  `eredmeny` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_hungarian_ci;

--
-- Dumping data for table `resztvesz`
--

INSERT INTO `resztvesz` (`resztveszid`, `versenyzoid`, `merkozesid`, `eredmeny`) VALUES
(38, 10, 3, 1),
(39, 10, 6, 1),
(41, 10, 8, 1),
(42, 11, 3, 0),
(43, 11, 6, 0),
(44, 11, 7, 1),
(45, 10, 7, 0),
(46, 12, 3, 0),
(47, 12, 6, 0),
(48, 12, 7, 0),
(49, 12, 8, 0),
(50, 14, 6, 0),
(51, 14, 7, 0),
(52, 15, 3, 1),
(53, 15, 6, 0),
(54, 15, 11, 1),
(55, 14, 10, 0);

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
-- Dumping data for table `versenyzo`
--

INSERT INTO `versenyzo` (`versenyzoid`, `nev`, `szuletesidatum`, `szuletesihely`, `allampolgarsag`, `aktiv`, `gyesvarany`, `felhasznalonev`) VALUES
(10, 'Nagy Sándor', '2000-05-30', 'Szolnok', 'magyar', 1, 3, 'kispeter77'),
(11, 'Kis Cica', '2000-05-30', 'Szeged', 'magyar', 1, 0.5, 'kiscica3000'),
(12, 'Kis Péter', '2013-10-09', 'Szeged', 'magyar', 1, 0, 'tiptoprunner'),
(13, 'Futok Áron', '1999-05-30', 'Szeged', 'magyar', 1, 0, 'futokár1099'),
(14, 'Nagy Admin', '1978-10-10', 'Budapest', 'magyar', 1, 0, 'admin'),
(15, 'Vers Elek', '1068-10-11', 'Budapest', 'magyar', 0, 2, 'verselek'),
(16, 'Nagy Felhasználó', '1967-03-05', 'Budapest', 'magyar', 1, 0, 'felhasznalo');

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
-- Indexes for table `merkozes`
--
ALTER TABLE `merkozes`
  ADD PRIMARY KEY (`merkozesid`),
  ADD KEY `nev` (`nev`) USING BTREE;

--
-- Indexes for table `resztvesz`
--
ALTER TABLE `resztvesz`
  ADD PRIMARY KEY (`resztveszid`),
  ADD KEY `versenyzoid` (`versenyzoid`),
  ADD KEY `merkozesid` (`merkozesid`);

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
  MODIFY `merkozesid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `resztvesz`
--
ALTER TABLE `resztvesz`
  MODIFY `resztveszid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT for table `versenyzo`
--
ALTER TABLE `versenyzo`
  MODIFY `versenyzoid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `merkozes`
--
ALTER TABLE `merkozes`
  ADD CONSTRAINT `merkozes_ibfk_1` FOREIGN KEY (`nev`) REFERENCES `bajnoksag` (`nev`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `resztvesz`
--
ALTER TABLE `resztvesz`
  ADD CONSTRAINT `resztvesz_ibfk_1` FOREIGN KEY (`merkozesid`) REFERENCES `merkozes` (`merkozesid`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `resztvesz_ibfk_2` FOREIGN KEY (`versenyzoid`) REFERENCES `versenyzo` (`versenyzoid`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `versenyzo`
--
ALTER TABLE `versenyzo`
  ADD CONSTRAINT `versenyzo_ibfk_1` FOREIGN KEY (`felhasznalonev`) REFERENCES `felhasznalo` (`felhasznalonev`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
