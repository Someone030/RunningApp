-- MySQL dump 10.19  Distrib 10.3.39-MariaDB, for Linux (x86_64)
--
-- Host: 146.245.252.140    Database: Robertson21_db
-- ------------------------------------------------------
-- Server version       10.6.21-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `accountName` varchar(10) NOT NULL,
  `accountNumber` int(10) NOT NULL,
  `userID` int(10) NOT NULL,
  `accountID` int(10) NOT NULL,
  `friends` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`accountName`,`accountNumber`,`userID`,`accountID`),
  UNIQUE KEY `accountID` (`accountID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('PhDGoose',2,2,2,'PhDuck'),('PhDSwan',3,3,3,'PhDGoose'),('PhDuck',1,1,1,'FriendDuck');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `achievements`
--

DROP TABLE IF EXISTS `achievements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `achievements` (
  `runStreak` int(10) NOT NULL,
  `goals` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`runStreak`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `achievements`
--

LOCK TABLES `achievements` WRITE;
/*!40000 ALTER TABLE `achievements` DISABLE KEYS */;
INSERT INTO `achievements` VALUES (24,'100 Miles');
/*!40000 ALTER TABLE `achievements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `routes`
--

DROP TABLE IF EXISTS `routes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `routes` (
  `startLocation` varchar(30) NOT NULL,
  `endLocation` varchar(30) NOT NULL,
  `routeID` int(10) NOT NULL,
  `accID` int(10) DEFAULT NULL,
  `location` varchar(30) DEFAULT NULL,
  `distancePreference` decimal(4,2) DEFAULT NULL,
  PRIMARY KEY (`startLocation`,`endLocation`,`routeID`),
  KEY `accID` (`accID`),
  CONSTRAINT `routes_ibfk_1` FOREIGN KEY (`accID`) REFERENCES `account` (`accountID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `routes`
--

LOCK TABLES `routes` WRITE;
/*!40000 ALTER TABLE `routes` DISABLE KEYS */;
INSERT INTO `routes` VALUES ('10 Grand Army Plz','285 Fulton St',7,1,'NYC',5.00),('2900 Bedford Ave','10 Grand Army Plz',718,1,'NYC',4.00),('2900 Bedford Ave','285 Fulton St',2015,1,'NYC',10.00);
/*!40000 ALTER TABLE `routes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `savedRoutes`
--

DROP TABLE IF EXISTS `savedRoutes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `savedRoutes` (
  `startLoc` varchar(30) NOT NULL,
  `endLoc` varchar(30) NOT NULL,
  `routesID` int(10) NOT NULL,
  `savedRouteID` int(10) NOT NULL,
  `accoID` int(10) NOT NULL,
  `savedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`savedRouteID`,`routesID`,`startLoc`,`endLoc`,`accoID`),
  KEY `startLoc` (`startLoc`,`endLoc`,`routesID`),
  KEY `accoID` (`accoID`),
  CONSTRAINT `savedRoutes_ibfk_1` FOREIGN KEY (`startLoc`, `endLoc`, `routesID`) REFERENCES `routes` (`startLocation`, `endLocation`, `routeID`),
  CONSTRAINT `savedRoutes_ibfk_2` FOREIGN KEY (`accoID`) REFERENCES `account` (`accountID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `savedRoutes`
--

LOCK TABLES `savedRoutes` WRITE;
/*!40000 ALTER TABLE `savedRoutes` DISABLE KEYS */;
INSERT INTO `savedRoutes` VALUES ('2900 Bedford Ave','285 Fulton St',2015,1,1,'2025-03-21 00:00:00'),('2900 Bedford Ave','10 Grand Army Plz',718,2,1,'2025-01-01 00:00:00'),('10 Grand Army Plz','285 Fulton St',7,3,1,'2025-02-03 00:00:00');
/*!40000 ALTER TABLE `savedRoutes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL,
  `icon` int(10) DEFAULT NULL,
  PRIMARY KEY (`username`,`password`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('DrDuck','secretpassword',12),('DrGoose','password123',12),('DrSwan','applejuice',23);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-03 18:57:16