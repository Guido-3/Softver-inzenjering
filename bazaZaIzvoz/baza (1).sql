CREATE DATABASE  IF NOT EXISTS `citypass` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `citypass`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: citypass
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `username` varchar(255) NOT NULL,
  `sifra` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('Elica',NULL),('Gvido','123'),('Krizo','456'),('Milica',NULL),('Sara',NULL);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `daily_pass`
--

DROP TABLE IF EXISTS `daily_pass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `daily_pass` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cijena` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daily_pass`
--

LOCK TABLES `daily_pass` WRITE;
/*!40000 ALTER TABLE `daily_pass` DISABLE KEYS */;
INSERT INTO `daily_pass` VALUES (1,30.00),(2,35.00);
/*!40000 ALTER TABLE `daily_pass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drzava`
--

DROP TABLE IF EXISTS `drzava`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `drzava` (
  `ime` varchar(255) NOT NULL,
  PRIMARY KEY (`ime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drzava`
--

LOCK TABLES `drzava` WRITE;
/*!40000 ALTER TABLE `drzava` DISABLE KEYS */;
INSERT INTO `drzava` VALUES ('Crna Gora'),('Sjeverna Makedonija'),('Slovenija');
/*!40000 ALTER TABLE `drzava` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `korisnik` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(255) DEFAULT NULL,
  `prezime` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `br_telefona` varchar(20) DEFAULT NULL,
  `drzava_ime` varchar(255) NOT NULL,
  `admin` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `drzava_ime` (`drzava_ime`),
  KEY `admin` (`admin`),
  CONSTRAINT `korisnik_ibfk_1` FOREIGN KEY (`drzava_ime`) REFERENCES `drzava` (`ime`),
  CONSTRAINT `korisnik_ibfk_2` FOREIGN KEY (`admin`) REFERENCES `admin` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES (1,'Gvido','Gvidovic','gvido@','12345678','Crna Gora','Gvido'),(2,'Boro','Gvidov','boro@','12345612','Slovenija','Gvido');
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kupovina`
--

DROP TABLE IF EXISTS `kupovina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kupovina` (
  `id` int NOT NULL AUTO_INCREMENT,
  `datum` date DEFAULT NULL,
  `korisnik_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `korisnik_id` (`korisnik_id`),
  CONSTRAINT `kupovina_ibfk_1` FOREIGN KEY (`korisnik_id`) REFERENCES `korisnik` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kupovina`
--

LOCK TABLES `kupovina` WRITE;
/*!40000 ALTER TABLE `kupovina` DISABLE KEYS */;
INSERT INTO `kupovina` VALUES (2,'2024-05-05',1),(3,'2020-12-12',2),(5,'2023-05-22',1),(6,'2020-10-01',2);
/*!40000 ALTER TABLE `kupovina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turista`
--

DROP TABLE IF EXISTS `turista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `turista` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(255) DEFAULT NULL,
  `prezime` varchar(255) DEFAULT NULL,
  `drzava_ime` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `drzava_ime` (`drzava_ime`),
  CONSTRAINT `turista_ibfk_1` FOREIGN KEY (`drzava_ime`) REFERENCES `drzava` (`ime`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turista`
--

LOCK TABLES `turista` WRITE;
/*!40000 ALTER TABLE `turista` DISABLE KEYS */;
INSERT INTO `turista` VALUES (1,'Gvido','Gvidic','Crna Gora'),(2,'Boro','Gvidov','Crna Gora'),(4,'Milica','Bjelica','Crna Gora');
/*!40000 ALTER TABLE `turista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turista_daily_pass`
--

DROP TABLE IF EXISTS `turista_daily_pass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `turista_daily_pass` (
  `turista_id` int NOT NULL,
  `daily_pass_id` int NOT NULL,
  `datum_od` date DEFAULT NULL,
  `datum_do` date DEFAULT NULL,
  `ukupna_cijena` decimal(10,2) DEFAULT NULL,
  `kupovina_id` int NOT NULL,
  PRIMARY KEY (`turista_id`,`daily_pass_id`),
  KEY `daily_pass_id` (`daily_pass_id`),
  KEY `kupovina_id` (`kupovina_id`),
  CONSTRAINT `turista_daily_pass_ibfk_1` FOREIGN KEY (`turista_id`) REFERENCES `turista` (`id`),
  CONSTRAINT `turista_daily_pass_ibfk_2` FOREIGN KEY (`daily_pass_id`) REFERENCES `daily_pass` (`id`),
  CONSTRAINT `turista_daily_pass_ibfk_3` FOREIGN KEY (`kupovina_id`) REFERENCES `kupovina` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turista_daily_pass`
--

LOCK TABLES `turista_daily_pass` WRITE;
/*!40000 ALTER TABLE `turista_daily_pass` DISABLE KEYS */;
INSERT INTO `turista_daily_pass` VALUES (1,1,'2024-08-22','2024-08-25',80.00,2),(1,2,'2021-01-01','2021-01-10',200.00,3),(2,1,'2024-08-22','2024-08-25',300.00,3),(2,2,'2020-09-05','2020-09-09',100.00,5);
/*!40000 ALTER TABLE `turista_daily_pass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `znamenitost`
--

DROP TABLE IF EXISTS `znamenitost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `znamenitost` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(255) DEFAULT NULL,
  `opis` text,
  `slika` varchar(255) DEFAULT NULL,
  `admin` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `admin` (`admin`),
  CONSTRAINT `znamenitost_ibfk_1` FOREIGN KEY (`admin`) REFERENCES `admin` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `znamenitost`
--

LOCK TABLES `znamenitost` WRITE;
/*!40000 ALTER TABLE `znamenitost` DISABLE KEYS */;
INSERT INTO `znamenitost` VALUES (1,'ime1','jdijk','neka','Gvido'),(2,'ime2','kifj','druga','Gvido'),(4,'ime3','kifj','druga','Gvido'),(5,'ime3','Gvido je najbolji covjek ikada!','Gvidova slika','Gvido'),(6,'ime6','kifj','druga','Gvido');
/*!40000 ALTER TABLE `znamenitost` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-07 15:21:14
