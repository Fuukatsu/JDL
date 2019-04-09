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
  `client_company` varchar(50) DEFAULT NULL,
  `client_position` varchar(25) DEFAULT NULL,
  `client_alias` varchar(25) DEFAULT NULL,
  `client_contact` varchar(30) DEFAULT NULL,
  `client_email` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `client_id_UNIQUE` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (16,'Jiabo','He','China','1987-06-16','Male','Theldi Construction Corp.','Project Manager','',' 0086-95583','jiabohe@netease.com'),(17,'Cunke','Jia','China','1982-06-05','Male','Theldi Construction Corp.','Consultant','',' 0086-95584','cunkejia@netease.com'),(18,' Cheng','Lu','China','1987-08-13','Male','Theldi Construction Corp','Construction Assistant','',' 0086-95585','LuCheng@netease.com'),(19,'Songyun','Zhang','China','1981-12-06','Male','Theldi Construction Corp','Project Assistant','',' 0086-95586','SongyunZhang@netease.com'),(20,'Hongkan','Wu','China','1980-02-27','Male','Theldi Construction Inc.','Construction Consultant','',' 0086-95586','HongkanPongkan@netease.com'),(21,'Yunchun','Gao','China','1980-02-28','Male','Theldi Corporation Inc.','Construction Manager','William',' 0086-95588','WilliamChina@netease.com'),(22,'Gyung','Jo ','China','1996-12-03','Male','Theldi Construction Corp.','Project Manager','',' 0086-95589','Gyungjo@gmail.com'),(25,'Shizuku','Rin','Japan','1980-01-31','Female','Capcom Co., Ltd','UI Developer','','000000000000','shizukurin04@gmail.com'),(26,'Zhao','Mekong','Andorra','1980-02-14','Male','','','Dong','6','mekong123@gmail.com'),(27,'Pau','Xiao','Panama','1980-02-20','Male','','','x','3',''),(28,'123','123','Andorra','1980-02-07','Male','3','','','222',''),(29,'Jacksonhjgjgjgj','Arkhfhfhfhf','Armenia','1980-02-28','Male','','','Labradog','5454545',''),(30,'Jackson','Ren','Andorra','1980-02-02','Male','','','Nine','909504954','fd3535523@gmail.com'),(31,'Chichioco','Okoy','Philippines','1980-02-28','Male','TIP','TIP-QC','ShrimpsNaDikitDikit','009148384',''),(32,'Francisco','Charles','Philippines','1980-02-20','Male','TIP','TIP-QC','bentong','023850860346','fgfigj@'),(33,'Prieto','Paul Christian','Philippines','1980-02-25','Male','Thelda Construction Corp.','Manager','Pau','09432569879','iampstud@gmail.com');
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
  `emp_position` varchar(25) DEFAULT NULL,
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
INSERT INTO `employees` VALUES ('Moore','Michael Andrew','Document Controller','Male','1980-02-24','938 Aurora Blvd, Cubao, QC.','09090909090','taka@gmail.com',1),('2132','s','asxsaxs','xsxsx','1980-02-15','xsxs','877','545121',2);
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
  `notif_ifSent` tinyint(4) DEFAULT NULL,
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
  `client_id` int(11) NOT NULL,
  `trans_transId` int(11) NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `client_id_UNIQUE` (`client_id`),
  UNIQUE KEY `trans_id_UNIQUE` (`trans_transId`),
  CONSTRAINT `remarks_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remarks`
--

LOCK TABLES `remarks` WRITE;
/*!40000 ALTER TABLE `remarks` DISABLE KEYS */;
INSERT INTO `remarks` VALUES (NULL,NULL,'','','',28,48);
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
  `client_id` int(11) NOT NULL,
  `trans_transId` int(11) NOT NULL,
  PRIMARY KEY (`client_id`),
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
  `client_id` int(11) NOT NULL,
  `trans_transId` int(11) NOT NULL,
  PRIMARY KEY (`trans_transId`),
  KEY `client_id` (`client_id`),
  CONSTRAINT `status_visa_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_visa`
--

LOCK TABLES `status_visa` WRITE;
/*!40000 ALTER TABLE `status_visa` DISABLE KEYS */;
INSERT INTO `status_visa` VALUES ('None','2019-04-19','Blah','2019-04-09','2019-04-04','','','','','',16,48),('',NULL,'',NULL,'2019-04-12','','','','','',16,54);
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
  `trans_visaType` varchar(70) DEFAULT NULL,
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
  `trans_isActive` int(11) DEFAULT NULL,
  PRIMARY KEY (`trans_transId`),
  UNIQUE KEY `trans_transId_UNIQUE` (`trans_transId`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES ('E68759489','486-442-390-000','9G','2019-04-01','2020-04-01','',NULL,NULL,'NCR-2016-11-16361','2019-05-01','2019-10-01',48,16,'2019-04-01','Mintaka',NULL,NULL),('000000000000','000000000000','9G','2019-03-31','2020-03-31','',NULL,NULL,'NCR-0000-0000','2019-04-03','2019-09-03',51,25,'2019-04-04','',NULL,NULL),('','','4d','2019-04-11','2019-04-29','',NULL,NULL,'34','2019-04-16','2019-04-17',52,17,'2019-04-06','',NULL,NULL),('E15896758452','486-442-390-000','9G','2020-04-29','2019-04-09','',NULL,NULL,'',NULL,NULL,53,16,'2019-04-08','Mintaka',NULL,NULL),('','486-442-390-000','',NULL,NULL,'',NULL,NULL,'',NULL,NULL,54,16,'2019-04-08','Mintaka',NULL,NULL),('E12593506849','','9G','2020-04-24','2019-04-09','',NULL,NULL,'',NULL,NULL,55,17,'2019-04-08','Mintaka',NULL,NULL),('','','jjh','2020-04-01','2019-04-01','',NULL,NULL,'',NULL,NULL,56,17,'2019-04-08','Mintaka',NULL,NULL),('E194340825','','',NULL,NULL,'',NULL,NULL,'',NULL,NULL,57,17,'2019-04-08','Mintaka',NULL,NULL),('E895769548694','','9G','2020-04-01','2019-04-01','',NULL,NULL,'',NULL,NULL,58,17,'2019-04-08','Mintaka',NULL,NULL),('','','',NULL,NULL,'',NULL,NULL,'946809438025E',NULL,'2019-09-04',59,17,'2019-04-08','Mintaka',NULL,NULL),('E93843985340','4309583403209','9G','2019-04-18',NULL,'',NULL,NULL,'',NULL,NULL,60,18,'2019-04-08','Mintaka',NULL,NULL),('E9860984058','4309583403209','',NULL,NULL,'Special Working Permit',NULL,'2019-04-03','',NULL,NULL,61,18,'2019-04-08','Mintaka',NULL,NULL),('','4309583403209','',NULL,NULL,'',NULL,NULL,'53095065609605',NULL,'2019-04-11',62,18,'2019-04-08','Mintaka',NULL,NULL),('0964305940','93468035430','',NULL,NULL,'Special Working Permit','2019-04-09','2019-04-25','',NULL,NULL,63,22,'2019-04-08','Mintaka',NULL,NULL),('E9090909090','090909090909','',NULL,NULL,'ij','2019-04-01','2020-04-01','ij',NULL,'2020-04-01',64,31,'2019-04-08','Mintaka',NULL,NULL),('E0000000000','00000000000000','9G','2020-04-26','2019-04-26','',NULL,NULL,'',NULL,NULL,65,32,'2019-04-08','Mintaka',NULL,NULL),('E1111111111111','00000000000000','9G','2020-04-26','2019-04-26','',NULL,NULL,'NCR-0000-0000-0000',NULL,'2020-04-26',66,32,'2019-04-08','Mintaka',NULL,NULL),('E000000000','00000000000000','9G','2020-04-03','2019-04-03','',NULL,NULL,'',NULL,NULL,67,32,'2019-04-08','Mintaka',NULL,NULL),('E000000000000','0000000000000','9G','2020-04-04','2019-04-04','',NULL,NULL,'',NULL,NULL,68,19,'2019-04-08','Mintaka',NULL,NULL),('E000000000000','0000000000000','9G','2020-04-05','2019-04-05','Special Working Permit','2019-04-19','2020-04-19','',NULL,NULL,69,19,'2019-04-08','Mintaka',NULL,NULL),('E00000000000','0000000000000','9G','2020-04-19','2019-04-19','',NULL,NULL,'NCR-0000-0000-000',NULL,'2019-04-24',70,19,'2019-04-08','Mintaka',NULL,NULL),('E12121212121','090909090909','9g','2019-04-26','2019-04-18','',NULL,NULL,'',NULL,NULL,71,31,'2019-04-08','Mintaka',NULL,NULL),('M12345678901','090909090909','9g','2019-04-26','2019-04-18','',NULL,NULL,'',NULL,NULL,72,31,'2019-04-08','Mintaka',NULL,NULL),('K1234567823','090909090909','9g','2019-04-01','2019-04-05','',NULL,NULL,'',NULL,NULL,73,31,'2019-04-08','',NULL,NULL),('L09090909090','090909090909','9g','2019-04-05','2019-04-02','',NULL,NULL,'',NULL,NULL,74,31,'2019-04-08','Mintaka',NULL,NULL),('L09090909090','','9g','2019-04-14','2019-04-12','Student','2019-04-04','2019-04-25','',NULL,NULL,75,20,'2019-04-08','Mintaka',NULL,NULL),('E39435056804529','486-442-390-000','9G','2019-04-04','2019-04-05','Special Working','2019-04-04','2019-04-05','NCR-0000-0000-0000','2019-04-04','2019-04-05',76,16,'2019-04-08','Mintaka',NULL,NULL),('E197364575938','1209485920485','9G','2019-04-25','2020-04-25','',NULL,NULL,'',NULL,NULL,77,33,'2019-04-08','Mintaka',NULL,NULL),('E345368986578','1209485920485','9G','2019-04-25','2020-04-25','',NULL,NULL,'',NULL,NULL,78,33,'2019-04-08','Mintaka',NULL,NULL),('E34221657980','1209485920485','',NULL,NULL,'Special Working Permit','2019-04-15','2019-04-30','',NULL,NULL,79,33,'2019-04-08','Mintaka',NULL,NULL),('E344366980646','1209485920485','',NULL,NULL,'',NULL,NULL,'NCR-1789-8954-2239','2019-04-25','2020-04-25',80,33,'2019-04-08','Mintaka',NULL,NULL);
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
INSERT INTO `users` VALUES (1,'Mintaka','mintakaaa',1),(2,'Paul','paulpaul',0);
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

-- Dump completed on 2019-04-10  4:15:50
