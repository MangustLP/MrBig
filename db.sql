-- MySQL dump 10.13  Distrib 5.5.49, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: DB_progetto
-- ------------------------------------------------------
-- Server version	5.5.49-0ubuntu0.14.04.1

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
-- Table structure for table `COORDINATES`
--

DROP TABLE IF EXISTS `COORDINATES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `COORDINATES` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LATITUDE` float NOT NULL,
  `LONGITUDE` float NOT NULL,
  `ADDRESS` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `COORDINATES`
--

LOCK TABLES `COORDINATES` WRITE;
/*!40000 ALTER TABLE `COORDINATES` DISABLE KEYS */;
/*!40000 ALTER TABLE `COORDINATES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CUISINES`
--

DROP TABLE IF EXISTS `CUISINES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CUISINES` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(25) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CUISINES`
--

LOCK TABLES `CUISINES` WRITE;
/*!40000 ALTER TABLE `CUISINES` DISABLE KEYS */;
/*!40000 ALTER TABLE `CUISINES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OPENING_HOURS_RANGES`
--

DROP TABLE IF EXISTS `OPENING_HOURS_RANGES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OPENING_HOURS_RANGES` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DAY_OF_THE_WEEK` int(11) DEFAULT NULL,
  `START_HOUR` time NOT NULL,
  `END_HOUR` time NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OPENING_HOURS_RANGES`
--

LOCK TABLES `OPENING_HOURS_RANGES` WRITE;
/*!40000 ALTER TABLE `OPENING_HOURS_RANGES` DISABLE KEYS */;
INSERT INTO `OPENING_HOURS_RANGES` VALUES (1,8,'00:00:00','00:00:00'),(2,8,'00:00:00','00:00:00');
/*!40000 ALTER TABLE `OPENING_HOURS_RANGES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OPENING_HOURS_RANGE_RESTAURANT`
--

DROP TABLE IF EXISTS `OPENING_HOURS_RANGE_RESTAURANT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OPENING_HOURS_RANGE_RESTAURANT` (
  `ID_RESTAURANT` int(11) NOT NULL,
  `ID_OPENING_HOURS_RANGE` int(11) NOT NULL,
  PRIMARY KEY (`ID_RESTAURANT`,`ID_OPENING_HOURS_RANGE`),
  KEY `ID_OPENING_HOURS_RANGE` (`ID_OPENING_HOURS_RANGE`),
  CONSTRAINT `OPENING_HOURS_RANGE_RESTAURANT_ibfk_1` FOREIGN KEY (`ID_RESTAURANT`) REFERENCES `RESTAURANTS` (`ID`),
  CONSTRAINT `OPENING_HOURS_RANGE_RESTAURANT_ibfk_2` FOREIGN KEY (`ID_OPENING_HOURS_RANGE`) REFERENCES `OPENING_HOURS_RANGES` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OPENING_HOURS_RANGE_RESTAURANT`
--

LOCK TABLES `OPENING_HOURS_RANGE_RESTAURANT` WRITE;
/*!40000 ALTER TABLE `OPENING_HOURS_RANGE_RESTAURANT` DISABLE KEYS */;
/*!40000 ALTER TABLE `OPENING_HOURS_RANGE_RESTAURANT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PHOTOS`
--

DROP TABLE IF EXISTS `PHOTOS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PHOTOS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(25) NOT NULL,
  `DESCRIPTION` varchar(32000) DEFAULT NULL,
  `PATH` varchar(255) NOT NULL,
  `ID_RESTAURANT` int(11) NOT NULL,
  `ID_OWNER` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_RESTAURANT` (`ID_RESTAURANT`),
  KEY `ID_OWNER` (`ID_OWNER`),
  CONSTRAINT `PHOTOS_ibfk_1` FOREIGN KEY (`ID_RESTAURANT`) REFERENCES `RESTAURANTS` (`ID`),
  CONSTRAINT `PHOTOS_ibfk_2` FOREIGN KEY (`ID_OWNER`) REFERENCES `USERS` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PHOTOS`
--

LOCK TABLES `PHOTOS` WRITE;
/*!40000 ALTER TABLE `PHOTOS` DISABLE KEYS */;
/*!40000 ALTER TABLE `PHOTOS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRICE_RANGES`
--

DROP TABLE IF EXISTS `PRICE_RANGES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRICE_RANGES` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(25) NOT NULL,
  `MIN_VALUE` float DEFAULT NULL,
  `MAX_VALUE` float DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRICE_RANGES`
--

LOCK TABLES `PRICE_RANGES` WRITE;
/*!40000 ALTER TABLE `PRICE_RANGES` DISABLE KEYS */;
/*!40000 ALTER TABLE `PRICE_RANGES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REPLIES`
--

DROP TABLE IF EXISTS `REPLIES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REPLIES` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(32000) NOT NULL,
  `DATE_CREATION` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ID_REVIEW` int(11) NOT NULL,
  `ID_OWNER` int(11) NOT NULL,
  `DATE_VALIDATION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `ID_VALIDATOR` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_REVIEW` (`ID_REVIEW`),
  KEY `ID_OWNER` (`ID_OWNER`),
  KEY `ID_VALIDATOR` (`ID_VALIDATOR`),
  CONSTRAINT `REPLIES_ibfk_1` FOREIGN KEY (`ID_REVIEW`) REFERENCES `REVIEWS` (`ID`),
  CONSTRAINT `REPLIES_ibfk_2` FOREIGN KEY (`ID_OWNER`) REFERENCES `USERS` (`ID`),
  CONSTRAINT `REPLIES_ibfk_3` FOREIGN KEY (`ID_VALIDATOR`) REFERENCES `USERS` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REPLIES`
--

LOCK TABLES `REPLIES` WRITE;
/*!40000 ALTER TABLE `REPLIES` DISABLE KEYS */;
/*!40000 ALTER TABLE `REPLIES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RESTAURANTS`
--

DROP TABLE IF EXISTS `RESTAURANTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RESTAURANTS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(32000) DEFAULT NULL,
  `WEB_SITE_URL` varchar(255) DEFAULT NULL,
  `GLOBAL_VALUE` int(11) DEFAULT NULL,
  `ID_OWNER` int(11) DEFAULT NULL,
  `ID_CREATOR` int(11) NOT NULL,
  `ID_PRICE_RANGE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_OWNER` (`ID_OWNER`),
  KEY `ID_CREATOR` (`ID_CREATOR`),
  KEY `ID_PRICE_RANGE` (`ID_PRICE_RANGE`),
  CONSTRAINT `RESTAURANTS_ibfk_1` FOREIGN KEY (`ID_OWNER`) REFERENCES `USERS` (`ID`),
  CONSTRAINT `RESTAURANTS_ibfk_2` FOREIGN KEY (`ID_CREATOR`) REFERENCES `USERS` (`ID`),
  CONSTRAINT `RESTAURANTS_ibfk_3` FOREIGN KEY (`ID_PRICE_RANGE`) REFERENCES `PRICE_RANGES` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RESTAURANTS`
--

LOCK TABLES `RESTAURANTS` WRITE;
/*!40000 ALTER TABLE `RESTAURANTS` DISABLE KEYS */;
/*!40000 ALTER TABLE `RESTAURANTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RESTAURANT_COORDINATE`
--

DROP TABLE IF EXISTS `RESTAURANT_COORDINATE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RESTAURANT_COORDINATE` (
  `ID_RESTAURANT` int(11) NOT NULL,
  `ID_COORDINATE` int(11) NOT NULL,
  PRIMARY KEY (`ID_RESTAURANT`,`ID_COORDINATE`),
  KEY `ID_COORDINATE` (`ID_COORDINATE`),
  CONSTRAINT `RESTAURANT_COORDINATE_ibfk_1` FOREIGN KEY (`ID_RESTAURANT`) REFERENCES `RESTAURANTS` (`ID`),
  CONSTRAINT `RESTAURANT_COORDINATE_ibfk_2` FOREIGN KEY (`ID_COORDINATE`) REFERENCES `COORDINATES` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RESTAURANT_COORDINATE`
--

LOCK TABLES `RESTAURANT_COORDINATE` WRITE;
/*!40000 ALTER TABLE `RESTAURANT_COORDINATE` DISABLE KEYS */;
/*!40000 ALTER TABLE `RESTAURANT_COORDINATE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RESTAURANT_CUISINE`
--

DROP TABLE IF EXISTS `RESTAURANT_CUISINE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RESTAURANT_CUISINE` (
  `ID_RESTAURANT` int(11) NOT NULL,
  `ID_CUISINE` int(11) NOT NULL,
  PRIMARY KEY (`ID_RESTAURANT`,`ID_CUISINE`),
  KEY `ID_CUISINE` (`ID_CUISINE`),
  CONSTRAINT `RESTAURANT_CUISINE_ibfk_1` FOREIGN KEY (`ID_RESTAURANT`) REFERENCES `RESTAURANTS` (`ID`),
  CONSTRAINT `RESTAURANT_CUISINE_ibfk_2` FOREIGN KEY (`ID_CUISINE`) REFERENCES `CUISINES` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RESTAURANT_CUISINE`
--

LOCK TABLES `RESTAURANT_CUISINE` WRITE;
/*!40000 ALTER TABLE `RESTAURANT_CUISINE` DISABLE KEYS */;
/*!40000 ALTER TABLE `RESTAURANT_CUISINE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REVIEWS`
--

DROP TABLE IF EXISTS `REVIEWS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REVIEWS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `GLOBAL_VALUE` int(11) NOT NULL,
  `FOOD` int(11) DEFAULT NULL,
  `SERVICE` int(11) DEFAULT NULL,
  `VALUE_FOR_MONEY` int(11) DEFAULT NULL,
  `ATMOSPHERE` int(11) DEFAULT NULL,
  `NAME` varchar(25) DEFAULT NULL,
  `DESCRIPTION` varchar(32000) DEFAULT NULL,
  `DATE_CREATION` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ID_RESTAURANT` int(11) NOT NULL,
  `ID_CREATOR` int(11) NOT NULL,
  `ID_PHOTO` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_RESTAURANT` (`ID_RESTAURANT`),
  KEY `ID_CREATOR` (`ID_CREATOR`),
  KEY `ID_PHOTO` (`ID_PHOTO`),
  CONSTRAINT `REVIEWS_ibfk_1` FOREIGN KEY (`ID_RESTAURANT`) REFERENCES `RESTAURANTS` (`ID`),
  CONSTRAINT `REVIEWS_ibfk_2` FOREIGN KEY (`ID_CREATOR`) REFERENCES `USERS` (`ID`),
  CONSTRAINT `REVIEWS_ibfk_3` FOREIGN KEY (`ID_PHOTO`) REFERENCES `PHOTOS` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REVIEWS`
--

LOCK TABLES `REVIEWS` WRITE;
/*!40000 ALTER TABLE `REVIEWS` DISABLE KEYS */;
/*!40000 ALTER TABLE `REVIEWS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USERS`
--

DROP TABLE IF EXISTS `USERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USERS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `SURNAME` varchar(255) NOT NULL,
  `NICKNAME` varchar(25) NOT NULL,
  `EMAIL` varchar(254) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NICKNAME` (`NICKNAME`),
  UNIQUE KEY `EMAIL` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERS`
--

LOCK TABLES `USERS` WRITE;
/*!40000 ALTER TABLE `USERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `USERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_REVIEW_LIKES`
--

DROP TABLE IF EXISTS `USER_REVIEW_LIKES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER_REVIEW_LIKES` (
  `ID_USER` int(11) NOT NULL,
  `ID_REVIEW` int(11) NOT NULL,
  `ID_CREATOR` int(11) NOT NULL,
  `LIKE_TYPE` int(11) NOT NULL,
  `DATE_CREATION` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID_USER`,`ID_REVIEW`,`ID_CREATOR`),
  KEY `ID_REVIEW` (`ID_REVIEW`),
  KEY `ID_CREATOR` (`ID_CREATOR`),
  CONSTRAINT `USER_REVIEW_LIKES_ibfk_1` FOREIGN KEY (`ID_USER`) REFERENCES `USERS` (`ID`),
  CONSTRAINT `USER_REVIEW_LIKES_ibfk_2` FOREIGN KEY (`ID_REVIEW`) REFERENCES `REVIEWS` (`ID`),
  CONSTRAINT `USER_REVIEW_LIKES_ibfk_3` FOREIGN KEY (`ID_CREATOR`) REFERENCES `USERS` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_REVIEW_LIKES`
--

LOCK TABLES `USER_REVIEW_LIKES` WRITE;
/*!40000 ALTER TABLE `USER_REVIEW_LIKES` DISABLE KEYS */;
/*!40000 ALTER TABLE `USER_REVIEW_LIKES` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-17 15:55:42
