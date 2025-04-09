-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mer. 09 avr. 2025 à 12:21
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `Vol_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `Vol`
--

CREATE TABLE `Vol` (
  `id` int(11) NOT NULL,
  `Numeroserie` varchar(100) NOT NULL,
  `type` varchar(100) NOT NULL,
  `description` text DEFAULT NULL,
  `ownerContact` varchar(100) NOT NULL,
  `date_reported` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `Vol`
--

INSERT INTO `Vol` (`id`, `Numeroserie`, `type`, `description`, `ownerContact`, `date_reported`) VALUES
(1, '123456', 'telephone', 's21', '6988888888', '2025-04-07 08:09:48'),
(2, '55555', 'laptop', 'dell', '697039654', '2025-04-07 14:50:30'),
(3, '123333', 'lolol', 's21', '699999999', '2025-04-07 15:18:56'),
(4, '33333333', 'telephone', 's21', '697039654', '2025-04-08 11:04:27'),
(5, '22222222', 'telephone', 's22', '697036666', '2025-04-08 12:32:58'),
(6, '8787878787878', 'laptop', 'dell', '698241878', '2025-04-09 09:27:24');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Vol`
--
ALTER TABLE `Vol`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `serial_number` (`Numeroserie`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Vol`
--
ALTER TABLE `Vol`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
