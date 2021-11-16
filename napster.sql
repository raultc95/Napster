-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3307
-- Tiempo de generación: 16-11-2021 a las 20:17:18
-- Versión del servidor: 10.4.13-MariaDB
-- Versión de PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `napster`
--
CREATE DATABASE IF NOT EXISTS `napster` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `napster`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `artistas`
--

DROP TABLE IF EXISTS `artistas`;
CREATE TABLE IF NOT EXISTS `artistas` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `nacionalidad` varchar(255) NOT NULL,
  `foto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `canciones`
--

DROP TABLE IF EXISTS `canciones`;
CREATE TABLE IF NOT EXISTS `canciones` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) NOT NULL,
  `duracion` varchar(255) DEFAULT NULL,
  `id_genero` int(255) NOT NULL,
  `n_reproducciones` int(255) DEFAULT NULL,
  `id_disco` int(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_disco` (`id_disco`),
  KEY `id_genero` (`id_genero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cancion_play`
--

DROP TABLE IF EXISTS `cancion_play`;
CREATE TABLE IF NOT EXISTS `cancion_play` (
  `id_cancion` int(255) NOT NULL,
  `id_play` int(255) NOT NULL,
  KEY `id_cancion` (`id_cancion`,`id_play`),
  KEY `id_play` (`id_play`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `discos`
--

DROP TABLE IF EXISTS `discos`;
CREATE TABLE IF NOT EXISTS `discos` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `fecha_publicacion` date DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `n_reproducciones` int(255) DEFAULT NULL,
  `id_artista` int(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `id_artista` (`id_artista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `generos`
--

DROP TABLE IF EXISTS `generos`;
CREATE TABLE IF NOT EXISTS `generos` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `titulo` (`titulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `listas_de_reproduccion`
--

DROP TABLE IF EXISTS `listas_de_reproduccion`;
CREATE TABLE IF NOT EXISTS `listas_de_reproduccion` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `id_creador` int(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_creador` (`id_creador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `subscripcion`
--

DROP TABLE IF EXISTS `subscripcion`;
CREATE TABLE IF NOT EXISTS `subscripcion` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(255) NOT NULL,
  `id_lista` int(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_usuario` (`id_usuario`,`id_lista`),
  KEY `id_lista` (`id_lista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `correo` varchar(255) NOT NULL,
  `foto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`,`correo`),
  UNIQUE KEY `correo` (`correo`),
  UNIQUE KEY `nombre_2` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `canciones`
--
ALTER TABLE `canciones`
  ADD CONSTRAINT `canciones_ibfk_1` FOREIGN KEY (`id_disco`) REFERENCES `discos` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `canciones_ibfk_2` FOREIGN KEY (`id_genero`) REFERENCES `generos` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `cancion_play`
--
ALTER TABLE `cancion_play`
  ADD CONSTRAINT `cancion_play_ibfk_1` FOREIGN KEY (`id_cancion`) REFERENCES `canciones` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `cancion_play_ibfk_2` FOREIGN KEY (`id_play`) REFERENCES `listas_de_reproduccion` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `discos`
--
ALTER TABLE `discos`
  ADD CONSTRAINT `discos_ibfk_1` FOREIGN KEY (`id_artista`) REFERENCES `artistas` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `listas_de_reproduccion`
--
ALTER TABLE `listas_de_reproduccion`
  ADD CONSTRAINT `listas_de_reproduccion_ibfk_1` FOREIGN KEY (`id_creador`) REFERENCES `usuarios` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `subscripcion`
--
ALTER TABLE `subscripcion`
  ADD CONSTRAINT `subscripcion_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `subscripcion_ibfk_2` FOREIGN KEY (`id_lista`) REFERENCES `listas_de_reproduccion` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
