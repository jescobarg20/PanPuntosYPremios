CREATE DATABASE  IF NOT EXISTS `panaderia` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `panaderia`;
-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: panaderia
-- ------------------------------------------------------
-- Server version	8.0.46

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `canjes`
--

DROP TABLE IF EXISTS `canjes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `canjes` (
  `idcanje` int NOT NULL AUTO_INCREMENT,
  `idClientes` int NOT NULL,
  `idrecompensa` int NOT NULL,
  `cantidad` int NOT NULL,
  `puntosUtilizados` int NOT NULL,
  `fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idcanje`),
  KEY `fk_canje_cliente` (`idClientes`),
  KEY `fk_canje_recompensa` (`idrecompensa`),
  CONSTRAINT `fk_canje_cliente` FOREIGN KEY (`idClientes`) REFERENCES `clientes` (`idClientes`),
  CONSTRAINT `fk_canje_recompensa` FOREIGN KEY (`idrecompensa`) REFERENCES `recompensas` (`idrecompensa`),
  CONSTRAINT `chk_canje_cantidad` CHECK ((`cantidad` > 0)),
  CONSTRAINT `chk_canje_puntos` CHECK ((`puntosUtilizados` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `canjes`
--

LOCK TABLES `canjes` WRITE;
/*!40000 ALTER TABLE `canjes` DISABLE KEYS */;
INSERT INTO `canjes` VALUES (1,123456,1,1,10,'2026-11-03 16:14:10');
/*!40000 ALTER TABLE `canjes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `idClientes` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellidos` varchar(45) DEFAULT NULL,
  `puntos` int DEFAULT NULL,
  `activo` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`idClientes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (54682,'Cosme','Fialnito',0,1),(123456,'Ana','Lopez',2,1),(401230,'Pedro','Villamar',30,1),(580020,'Luis','Chacón',0,1),(987450,'Carlos','Martinez',0,0);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_pedido`
--

DROP TABLE IF EXISTS `detalle_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_pedido` (
  `iddetalle` int NOT NULL AUTO_INCREMENT,
  `idpedido` int NOT NULL,
  `idmenu` int NOT NULL,
  `cantidad` int NOT NULL,
  `precio_unitario` decimal(10,2) NOT NULL,
  PRIMARY KEY (`iddetalle`),
  KEY `fk_detalle_pedido` (`idpedido`),
  KEY `fk_detalle_menu` (`idmenu`),
  CONSTRAINT `fk_detalle_menu` FOREIGN KEY (`idmenu`) REFERENCES `menu` (`idmenu`),
  CONSTRAINT `fk_detalle_pedido` FOREIGN KEY (`idpedido`) REFERENCES `pedido` (`idpedido`),
  CONSTRAINT `chk_detalle_cantidad` CHECK ((`cantidad` > 0)),
  CONSTRAINT `chk_detalle_precio` CHECK ((`precio_unitario` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_pedido`
--

LOCK TABLES `detalle_pedido` WRITE;
/*!40000 ALTER TABLE `detalle_pedido` DISABLE KEYS */;
INSERT INTO `detalle_pedido` VALUES (1,24579,1520,2,15.00),(2,24579,2069,3,16.00),(3,24580,2069,2,16.00),(4,24581,2069,2,16.00),(5,24582,2069,2,16.00),(6,24583,2069,1,16.00);
/*!40000 ALTER TABLE `detalle_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventario`
--

DROP TABLE IF EXISTS `inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventario` (
  `idinventario` int NOT NULL AUTO_INCREMENT,
  `idmenu` int NOT NULL,
  `unidades` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`idinventario`),
  UNIQUE KEY `uq_inventario_menu` (`idmenu`),
  CONSTRAINT `fk_inventario_menu` FOREIGN KEY (`idmenu`) REFERENCES `menu` (`idmenu`),
  CONSTRAINT `chk_inventario_unidades` CHECK ((`unidades` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario`
--

LOCK TABLES `inventario` WRITE;
/*!40000 ALTER TABLE `inventario` DISABLE KEYS */;
INSERT INTO `inventario` VALUES (1,1520,0),(2,2069,10);
/*!40000 ALTER TABLE `inventario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `idmenu` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `precio` decimal(10,2) NOT NULL,
  `individual` tinyint NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idmenu`),
  CONSTRAINT `chk_menu_individual` CHECK ((`individual` in (0,1)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1520,'Sandwich de Jamin',15.00,1,0),(2069,'Café',7.00,1,1),(2539,'Menú completo',50.00,0,1),(8972,'Combo alimentario',78.00,0,1);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimientos_inventario`
--

DROP TABLE IF EXISTS `movimientos_inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimientos_inventario` (
  `idmovimiento` int NOT NULL AUTO_INCREMENT,
  `idmenu` int NOT NULL,
  `cantidad` int NOT NULL,
  `tipo` varchar(30) NOT NULL,
  `fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idmovimiento`),
  KEY `fk_movimiento_menu` (`idmenu`),
  CONSTRAINT `fk_movimiento_menu` FOREIGN KEY (`idmenu`) REFERENCES `menu` (`idmenu`),
  CONSTRAINT `chk_movimiento_cantidad` CHECK ((((`tipo` = _utf8mb4'ENTRADA') and (`cantidad` > 0)) or ((`tipo` = _utf8mb4'AJUSTE') and (`cantidad` <> 0)) or ((`tipo` in (_utf8mb4'SALIDA_VENTA',_utf8mb4'SALIDA_CANJE')) and (`cantidad` > 0)))),
  CONSTRAINT `chk_movimiento_tipo` CHECK ((`tipo` in (_utf8mb4'ENTRADA',_utf8mb4'SALIDA_VENTA',_utf8mb4'SALIDA_CANJE',_utf8mb4'AJUSTE')))
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimientos_inventario`
--

LOCK TABLES `movimientos_inventario` WRITE;
/*!40000 ALTER TABLE `movimientos_inventario` DISABLE KEYS */;
INSERT INTO `movimientos_inventario` VALUES (1,2069,10,'ENTRADA','2026-11-03 13:03:43'),(2,2069,-2,'AJUSTE','2026-11-03 13:25:45'),(3,2069,2,'SALIDA_VENTA','2026-11-03 15:19:17'),(4,2069,1,'SALIDA_VENTA','2026-11-03 15:29:14'),(5,2069,1,'SALIDA_CANJE','2026-11-03 16:14:10'),(6,2069,1,'ENTRADA','2026-11-03 18:29:03'),(7,2069,5,'ENTRADA','2026-11-03 18:30:33'),(8,2069,5,'AJUSTE','2026-11-03 18:34:04'),(9,2069,15,'ENTRADA','2026-11-03 18:34:10'),(10,2069,5,'ENTRADA','2026-11-03 18:34:17'),(11,2069,25,'AJUSTE','2026-11-03 18:34:21'),(12,2069,10,'ENTRADA','2026-11-03 18:34:29'),(13,2069,40,'ENTRADA','2026-11-03 19:19:17'),(14,2069,40,'AJUSTE','2026-11-03 19:19:24');
/*!40000 ALTER TABLE `movimientos_inventario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagos`
--

DROP TABLE IF EXISTS `pagos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagos` (
  `idpago` int NOT NULL AUTO_INCREMENT,
  `idpedido` int NOT NULL,
  `metodo` varchar(20) NOT NULL,
  `monto_recibido` decimal(10,2) NOT NULL,
  `cambio` decimal(10,2) NOT NULL DEFAULT '0.00',
  `estado` varchar(20) NOT NULL,
  `referencia` varchar(100) DEFAULT NULL,
  `fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idpago`),
  KEY `fk_pago_pedido` (`idpedido`),
  CONSTRAINT `fk_pago_pedido` FOREIGN KEY (`idpedido`) REFERENCES `pedido` (`idpedido`),
  CONSTRAINT `chk_pago_estado` CHECK ((`estado` in (_utf8mb4'APROBADO',_utf8mb4'RECHAZADO'))),
  CONSTRAINT `chk_pago_metodo` CHECK ((`metodo` in (_utf8mb4'EFECTIVO',_utf8mb4'TARJETA')))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagos`
--

LOCK TABLES `pagos` WRITE;
/*!40000 ALTER TABLE `pagos` DISABLE KEYS */;
INSERT INTO `pagos` VALUES (1,24579,'EFECTIVO',100.00,22.00,'APROBADO',NULL,'2026-11-03 14:43:56'),(2,24580,'EFECTIVO',50.00,18.00,'APROBADO',NULL,'2026-11-03 14:48:12'),(3,24580,'EFECTIVO',50.00,18.00,'APROBADO',NULL,'2026-11-03 14:49:56'),(4,24582,'EFECTIVO',40.00,8.00,'APROBADO',NULL,'2026-11-03 15:19:17'),(5,24583,'EFECTIVO',20.00,4.00,'APROBADO',NULL,'2026-11-03 15:29:14');
/*!40000 ALTER TABLE `pagos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `idpedido` int NOT NULL,
  `TotalPrecio` decimal(2,0) DEFAULT NULL,
  `Estado` varchar(45) DEFAULT NULL,
  `idClientes` int DEFAULT NULL,
  PRIMARY KEY (`idpedido`),
  KEY `fk_pedido_clientes` (`idClientes`),
  CONSTRAINT `fk_pedido_clientes` FOREIGN KEY (`idClientes`) REFERENCES `clientes` (`idClientes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (24578,50,'Pendiente',401230),(24579,78,'PENDIENTE',123456),(24580,32,'PAGADO',401230),(24581,32,'PENDIENTE',401230),(24582,32,'PAGADO',123456),(24583,16,'PAGADO',123456);
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recompensas`
--

DROP TABLE IF EXISTS `recompensas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recompensas` (
  `idrecompensa` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `puntosNecesarios` int NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT '1',
  `idmenu` int NOT NULL,
  PRIMARY KEY (`idrecompensa`),
  KEY `fk_recompensa_menu` (`idmenu`),
  CONSTRAINT `fk_recompensa_menu` FOREIGN KEY (`idmenu`) REFERENCES `menu` (`idmenu`),
  CONSTRAINT `chk_recompensa_puntos` CHECK ((`puntosNecesarios` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recompensas`
--

LOCK TABLES `recompensas` WRITE;
/*!40000 ALTER TABLE `recompensas` DISABLE KEYS */;
INSERT INTO `recompensas` VALUES (1,'Bebida',10,1,2069),(2,'Sandwich',30,1,1520);
/*!40000 ALTER TABLE `recompensas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-11-05 22:15:15
