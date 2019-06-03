-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: jdl_accounts
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `clients` (
  `client_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_lastname` varchar(50) DEFAULT NULL,
  `client_firstname` varchar(50) DEFAULT NULL,
  `client_nationality` varchar(50) DEFAULT NULL,
  `client_birthdate` date DEFAULT NULL,
  `client_gender` varchar(15) DEFAULT NULL,
  `client_company` varchar(70) DEFAULT NULL,
  `client_position` varchar(70) DEFAULT NULL,
  `client_alias` varchar(25) DEFAULT NULL,
  `client_contact` varchar(30) DEFAULT NULL,
  `client_email` varchar(70) DEFAULT NULL,
  `client_action` varchar(50) DEFAULT NULL,
  `client_isActive` int(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `client_id_UNIQUE` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employees` (
  `emp_lastname` varchar(50) DEFAULT NULL,
  `emp_firstname` varchar(50) DEFAULT NULL,
  `emp_position` varchar(50) DEFAULT NULL,
  `emp_gender` varchar(15) DEFAULT NULL,
  `emp_birthdate` date DEFAULT NULL,
  `emp_address` varchar(150) DEFAULT NULL,
  `emp_contact` varchar(30) DEFAULT NULL,
  `emp_email` varchar(70) DEFAULT NULL,
  `emp_isActive` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `notifications` (
  `notif_date` date NOT NULL,
  UNIQUE KEY `notif_date_UNIQUE` (`notif_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `remarks`
--

DROP TABLE IF EXISTS `remarks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `remarks` (
  `remarks_dateReceived` date DEFAULT NULL,
  `remarks_dateUpdated` date DEFAULT NULL,
  `remarks_reminders` varchar(50) DEFAULT NULL,
  `remarks_toDo` varchar(50) DEFAULT NULL,
  `remarks_transaction` varchar(50) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `trans_transId` int(11) NOT NULL,
  PRIMARY KEY (`trans_transId`),
  UNIQUE KEY `trans_id_UNIQUE` (`trans_transId`),
  KEY `remarks_ibfk_1` (`client_id`),
  CONSTRAINT `remarks_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remarks`
--

LOCK TABLES `remarks` WRITE;
/*!40000 ALTER TABLE `remarks` DISABLE KEYS */;
/*!40000 ALTER TABLE `remarks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_permits`
--

DROP TABLE IF EXISTS `status_permits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `status_permits` (
  `statusP_dateReceived` date DEFAULT NULL,
  `statusP_instructions` varchar(100) DEFAULT NULL,
  `statusP_aepCancellation` varchar(70) DEFAULT NULL,
  `statusP_downgrading` varchar(70) DEFAULT NULL,
  `statusP_aepExitClearance` varchar(70) DEFAULT NULL,
  `statusP_updatedVisaExtend` varchar(70) DEFAULT NULL,
  `statusP_documentation` varchar(70) DEFAULT NULL,
  `statusP_addRequirements` varchar(70) DEFAULT NULL,
  `statusP_aepDateFiled` date DEFAULT NULL,
  `statusP_aepDateRelease` date DEFAULT NULL,
  `statusP_permitDateFiled` date DEFAULT NULL,
  `statusP_acrIcard` varchar(70) DEFAULT NULL,
  `statusP_permitDateReleased` date DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `trans_transId` int(11) NOT NULL,
  PRIMARY KEY (`trans_transId`),
  UNIQUE KEY `trans_transId_UNIQUE` (`trans_transId`),
  KEY `client_id` (`client_id`),
  CONSTRAINT `status_permits_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_permits`
--

LOCK TABLES `status_permits` WRITE;
/*!40000 ALTER TABLE `status_permits` DISABLE KEYS */;
/*!40000 ALTER TABLE `status_permits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_visa`
--

DROP TABLE IF EXISTS `status_visa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `status_visa` (
  `statusV_documentation` varchar(70) DEFAULT NULL,
  `statusV_dateFiled` date DEFAULT NULL,
  `statusV_immigrant` varchar(70) DEFAULT NULL,
  `statusV_hearingDate` date DEFAULT NULL,
  `statusV_earlyHearing` date DEFAULT NULL,
  `statusV_agenda` varchar(70) DEFAULT NULL,
  `statusV_visaReleased` varchar(70) DEFAULT NULL,
  `statusV_waiverECC` varchar(70) DEFAULT NULL,
  `statusV_acrIcard` varchar(70) DEFAULT NULL,
  `statusV_docComplete` varchar(70) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `trans_transId` int(11) NOT NULL,
  PRIMARY KEY (`trans_transId`),
  UNIQUE KEY `trans_transId_UNIQUE` (`trans_transId`),
  KEY `client_id` (`client_id`),
  CONSTRAINT `status_visa_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_visa`
--

LOCK TABLES `status_visa` WRITE;
/*!40000 ALTER TABLE `status_visa` DISABLE KEYS */;
/*!40000 ALTER TABLE `status_visa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `transactions` (
  `trans_passportNo` varchar(30) DEFAULT NULL,
  `trans_tinID` varchar(30) DEFAULT NULL,
  `trans_visaType` varchar(100) DEFAULT NULL,
  `trans_visaStartDate` date DEFAULT NULL,
  `trans_visaEndDate` date DEFAULT NULL,
  `trans_permitType` varchar(30) DEFAULT NULL,
  `trans_permitStartDate` date DEFAULT NULL,
  `trans_permitEndDate` date DEFAULT NULL,
  `trans_aepID` varchar(30) DEFAULT NULL,
  `trans_aepStartDate` date DEFAULT NULL,
  `trans_aepEndDate` date DEFAULT NULL,
  `trans_transId` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) NOT NULL,
  `trans_transTimestamp` date NOT NULL,
  `trans_transAuthor` varchar(50) NOT NULL,
  `trans_transAction` varchar(50) DEFAULT NULL,
  `trans_isActive` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`trans_transId`),
  UNIQUE KEY `trans_transId_UNIQUE` (`trans_transId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `user_username` varchar(20) DEFAULT NULL,
  `user_password` varchar(20) DEFAULT NULL,
  `user_ifAdmin` int(11) DEFAULT NULL,
  `user_isActive` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `user_username` (`user_username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Mintaka','mintakaaa',1,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-04  4:58:00
