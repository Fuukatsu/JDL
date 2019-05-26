-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
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
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (68,'Greenvale','Kleo','Antigua and Barbuda','1980-02-28','Female','HGS','PLC Trading Corps.','Grella','+6394567342','kleo.greenview@plc12.com','DELETED',0),(69,'Paul','Firstname','United Arab Emirates','1998-02-12','Male','company','','','','email@gmail.com','DELETED',0),(70,'Francisco','Charles','Philippines','1986-02-20','Male','Thelda Communication','Secretary','RagnaBoy','09179345678','mr.charlsRagna@gmail.com','DELETED',0),(71,'Fregal','Akari','Japan','1980-04-19','Male','BUN Corps.','Store Opener','','+6390123456','akari.BUN@gbum.com',NULL,1),(72,'asdasdas','manansala','Anguilla','1980-02-13','Female','asdasdas','asdasdasd','','23423423','asdasd@gmail.com','DELETED',0),(73,'asdasd','Jounel','Andorra','1980-02-14','Male','asdasda','qweqweq','','12312312','asdasd@asds.com','DELETED',0),(74,'asasdsaasdas','asdsa','Andorra','1980-02-21','Male','asdas','asdasd','asdas','2312312asd','asdasd@asda.com','DELETED',0),(75,'Test','TEster','Andorra','1980-02-15','Male','','','T','2213','Masa.tester@t.com','DELETED',0),(76,'aaaa','nbbbn','Andorra','1980-02-20','Male','','','','1323','aadsada@sdsadadasd.com','DELETED',0),(77,'gfhsjfsd','sadasda','Andorra','1980-02-13','Male','','','','23123213','asdsa@sfdf.com',NULL,1),(78,'Happy','M','Andorra','1980-02-12','Male','','','','13231','adas@jsad.com',NULL,1),(79,'Reyes','Mika','Andorra','1980-02-20','Female','TESDA Inc.','Proctor','','09897803657','adas@gmail.com',NULL,1),(80,'George','Gray','Philippines','1980-02-04','Male','Thelda Consumers Inc.','Sales Manager','Anatomy','09179345768','georgeGray@gmail.com','DELETED',0);
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
INSERT INTO `employees` VALUES ('Moore','Michael Andrew','HELLLOOO','Male','2019-04-04','12345678','090908687699','1',1),('adsdsa','asdsada','asdada','Female','1980-02-13','','31232','adadsa@saddf.com',4),('','hello','gfgfg','Male','1980-02-07','fgfdgdfg','57547546','sdfs@gmail.com',5),('Tsukiko','Makoy','sixty-nine','Male','1994-10-14','asdasd','asdasd','llll@gmail.com',12),('','jkjkjkjkj','nknkn','Female','1980-02-16','klmklm','9090909','kjkljk@gmail.com',98);
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
INSERT INTO `notifications` VALUES ('2019-05-06'),('2019-05-15'),('2019-05-16'),('2019-05-21'),('2019-05-23'),('2019-05-24'),('2019-05-25');
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
INSERT INTO `remarks` VALUES ('2019-05-08','2019-05-21','f','gh','d',68,123),('2019-05-01','2019-05-02','ukinam','asdasdasd','asdasdas',70,182),(NULL,NULL,'','','',78,189),(NULL,NULL,'koko','','',78,234),('2019-05-15','2019-05-16','','','',77,235);
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
INSERT INTO `status_permits` VALUES ('2019-05-15','','','','','','','',NULL,NULL,NULL,'',NULL,70,182),(NULL,'','','','','','','','2019-05-02',NULL,NULL,'',NULL,71,183),('2019-05-10','','','','','','','',NULL,NULL,NULL,'',NULL,78,189),('2019-05-08','','','','','','','',NULL,NULL,NULL,'',NULL,68,209),('2019-05-15','','','','','','','',NULL,NULL,NULL,'',NULL,69,231),(NULL,'Q','Q','Q','','','','',NULL,NULL,NULL,'',NULL,78,234),('2019-05-10','','','','','','','',NULL,NULL,NULL,'',NULL,78,236);
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
INSERT INTO `status_visa` VALUES ('wewerqwrwer',NULL,'',NULL,NULL,'','','','','',68,123),('','2019-05-17','',NULL,NULL,'','','','','',70,182),('H',NULL,'',NULL,NULL,'','','','','',78,189),('der',NULL,'123fg',NULL,NULL,'','','','','',68,209),('Im here','2019-05-09','','2019-05-15','2019-05-10','','','','','',68,230),('sad','2019-05-17','',NULL,NULL,'','','','','',69,231),('B','2019-05-17','B','2019-05-30','2019-05-17','','','','','',78,234),('H',NULL,'',NULL,NULL,'','','','','',78,236);
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
) ENGINE=InnoDB AUTO_INCREMENT=237 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES ('123412123','123213','oi8','2019-05-16','2019-05-19','',NULL,NULL,'',NULL,NULL,123,68,'2019-05-07','Mintaka','DELETED',0),('PH8867890','9612900282','Pre-arragend Employment Visa (9G)','2019-01-24','2020-03-25','Special Working Permit (SWP)','2019-02-13','2020-03-12','NCR-000-000-000-001','2019-01-16','2020-04-02',124,1,'2018-12-31','cfrancisco',NULL,1),('PH4870705','8554617382','Pre-arragend Employment Visa (9G)','2019-02-25','2020-02-05','Special Working Permit (SWP)','2019-01-31','2020-04-06','NCR-000-000-000-002','2019-01-16','2020-02-19',125,2,'2018-12-31','fmauricio',NULL,1),('PH1460486','2069732379','Pre-arragend Employment Visa (9G)','2019-01-14','2020-01-08','Special Working Permit (SWP)','2019-01-07','2020-02-11','NCR-000-000-000-003','2019-02-21','2020-03-12',126,3,'2018-12-31','fperia',NULL,1),('PH2124712','6377944399','Pre-arragend Employment Visa (9G)','2019-01-22','2020-04-09','Special Working Permit (SWP)','2019-01-13','2020-04-02','NCR-000-000-000-004','2019-02-14','2020-03-05',127,4,'2018-12-31','mestacio',NULL,1),('PH4919013','3462059336','Pre-arragend Employment Visa (9G)','2019-01-13','2020-02-12','Special Working Permit (SWP)','2019-02-11','2020-02-10','NCR-000-000-000-005','2019-01-06','2020-01-19',128,5,'2018-12-31','mchichioco',NULL,1),('PH9823041','1770445568','Pre-arragend Employment Visa (9G)','2019-01-31','2020-03-10','Special Working Permit (SWP)','2019-01-31','2020-03-15','NCR-000-000-000-006','2019-02-25','2020-04-08',129,6,'2018-12-31','mmoore',NULL,1),('PH8541395','7799042503','Pre-arragend Employment Visa (9G)','2019-02-21','2020-01-26','Special Working Permit (SWP)','2019-01-10','2020-02-12','NCR-000-000-000-007','2019-01-24','2020-01-16',130,7,'2019-01-01','pprieto',NULL,1),('PH3772457','5468025948','Pre-arragend Employment Visa (9G)','2019-02-10','2020-04-07','Special Working Permit (SWP)','2019-01-02','2020-03-11','NCR-000-000-000-008','2019-02-20','2020-01-22',131,8,'2019-01-01','cpestanio',NULL,1),('PH1227031','6669413038','Pre-arragend Employment Visa (9G)','2019-01-07','2020-01-07','Special Working Permit (SWP)','2019-02-13','2020-02-10','NCR-000-000-000-009','2019-01-30','2020-01-12',132,9,'2019-01-01','cfrancisco',NULL,1),('PH7494792','7728088497','Pre-arragend Employment Visa (9G)','2018-12-31','2020-03-24','Special Working Permit (SWP)','2019-02-13','2020-03-02','NCR-000-000-000-010','2019-01-15','2020-04-07',133,10,'2019-01-01','fmauricio',NULL,1),('PH5085373','7490037255','Pre-arragend Employment Visa (9G)','2019-02-24','2020-01-16','Special Working Permit (SWP)','2019-01-01','2020-04-07','NCR-000-000-000-013','2019-02-19','2020-01-15',134,13,'2019-01-01','mchichioco',NULL,1),('PH3849461','1147131448','Pre-arragend Employment Visa (9G)','2019-01-17','2020-03-25','Special Working Permit (SWP)','2019-01-10','2020-02-05','NCR-000-000-000-014','2019-02-25','2020-04-09',135,14,'2019-01-02','mmoore',NULL,1),('PH5173568','840160527','Pre-arragend Employment Visa (9G)','2019-01-21','2020-01-20','Special Working Permit (SWP)','2019-01-08','2020-02-17','NCR-000-000-000-015','2019-02-14','2020-01-26',136,15,'2019-01-02','pprieto',NULL,1),('PH9849970','7807852759','Pre-arragend Employment Visa (9G)','2019-02-12','2020-03-09','Special Working Permit (SWP)','2019-01-21','2020-03-19','NCR-000-000-000-016','2019-01-02','2020-04-08',137,16,'2019-01-03','cpestanio',NULL,1),('PH1463792','7075437355','Pre-arragend Employment Visa (9G)','2019-01-27','2020-03-11','Special Working Permit (SWP)','2019-01-28','2020-01-19','NCR-000-000-000-017','2019-02-26','2020-02-10',138,17,'2019-01-03','cfrancisco',NULL,1),('PH4737823','457103647','Pre-arragend Employment Visa (9G)','2019-01-01','2020-03-26','Special Working Permit (SWP)','2019-02-14','2020-03-01','NCR-000-000-000-018','2019-01-20','2020-03-08',139,18,'2019-01-03','fmauricio',NULL,1),('PH2758688','5797478333','Pre-arragend Employment Visa (9G)','2019-01-21','2020-03-03','Special Working Permit (SWP)','2019-02-21','2020-01-29','NCR-000-000-000-019','2019-01-28','2020-03-11',140,19,'2019-01-03','fperia',NULL,1),('PH3800610','9032537548','Pre-arragend Employment Visa (9G)','2019-01-30','2020-02-26','Special Working Permit (SWP)','2019-01-23','2020-02-05','NCR-000-000-000-021','2019-01-23','2020-03-26',141,21,'2019-01-03','mchichioco',NULL,1),('PH4405414','6638723148','Pre-arragend Employment Visa (9G)','2019-02-06','2020-04-05','Special Working Permit (SWP)','2019-01-31','2020-01-12','NCR-000-000-000-022','2019-01-10','2020-01-29',142,22,'2019-01-06','mmoore',NULL,1),('PH7582072','5691373394','Pre-arragend Employment Visa (9G)','2019-01-22','2020-03-16','Special Working Permit (SWP)','2019-02-21','2020-02-03','NCR-000-000-000-024','2019-01-14','2020-02-27',143,24,'2019-01-06','cpestanio',NULL,1),('PH2932950','7873677309','Pre-arragend Employment Visa (9G)','2019-02-20','2020-01-05','Special Working Permit (SWP)','2019-02-14','2020-02-18','NCR-000-000-000-025','2019-02-03','2020-02-27',144,25,'2019-01-06','cfrancisco',NULL,1),('PH8083153','7617122668','Pre-arragend Employment Visa (9G)','2019-01-15','2020-01-26','Special Working Permit (SWP)','2019-01-06','2020-02-10','NCR-000-000-000-026','2019-01-13','2020-01-29',145,26,'2019-01-06','fmauricio',NULL,1),('PH9438388','5906345203','Pre-arragend Employment Visa (9G)','2019-02-11','2020-02-26','Special Working Permit (SWP)','2019-02-26','2020-01-30','NCR-000-000-000-027','2019-02-11','2020-03-11',146,27,'2019-01-07','fperia',NULL,1),('PH8008327','8573374307','Pre-arragend Employment Visa (9G)','2019-01-13','2020-02-25','Special Working Permit (SWP)','2019-02-12','2020-03-05','NCR-000-000-000-028','2019-01-06','2020-03-25',147,28,'2019-01-07','mestacio',NULL,1),('PH5206841','247318','Pre-arragend Employment Visa (9G)','2019-02-18','2020-03-22','Special Working Permit (SWP)','2019-01-02','2020-03-29','NCR-000-000-000-030','2019-01-28','2020-03-22',148,30,'2019-01-07','mmoore',NULL,1),('PH2026737','1759816907','Pre-arragend Employment Visa (9G)','2019-02-04','2020-03-25','Special Working Permit (SWP)','2019-01-27','2020-03-16','NCR-000-000-000-031','2019-02-03','2020-03-29',149,31,'2019-01-08','pprieto',NULL,1),('PH3603576','987448196','Pre-arragend Employment Visa (9G)','2019-02-07','2020-03-29','Special Working Permit (SWP)','2019-02-11','2020-01-23','NCR-000-000-000-032','2019-02-24','2020-02-16',150,32,'2019-01-09','cpestanio',NULL,1),('PH9649205','8063747521','Pre-arragend Employment Visa (9G)','2019-02-18','2020-01-26','Special Working Permit (SWP)','2019-01-31','2020-01-28','NCR-000-000-000-033','2019-01-30','2020-02-20',151,33,'2019-01-09','cfrancisco',NULL,1),('PH7776254','9455331423','Pre-arragend Employment Visa (9G)','2019-02-12','2020-03-10','Special Working Permit (SWP)','2019-02-20','2020-02-23','NCR-000-000-000-034','2019-02-25','2020-04-05',152,34,'2019-01-10','fmauricio',NULL,1),('PH2790899','8826800926','Pre-arragend Employment Visa (9G)','2019-01-30','2020-03-25','Special Working Permit (SWP)','2019-01-15','2020-02-11','NCR-000-000-000-035','2019-01-27','2020-02-19',153,35,'2019-01-10','fperia',NULL,1),('PH4090826','268218805','Pre-arragend Employment Visa (9G)','2019-01-09','2020-01-16','Special Working Permit (SWP)','2019-01-01','2020-03-08','NCR-000-000-000-036','2019-02-18','2020-03-05',154,36,'2019-01-10','mestacio',NULL,1),('PH4390264','4782141618','Pre-arragend Employment Visa (9G)','2019-02-20','2020-03-31','Special Working Permit (SWP)','2019-02-26','2020-03-23','NCR-000-000-000-037','2019-01-01','2020-01-08',155,37,'2019-01-10','mchichioco',NULL,1),('PH7845970','8822867542','Pre-arragend Employment Visa (9G)','2019-02-07','2020-01-22','Special Working Permit (SWP)','2019-01-30','2020-02-16','NCR-000-000-000-038','2019-02-26','2020-01-26',156,38,'2019-01-13','mmoore',NULL,1),('PH6649100','2292961349','Pre-arragend Employment Visa (9G)','2019-01-23','2020-01-05','Special Working Permit (SWP)','2019-01-08','2020-03-16','NCR-000-000-000-039','2019-01-07','2020-03-01',157,39,'2019-01-13','pprieto',NULL,1),('PH2152904','7699958944','Pre-arragend Employment Visa (9G)','2019-01-20','2020-02-10','Special Working Permit (SWP)','2019-02-26','2020-02-10','NCR-000-000-000-040','2019-02-14','2020-03-11',158,40,'2019-01-13','cpestanio',NULL,1),('PH7496368','4484272326','Pre-arragend Employment Visa (9G)','2019-01-23','2020-03-24','Special Working Permit (SWP)','2019-01-06','2020-02-17','NCR-000-000-000-041','2019-01-22','2020-01-16',159,41,'2019-01-13','cfrancisco',NULL,1),('PH1936490','966299998','Pre-arragend Employment Visa (9G)','2019-01-29','2020-01-02','Special Working Permit (SWP)','2019-01-13','2020-01-15','NCR-000-000-000-042','2019-02-10','2020-03-25',160,42,'2019-01-14','fmauricio',NULL,1),('PH7386390','2349439625','Pre-arragend Employment Visa (9G)','2019-02-17','2020-03-09','Special Working Permit (SWP)','2019-01-22','2020-03-29','NCR-000-000-000-043','2019-02-19','2020-02-02',161,43,'2019-01-14','fperia',NULL,1),('PH6046038','4564743800','Pre-arragend Employment Visa (9G)','2019-01-20','2020-02-10','Special Working Permit (SWP)','2019-01-01','2020-01-14','NCR-000-000-000-044','2019-02-07','2020-02-10',162,44,'2019-01-14','mestacio',NULL,1),('PH8816271','4831013815','Pre-arragend Employment Visa (9G)','2019-01-30','2019-12-31','Special Working Permit (SWP)','2018-12-31','2020-02-25','NCR-000-000-000-047','2019-01-09','2020-02-09',163,47,'2019-01-14','pprieto',NULL,1),('PH3610962','6476834814','Pre-arragend Employment Visa (9G)','2019-01-15','2020-02-19','Special Working Permit (SWP)','2019-02-25','2020-01-21','NCR-000-000-000-048','2019-02-03','2020-04-05',164,48,'2019-01-15','cpestanio',NULL,1),('PH2693470','3153376428','Pre-arragend Employment Visa (9G)','2019-01-07','2020-02-18','Special Working Permit (SWP)','2019-01-15','2020-03-29','NCR-000-000-000-049','2019-02-24','2020-03-19',165,49,'2019-01-15','cfrancisco',NULL,1),('PH4938556','540335685','Pre-arragend Employment Visa (9G)','2019-01-10','2020-01-12','Special Working Permit (SWP)','2019-01-01','2020-03-02','NCR-000-000-000-050','2019-01-28','2020-04-05',166,50,'2019-01-15','fmauricio',NULL,1),('PH6873039','8609338665','Pre-arragend Employment Visa (9G)','2019-02-17','2020-01-13','Special Working Permit (SWP)','2019-02-24','2020-02-18','NCR-000-000-000-051','2019-01-17','2020-02-12',167,51,'2019-01-15','fperia',NULL,1),('PH1774760','8860156311','Pre-arragend Employment Visa (9G)','2019-01-17','2020-01-23','Special Working Permit (SWP)','2019-01-27','2020-01-12','NCR-000-000-000-053','2019-01-17','2020-04-02',168,53,'2019-01-16','mchichioco',NULL,1),('PH6562366','2899545060','Pre-arragend Employment Visa (9G)','2019-01-30','2020-01-14','Special Working Permit (SWP)','2019-02-10','2020-03-01','NCR-000-000-000-054','2019-01-09','2020-04-02',169,54,'2019-01-16','mmoore',NULL,1),('PH6691839','9758758889','Pre-arragend Employment Visa (9G)','2019-01-29','2020-02-11','Special Working Permit (SWP)','2019-01-27','2020-03-02','NCR-000-000-000-055','2019-01-17','2020-03-18',170,55,'2019-01-16','pprieto',NULL,1),('PH1768519','8979515169','Pre-arragend Employment Visa (9G)','2019-01-03','2020-01-05','Special Working Permit (SWP)','2019-02-03','2020-01-19','NCR-000-000-000-056','2019-01-07','2020-01-07',171,56,'2019-01-16','cpestanio',NULL,1),('PH7257834','7410511148','Pre-arragend Employment Visa (9G)','2019-02-13','2020-01-26','Special Working Permit (SWP)','2019-01-01','2020-02-20','NCR-000-000-000-057','2019-01-02','2020-04-05',172,57,'2019-01-17','cfrancisco',NULL,1),('PH2841254','4321865975','Pre-arragend Employment Visa (9G)','2019-02-11','2020-03-19','Special Working Permit (SWP)','2019-01-14','2020-02-05','NCR-000-000-000-059','2019-02-11','2020-01-23',173,59,'2019-01-17','fperia',NULL,1),('PH1703582','8179726895','Pre-arragend Employment Visa (9G)','2019-01-09','2020-04-06','Special Working Permit (SWP)','2019-01-28','2020-02-23','NCR-000-000-000-060','2019-01-31','2020-03-24',174,60,'2019-01-17','mestacio',NULL,1),('PH7118866','6247628872','Pre-arragend Employment Visa (9G)','2019-01-08','2020-03-05','Special Working Permit (SWP)','2019-01-27','2020-01-27','NCR-000-000-000-061','2019-01-15','2020-01-16',175,61,'2019-01-17','mchichioco',NULL,1),('PH4428522','2628063614','Pre-arragend Employment Visa (9G)','2019-01-20','2020-01-05','Special Working Permit (SWP)','2019-02-24','2020-03-08','NCR-000-000-000-062','2019-01-13','2020-04-01',176,62,'2019-01-17','mmoore',NULL,1),('PH4999911','1819998700','Pre-arragend Employment Visa (9G)','2019-01-22','2020-02-13','Special Working Permit (SWP)','2019-02-24','2020-02-02','NCR-000-000-000-064','2019-01-28','2020-01-02',177,64,'2019-01-20','cpestanio',NULL,1),('PH4184766','1676476714','Pre-arragend Employment Visa (9G)','2019-02-12','2020-01-30','Special Working Permit (SWP)','2018-12-31','2020-01-20','NCR-000-000-000-065','2019-01-06','2020-04-02',178,65,'2019-01-20','cfrancisco',NULL,1),('PH5288635','165863848','Pre-arragend Employment Visa (9G)','2019-01-16','2020-03-26','Special Working Permit (SWP)','2019-01-06','2020-01-14','NCR-000-000-000-066','2019-01-23','2020-03-05',179,66,'2019-01-20','fmauricio',NULL,1),('PH1646141','2418520066','Pre-arragend Employment Visa (9G)','2019-01-15','2020-01-02','Special Working Permit (SWP)','2019-01-08','2020-02-23','NCR-000-000-000-067','2019-02-26','2020-01-07',180,67,'2019-01-20','fperia',NULL,1),('PH7041062','8808072894','Pre-arragend Employment Visa (9G)','2019-02-18','2020-03-09','Special Working Permit (SWP)','2019-01-03','2020-03-23','NCR-000-000-000-068','2019-01-23','2020-03-16',181,68,'2019-01-20','Mintaka','DELETED',0),('PH2488496','3947924070','Pre-arragend Employment Visa (9G)','2019-02-06','2020-02-02','Special Working Permit (SWP)','2019-02-06','2020-03-22','NCR-000-000-000-072','2019-02-13','2020-01-20',184,72,'2019-01-21','Chicho','DELETED',0),('PH3382364','9515964526','Pre-arragend Employment Visa (9G)','2019-02-18','2020-02-03','Special Working Permit (SWP)','2019-01-01','2020-03-18','NCR-000-000-000-073','2019-02-19','2020-03-29',185,73,'2019-01-21','Chicho','DELETED',0),('PH7880494','568353735','Pre-arragend Employment Visa (9G)','2019-01-17','2020-02-09','Special Working Permit (SWP)','2019-01-16','2020-02-11','NCR-000-000-000-074','2019-01-22','2020-02-18',186,74,'2019-01-21','Chicho','DELETED',0),('PH8385576','77734598','Pre-arragend Employment Visa (9G)','2019-01-09','2020-02-18','Special Working Permit (SWP)','2019-01-03','2020-03-30','NCR-000-000-000-076','2019-01-09','2020-03-29',187,76,'2019-01-21','Chicho','DELETED',0),('PH3989414','1878283152','Pre-arragend Employment Visa (9G)','2019-01-01','2020-01-27','Special Working Permit (SWP)','2019-01-15','2020-03-01','NCR-000-000-000-078','2019-01-15','2020-02-27',189,78,'2019-01-22','mmoore',NULL,1),('PH8166426','9334363517','Pre-arranged Employment Visa (9g/Working Visa)','2019-02-10','2020-03-16','Special Working Permit(SWP)','2019-02-27','2020-02-20','NCR-000-000-000-080','2019-01-06','2020-03-11',191,80,'2019-05-24','Mintaka','DELETED',0),('PH2823502','7126405489','Pre-arragend Employment Visa (9G)','2019-02-26','2020-02-16','Special Working Permit (SWP)','2019-02-19','2020-04-09','NCR-000-000-000-086','2019-01-29','2020-01-06',197,86,'2019-01-27','mmoore',NULL,1),('PH7048408','7139509125','Pre-arragend Employment Visa (9G)','2019-01-23','2020-02-10','Special Working Permit (SWP)','2019-01-10','2020-03-26','NCR-000-000-000-087','2019-01-29','2020-04-06',198,87,'2019-01-27','pprieto',NULL,1),('PH3718425','3360583614','Pre-arragend Employment Visa (9G)','2018-12-31','2020-02-18','Special Working Permit (SWP)','2019-01-28','2020-02-23','NCR-000-000-000-088','2019-01-21','2020-03-29',199,88,'2019-01-27','cpestanio',NULL,1),('PH2453208','4250317700','Pre-arragend Employment Visa (9G)','2019-02-25','2020-01-02','Special Working Permit (SWP)','2019-01-22','2020-04-01','NCR-000-000-000-089','2019-01-06','2020-02-18',200,89,'2019-01-27','cfrancisco',NULL,1),('PH4464201','8710965989','Pre-arragend Employment Visa (9G)','2019-01-13','2020-01-26','Special Working Permit (SWP)','2019-01-14','2020-02-02','NCR-000-000-000-091','2019-02-07','2020-03-01',201,91,'2019-01-28','fperia',NULL,1),('PH7716391','1882512783','Pre-arragend Employment Visa (9G)','2019-01-01','2020-02-17','Special Working Permit (SWP)','2019-01-13','2020-02-24','NCR-000-000-000-092','2019-01-09','2020-01-23',202,92,'2019-01-28','mestacio',NULL,1),('PH4528622','4826134014','Pre-arragend Employment Visa (9G)','2019-02-06','2020-03-11','Special Working Permit (SWP)','2019-01-14','2020-01-13','NCR-000-000-000-093','2019-01-20','2020-01-01',203,93,'2019-01-28','mchichioco',NULL,1),('PH3599183','9691909144','Pre-arragend Employment Visa (9G)','2019-01-24','2020-02-04','Special Working Permit (SWP)','2019-02-05','2020-02-13','NCR-000-000-000-094','2019-01-02','2020-04-05',204,94,'2019-01-28','mmoore',NULL,1),('PH3545757','5658450183','Pre-arragend Employment Visa (9G)','2019-01-20','2020-01-30','Special Working Permit (SWP)','2019-01-02','2020-01-01','NCR-000-000-000-095','2019-01-06','2020-02-12',205,95,'2019-01-28','pprieto',NULL,1),('PH3718167','931600319','Pre-arragend Employment Visa (9G)','2019-01-21','2020-03-03','Special Working Permit (SWP)','2019-01-22','2020-02-27','NCR-000-000-000-098','2019-01-08','2020-01-07',206,98,'2019-01-29','fmauricio',NULL,1),('PH3493400','9177644626','Pre-arragend Employment Visa (9G)','2019-01-23','2020-02-10','Special Working Permit (SWP)','2018-12-31','2020-01-06','NCR-000-000-000-099','2019-01-20','2020-01-21',207,99,'2019-01-30','fperia',NULL,1),('PH7037726','5536841327','Pre-arragend Employment Visa (9G)','2019-01-03','2020-03-03','Special Working Permit (SWP)','2019-01-31','2020-02-20','NCR-000-000-000-100','2019-02-20','2020-04-07',208,100,'2019-01-30','mestacio',NULL,1),('PH3989414','1878283152','Pre-arranged Employment Visa (9g/Working Visa Commercial)','2019-05-10','2020-05-15','',NULL,NULL,'NCR-000-000-000-078','2019-05-10','2019-05-31',234,78,'2019-05-16','Test',NULL,1),('PH8385576','464363634','Pre-arranged Employment Visa (9g/Working Visa)','2019-05-10','2019-05-31','',NULL,NULL,'',NULL,NULL,235,77,'2019-05-25','Mintaka',NULL,1),('PH8385576','909090909','Pre-arranged Employment Visa (9g/Working Visa)','2019-05-17','2019-05-31','',NULL,NULL,'',NULL,NULL,236,78,'2019-05-25','Mintaka',NULL,1);
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
INSERT INTO `users` VALUES (1,'Mintaka','mintakaaa',1),(4,'mmoore','mabelski',1),(5,'Test','mabelski',0),(7,'Charles','charles123',1),(12,'Chicho','asdasdasd',1),(98,'pau','12345678',1);
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

-- Dump completed on 2019-05-26  1:35:00
