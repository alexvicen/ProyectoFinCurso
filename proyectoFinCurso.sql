-- phpMyAdmin SQL Dump
-- version 4.2.8deb1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 11-05-2016 a las 15:56:20
-- Versión del servidor: 5.5.33-0+wheezy1-log
-- Versión de PHP: 5.4.4-15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `proyectoFinCurso`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mos_personaje`
--

CREATE TABLE IF NOT EXISTS `mos_personaje` (
`id_personaje` int(11) NOT NULL,
  `nombre_personaje` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `fk_usuario` int(11) NOT NULL,
  `nivel` int(11) NOT NULL,
  `nivCasco` int(11) NOT NULL,
  `nivArco` int(11) NOT NULL,
  `nivEscudo` int(11) NOT NULL,
  `nivGuantes` int(11) NOT NULL,
  `nivBotas` int(11) NOT NULL,
  `nivFlecha` int(11) NOT NULL,
  `pepita` int(11) NOT NULL,
  `roca` int(11) NOT NULL,
  `tronco` int(11) NOT NULL,
  `hierro` int(11) NOT NULL,
  `gema_bruto` int(11) NOT NULL,
  `lingote_oro` int(11) NOT NULL,
  `lingote_hierro` int(11) NOT NULL,
  `gema` int(11) NOT NULL,
  `piedra` int(11) NOT NULL,
  `tabla_madera` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `mos_personaje`
--

INSERT INTO `mos_personaje` (`id_personaje`, `nombre_personaje`, `fk_usuario`, `nivel`, `nivCasco`, `nivArco`, `nivEscudo`, `nivGuantes`, `nivBotas`, `nivFlecha`, `pepita`, `roca`, `tronco`, `hierro`, `gema_bruto`, `lingote_oro`, `lingote_hierro`, `gema`, `piedra`, `tabla_madera`) VALUES
(2, 'guarior', 1, 15, 0, 0, 0, 0, 0, 0, 100, 100, 100, 100, 100, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mos_usuarios`
--

CREATE TABLE IF NOT EXISTS `mos_usuarios` (
`id_usuario` int(11) NOT NULL,
  `nombre_usuario` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `login_usuario` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `pass_usuario` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `mos_usuarios`
--

INSERT INTO `mos_usuarios` (`id_usuario`, `nombre_usuario`, `login_usuario`, `pass_usuario`, `email`) VALUES
(1, 'pepe', 'pepe', '265392dc2782778664cc9d56c8e3cd9956661bb0', 'pepe@hotmail.com');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `mos_personaje`
--
ALTER TABLE `mos_personaje`
 ADD PRIMARY KEY (`id_personaje`), ADD KEY `fk_usuario` (`fk_usuario`);

--
-- Indices de la tabla `mos_usuarios`
--
ALTER TABLE `mos_usuarios`
 ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `mos_personaje`
--
ALTER TABLE `mos_personaje`
MODIFY `id_personaje` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `mos_usuarios`
--
ALTER TABLE `mos_usuarios`
MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `mos_personaje`
--
ALTER TABLE `mos_personaje`
ADD CONSTRAINT `mos_personaje_ibfk_1` FOREIGN KEY (`fk_usuario`) REFERENCES `mos_usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
