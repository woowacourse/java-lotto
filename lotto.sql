-- MySQL dump 10.13  Distrib 8.0.15, for macos10.14 (x86_64)
--
-- Host: localhost    Database: lotto
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
-- Table structure for table `lottos`
--

DROP TABLE IF EXISTS `lottos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `lottos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `round` int(11) NOT NULL,
  `lotto` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lottos`
--

LOCK TABLES `lottos` WRITE;
/*!40000 ALTER TABLE `lottos` DISABLE KEYS */;
INSERT INTO `lottos` VALUES (52,1,'[1,2,3,4,5,6]'),(53,1,'[17,21,26,28,33,44]'),(54,1,'[25,27,31,33,34,43]'),(55,1,'[4,11,16,23,38,44]'),(56,1,'[15,19,27,32,38,43]'),(57,1,'[19,22,28,32,35,36]'),(58,1,'[3,10,15,22,30,43]'),(59,1,'[2,13,17,26,30,38]'),(60,1,'[3,14,15,28,35,42]'),(61,1,'[29,34,35,36,41,43]'),(62,1,'[17,18,20,23,38,42]'),(63,1,'[10,12,18,35,38,45]'),(64,2,'[1,2,3,4,6,7]'),(65,2,'[1,3,4,5,6,7]'),(66,2,'[2,3,4,5,6,7]'),(67,2,'[1,2,3,5,6,7]'),(68,2,'[1,2,3,4,5,6]'),(69,2,'[16,20,28,29,39,40]'),(70,2,'[2,24,31,34,38,41]'),(71,2,'[1,4,14,15,35,37]'),(72,2,'[4,11,19,21,33,36]'),(73,2,'[1,4,7,16,18,21]'),(74,2,'[2,9,10,11,19,27]'),(75,2,'[16,18,26,33,34,41]'),(76,3,'[1,2,3,4,5,7]'),(77,3,'[1,2,3,4,6,7]'),(78,3,'[3,4,5,6,7,8]'),(79,3,'[13,20,25,33,35,44]'),(80,3,'[12,15,19,20,22,39]'),(81,3,'[1,11,24,25,40,43]'),(82,3,'[1,3,4,30,35,43]'),(83,3,'[4,5,10,12,32,34]'),(84,4,'[1,2,3,4,5,6]'),(85,4,'[1,17,18,21,28,31]'),(86,4,'[5,17,21,23,36,38]'),(87,4,'[29,34,38,39,42,43]'),(88,4,'[6,8,11,14,20,35]'),(89,4,'[2,4,5,15,27,42]'),(90,4,'[2,11,13,35,36,45]'),(91,4,'[16,24,27,28,38,45]'),(92,4,'[9,16,19,21,26,37]'),(93,4,'[13,17,36,37,42,43]'),(94,4,'[1,4,6,16,24,39]'),(95,4,'[2,16,21,26,31,45]'),(96,5,'[1,2,3,4,5,6]'),(97,5,'[5,10,13,20,21,43]'),(98,5,'[6,9,10,22,24,30]'),(99,5,'[6,17,19,30,31,34]'),(100,5,'[1,2,5,9,11,44]'),(101,5,'[1,26,28,30,34,42]'),(102,5,'[10,15,21,22,25,43]'),(103,5,'[7,13,14,34,36,38]'),(104,5,'[19,20,21,23,32,45]'),(105,5,'[11,15,20,21,27,40]'),(106,5,'[11,13,20,24,25,41]'),(107,5,'[1,3,4,13,39,45]');
/*!40000 ALTER TABLE `lottos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `result` (
  `rId` int(11) NOT NULL AUTO_INCREMENT,
  `winning_lotto` varchar(45) NOT NULL,
  `bonus_ball` int(11) NOT NULL,
  `result_statistic` varchar(300) NOT NULL,
  `result_prize` int(11) NOT NULL,
  `result_rate` int(11) NOT NULL,
  PRIMARY KEY (`rId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
INSERT INTO `result` VALUES (1,'[1, 2, 3, 4, 5, 6]',7,'[6개 일치 (2000000000원) - 1개\n, 5개 일치, 보너스볼 일치 (30000000원) - 0개\n, 5개 일치 (1500000원) - 0개\n, 4개 일치 (50000원) - 0개\n, 3개 일치 (5000원) - 0개\n, ]',2000000000,16666600),(2,'[2, 3, 4, 5, 6, 7]',9,'[6개 일치 (2000000000원) - 1개\n, 5개 일치, 보너스볼 일치 (30000000원) - 0개\n, 5개 일치 (1500000원) - 4개\n, 4개 일치 (50000원) - 0개\n, 3개 일치 (5000원) - 0개\n, ]',2006000000,16716600),(3,'[2, 3, 4, 5, 6, 10]',1,'[6개 일치 (2000000000원) - 0개\n, 5개 일치, 보너스볼 일치 (30000000원) - 0개\n, 5개 일치 (1500000원) - 0개\n, 4개 일치 (50000원) - 3개\n, 3개 일치 (5000원) - 1개\n, ]',155000,1900),(4,'[1, 2, 3, 4, 5, 6]',7,'[6개 일치 (2000000000원) - 1개\n, 5개 일치, 보너스볼 일치 (30000000원) - 0개\n, 5개 일치 (1500000원) - 0개\n, 4개 일치 (50000원) - 0개\n, 3개 일치 (5000원) - 2개\n, ]',2000010000,16666700),(5,'[1, 2, 3, 4, 5, 6]',7,'[6개 일치 (2000000000원) - 1개\n, 5개 일치, 보너스볼 일치 (30000000원) - 0개\n, 5개 일치 (1500000원) - 0개\n, 4개 일치 (50000원) - 0개\n, 3개 일치 (5000원) - 2개\n, ]',2000010000,16666700);
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-21 20:25:00
