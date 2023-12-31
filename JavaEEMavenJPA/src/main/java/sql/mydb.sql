CREATE DATABASE  IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mydb`;
-- MySQL dump 10.13  Distrib 8.0.26, for macos11 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `Composite_ACCOUNT`
--

DROP TABLE IF EXISTS `Composite_ACCOUNT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Composite_ACCOUNT` (
  `ACCOUNT_NUMBER` varchar(255) COLLATE utf8mb4_0900_as_ci NOT NULL,
  `ACCOUNT_TYPE` varchar(255) COLLATE utf8mb4_0900_as_ci NOT NULL,
  `ACCOUNT_NAME` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  PRIMARY KEY (`ACCOUNT_NUMBER`,`ACCOUNT_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Composite_ACCOUNT`
--

LOCK TABLES `Composite_ACCOUNT` WRITE;
/*!40000 ALTER TABLE `Composite_ACCOUNT` DISABLE KEYS */;
INSERT INTO `Composite_ACCOUNT` VALUES ('N001','NORMAL','Jim1'),('T001','TEMP','Jim2');
/*!40000 ALTER TABLE `Composite_ACCOUNT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Composite_Embed_BOOK`
--

DROP TABLE IF EXISTS `Composite_Embed_BOOK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Composite_Embed_BOOK` (
  `LANGUAGE` varchar(255) COLLATE utf8mb4_0900_as_ci NOT NULL,
  `TITLE` varchar(255) COLLATE utf8mb4_0900_as_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  PRIMARY KEY (`LANGUAGE`,`TITLE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Composite_Embed_BOOK`
--

LOCK TABLES `Composite_Embed_BOOK` WRITE;
/*!40000 ALTER TABLE `Composite_Embed_BOOK` DISABLE KEYS */;
INSERT INTO `Composite_Embed_BOOK` VALUES ('.Net Framework','Microsoft','Microsoft Programming Language'),('Java SE 8','Oracle','Java Programming Language');
/*!40000 ALTER TABLE `Composite_Embed_BOOK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_embedded`
--

DROP TABLE IF EXISTS `customer_embedded`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_embedded` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `purchased_date` datetime(6) DEFAULT NULL,
  `received_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_embedded`
--

LOCK TABLES `customer_embedded` WRITE;
/*!40000 ALTER TABLE `customer_embedded` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_embedded` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_item_qty_map`
--

DROP TABLE IF EXISTS `customer_item_qty_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_item_qty_map` (
  `customer_id` int NOT NULL,
  `itemQtyMap` int DEFAULT NULL,
  `itemQtyMap_KEY` varchar(255) COLLATE utf8mb4_0900_as_ci NOT NULL,
  PRIMARY KEY (`customer_id`,`itemQtyMap_KEY`),
  CONSTRAINT `FKsbo8g6rfd7tt97jl4s242ncy9` FOREIGN KEY (`customer_id`) REFERENCES `customer_embedded` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_item_qty_map`
--

LOCK TABLES `customer_item_qty_map` WRITE;
/*!40000 ALTER TABLE `customer_item_qty_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_item_qty_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `email` varchar(30) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21606 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'fff',NULL),(2,'batch_1',NULL),(3,'batch_2',NULL),(4,'batch_3',NULL),(5,'batch_4',NULL),(6,'batch_5',NULL),(7,'batch_6',NULL),(8,'batch_7',NULL),(9,'batch_8',NULL),(10,'batch_9',NULL),(11,'batch_10',NULL),(12,'batch_11',NULL),(13,'batch_12',NULL),(14,'batch_13',NULL),(15,'batch_14',NULL),(16,'batch_15',NULL),(17,'batch_16',NULL),(18,'batch_17',NULL),(19,'batch_18',NULL),(20,'batch_19',NULL),(21,'batch_20',NULL),(22,'batch_21',NULL),(23,'batch_22',NULL),(24,'batch_23',NULL),(25,'batch_24',NULL),(26,'batch_25',NULL),(27,'batch_26',NULL),(28,'batch_27',NULL),(29,'batch_28',NULL),(30,'batch_29',NULL),(31,'batch_30',NULL),(32,'batch_31',NULL),(33,'batch_32',NULL),(34,'batch_33',NULL),(35,'batch_34',NULL),(36,'batch_35',NULL),(37,'batch_36',NULL),(38,'batch_37',NULL),(39,'batch_38',NULL),(40,'batch_39',NULL),(41,'batch_40',NULL),(42,'batch_41',NULL),(43,'batch_42',NULL),(44,'batch_43',NULL),(45,'batch_44',NULL),(46,'batch_45',NULL),(47,'batch_46',NULL),(48,'batch_47',NULL),(49,'batch_48',NULL),(50,'batch_49',NULL),(51,'batch_0',NULL),(52,'batch_1',NULL),(53,'batch_2',NULL),(54,'batch_3',NULL),(55,'batch_4',NULL),(56,'batch_5',NULL),(57,'batch_6',NULL),(58,'batch_7',NULL),(59,'batch_8',NULL),(60,'batch_9',NULL),(61,'batch_10',NULL),(62,'batch_11',NULL),(63,'batch_12',NULL),(64,'batch_13',NULL),(65,'batch_14',NULL),(66,'batch_15',NULL),(67,'batch_16',NULL),(68,'batch_17',NULL),(69,'batch_18',NULL),(70,'batch_19',NULL),(71,'batch_20',NULL),(72,'batch_21',NULL),(73,'batch_22',NULL),(74,'batch_23',NULL),(75,'batch_24',NULL),(76,'batch_25',NULL),(77,'batch_26',NULL),(78,'batch_27',NULL),(79,'batch_28',NULL),(80,'batch_29',NULL),(81,'batch_30',NULL),(82,'batch_31',NULL),(83,'batch_32',NULL),(84,'batch_33',NULL),(85,'batch_34',NULL),(86,'batch_35',NULL),(87,'batch_36',NULL),(88,'batch_37',NULL),(89,'batch_38',NULL),(90,'batch_39',NULL),(91,'batch_40',NULL),(92,'batch_41',NULL),(93,'batch_42',NULL),(94,'batch_43',NULL),(95,'batch_44',NULL),(96,'batch_45',NULL),(97,'batch_46',NULL),(98,'batch_47',NULL),(99,'batch_48',NULL),(100,'batch_49',NULL),(101,'Ted','ted@gmail.com'),(102,'Ted','ted@gmail.com'),(103,'Ted','ted@gmail.com'),(104,'Ted','ted@gmail.com'),(105,'Ted','ted@gmail.com'),(106,'Ted','ted@gmail.com'),(107,'Ted','ted@gmail.com'),(108,'Ted','ted@gmail.com'),(109,'Ted','ted@gmail.com'),(110,'Ted','ted@gmail.com'),(111,'Ted','ted@gmail.com'),(112,'Ted','ted@gmail.com'),(10115,'qqiq','qqiq@gmail.com'),(10116,'tiger12','tiger12@gmail.com'),(10117,'tiger11','tiger11@gmail.com'),(11087,'James','james@hotmail.com'),(11088,'David','david@hotmail.com'),(11089,'Tom','tom@hotmail.com'),(11090,'Ted','ted@gmail.com');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_jpql_literal`
--

DROP TABLE IF EXISTS `employee_jpql_literal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_jpql_literal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `local_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `role` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `salary` int DEFAULT NULL,
  `valid` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_jpql_literal`
--

LOCK TABLES `employee_jpql_literal` WRITE;
/*!40000 ALTER TABLE `employee_jpql_literal` DISABLE KEYS */;
INSERT INTO `employee_jpql_literal` VALUES (1,'2015-11-15 00:00:00.000000','Jim','IT',3000,_binary ''),(2,'2017-05-01 00:00:00.000000','ROSE','ADMIN',4000,_binary '\0'),(3,'2016-01-10 00:00:00.000000','Denise','IT',1500,_binary ''),(4,'2015-11-15 00:00:00.000000','Jim','IT',3000,_binary ''),(5,'2017-05-01 00:00:00.000000','ROSE','ADMIN',4000,_binary '\0'),(6,'2016-01-10 00:00:00.000000','Denise','IT',1500,_binary ''),(7,'2015-11-15 00:00:00.000000','Jim','IT',3000,_binary ''),(8,'2017-05-01 00:00:00.000000','ROSE','ADMIN',4000,_binary '\0'),(9,'2016-01-10 00:00:00.000000','Denise','IT',1500,_binary ''),(10,'2015-11-15 00:00:00.000000','Jim','IT',3000,_binary ''),(11,'2017-05-01 00:00:00.000000','ROSE','ADMIN',4000,_binary '\0'),(12,'2016-01-10 00:00:00.000000','Denise','IT',1500,_binary ''),(13,'2015-11-15 00:00:00.000000','Jim','IT',3000,_binary ''),(14,'2017-05-01 00:00:00.000000','ROSE','ADMIN',4000,_binary '\0'),(15,'2016-01-10 00:00:00.000000','Denise','IT',1500,_binary ''),(16,'2015-11-15 00:00:00.000000','Jim','IT',3000,_binary ''),(17,'2017-05-01 00:00:00.000000','ROSE','ADMIN',4000,_binary '\0'),(18,'2016-01-10 00:00:00.000000','Denise','IT',1500,_binary ''),(19,'2015-11-15 00:00:00.000000','Jim','IT',3000,_binary ''),(20,'2017-05-01 00:00:00.000000','ROSE','ADMIN',4000,_binary '\0'),(21,'2016-01-10 00:00:00.000000','Denise','IT',1500,_binary ''),(22,'2015-11-15 00:00:00.000000','Jim','IT',3000,_binary ''),(23,'2017-05-01 00:00:00.000000','ROSE','ADMIN',4000,_binary '\0'),(24,'2016-01-10 00:00:00.000000','Denise','IT',1500,_binary ''),(25,'2015-11-15 00:00:00.000000','Jim','IT',3000,_binary ''),(26,'2017-05-01 00:00:00.000000','ROSE','ADMIN',4000,_binary '\0'),(27,'2016-01-10 00:00:00.000000','Denise','IT',1500,_binary ''),(28,'2015-11-15 00:00:00.000000','Jim','IT',3000,_binary ''),(29,'2017-05-01 00:00:00.000000','ROSE','ADMIN',4000,_binary '\0'),(30,'2016-01-10 00:00:00.000000','Denise','IT',1500,_binary ''),(31,'2015-11-15 00:00:00.000000','Jim','IT',3000,_binary ''),(32,'2017-05-01 00:00:00.000000','ROSE','ADMIN',4000,_binary '\0'),(33,'2016-01-10 00:00:00.000000','Denise','IT',1500,_binary ''),(34,'2015-11-15 00:00:00.000000','Jim','IT',3000,_binary ''),(35,'2017-05-01 00:00:00.000000','ROSE','ADMIN',4000,_binary '\0'),(36,'2016-01-10 00:00:00.000000','Denise','IT',1500,_binary ''),(37,'2015-11-15 00:00:00.000000','Jim','IT',3000,_binary ''),(38,'2017-05-01 00:00:00.000000','ROSE','ADMIN',4000,_binary '\0'),(39,'2016-01-10 00:00:00.000000','Denise','IT',1500,_binary ''),(40,'2015-11-15 00:00:00.000000','Jim','IT',3000,_binary ''),(41,'2017-05-01 00:00:00.000000','ROSE','ADMIN',4000,_binary '\0'),(42,'2016-01-10 00:00:00.000000','Denise','IT',1500,_binary ''),(43,'2015-11-15 00:00:00.000000','Jim','IT',3000,_binary ''),(44,'2017-05-01 00:00:00.000000','ROSE','ADMIN',4000,_binary '\0'),(45,'2016-01-10 00:00:00.000000','Denise','IT',1500,_binary ''),(46,'2015-11-15 00:00:00.000000','Jim','IT',3000,_binary ''),(47,'2017-05-01 00:00:00.000000','ROSE','ADMIN',4000,_binary '\0'),(48,'2016-01-10 00:00:00.000000','Denise','IT',1500,_binary ''),(49,'2015-11-15 00:00:00.000000','Jim','IT',3000,_binary ''),(50,'2017-05-01 00:00:00.000000','ROSE','ADMIN',4000,_binary '\0'),(51,'2016-01-10 00:00:00.000000','Denise','IT',1500,_binary ''),(52,'2015-11-15 00:00:00.000000','Jim','IT',3000,_binary ''),(53,'2017-05-01 00:00:00.000000','ROSE','ADMIN',4000,_binary '\0'),(54,'2016-01-10 00:00:00.000000','Denise','IT',1500,_binary '');
/*!40000 ALTER TABLE `employee_jpql_literal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_native_literal`
--

DROP TABLE IF EXISTS `employee_native_literal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_native_literal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `local_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `role` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `salary` int DEFAULT NULL,
  `valid` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_native_literal`
--

LOCK TABLES `employee_native_literal` WRITE;
/*!40000 ALTER TABLE `employee_native_literal` DISABLE KEYS */;
INSERT INTO `employee_native_literal` VALUES (1,'2015-11-15 00:00:00.000000','Jim','IT',3000,_binary ''),(2,'2017-05-01 00:00:00.000000','ROSE','ADMIN',4000,_binary '\0'),(3,'2016-01-10 00:00:00.000000','Denise','IT',1500,_binary '');
/*!40000 ALTER TABLE `employee_native_literal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (5);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `id_card`
--

DROP TABLE IF EXISTS `id_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `id_card` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_number` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `issue_date` datetime(6) DEFAULT NULL,
  `valid` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `id_card`
--

LOCK TABLES `id_card` WRITE;
/*!40000 ALTER TABLE `id_card` DISABLE KEYS */;
INSERT INTO `id_card` VALUES (1,'B12345678','2023-08-25 17:00:00.491000',1),(2,'682932742','2023-08-25 16:59:41.644000',1),(3,'999999','2023-08-31 10:16:44.200000',1),(4,'999999','2023-08-31 11:14:56.704000',1),(5,'999999','2023-08-31 11:28:36.602000',1),(6,'999999','2023-08-31 13:08:38.843000',1),(7,'999999','2023-08-31 13:09:11.589000',1),(8,'999999','2023-08-31 13:09:44.973000',1),(9,'999999','2023-08-31 13:12:09.747000',1),(10,'999999','2023-08-31 13:12:10.183000',1),(11,'999999','2023-08-31 13:12:10.546000',1),(12,'999999','2023-08-31 13:12:10.903000',1),(13,'999999','2023-08-31 13:12:11.187000',1),(14,'999999','2023-08-31 13:12:11.501000',1),(15,'999999','2023-08-31 13:12:11.800000',1),(16,'999999','2023-08-31 13:12:12.055000',1),(17,'999999','2023-08-31 13:12:12.330000',1),(18,'999999','2023-08-31 13:12:12.586000',1),(19,'999999','2023-08-31 13:12:12.951000',1),(20,'999999','2023-08-31 13:12:13.333000',1),(21,'999999','2023-08-31 13:12:13.599000',1),(22,'999999','2023-08-31 13:12:13.831000',1),(23,'999999','2023-08-31 13:12:14.072000',1),(24,'999999','2023-08-31 13:12:14.375000',1),(25,'999999','2023-08-31 13:12:14.724000',1),(26,'999999','2023-08-31 13:12:14.951000',1),(27,'999999','2023-08-31 13:12:15.231000',1),(28,'999999','2023-08-31 13:12:15.580000',1),(29,'999999','2023-08-31 13:12:15.827000',1),(30,'999999','2023-08-31 13:12:16.004000',1),(31,'999999','2023-08-31 13:12:16.186000',1),(32,'999999','2023-08-31 13:12:16.380000',1),(33,'999999','2023-08-31 13:12:16.579000',1),(34,'999999','2023-08-31 13:12:16.753000',1),(35,'999999','2023-08-31 13:12:16.892000',1),(36,'999999','2023-08-31 13:12:17.045000',1),(37,'999999','2023-08-31 13:12:17.225000',1),(38,'999999','2023-08-31 13:12:17.439000',1),(39,'999999','2023-08-31 13:12:17.657000',1),(40,'999999','2023-08-31 13:12:18.010000',1),(41,'999999','2023-08-31 13:12:18.195000',1),(42,'999999','2023-08-31 13:12:18.354000',1),(43,'999999','2023-08-31 13:12:18.500000',1),(44,'999999','2023-08-31 13:12:18.682000',1),(45,'999999','2023-08-31 13:12:18.824000',1),(46,'999999','2023-08-31 13:12:18.988000',1),(47,'999999','2023-08-31 13:12:19.142000',1),(48,'999999','2023-08-31 13:12:19.300000',1),(49,'999999','2023-08-31 13:12:19.467000',1),(50,'999999','2023-08-31 13:12:19.615000',1),(51,'999999','2023-08-31 13:12:19.774000',1),(52,'999999','2023-08-31 13:12:20.165000',1),(53,'999999','2023-08-31 13:12:20.385000',1),(54,'999999','2023-08-31 13:12:20.533000',1),(55,'999999','2023-08-31 13:12:20.659000',1),(56,'999999','2023-08-31 13:12:20.771000',1),(57,'999999','2023-08-31 13:12:20.936000',1),(58,'999999','2023-08-31 13:12:21.067000',1),(59,'999999','2023-08-31 13:12:21.233000',1),(60,'999999','2023-08-31 13:12:21.502000',1),(61,'999999','2023-08-31 13:12:21.725000',1),(62,'999999','2023-08-31 13:12:21.842000',1),(63,'999999','2023-08-31 13:12:21.991000',1),(64,'999999','2023-08-31 13:12:22.097000',1),(65,'999999','2023-08-31 13:12:22.216000',1),(66,'999999','2023-08-31 13:12:22.346000',1),(67,'999999','2023-08-31 13:12:22.480000',1),(68,'999999','2023-08-31 13:12:22.601000',1),(69,'999999','2023-08-31 13:12:22.742000',1),(70,'999999','2023-08-31 13:12:23.016000',1),(71,'999999','2023-08-31 13:12:23.137000',1),(72,'999999','2023-08-31 13:12:23.277000',1),(73,'999999','2023-08-31 13:12:23.444000',1),(74,'999999','2023-08-31 13:12:23.618000',1),(75,'999999','2023-08-31 13:12:23.718000',1),(76,'999999','2023-08-31 13:12:23.813000',1),(77,'999999','2023-08-31 13:12:23.923000',1),(78,'999999','2023-08-31 13:12:24.085000',1),(79,'999999','2023-08-31 13:12:24.195000',1),(80,'999999','2023-08-31 13:12:24.309000',1),(81,'999999','2023-08-31 13:12:24.424000',1),(82,'999999','2023-08-31 13:12:24.522000',1),(83,'999999','2023-08-31 13:12:24.624000',1),(84,'999999','2023-08-31 13:12:24.740000',1),(85,'999999','2023-08-31 13:12:24.873000',1),(86,'999999','2023-08-31 13:12:24.982000',1),(87,'999999','2023-08-31 13:12:25.102000',1),(88,'999999','2023-08-31 13:12:25.324000',1),(89,'999999','2023-08-31 13:12:25.547000',1),(90,'999999','2023-08-31 13:12:25.732000',1),(91,'999999','2023-08-31 13:12:25.838000',1),(92,'999999','2023-08-31 13:12:25.937000',1),(93,'999999','2023-08-31 13:12:26.057000',1),(94,'999999','2023-08-31 13:12:26.205000',1),(95,'999999','2023-08-31 13:12:26.443000',1),(96,'999999','2023-08-31 13:12:26.636000',1),(97,'999999','2023-08-31 13:12:26.762000',1),(98,'999999','2023-08-31 13:12:26.866000',1),(99,'999999','2023-08-31 13:12:26.970000',1),(100,'999999','2023-08-31 13:12:27.051000',1),(101,'999999','2023-08-31 13:12:27.155000',1),(102,'999999','2023-08-31 13:12:27.251000',1),(103,'999999','2023-08-31 13:12:27.346000',1),(104,'999999','2023-08-31 13:12:27.486000',1),(105,'999999','2023-08-31 13:12:27.616000',1),(106,'999999','2023-08-31 13:12:27.729000',1),(107,'999999','2023-08-31 13:12:27.830000',1),(108,'999999','2023-08-31 13:12:27.931000',1),(109,'999999','2023-08-31 13:12:28.038000',1),(110,'999999','2023-08-31 13:17:36.623000',1),(111,'999999','2023-08-31 13:17:37.051000',1),(112,'999999','2023-08-31 13:17:37.409000',1),(113,'999999','2023-08-31 13:17:37.774000',1),(114,'999999','2023-08-31 13:17:38.084000',1),(115,'999999','2023-08-31 13:17:38.390000',1),(116,'999999','2023-08-31 13:17:38.691000',1),(117,'999999','2023-08-31 13:17:38.946000',1),(118,'999999','2023-08-31 13:17:39.227000',1),(119,'999999','2023-08-31 13:17:39.558000',1),(120,'999999','2023-08-31 13:17:39.872000',1),(121,'999999','2023-08-31 13:17:40.274000',1),(122,'999999','2023-08-31 13:17:40.555000',1),(123,'999999','2023-08-31 13:17:40.790000',1),(124,'999999','2023-08-31 13:17:41.040000',1),(125,'999999','2023-08-31 13:17:41.242000',1),(126,'999999','2023-08-31 13:17:41.482000',1),(127,'999999','2023-08-31 13:17:41.668000',1),(128,'999999','2023-08-31 13:17:42.002000',1),(129,'999999','2023-08-31 13:17:42.348000',1),(130,'999999','2023-08-31 13:59:39.964000',1),(131,'999999','2023-08-31 14:12:17.514000',1),(132,'999999','2023-08-31 14:28:58.473000',1),(133,'999999','2023-08-31 15:01:39.131000',1),(134,'999999','2023-08-31 15:08:34.378000',1),(135,'999999','2023-09-03 16:44:22.372000',1),(136,'999999','2023-09-03 16:47:05.700000',1),(137,'123456789','2023-09-03 22:47:07.563000',1),(138,'123456789','2023-09-03 22:48:13.103000',1);
/*!40000 ALTER TABLE `id_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_info`
--

DROP TABLE IF EXISTS `job_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `department` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `position` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_info`
--

LOCK TABLES `job_info` WRITE;
/*!40000 ALTER TABLE `job_info` DISABLE KEYS */;
INSERT INTO `job_info` VALUES (1,'R&D','Developer'),(2,'Sales','Marketing Development'),(3,'ME','Developer'),(4,'Logistics','Logistics for Warehouse'),(9,'N/A','N/A');
/*!40000 ALTER TABLE `job_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `owner`
--

DROP TABLE IF EXISTS `owner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `owner` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `owner`
--

LOCK TABLES `owner` WRITE;
/*!40000 ALTER TABLE `owner` DISABLE KEYS */;
INSERT INTO `owner` VALUES (1,'james');
/*!40000 ALTER TABLE `owner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `owner_t`
--

DROP TABLE IF EXISTS `owner_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `owner_t` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `owner_t`
--

LOCK TABLES `owner_t` WRITE;
/*!40000 ALTER TABLE `owner_t` DISABLE KEYS */;
INSERT INTO `owner_t` VALUES (1,'james'),(2,'james');
/*!40000 ALTER TABLE `owner_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `DTYPE` varchar(31) COLLATE utf8mb4_0900_as_ci NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `PROG_LANG` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES ('Developer',1,'James','Liao','C#'),('200',2,'Ted','Wang','Cobol'),('200',3,'James','Liao','C#'),('200',4,'David','Nyon','Java'),('200',5,'Tom','Duck','C++'),('Person',6,'person','Go',NULL),('200',7,NULL,NULL,'Ajax'),('200',8,'James','Liao','C#'),('200',9,'David','Nyon','Java'),('200',10,'Tom','Duck','C++'),('Person',11,'person','Go',NULL),('200',12,NULL,NULL,'Ajax');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_many`
--

DROP TABLE IF EXISTS `person_many`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person_many` (
  `DTYPE` varchar(31) COLLATE utf8mb4_0900_as_ci NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `prog_lang` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `id_card_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1yla88ij0ci4mbypgkscyhaca` (`id_card_id`),
  CONSTRAINT `FK1yla88ij0ci4mbypgkscyhaca` FOREIGN KEY (`id_card_id`) REFERENCES `id_card` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_many`
--

LOCK TABLES `person_many` WRITE;
/*!40000 ALTER TABLE `person_many` DISABLE KEYS */;
INSERT INTO `person_many` VALUES ('Designer',1,'James','Gruicci','.NET',136);
/*!40000 ALTER TABLE `person_many` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_project`
--

DROP TABLE IF EXISTS `person_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person_project` (
  `project_id` int NOT NULL,
  `designer_id` int NOT NULL,
  KEY `FKbptk3m22oahhg6yletw7mv3of` (`designer_id`),
  KEY `FKcb0682fxd4idjr270yh43sona` (`project_id`),
  CONSTRAINT `FKbptk3m22oahhg6yletw7mv3of` FOREIGN KEY (`designer_id`) REFERENCES `project` (`id`),
  CONSTRAINT `FKcb0682fxd4idjr270yh43sona` FOREIGN KEY (`project_id`) REFERENCES `person_many` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_project`
--

LOCK TABLES `person_project` WRITE;
/*!40000 ALTER TABLE `person_project` DISABLE KEYS */;
INSERT INTO `person_project` VALUES (1,2),(1,3);
/*!40000 ALTER TABLE `person_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phones`
--

DROP TABLE IF EXISTS `phones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `number` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `owner_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfo09c30243b5yhjm04wcda8t4` (`owner_id`),
  CONSTRAINT `FKfo09c30243b5yhjm04wcda8t4` FOREIGN KEY (`owner_id`) REFERENCES `owner` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phones`
--

LOCK TABLES `phones` WRITE;
/*!40000 ALTER TABLE `phones` DISABLE KEYS */;
INSERT INTO `phones` VALUES (1,'iPhone14','0920890890',1),(2,'Samsung Galaxy','0936987987',1);
/*!40000 ALTER TABLE `phones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phones_t`
--

DROP TABLE IF EXISTS `phones_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phones_t` (
  `id` int NOT NULL,
  `brand_name` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `number` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `owner_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK11chyxj97du8dbg7jnin1ljqt` (`owner_id`),
  CONSTRAINT `FK11chyxj97du8dbg7jnin1ljqt` FOREIGN KEY (`owner_id`) REFERENCES `owner_t` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phones_t`
--

LOCK TABLES `phones_t` WRITE;
/*!40000 ALTER TABLE `phones_t` DISABLE KEYS */;
INSERT INTO `phones_t` VALUES (1,'iPhone14','0920890890',1),(2,'Samsung Galaxy','0936987987',1),(52,'iPhone14','0920890890',2),(53,'Samsung Galaxy','0936987987',2);
/*!40000 ALTER TABLE `phones_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pk_auto`
--

DROP TABLE IF EXISTS `pk_auto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pk_auto` (
  `id` int NOT NULL,
  `first_name` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pk_auto`
--

LOCK TABLES `pk_auto` WRITE;
/*!40000 ALTER TABLE `pk_auto` DISABLE KEYS */;
INSERT INTO `pk_auto` VALUES (2,'James','Liao'),(3,'James','Liao'),(4,'James','Liao');
/*!40000 ALTER TABLE `pk_auto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pk_identity`
--

DROP TABLE IF EXISTS `pk_identity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pk_identity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pk_identity`
--

LOCK TABLES `pk_identity` WRITE;
/*!40000 ALTER TABLE `pk_identity` DISABLE KEYS */;
INSERT INTO `pk_identity` VALUES (1,'James','Liao'),(2,'James','Liao'),(3,'James','Liao'),(4,'James','Liao');
/*!40000 ALTER TABLE `pk_identity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PK_S`
--

DROP TABLE IF EXISTS `PK_S`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PK_S` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PK_S`
--

LOCK TABLES `PK_S` WRITE;
/*!40000 ALTER TABLE `PK_S` DISABLE KEYS */;
INSERT INTO `PK_S` VALUES (401);
/*!40000 ALTER TABLE `PK_S` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pk_sequence`
--

DROP TABLE IF EXISTS `pk_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pk_sequence` (
  `id` int NOT NULL,
  `first_name` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pk_sequence`
--

LOCK TABLES `pk_sequence` WRITE;
/*!40000 ALTER TABLE `pk_sequence` DISABLE KEYS */;
INSERT INTO `pk_sequence` VALUES (1,'James','Liao'),(102,'James','Liao'),(202,'James','Liao');
/*!40000 ALTER TABLE `pk_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pk_table`
--

DROP TABLE IF EXISTS `pk_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pk_table` (
  `id` int NOT NULL,
  `first_name` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pk_table`
--

LOCK TABLES `pk_table` WRITE;
/*!40000 ALTER TABLE `pk_table` DISABLE KEYS */;
INSERT INTO `pk_table` VALUES (1,'James','Liao'),(52,'James','Liao'),(102,'James','Liao'),(152,'James','Liao'),(202,'James','Liao');
/*!40000 ALTER TABLE `pk_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PK_TABLE_SEQUENCES`
--

DROP TABLE IF EXISTS `PK_TABLE_SEQUENCES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PK_TABLE_SEQUENCES` (
  `SEQ_NAME` varchar(255) COLLATE utf8mb4_0900_as_ci NOT NULL,
  `next_val` bigint DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PK_TABLE_SEQUENCES`
--

LOCK TABLES `PK_TABLE_SEQUENCES` WRITE;
/*!40000 ALTER TABLE `PK_TABLE_SEQUENCES` DISABLE KEYS */;
INSERT INTO `PK_TABLE_SEQUENCES` VALUES ('PHONE',301);
/*!40000 ALTER TABLE `PK_TABLE_SEQUENCES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile` (
  `id` int NOT NULL AUTO_INCREMENT,
  `department` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `phone_number` int DEFAULT NULL,
  `salary` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (1,'R&D','james',988909909,7892),(2,'R&D','david',2098763,8280),(3,'R&D','marry',92873134,9980),(4,'R&D','tim',987424,10540),(5,'R&D','tom',902342342,123480),(6,'Sales','zoy',188909909,2892),(7,'Sales','Judy',32098763,8280),(8,'ME','Neo',92873134,9980),(9,'ME','ted',87424,15540),(10,'Logistics','Linda',802342342,12324),(11,'R&D','james',988909909,7892),(12,'R&D','david',2098763,8280),(13,'R&D','marry',92873134,9980),(14,'R&D','tim',987424,10540),(15,'R&D','tom',902342342,123480),(16,'Sales','zoy',188909909,2892),(17,'Sales','Judy',32098763,8280),(18,'ME','Neo',92873134,9980),(19,'ME','ted',87424,15540),(20,'Logistics','Linda',802342342,12324);
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `id` int NOT NULL AUTO_INCREMENT,
  `end_date` datetime(6) DEFAULT NULL,
  `projectType` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'2019-12-31 09:30:00.000000','LONG','2019-08-01 09:00:00.000000','Java Project'),(2,'2023-09-03 16:47:05.700000',NULL,'2023-09-03 16:47:05.700000','Fido - Transaction'),(3,'2023-09-03 16:47:05.700000','LONG','2023-09-03 16:47:05.700000','ChatGPT - OpenAPI'),(4,'2019-12-31 09:30:00.000000','LONG','2019-08-01 09:00:00.000000','Java Project');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_embedded`
--

DROP TABLE IF EXISTS `project_embedded`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_embedded` (
  `id` int NOT NULL AUTO_INCREMENT,
  `END_DATE` datetime(6) DEFAULT NULL,
  `START_DATE` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_embedded`
--

LOCK TABLES `project_embedded` WRITE;
/*!40000 ALTER TABLE `project_embedded` DISABLE KEYS */;
INSERT INTO `project_embedded` VALUES (1,NULL,NULL),(2,NULL,NULL),(3,'2023-12-01 09:30:00.000000','2023-09-01 09:00:00.000000'),(4,NULL,NULL);
/*!40000 ALTER TABLE `project_embedded` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchased_order`
--

DROP TABLE IF EXISTS `purchased_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchased_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchased_order`
--

LOCK TABLES `purchased_order` WRITE;
/*!40000 ALTER TABLE `purchased_order` DISABLE KEYS */;
INSERT INTO `purchased_order` VALUES (1,'iPhone15',3000),(2,'iPhone15',3000),(3,'iPhone14',2500),(4,'iPhone13',1500),(5,'iPhone12',1000),(6,'iPhone15',3000),(7,'iPhone14',2500),(8,'iPhone13',1500),(9,'iPhone12',1000),(10,'iPhone15',3000),(11,'iPhone14',2500),(12,'iPhone13',1500),(13,'iPhone12',1000),(14,'iPhone15',3000),(15,'iPhone14',2500),(16,'iPhone13',1500),(17,'iPhone12',1000),(18,'iPhone15',3000),(19,'iPhone14',2500),(20,'iPhone13',1500),(21,'iPhone12',1000),(22,'iPhone15',3000),(23,'iPhone14',2500),(24,'iPhone13',1500),(25,'iPhone12',1000),(26,'iPhone15',3000),(27,'iPhone14',2500),(28,'iPhone13',1500),(29,'iPhone12',1000),(30,'iPhone15',3000),(31,'iPhone14',2500),(32,'iPhone13',1500),(33,'iPhone12',1000),(34,'iPhone15',3000),(35,'iPhone14',2500),(36,'iPhone13',1500),(37,'iPhone12',1000),(38,'iPhone15',3000),(39,'iPhone14',2500),(40,'iPhone13',1500),(41,'iPhone12',1000),(42,'iPhone15',3000),(43,'iPhone14',2500),(44,'iPhone13',1500),(45,'iPhone12',1000),(46,'iPhone15',3000),(47,'iPhone14',2500),(48,'iPhone13',1500),(49,'iPhone12',1000),(50,'iPhone15',3000),(51,'iPhone14',2500),(52,'iPhone13',1500),(53,'iPhone12',1000),(54,'iPhone15',3000),(55,'iPhone14',2500),(56,'iPhone13',1500),(57,'iPhone12',1000),(58,'iPhone15',3000),(59,'iPhone14',2500),(60,'iPhone13',1500),(61,'iPhone12',1000),(62,'iPhone15',3183),(63,'iPhone15',3000),(64,'iPhone14',2500),(65,'iPhone13',1500),(66,'iPhone12',1000),(67,'iPhone15',3183),(68,'iPhone15',3000),(69,'iPhone14',2500),(70,'iPhone13',1500),(71,'iPhone12',1000),(72,'iPhone15',3183),(73,'iPhone15',3000),(74,'iPhone14',2500),(75,'iPhone13',1500),(76,'iPhone12',1000),(77,'iPhone15',3183),(78,'iPhone15',3000),(79,'iPhone14',2500),(80,'iPhone13',1500),(81,'iPhone12',1000),(82,'iPhone15',3183),(83,'iPhone15',3000),(84,'iPhone14',2500),(85,'iPhone13',1500),(86,'iPhone12',1000),(87,'iPhone15',3183),(88,'iPhone15',3000),(89,'iPhone14',2500),(90,'iPhone13',1500),(91,'iPhone12',1000),(92,'iPhone15',3183),(93,'iPhone15',3000),(94,'iPhone14',2500),(95,'iPhone13',1500),(96,'iPhone12',1000),(97,'iPhone15',3183),(98,'iPhone15',3000),(99,'iPhone14',2500),(100,'iPhone13',1500),(101,'iPhone12',1000),(102,'iPhone15',3183),(103,'iPhone15',3000),(104,'iPhone14',2500),(105,'iPhone13',1500),(106,'iPhone12',1000),(107,'iPhone15',3183),(108,'iPhone15',3000),(109,'iPhone14',2500),(110,'iPhone13',1500),(111,'iPhone12',1000),(112,'iPhone15',3183),(113,'iPhone15',3000),(114,'iPhone14',2500),(115,'iPhone13',1500),(116,'iPhone12',1000),(117,'iPhone15',3183),(118,'iPhone15',3000),(119,'iPhone14',2500),(120,'iPhone13',1500),(121,'iPhone12',1000),(122,'iPhone15',3183),(123,'iPhone15',3000),(124,'iPhone14',2500),(125,'iPhone13',1500),(126,'iPhone12',1000),(127,'iPhone15',3183),(128,'iPhone15',3000),(129,'iPhone14',2500),(130,'iPhone13',1500),(131,'iPhone12',1000),(132,'iPhone15',3183),(133,'iPhone15',3000),(134,'iPhone14',2500),(135,'iPhone13',1500),(136,'iPhone12',1000),(137,'iPhone15',3183),(138,'iPhone15',3000),(139,'iPhone14',2500),(140,'iPhone13',1500),(141,'iPhone12',1000),(142,'iPhone15',3183),(143,'iPhone15',3000),(144,'iPhone14',2500),(145,'iPhone13',1500),(146,'iPhone12',1000),(147,'iPhone15',3183),(148,'iPhone15',3000),(149,'iPhone14',2500),(150,'iPhone13',1500),(151,'iPhone12',1000),(152,'iPhone15',3183);
/*!40000 ALTER TABLE `purchased_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `S_PROJECT`
--

DROP TABLE IF EXISTS `S_PROJECT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `S_PROJECT` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `S_PROJECT`
--

LOCK TABLES `S_PROJECT` WRITE;
/*!40000 ALTER TABLE `S_PROJECT` DISABLE KEYS */;
INSERT INTO `S_PROJECT` VALUES (21);
/*!40000 ALTER TABLE `S_PROJECT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_BILLING_PERIOD`
--

DROP TABLE IF EXISTS `T_BILLING_PERIOD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `T_BILLING_PERIOD` (
  `PROJECT_ID` int NOT NULL,
  `END_DATE` datetime(6) DEFAULT NULL,
  `START_DATE` datetime(6) DEFAULT NULL,
  KEY `FKn4o0q6sglrao5nkiy1dkgko0l` (`PROJECT_ID`),
  CONSTRAINT `FKn4o0q6sglrao5nkiy1dkgko0l` FOREIGN KEY (`PROJECT_ID`) REFERENCES `project_embedded` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_BILLING_PERIOD`
--

LOCK TABLES `T_BILLING_PERIOD` WRITE;
/*!40000 ALTER TABLE `T_BILLING_PERIOD` DISABLE KEYS */;
INSERT INTO `T_BILLING_PERIOD` VALUES (1,'2020-01-31 09:30:00.000000','2020-01-01 09:00:00.000000'),(1,'2020-03-02 09:30:00.000000','2020-02-01 09:00:00.000000'),(2,'2020-01-31 09:30:00.000000','2020-01-01 09:00:00.000000'),(2,'2020-03-02 09:30:00.000000','2020-02-01 09:00:00.000000'),(4,'2023-12-01 09:30:00.000000','2023-11-01 09:00:00.000000'),(4,'2024-01-01 09:30:00.000000','2023-10-01 09:00:00.000000');
/*!40000 ALTER TABLE `T_BILLING_PERIOD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `T_SEQUENCES`
--

DROP TABLE IF EXISTS `T_SEQUENCES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `T_SEQUENCES` (
  `SEQ_NAME` varchar(255) COLLATE utf8mb4_0900_as_ci NOT NULL,
  `SEQ_VALUE` bigint DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `T_SEQUENCES`
--

LOCK TABLES `T_SEQUENCES` WRITE;
/*!40000 ALTER TABLE `T_SEQUENCES` DISABLE KEYS */;
INSERT INTO `T_SEQUENCES` VALUES ('PHONE',151);
/*!40000 ALTER TABLE `T_SEQUENCES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test` (
  `id` int DEFAULT NULL,
  `name` varchar(10) COLLATE utf8mb4_0900_as_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (1,'s'),(1,'s'),(1,'s'),(1,'s'),(1,'s'),(1,'s');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `DTYPE` varchar(31) COLLATE utf8mb4_0900_as_ci NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `PROG_LANG` varchar(255) COLLATE utf8mb4_0900_as_ci DEFAULT NULL,
  `id_card_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2y8xk8loi3isby5ju3eg7xan1` (`id_card_id`),
  CONSTRAINT `FK2y8xk8loi3isby5ju3eg7xan1` FOREIGN KEY (`id_card_id`) REFERENCES `id_card` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('User',1,'Moris','Chang',NULL,1),('Programming',2,'Tim','Liao','C#',NULL),('Programming',3,'Ted','Go','Java',2),('User',5,'Ted','Wang',NULL,137),('User',6,'Ted','Wang',NULL,138);
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

-- Dump completed on 2023-09-12 17:27:34
