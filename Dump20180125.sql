CREATE DATABASE  IF NOT EXISTS `Tickets` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `Tickets`;
-- MySQL dump 10.13  Distrib 5.6.17, for osx10.6 (i386)
--
-- Host: localhost    Database: Tickets
-- ------------------------------------------------------
-- Server version	5.7.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Info`
--

DROP TABLE IF EXISTS `Info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Info` (
  `TicketName` varchar(45) NOT NULL DEFAULT ' ',
  `TicketNumber` int(11) NOT NULL DEFAULT '0',
  `TicketComment` varchar(45) DEFAULT ' ',
  PRIMARY KEY (`TicketNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Info`
--

LOCK TABLES `Info` WRITE;
/*!40000 ALTER TABLE `Info` DISABLE KEYS */;
INSERT INTO `Info` VALUES ('This is a test ticket',23455,'This is a test Comment'),('Name of 112233',112233,'Comment for ticket 112233'),('Name of 223344',223344,'Comment for ticket 223344'),('Name of 445566',445566,'Comment for ticket 445566');
/*!40000 ALTER TABLE `Info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TicketData`
--

DROP TABLE IF EXISTS `TicketData`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TicketData` (
  `TicketNumber` int(11) NOT NULL,
  `TicketName` varchar(45) DEFAULT NULL,
  `TicketComment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`TicketNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TicketData`
--

LOCK TABLES `TicketData` WRITE;
/*!40000 ALTER TABLE `TicketData` DISABLE KEYS */;
INSERT INTO `TicketData` VALUES (1234567890,'Test Ticket 1','Comment for test ticket 1');
/*!40000 ALTER TABLE `TicketData` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TicketHours`
--

DROP TABLE IF EXISTS `TicketHours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TicketHours` (
  `TicketNumber` int(11) NOT NULL,
  `Date` date DEFAULT NULL,
  `Hours` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TicketHours`
--

LOCK TABLES `TicketHours` WRITE;
/*!40000 ALTER TABLE `TicketHours` DISABLE KEYS */;
INSERT INTO `TicketHours` VALUES (1234567890,'2018-01-22',8.5),(1234567890,'2018-01-22',8.5),(1234567890,'2018-01-22',3),(1234567890,'2018-01-22',3),(1234567890,'2018-01-22',4),(1234567890,'2018-01-25',4),(1234567890,'2018-01-25',4),(1234567890,'2018-01-25',4),(1234567890,'2018-01-25',4),(1234567890,'2018-01-25',4);
/*!40000 ALTER TABLE `TicketHours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Time`
--

DROP TABLE IF EXISTS `Time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Time` (
  `Date` date NOT NULL,
  `HoursWorked` decimal(5,2) DEFAULT NULL,
  `TicketNumber` int(11) NOT NULL DEFAULT '0',
  `Time` blob,
  PRIMARY KEY (`TicketNumber`),
  UNIQUE KEY `TicketNumber_UNIQUE` (`TicketNumber`),
  CONSTRAINT `TicketNumber` FOREIGN KEY (`TicketNumber`) REFERENCES `Info` (`TicketNumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Time`
--

LOCK TABLES `Time` WRITE;
/*!40000 ALTER TABLE `Time` DISABLE KEYS */;
INSERT INTO `Time` VALUES ('2018-01-18',8.50,112233,'¨Ì\0sr\0java.util.ArrayListxÅ“ô«aù\0I\0sizexp\0\0\0w\0\0\0sr\0$com.superklamer.serialize.TimeWorked\Zƒ5:•∑|\0D\0hoursWorkedL\0datet\0Ljava/util/Date;xp@!\0\0\0\0\0\0sr\0java.util.DatehjÅKYt\0\0xpw\0\0a\rã¢xsq\0~\0@\0\0\0\0\0\0sq\0~\0w\0\0a\rã¢xsq\0~\0?‡\0\0\0\0\0\0sq\0~\0w\0\0a\rã¢xx');
/*!40000 ALTER TABLE `Time` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-25 23:13:55
