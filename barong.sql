CREATE DATABASE  IF NOT EXISTS `barong` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `barong`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 165.22.241.39    Database: barong
-- ------------------------------------------------------
-- Server version	5.7.28

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
-- Table structure for table `floor`
--

DROP TABLE IF EXISTS `floor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `floor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `floor_name` varchar(45) DEFAULT NULL,
  `master_building_id` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_floor_master_building1_idx` (`master_building_id`),
  CONSTRAINT `fk_floor_master_building1` FOREIGN KEY (`master_building_id`) REFERENCES `master_building` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `floor`
--

LOCK TABLES `floor` WRITE;
/*!40000 ALTER TABLE `floor` DISABLE KEYS */;
INSERT INTO `floor` VALUES (1,'Floor 1',1,1),(2,'Floor 2',1,1),(10,'Al',2,0),(13,'Lantai 1',19,1),(14,'Lantai 2',19,1),(15,'A-001',27,1),(16,'A-002',27,1),(17,'RG-001',38,0),(18,'RG-002',35,1),(19,'A-003',27,1),(20,'RG-003',35,1),(21,'RG-004',35,1),(22,'RG-005',35,1),(23,'RG-006',35,1),(24,'Lantai 1',36,1),(25,'Lantai 1',37,1),(26,'Lantai 2',37,1),(27,'RG-005',35,1),(28,'Lantai 1',35,1),(29,'Lantai 1',26,1),(30,'Lantai 2',35,1),(31,'A01',49,1),(32,'B01',50,1),(33,'J-001',51,1),(34,'J-002',51,1),(35,'Lantai 3',19,1),(36,'lantai 9',19,1),(37,'Z12',20,1),(38,'A20',38,1),(39,'Lantai 8',19,1),(40,'RAG_007',38,1),(41,'RAG_007',38,1),(42,'RAG_007',38,1),(43,'RAG_007',38,1),(44,'RAG_007',38,1),(45,'RAG_007',38,1),(46,'RAG_007',38,1),(47,'RAG_007',38,1),(48,'RAG_007',38,1),(49,'RAG_007',38,1);
/*!40000 ALTER TABLE `floor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `master_building`
--

DROP TABLE IF EXISTS `master_building`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `master_building` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) DEFAULT NULL,
  `building_name` varchar(45) NOT NULL,
  `barcode` varchar(100) NOT NULL,
  `location` varchar(255) NOT NULL,
  `latitude` varchar(100) NOT NULL,
  `longitude` varchar(100) NOT NULL,
  `min_price` decimal(10,2) NOT NULL,
  `max_price` decimal(10,2) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `master_building`
--

LOCK TABLES `master_building` WRITE;
/*!40000 ALTER TABLE `master_building` DISABLE KEYS */;
INSERT INTO `master_building` VALUES (1,NULL,'Mall R','97fa350ad5da0c0885ab73d79a86f23c','Jakarata Selatan','-6.299860','106.82246',2000.00,20000.00,0),(2,NULL,'Mall Saya','3adebddf9a205eae76e62496137254c8','Jakarata Selatan','-6.310851','106.821199',2000.00,20000.00,0),(13,NULL,'Mall Loh','8eeff082c0762305921351b262f0b310','Jakarata Selatan','-6.303749','106.817510',1500.00,150000.00,0),(14,NULL,'Mall Dia',' 5240693a3042d3d11669488acd138535','Jakarta Selatan','-6.302851 ','106.821684',1500.00,15000.00,0),(17,133,'Mall Ambasador','$2a$10$EZgebfhuf3dO62GUB6pbuuTf4Gy8OZTX0GWyYGqTzGvlNK10uTj7q','ChIJlyfB6fvzaS4R_eJ6D4fj-g8','-6.2235368','106.82648740000002',1000.00,10000.00,0),(18,14,'Mall Ambasador','$2a$10$Y6FTayZe07Iqgv.o5QhUJeOd7NRWqf6nwu8otl1b4DcK6eKyIfHXC','Mall Ambasador, Jalan Professor Doktor Satrio, RW.4, Kuningan, Karet Kuningan, South Jakarta City, Jakarta, Indonesia','-6.2235368','106.82648740000002',1000.00,10000.00,0),(19,130,'Enigma Camp','$2a$10$Akwyi9qLad.2eSuYS.9/S.UBLqYC8Xa/.NaNL6f2tZqCM988ef5Fm','Enigma Camp, Jalan H. Dahlan, RT.8/RW.4, Ragunan, South Jakarta City, Jakarta, Indonesia','-6.3016617','106.8191984',2000.00,20000.00,1),(20,NULL,'Colloseum Jakarta','$2a$10$GUfihJsuT8fQplqsvFCFEuWyta4VRMTzIiOVny231Z67jks.RNyU.','Colosseum Club, Jalan Kunir, RT.7/RW.7, Pinangsia, West Jakarta City, Jakarta, Indonesia','-6.1328296','106.81403469999998',1000.00,10000.00,0),(21,NULL,'Monas','$2a$10$aHkbHLEvrwfQQ8Uc55SuDuw740VrumtG/zd6PdfbPNi0TS09EYHT.','Monas, Gambir, Central Jakarta City, Jakarta, Indonesia','-6.1753924','106.82715280000002',1000.00,10000.00,0),(22,NULL,'las vegas','$2a$10$q9rBqyXwBLcRba3d2EYZkem/0Mzanv2TUTu2HciSkMVeOk5OioVaa','Las Vegas, NV, USA','36.1699412','-115.13982959999998',1000.00,10000.00,0),(26,118,'Ragunan','$2a$10$7rpRxquA9LEmLNqeAO6gHusxBQGd889sXj391RkPHuzBZ1PST3E5y','Ragunan, South Jakarta City, Jakarta, Indonesia','-6.301752','106.82078750000005',1000.00,10000.00,0),(27,35,'BRI Corporate','$2a$10$HaPQPKu9u4hnDS.Vre1Ep.uG/if/Td5eprCVZrCT0.w8.Ti.fN08y','Bank BRI Corporate University, Jalan Harsono RM, RT.6/RW.7, Ragunan, South Jakarta City, Jakarta, Indonesia','-6.3027428','106.82163909999997',1000.00,10000.00,1),(35,143,'Ragunan','$2a$10$fcOQXDDOiUr.nZxq9tWc9O5eWTpK/uP.p0yGBgNAUYGmIMUXdbL0G','Ragunan Zoo, Jalan Harsono RM, Ragunan, South Jakarta City, Jakarta, Indonesia','-6.3124593','106.82018649999998',5000.00,20000.00,1),(36,36,'Kementrian Pertanian','$2a$10$RGiEjUz9KlCx9ceAYYkqcuVaJpGR7ka30thdUS8sY/6hSeLqT8rA.','Kamentrian Pertanian Republik Indonésia, Jalan Harsono RM, RT.5/RW.7, Ragunan, South Jakarta City, Jakarta, Indonesia','-6.2967982','106.82301080000002',2000.00,20000.00,0),(37,37,'Menara Unas','$2a$10$gTf/l./1UeIAQdX0b3C0AuaQGO.l8kHI0qnMM0COASL2q0FGKEIti','Menara Universitas Nasional, RT.9/RW.4, Ragunan, South Jakarta City, Jakarta, Indonesia','-6.2984095','106.8206692',1000.00,20000.00,1),(38,NULL,'DPP Partai Gerindra','$2a$10$WKrd7xWsRzLO3oMEeSGATO3nFjpX.hTJIc1XKW2W2IIodx1I5zgGy','DPP Partai Gerindra, RT.8/RW.4, Ragunan, South Jakarta City, Jakarta, Indonesia','-6.3021315','106.82073000000003',1000.00,10000.00,1),(39,NULL,'Mall Ambasador','$2a$10$dNHdU.oJzuT0vUClxeRdH.8cEuovNkgBgrRmzqc72JDHHgWJ6bUBO','Mall Ambasador, Jalan Professor Doktor Satrio, Kuningan, Karet Kuningan, South Jakarta City, Jakarta, Indonesia','-6.2235368','106.82648740000002',1000.00,10000.00,1),(40,NULL,'Grand Indonesia','$2a$10$exr6.IiaRzwaVZfii2TG5u9xJpe3LrP69T5YcWb8H3zG8Hjj1IAe.','Grand Indonesia, Jalan M.H. Thamrin, RT.1/RW.5, Kebon Melati, Central Jakarta City, Jakarta, Indonesia','-6.195083800000001','106.82093929999996',1000.00,10000.00,1),(41,NULL,'Monas 2','$2a$10$8DZgSvV4rjXeHZNv8RA1JunYJ4J3D0uabZ32DJu1Lq2jxpa15mO8u','Monas, Gambir, Central Jakarta City, Jakarta, Indonesia','-6.1753924','106.82715280000002',1000.00,10000.00,1),(43,NULL,'Test','$2a$10$KRh.Ip5wgqL3Z4.nBk.EWe.EqEcMFdMJbyAluiBflKbpDmRDMf/hW','','-6.301666894535101','106.8197781632091',1000.00,10000.00,0),(44,NULL,'Grand Indonesia','$2a$10$.qhMEW2G8/lYbl8lGL27oOWI6TP2k1d0cTxqYBanUfEErd3zDtJ0m','Grand Indonesia, Jalan M.H. Thamrin, RT.1/RW.5, Kebon Melati, Central Jakarta City, Jakarta, Indonesia','-6.195083800000001','106.82093929999996',1000.00,10000.00,1),(45,NULL,'Rumah Sakit Hewan','$2a$10$R1a2wvWwCh8anOtUAUArKunM9YW2GOidONcQfovuLcFX0n8U7f6my','Rumah Sakit Hewan Jakarta, Jalan Harsono RM, RT.9/RW.4, Ragunan, South Jakarta City, Jakarta, Indonesia','-6.2990973','106.8196246',1000.00,10000.00,0),(46,NULL,'Bank BCA Menteng','$2a$10$FqGU8R3qzooZQ/xhqXp1EeVV5xPVzuE8RWmr6K9aMaVVVm3rMdrrO','Bank Bca Menteng, Jalan HOS. Cokroaminoto, RT.3/RW.5, Menteng, Central Jakarta City, Jakarta, Indonesia','-6.197466299999999','106.82871239999997',2000.00,20000.00,0),(47,NULL,'ICBC','$2a$10$vL0H4TjJoeY9RntPzkrGjeV7fU9tARofEp1isD64tVZXRnySAgA36','Bank ICBC Indonesia, RT.1/RW.6, Dukuh Atas, Menteng, Central Jakarta City, Jakarta, Indonesia','-6.1990906','106.82343519999995',1000.00,10000.00,1),(48,NULL,'Toko Ali','$2a$10$nrh.M/6NqxAsudGXHJ8Ic.5mgjZsejb7Za/u8Xzp2cg96.bL7BRvi','Toko Ali','-6.301679512374764','106.81977708849001',1000.00,10000.00,0),(49,NULL,'Smk 57 Jakarta Selatan','$2a$10$cwlwJ1/4NCHOffj/e1pDwe3/3u3VORs7w89laacmtrpWltMHw4I3q','SMKN 57 Jakarta, Jalan Taman Margasatwa Raya, RT.12/RW.5, Jati Padang, South Jakarta City, Jakarta, Indonesia','-6.2925049','106.82417469999996',2000.00,20000.00,1),(50,NULL,'Rumah Sakit Jakarta Selatan','$2a$10$HHrIyi0ZVhsJvlNjpXgd6O8hpNmkkooPoG1i1EWWT/t46iMfZ4gCS','Rumah Sakit Jakarta, Jalan Jendral Sudirman, RT.5/RW.4, Karet Semanggi, South Jakarta City, Jakarta, Indonesia','-6.2184275','106.81614260000003',2000.00,20000.00,0),(51,NULL,'Jakarta Medical Center','$2a$10$B/Dc46mMSx/Nc6hjPl6McuIKNwUrnirds9Xr4rlLJvTyQ6J.XrEWa','Jakarta Medical Center (JMC) Hospital, Jalan Warung Buncit Raya, RT.10/RW.5, Kalibata, South Jakarta City, Jakarta, Indonesia','-6.271392','106.83025599999996',2000.00,20000.00,1),(52,NULL,'BRI Ragunan','$2a$10$zwkI8eF4BNIUwJ7gZBVixuEhCbrWyJt.rndtfz3cA3xtH9h.trM5y','BRI Ragunan','-6.285581240431917','106.8348755616006',2000.00,10000.00,1),(53,NULL,'Monas Jakarta','$2a$10$L9nDd4PXcHLHUH0bYDoqJ.t7KHP37FJ25zULvDdkYwZV0flq/qSvm','Monas, Gambir, Central Jakarta City, Jakarta, Indonesia','-6.1753924','106.82715280000002',2000.00,20000.00,1),(54,NULL,'Sudirman','$2a$10$x7mfN9oXp0QBRgASIJRZfuMP7B2vh32BkEL8T5lpBE.wiBODG9Mgy','Sudirman','-6.2053983','106.82240660000002',1111.00,11111.00,0),(55,NULL,'Transmart Cilandak','$2a$10$GpUO8PUw8AzfXltL9AV/R.k4Tao540XTxmqbh6hzOm7YHzKa9F4I2','Transmart Carrefour Cilandak Kko, Jalan Raya Cilandak Kko, RT.1/RW.8, East Cilandak, South Jakarta City, Jakarta, Indonesia','-6.3015284','106.81401529999994',2000.00,20000.00,1);
/*!40000 ALTER TABLE `master_building` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `master_user`
--

DROP TABLE IF EXISTS `master_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `master_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `master_user_info_id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `saldo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_master_user_master_user_info_idx` (`master_user_info_id`),
  KEY `FK_saldo` (`saldo_id`),
  CONSTRAINT `FK_saldo` FOREIGN KEY (`saldo_id`) REFERENCES `saldo` (`id`),
  CONSTRAINT `fk_master_user_master_user_info` FOREIGN KEY (`master_user_info_id`) REFERENCES `master_user_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `master_user`
--

LOCK TABLES `master_user` WRITE;
/*!40000 ALTER TABLE `master_user` DISABLE KEYS */;
INSERT INTO `master_user` VALUES (72,'wisnucakra@gmail.com','$2a$10$rGp0JEq3ae/zIOQ9TcMZ3O2gbKHToYpIj8fI4Xr4nPO.wwZE0Qz42',82,3,77),(99,'testing@gmail.com','$2a$10$58u4lZP9h83XdsjhSU.kieFvSdTlIB9m4t2lZGn8gbHUtxCzCeHuy',161,3,105),(100,'ngetes@gmail.com','$2a$10$aFvR3/B6zfK.VWcM7eRqwubM6RsYZAeluADr8pvHrB0oDM08d4d8W',162,3,106),(105,'edo@gmail.com','$2a$10$6kn/ZcCXzuMnb4YbIUpCn..gJgDuYqubkjY2STTA5Up8JJkkqoJz2',170,3,111),(106,'wkkkkk@gmail.com','$2a$10$WtGo0wbhD1u/6IcRRKUWKeRPWpqdnXT0W1TscnRS9JnUBCULw/rZ2',171,3,112),(108,'rey@gmail.com','$2a$10$C0quXClb4ljfb6x9Oj4OQuPbseCUFDWXROaYKJ8V.dXDB329rItA6',174,1,NULL),(129,'reyvirgianto@gmail.com','$2a$10$6t1AC1sBWDj9u8B.Tw/VX.EFSC2yVdIraaupV5aExwbiFtSLiFpFC',195,2,NULL),(130,'ajigalih812@gmail.com','$2a$10$7eW7P89E56tmtsX7wEJxv.0V24yP3WWaK8OZ2wYsKGO6vHqy79S76',196,2,NULL),(136,'herianto.oy@gmail.com','$2a$10$XQsuVQA6I93AmgM5PDxbX.p6niDLRH898bMJ9p5IWkgDNV7lB4Diq',202,1,NULL),(137,'nunu@gmail.com','$2a$10$0K.AlEC0jENRf1Mx.qvieemYmyF2LSu5.bDBcagFtfJ2frPYoMURe',203,3,113),(138,'elen@gmail.com','$2a$10$xoRv6XhHYd.1bsxU5bkj6uFaSbiFFe4vvEHQ.7Tq6NeGEbOGDM0F2',205,3,115),(139,'blueboy.skyone@gmail.com','$2a$10$uiA5x6dEjxRwqOrDydKaIekA4RcjUgiolLkm/aWhQg4Q91vDqMOXa',206,1,NULL),(140,'acountGoib@gmail.com','$2a$10$aZaABJOMTZm5UBac0VKEg.0P2aWV1xw461TAfnHxunFq.W9WzcGkm',207,1,NULL),(141,'enigma@gmail.com','$2a$10$k2v/.7awdnnQIdyvqwzwIO3n3QUV0a2Udp4/M0TYLLW37udssh9LC',208,3,116),(142,'tester@gmail.com','$2a$10$b8MGTlCTSyn5x0alrpUf/.O1zpLRyIYq6Y9gJkx4VAga1BXxh.MKy',209,3,117),(143,'rvirgianto82@gmail.com','$2a$10$uKiCAtFdxv/tLOUjvni/qu0YBeqogex0SC.JwXOA3mFAx3lhkMH7e',210,2,NULL),(144,'soakiyoshi@gmail.com','$2a$10$NJKemIQsoWDdakhy1GURbuCEVqEnHG99uEWKOLTU9MKmkiyXiBUoa',211,3,118);
/*!40000 ALTER TABLE `master_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `master_user_info`
--

DROP TABLE IF EXISTS `master_user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `master_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(100) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `phone_number` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `master_user_info`
--

LOCK TABLES `master_user_info` WRITE;
/*!40000 ALTER TABLE `master_user_info` DISABLE KEYS */;
INSERT INTO `master_user_info` VALUES (81,'Reynaldi Saiful Jamill','2019-12-19','08575412'),(82,'Wisnu Cakra','2001-04-01','082328827471'),(145,'saya lupa','1997-01-01','121323'),(146,'Reynaldi Sukirman','2019-12-23','817822'),(147,'Reynaldi','2019-12-06','0898879789713'),(148,'Admin','2019-12-26','0881357618361'),(161,'testing yeyy','2019-12-27','08224371444'),(162,'Wkwkwk','2019-12-26','0973181248439'),(167,'Pantek','2019-12-23','812812'),(168,'admin Menara Unas','2019-12-04','912912'),(170,'Edo Sensei','1966-12-27','0823791345311'),(171,'Galih','1995-12-22','12312'),(174,'Reynaldi.V','2020-01-01','0834978133325'),(184,'Reynaldi Virgianto','1997-09-13','08818808982'),(194,'Wis Udah','2000-12-02','12312'),(195,'Rey','1997-09-13','0897989868598'),(196,'Galih','1997-01-08','083815736123'),(202,'Heri OY','2020-01-09','12312'),(203,'nunu','2000-12-02','12312'),(205,'elen chan','2020-01-03','0834978133325'),(206,'Nanda','1992-06-05','081290466167'),(207,'acountBebas','1999-03-01','1111'),(208,'Enigma Camp','2019-12-31','0464837843184'),(209,'tester','2020-01-08','0873131518211'),(210,'aldi virgianto','2020-01-30','08234799124'),(211,NULL,NULL,NULL);
/*!40000 ALTER TABLE `master_user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `master_vehicle`
--

DROP TABLE IF EXISTS `master_vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `master_vehicle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vehicle_name` varchar(100) NOT NULL,
  `vehicle_type` varchar(100) NOT NULL,
  `license_plate` varchar(10) NOT NULL,
  `master_user_info_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_master_vehicle_master_user_info1_idx` (`master_user_info_id`),
  CONSTRAINT `fk_master_vehicle_master_user_info1` FOREIGN KEY (`master_user_info_id`) REFERENCES `master_user_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `master_vehicle`
--

LOCK TABLES `master_vehicle` WRITE;
/*!40000 ALTER TABLE `master_vehicle` DISABLE KEYS */;
INSERT INTO `master_vehicle` VALUES (52,'Motor','Motorcycle','B 52 HUZ',82,0),(54,'CAR','Car','CARRRR',82,0),(65,'Kawasaki Er6n','Motorcycle','B 846 HY',170,1),(66,'Benepi Patagonian Eagle','Motorcycle','B 064 Ph',170,0),(67,'Kawasaki ZX-800','Motorcycle','W 15 NU',82,1),(68,'Ferari California','Car','B 65 LSJ',82,1),(69,'Harley Davidson','Motorcycle','B 84 HBS',82,1),(70,'Van Car','Car','B 42 LTU',208,1),(71,'Car Tester','Car','L472 sh',209,1),(72,'Honda Civic','Car','B 27 lag',170,1),(73,'Pune','Car','P 85 NE',82,1),(74,'Porche','Car','l 3659 AN',82,0);
/*!40000 ALTER TABLE `master_vehicle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `master_voucher`
--

DROP TABLE IF EXISTS `master_voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `master_voucher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `voucher_code` varchar(45) DEFAULT NULL,
  `discount` decimal(10,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `voucher_code_UNIQUE` (`voucher_code`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `master_voucher`
--

LOCK TABLES `master_voucher` WRITE;
/*!40000 ALTER TABLE `master_voucher` DISABLE KEYS */;
INSERT INTO `master_voucher` VALUES (2,'115',50000.00,0),(4,'123456789100',1000.00,1),(5,'FREE',20000.00,1),(6,'TK001',1000.00,0),(7,'Y1234',100.00,0),(8,'007',10000.00,0),(11,'8192',1000.00,1),(18,'hatiku',20000.00,1),(19,'BukaMall',20000.00,1),(20,'1000000000',10000.00,1),(21,'Bayar',10000.00,0),(22,'ENIGMA',1000.00,0),(23,'PAKEDO',1250.00,0);
/*!40000 ALTER TABLE `master_voucher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `saldo`
--

DROP TABLE IF EXISTS `saldo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `saldo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `saldo` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saldo`
--

LOCK TABLES `saldo` WRITE;
/*!40000 ALTER TABLE `saldo` DISABLE KEYS */;
INSERT INTO `saldo` VALUES (77,92334600.00),(93,0.00),(94,0.00),(95,0.00),(96,0.00),(97,0.00),(98,0.00),(99,0.00),(100,0.00),(101,0.00),(102,0.00),(103,0.00),(104,999000.00),(105,5000.00),(106,0.00),(111,49250.00),(112,0.00),(113,0.00),(115,800000.00),(116,96100.00),(117,101000.00),(118,0.00);
/*!40000 ALTER TABLE `saldo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `saldo_histories`
--

DROP TABLE IF EXISTS `saldo_histories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `saldo_histories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `saldo_id` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `information` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saldo_histories`
--

LOCK TABLES `saldo_histories` WRITE;
/*!40000 ALTER TABLE `saldo_histories` DISABLE KEYS */;
INSERT INTO `saldo_histories` VALUES (9,77,'2020-01-02 17:01:14','- Rp1000.00'),(10,111,'2020-01-03 08:27:32','- Rp1000.00'),(11,111,'2020-01-03 08:27:50','- Rp2000.00'),(12,77,'2020-01-03 17:52:17','- Rp4000.0'),(13,77,'2020-01-03 18:02:36','- Rp2000.00'),(14,77,'2020-01-03 18:13:09','- Rp4000.0'),(15,115,'2020-01-03 18:21:57','+ Rp800000.00'),(16,77,'2020-01-04 04:18:19','- Rp20000.0'),(17,77,'2020-01-04 04:25:32','- Rp19900.0'),(18,77,'2020-01-04 04:40:58','- Rp4000.0'),(19,77,'2020-01-04 04:43:50','- Rp1000.00'),(20,77,'2020-01-04 07:03:53','- Rp2000.0'),(21,77,'2020-01-04 07:08:29','- Rp1000.00'),(22,77,'2020-01-04 07:08:30','- Rp1000.00'),(23,77,'2020-01-04 07:23:39','- Rp2000.0'),(24,77,'2020-01-04 14:58:43','- Rp14000.0'),(25,77,'2020-01-04 14:58:45','- Rp14000.0'),(26,77,'2020-01-04 15:02:06','- Rp2000.0'),(27,77,'2020-01-04 15:02:07','- Rp2000.0'),(28,77,'2020-01-04 15:03:36','- Rp2000.0'),(29,77,'2020-01-04 15:03:38','- Rp2000.0'),(30,77,'2020-01-04 15:20:31','- Rp0.0'),(31,77,'2020-01-04 15:35:32','- Rp2000.0'),(32,77,'2020-01-04 08:57:05','- Rp0'),(33,77,'2020-01-04 15:58:27','- Rp0'),(34,77,'2020-01-04 16:12:03','- Rp0'),(35,77,'2020-01-04 16:15:06','- Rp2000.0'),(36,77,'2020-01-04 16:15:07','- Rp2000.0'),(37,77,'2020-01-04 16:15:08','- Rp2000.0'),(38,77,'2020-01-04 16:17:34','- Rp1000.00'),(39,77,'2020-01-04 16:21:30','- Rp1000.0'),(40,77,'2020-01-04 16:23:19','- Rp0'),(41,77,'2020-01-05 04:21:54','- Rp2000.0'),(42,77,'2020-01-05 11:51:50','- Rp18000.0'),(43,77,'2020-01-05 12:42:29','- Rp19000.0'),(44,77,'2020-01-05 17:54:06','- Rp0'),(45,111,'2020-01-06 01:30:30','+ Rp100000'),(46,111,'2020-01-06 01:48:40','+ Rp100000'),(47,116,'2020-01-06 05:37:40','- Rp3900.0'),(48,111,'2020-01-06 05:53:43','+ Rp1000'),(49,111,'2020-01-06 05:55:04','+ Rp1000'),(50,117,'2020-01-06 07:57:10','+ Rp1000'),(51,117,'2020-01-06 07:58:39','+ Rp100000'),(52,117,'2020-01-06 08:08:47','- Rp0'),(53,77,'2020-01-06 08:48:11','- Rp2000.00'),(54,77,'2020-01-06 09:08:09','- Rp4000.0'),(55,77,'2020-01-06 17:59:36','- Rp3000.0'),(56,77,'2020-01-06 18:03:49','+ Rp50000.00'),(59,77,'2020-01-07 03:09:07','+ Rp1000000'),(60,77,'2020-01-07 03:09:14','+ Rp1000000'),(61,111,'2020-01-07 03:10:04','+ Rp1000'),(62,111,'2020-01-07 03:52:53','+ Rp50000'),(63,111,'2020-01-07 03:58:22','- Rp2750.0');
/*!40000 ALTER TABLE `saldo_histories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slot`
--

DROP TABLE IF EXISTS `slot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `slot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `slot_name` varchar(45) DEFAULT NULL,
  `floor_id` int(11) NOT NULL,
  `slot_type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_slot_floor1_idx` (`floor_id`),
  CONSTRAINT `fk_slot_floor1` FOREIGN KEY (`floor_id`) REFERENCES `floor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slot`
--

LOCK TABLES `slot` WRITE;
/*!40000 ALTER TABLE `slot` DISABLE KEYS */;
INSERT INTO `slot` VALUES (1,'B001',1,1,1),(2,'B002',1,1,1),(11,'E001',13,1,1),(12,'E002',13,1,1),(13,'E003',13,1,1),(14,'E004',13,1,1),(15,'EM01',14,2,1),(16,'EM01',14,2,1),(17,'EMM01',14,2,1),(18,'BRI-001',15,1,1),(19,'BRI-002',16,2,1),(20,'RAG-001',17,2,1),(29,'RGA-003',17,1,1),(30,'RGA-001',17,NULL,1),(31,'RGA-002',17,2,1),(32,'A001',24,1,1),(33,'A002',24,1,1),(34,'A002',24,1,1),(35,'A1',25,1,1),(36,'A01',17,1,1),(37,'A01',17,2,1),(38,'A91',24,2,1),(39,'C-001',18,1,1),(40,'JKT-001',33,1,1),(41,'JKT-002',33,2,1),(42,'JKT2-001',34,1,1),(43,'JKT2-002',34,2,1),(44,'A19',18,2,1),(45,'J1234',38,1,1),(46,'B12',36,1,1);
/*!40000 ALTER TABLE `slot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu_children`
--

DROP TABLE IF EXISTS `sys_menu_children`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu_children` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `header_id` varchar(100) DEFAULT NULL,
  `admin_role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu_children`
--

LOCK TABLES `sys_menu_children` WRITE;
/*!40000 ALTER TABLE `sys_menu_children` DISABLE KEYS */;
INSERT INTO `sys_menu_children` VALUES (1,'Building','/protected/main/buildings','fas fa-building','1',1),(2,'Vehicle','/protected/main/masterVehicle','fas fa-car','1',1),(3,'Floor','/protected/main/masterFloor','fa-dolly-flatbed','1',1),(4,'Slot','/protected/main/masterSlot','fa-dolly-flatbed','1',1),(5,'Voucher','/protected/main/masterVoucher','far fa-tags','1',1),(6,'Motor  Transaction','/protected/main/transactions','fas fa-motorcycle','2',1),(7,'Floor','/protected/main/masterFloor','fa-dolly-flatbed','3',2),(8,'Slot','/protected/main/masterSlot','fa-dolly-flatbed','3',2),(9,'Motor Transaction','/protected/main/transactions','fas fa-motorcycle','4',2),(10,'Account','/protected/main/masterAdminList','far fa-user-plus','1',1),(11,'Car Transaction','/protected/main/carTransaction','far fa-car','4',2),(15,'Car Transaction','/protected/main/carTransaction','far fa-car','2',1),(16,'Voucher Top Up','/protected/main/topUp','fa fa-credit-card-alt','1',1);
/*!40000 ALTER TABLE `sys_menu_children` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu_header`
--

DROP TABLE IF EXISTS `sys_menu_header`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu_header` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `header` varchar(100) DEFAULT NULL,
  `admin_role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu_header`
--

LOCK TABLES `sys_menu_header` WRITE;
/*!40000 ALTER TABLE `sys_menu_header` DISABLE KEYS */;
INSERT INTO `sys_menu_header` VALUES (1,'Master',1),(2,'Transaction',1),(3,'Master',2),(4,'Transaction',2);
/*!40000 ALTER TABLE `sys_menu_header` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_detail`
--

DROP TABLE IF EXISTS `transaction_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_header_id` int(11) NOT NULL,
  `enter_date` datetime DEFAULT NULL,
  `exit_date` datetime DEFAULT NULL,
  `voucher_code` varchar(100) DEFAULT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_transaction_detail_transaction_header1_idx` (`transaction_header_id`),
  CONSTRAINT `fk_transaction_detail_transaction_header1` FOREIGN KEY (`transaction_header_id`) REFERENCES `transaction_header` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_detail`
--

LOCK TABLES `transaction_detail` WRITE;
/*!40000 ALTER TABLE `transaction_detail` DISABLE KEYS */;
INSERT INTO `transaction_detail` VALUES (57,93,'2019-12-31 08:24:56','2019-12-31 08:25:07',NULL,2000.00,0),(58,94,'2019-12-31 08:42:33','2019-12-31 08:43:53',NULL,4000.00,0),(59,95,'2020-01-01 12:57:43','2020-01-01 12:57:56',NULL,2000.00,0),(60,96,'2020-01-01 13:07:25','2020-01-01 13:07:47',NULL,2000.00,0),(61,97,'2020-01-01 16:02:05','2020-01-01 16:02:22',NULL,1000.00,0),(62,100,'2020-01-01 16:59:29','2020-01-01 16:59:38',NULL,1000.00,0),(63,101,'2020-01-02 16:42:49','2020-01-02 16:43:14',NULL,2000.00,0),(64,102,'2020-01-02 16:43:02','2020-01-02 17:01:14',NULL,1000.00,0),(65,105,'2020-01-03 08:27:06','2020-01-03 08:27:50',NULL,2000.00,0),(66,104,'2020-01-03 08:27:17','2020-01-03 08:27:32',NULL,1000.00,0),(67,106,'2020-01-03 17:38:00','2020-01-03 18:02:36',NULL,2000.00,0),(68,107,'2020-01-03 17:38:06','2020-01-03 17:52:17',NULL,4000.00,0),(69,108,'2020-01-03 18:09:23','2020-01-03 18:13:09',NULL,4000.00,0),(70,109,'2020-01-03 18:16:26','2020-01-04 04:25:32',NULL,19900.00,0),(71,110,'2020-01-04 04:23:10','2020-01-04 04:40:58',NULL,4000.00,0),(72,111,'2020-01-04 04:28:54','2020-01-04 04:43:50',NULL,1000.00,0),(73,112,'2020-01-04 04:44:43','2020-01-04 07:03:53',NULL,2000.00,0),(74,113,'2020-01-04 07:04:51','2020-01-04 07:08:30',NULL,1000.00,0),(75,114,'2020-01-04 07:11:50','2020-01-04 14:58:45',NULL,14000.00,0),(76,114,'2020-01-04 07:11:51','2020-01-04 07:23:39',NULL,2000.00,0),(77,115,'2020-01-04 15:00:25','2020-01-04 15:03:38',NULL,2000.00,0),(78,115,'2020-01-04 15:00:26','2020-01-04 15:02:07',NULL,2000.00,0),(79,116,'2020-01-04 15:04:31','2020-01-04 15:20:31','TK001',0.00,0),(80,117,'2020-01-04 15:35:06','2020-01-04 15:35:32',NULL,2000.00,0),(81,118,'2020-01-04 15:47:31','2020-01-04 15:58:27','1234',0.00,0),(82,118,'2020-01-04 15:47:32','2020-01-04 08:57:05','115',0.00,0),(83,119,'2020-01-04 16:11:44','2020-01-04 16:12:03','1234',0.00,0),(84,121,'2020-01-04 16:14:10','2020-01-04 16:17:34',NULL,1000.00,0),(85,120,'2020-01-04 16:14:19','2020-01-04 16:15:08',NULL,2000.00,0),(86,122,'2020-01-04 16:21:09','2020-01-04 16:21:30','TK001',1000.00,0),(87,123,'2020-01-04 16:22:58','2020-01-05 12:42:29','TK001',19000.00,0),(88,123,'2020-01-04 16:22:59','2020-01-04 16:23:19','115',0.00,0),(89,125,'2020-01-05 04:21:22','2020-01-05 04:21:54',NULL,2000.00,0),(90,127,'2020-01-05 04:50:48','2020-01-05 11:51:50','Bayar',18000.00,0),(91,136,'2020-01-05 12:20:58','2020-01-05 17:54:06','115',0.00,0),(92,141,'2020-01-06 05:36:08','2020-01-06 05:37:40','Y1234',3900.00,0),(93,142,'2020-01-06 08:06:38','2020-01-06 08:08:47','007',0.00,0),(94,143,'2020-01-06 08:47:45','2020-01-06 08:48:11',NULL,2000.00,0),(95,144,'2020-01-06 09:07:50','2020-01-06 09:08:09',NULL,4000.00,0),(96,145,'2020-01-06 17:57:32','2020-01-06 17:59:36','ENIGMA',3000.00,0),(97,146,'2020-01-07 03:56:59','2020-01-07 03:58:22','PAKEDO',2750.00,0);
/*!40000 ALTER TABLE `transaction_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_header`
--

DROP TABLE IF EXISTS `transaction_header`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_header` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `booking_date` datetime DEFAULT NULL,
  `expired_booking` datetime DEFAULT NULL,
  `master_vehicle_id` int(11) NOT NULL,
  `slot_id` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_transaction_header_master_vehicle1_idx` (`master_vehicle_id`),
  KEY `fk_transaction_header_slot1_idx` (`slot_id`),
  CONSTRAINT `fk_transaction_header_master_vehicle1` FOREIGN KEY (`master_vehicle_id`) REFERENCES `master_vehicle` (`id`),
  CONSTRAINT `fk_transaction_header_slot1` FOREIGN KEY (`slot_id`) REFERENCES `slot` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_header`
--

LOCK TABLES `transaction_header` WRITE;
/*!40000 ALTER TABLE `transaction_header` DISABLE KEYS */;
INSERT INTO `transaction_header` VALUES (93,'2019-12-31 08:24:44','2019-12-31 08:54:44',52,38,0),(94,'2019-12-31 08:41:39','2019-12-31 09:11:39',54,32,0),(95,'2020-01-01 12:53:46','2020-01-01 13:23:46',54,13,0),(96,'2020-01-01 13:06:56','2020-01-01 13:36:56',54,13,0),(97,'2020-01-01 16:01:45','2020-01-01 16:31:45',52,16,0),(98,'2020-01-01 16:05:23','2020-01-01 16:35:23',52,16,0),(99,'2020-01-01 16:06:06','2020-01-01 16:36:06',54,13,0),(100,'2020-01-01 16:59:15','2020-01-01 17:29:15',52,16,0),(101,'2020-01-02 16:28:03','2020-01-02 16:58:03',54,13,0),(102,'2020-01-02 16:28:58','2020-01-02 16:58:58',52,16,0),(103,'2020-01-03 04:10:31','2020-01-03 04:40:31',54,13,0),(104,'2020-01-03 08:24:23','2020-01-03 08:54:23',65,19,0),(105,'2020-01-03 08:24:40','2020-01-03 08:54:40',66,41,0),(106,'2020-01-03 17:36:29','2020-01-03 18:06:29',67,41,0),(107,'2020-01-03 17:36:40','2020-01-03 18:06:40',68,40,0),(108,'2020-01-03 18:09:07','2020-01-03 18:39:07',68,40,0),(109,'2020-01-03 18:16:10','2020-01-03 18:46:10',67,41,0),(110,'2020-01-04 04:22:15','2020-01-04 04:52:15',68,40,0),(111,'2020-01-04 04:28:09','2020-01-04 04:58:09',67,16,0),(112,'2020-01-04 04:44:29','2020-01-04 05:14:29',67,16,0),(113,'2020-01-04 07:04:43','2020-01-04 07:34:43',67,16,0),(114,'2020-01-04 07:11:32','2020-01-04 07:41:32',68,13,0),(115,'2020-01-04 15:00:11','2020-01-04 15:30:11',68,13,0),(116,'2020-01-04 15:04:13','2020-01-04 15:34:13',67,16,0),(117,'2020-01-04 15:28:41','2020-01-04 15:58:41',68,13,0),(118,'2020-01-04 15:46:47','2020-01-04 16:16:47',68,13,0),(119,'2020-01-04 16:11:28','2020-01-04 16:41:28',68,13,0),(120,'2020-01-04 16:13:42','2020-01-04 16:43:42',68,13,0),(121,'2020-01-04 16:13:56','2020-01-04 16:43:56',67,16,0),(122,'2020-01-04 16:20:41','2020-01-04 16:50:41',68,13,0),(123,'2020-01-04 16:22:37','2020-01-04 16:52:37',67,16,0),(124,'2020-01-05 03:36:06','2020-01-05 04:06:06',67,15,0),(125,'2020-01-05 04:06:06','2020-01-05 04:36:06',68,11,0),(126,'2020-01-05 04:06:53','2020-01-05 04:36:53',67,15,0),(127,'2020-01-05 04:31:55','2020-01-05 05:01:55',68,11,0),(128,'2020-01-05 04:49:07','2020-01-05 05:19:07',67,41,0),(129,'2020-01-05 04:56:44','2020-01-05 05:26:44',68,35,0),(130,'2020-01-05 05:01:25','2020-01-05 05:31:25',69,15,0),(131,'2020-01-05 05:25:31','2020-01-05 05:55:31',67,41,0),(132,'2020-01-05 09:06:00','2020-01-05 09:36:00',69,15,0),(133,'2020-01-05 09:06:39','2020-01-05 09:36:39',68,18,0),(134,'2020-01-05 09:44:44','2020-01-05 10:14:44',67,16,0),(135,'2020-01-05 09:45:21','2020-01-05 10:15:21',69,41,0),(136,'2020-01-05 11:54:23','2020-01-05 12:24:23',67,15,0),(137,'2020-01-05 13:45:07','2020-01-05 14:15:07',68,11,0),(138,'2020-01-05 13:45:24','2020-01-05 14:15:24',69,16,0),(139,'2020-01-05 15:34:24','2020-01-05 16:04:24',68,11,0),(140,'2020-01-05 15:34:49','2020-01-05 16:04:49',69,16,0),(141,'2020-01-06 05:35:51','2020-01-06 06:05:51',70,40,0),(142,'2020-01-06 08:05:51','2020-01-06 08:35:51',71,40,0),(143,'2020-01-06 08:47:18','2020-01-06 09:17:18',52,15,0),(144,'2020-01-06 09:06:50','2020-01-06 09:36:50',68,11,0),(145,'2020-01-06 17:54:40','2020-01-06 18:24:40',68,40,0),(146,'2020-01-07 03:54:30','2020-01-07 04:24:30',72,40,0),(147,'2020-01-07 06:56:04','2020-01-07 07:26:04',73,40,0);
/*!40000 ALTER TABLE `transaction_header` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voucher_topup`
--

DROP TABLE IF EXISTS `voucher_topup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voucher_topup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `voucher_code` varchar(16) NOT NULL,
  `value` decimal(10,2) NOT NULL,
  `expired` date NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `voucher_code_UNIQUE` (`voucher_code`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher_topup`
--

LOCK TABLES `voucher_topup` WRITE;
/*!40000 ALTER TABLE `voucher_topup` DISABLE KEYS */;
INSERT INTO `voucher_topup` VALUES (22,'0075162300171037',20000.00,'2020-09-12','0'),(23,'1000114110633696',50000.00,'2020-09-12','0'),(24,'4494920874525741',50000.00,'2020-09-12','0'),(25,'3431655378740731',50000.00,'2020-01-01','0'),(26,'9628451864067831',800000.00,'2020-01-15','0'),(27,'0622352467396475',8.00,'2020-01-15','0'),(28,'1946642957806226',100000.00,'2020-01-31','0'),(29,'1125651767416861',15000.00,'2020-01-20','1'),(30,'2360975024368191',100000.00,'2020-01-10','1'),(31,'9871222545183668',50000.00,'2020-01-10','0'),(32,'8359674466423901',15000.00,'2020-01-31','1'),(33,'5310114760168259',140000.00,'2020-01-07','1'),(34,'5445312137282218',500000.00,'2020-01-20','0');
/*!40000 ALTER TABLE `voucher_topup` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-18  0:55:27
