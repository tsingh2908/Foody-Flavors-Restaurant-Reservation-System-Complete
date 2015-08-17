CREATE DATABASE  IF NOT EXISTS `foody_flavors_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `foody_flavors_db`;
-- MySQL dump 10.13  Distrib 5.6.24, for Win32 (x86)
--
-- Host: localhost    Database: foody_flavors_db
-- ------------------------------------------------------
-- Server version	5.6.26

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
-- Table structure for table `businesshours`
--

DROP TABLE IF EXISTS `businesshours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `businesshours` (
  `day_id` int(5) NOT NULL,
  `day` varchar(50) NOT NULL DEFAULT '1970-01-01T14:00:00.000Z',
  `from_time` varchar(50) DEFAULT '1970-01-01T22:00:00.000Z',
  `to_time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`day_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `businesshours`
--

LOCK TABLES `businesshours` WRITE;
/*!40000 ALTER TABLE `businesshours` DISABLE KEYS */;
INSERT INTO `businesshours` VALUES (1,'Monday','1970-01-01T14:00:00.000Z','1970-01-01T22:00:00.000Z'),(2,'Tuesday','1970-01-01T14:00:00.000Z','1970-01-01T22:00:00.000Z'),(3,'Wednesday','1970-01-01T14:00:00.000Z','1970-01-01T22:00:00.000Z'),(4,'Thursday','1970-01-01T14:00:00.000Z','1970-01-01T22:00:00.000Z'),(5,'Friday','1970-01-01T14:00:00.000Z','1970-01-01T22:00:00.000Z'),(6,'Saturday','1970-01-01T14:00:00.000Z','1970-01-01T22:00:00.000Z'),(7,'Sunday','1970-01-01T14:00:00.000Z','1970-01-01T22:00:00.000Z');
/*!40000 ALTER TABLE `businesshours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `cust_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Tom Hanks','2343454568','tom.hanks@gmail.com'),(2,'John Doe','3454565678','john.doe@yahoo.com'),(3,'Stone Cold','3456723469','stone.cold@gmail.com'),(4,'Dwyane Johnson','2343454532','dwayne.johnson@hotmail.com'),(5,'Hulk Hogan','2343454567','hulk@gmail.com'),(6,'Tiger Sen','2343454567','tiger@gmail.com'),(7,'tarzan zee','3454564532','tarzan@hotmail.com'),(8,'tarzan zee','3454564532','tarzan@hotmail.com'),(9,'John Traver','3454564534','john@hotmail.com'),(10,'Simon Doull','234387898','simon.doull@gmail.com'),(11,'Simon Doull','234387898','simon.doull@gmail.com'),(12,'Simon Jose','234387898','simon.jose@gmail.com'),(13,'Simon Jose','234387898','simon.jose@gmail.com'),(14,'Simon Jose','234387898','simon.jose@gmail.com'),(15,'Simon Jose','234387898','simon.jose@gmail.com'),(16,'Simon Jose','234387898','simon.jose@gmail.com'),(17,'Simon Jose','234387898','simon.jose@gmail.com'),(18,'Jen Hawking','2123423122','jen@gmail.com'),(19,'Jen Hawking','2123423122','jen@gmail.com'),(20,'Sindbad','3456789876','sindbad@gmail.com'),(21,'Shah Nawaz','2343456789','shah@hotmail.com'),(22,'Daler Singh','2343454578','daler.singh@yahoo.com'),(23,'test Cust','3452341239','test@gmail.com'),(24,'Jamie Fox','4567865234','jamie.fox@gmail.com'),(25,'Jimmy Shergill','6573452341','jimmy@gmail.com'),(26,'Mike Getting','4533498765','Mike@gmail.com'),(27,'Mike Getting','4533498765','Mike@gmail.com'),(28,'Mike Getting','4533498765','Mike@gmail.com'),(29,'Zandu Balm','6564563423','zandu@hotmail.com'),(30,'trevor','5453432341','trevor@gmail.com'),(31,'final 7th','6564352342','final@gmail.com'),(32,'Paul Derek','8765674356','paul@gmail.com'),(33,'lkshla','5647896784','ING@GMAIL.COM'),(34,'lahdll','3456789087','dkak@gmail.com'),(35,'klmdam','9898786758','ksklas@gmail.com'),(36,'Bhai','9898786758','bhai@gmail.com'),(37,'ijdaj','7789898797','lnasl@gmail.com'),(38,'klskskl','7678989787','gshsh@gmail.com'),(39,'hggjk','5675768897','rgt@gmail.com'),(40,'gj','6787898765','ghh@gmail.com'),(41,'hjgkk','5676787899','kgsgk@gmail.com'),(42,'jknknk','6567898789','tth@gmail.com'),(43,'Test User1','3478987123','test@email.com'),(44,'Test Customer2','3452345678','test2@gmail.com'),(45,'Test Customer2','3452345678','test2@gmail.com'),(46,'Test User3','2345467897','test3@gmail.com'),(47,'Test User4','2345467888','test4@gmail.com'),(48,'simon paul','3456789876','simon@gmail.com'),(49,'Jazzy B','3456789879','jazzyb@gmail.com'),(50,'Zen Estillo','2345344567','zen@yahoo.com'),(51,'khk','6578768909','khk@gmail.com'),(52,'asdf','2343456789','asdf@gmail.com'),(53,'drt','3454343234','drt@gmail.com'),(54,'greeb','2345431234','greb@gmail.com'),(55,'test','4565678745','test@yahoo.com'),(56,'fred','3454565678','fred@gmail.com'),(57,'grid','2343456787','grid@gmail.com'),(58,'hsdn','6787898765','grg@gmail.com'),(59,'fgrg','5456567678','grf@gmail.com'),(60,'grd','5654345678','grd@gmail.com'),(61,'rykgk','5456787654','arb@gmail.com'),(62,'rykgk','5456787654','arb@gmail.com'),(63,'khgkhk','6567878987','ggg@gmail.com'),(64,'hghgk','7678789876','er@yahoo.com'),(65,'khkh','5467878906','er@yahoo.com'),(66,'khgkkhklh','5645678765','er@yahoo.com'),(67,'vjjg','5645678789','er@trg.com'),(68,'lkjhl','5645678789','re@gmail.com'),(69,'ghh','6567878997','er@yahoo.com'),(70,'tran','5467867897','tr@yahoo.com'),(71,'Tuesday','3453467898','tue@gmail.com'),(72,'Simer Jeet','3454567867','simer@gmail.com'),(73,'Taranjeet Singh','2343455678','taran@gmail.com'),(74,'Harpreet','4345676543','har@gmail.com'),(75,'Tiger Boss','5676787656','tiger@gmail.com'),(76,'Firdausi','4565678768','fird@gmail.com'),(77,'Atif','4565676789','atif@yahoo.com'),(78,'Jigar','2343456789','jigar@gmail.com'),(79,'Test Sunday','3454567899','test@gmail.com'),(80,'Test Sun','4565676789','test@yahoo.com'),(81,'Praveen Salitra','2343456789','praveen@gmail.com');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('owner@foodyflavors.com','admin');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `cnf_code` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `time` varchar(50) NOT NULL,
  `partysize` int(5) NOT NULL,
  `special_event` varchar(100) DEFAULT 'N/A',
  `cust_id` int(11) NOT NULL,
  PRIMARY KEY (`cnf_code`),
  KEY `fk_cust_id_idx` (`cust_id`),
  CONSTRAINT `fk_cust_id` FOREIGN KEY (`cust_id`) REFERENCES `customer` (`cust_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=555585 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (100001,'2015-08-19','1970-01-02T01:30:00.000Z',0,'Birthday',2),(123456,'2015-08-29','1970-01-01T18:30:00.000Z',4,'Anniversary',1),(424242,'2015-08-15','1970-01-01T19:30:00.000Z',6,'Meeting',4),(555555,'2015-09-01','1970-01-01T18:30:00.000Z',3,'N/A',3),(555561,'2015-08-11','1970-01-01T18:30:00.000Z',5,'Party',42),(555562,'2015-08-11','1970-01-01T18:30:00.000Z',5,'Birthday',43),(555563,'2015-08-17','1970-01-01T18:30:00.000Z',6,'Lunch',44),(555564,'2015-08-11','1970-01-01T18:30:00.000Z',5,'Birthday',46),(555565,'2015-08-18','1970-01-01T18:30:00.000Z',6,'Anniversary',47),(555566,'2015-08-18','1970-01-01T18:30:00.000Z',5,'Anniversary',50),(555568,'2015-08-17','1970-01-01T18:30:00.000Z',4,'N/A',53),(555569,'2015-08-16','1970-01-01T18:30:00.000Z',4,'lunch',54),(555570,'2015-08-20','1970-01-01T18:30:00.000Z',6,'Lunch',58),(555571,'2015-08-17','1970-01-01T18:30:00.000Z',5,'Lunch',68),(555573,'2015-08-15','1970-01-01T18:30:00.000Z',5,'Anniv',70),(555574,'2015-08-13','1970-01-01T18:30:00.000Z',5,'Lunch',71),(555575,'2015-08-25','1970-01-01T18:30:00.000Z',5,'Lunch',72),(555576,'2015-08-13','1970-01-01T22:30:00.000Z',6,'Birthday',73),(555577,'2015-09-21','1970-01-01T21:45:00.000Z',5,'Anniv',74),(555578,'2015-08-20','1970-01-01T19:00:00.000Z',5,NULL,75),(555579,'2015-08-17','1970-01-01T19:00:00.000Z',6,NULL,76),(555580,'2015-08-18','1970-01-01T22:00:00.000Z',5,'Birthday',77),(555581,'2015-08-25','1970-01-01T18:00:00.000Z',5,'Anniversary',78),(555582,'2015-08-19','1970-01-01T19:01:00.000Z',5,'Birthday',79),(555583,'2015-08-25','1970-01-01T19:00:00.000Z',5,'Birthday',80),(555584,'2015-08-13','1970-01-01T19:00:00.000Z',4,NULL,81);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurantprofile`
--

DROP TABLE IF EXISTS `restaurantprofile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurantprofile` (
  `rest_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `address` varchar(200) NOT NULL,
  PRIMARY KEY (`rest_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurantprofile`
--

LOCK TABLES `restaurantprofile` WRITE;
/*!40000 ALTER TABLE `restaurantprofile` DISABLE KEYS */;
INSERT INTO `restaurantprofile` VALUES (1,'Foody-Flavors','9998987844','contact@foody-flavors.com','91-95 124 ST, Queens, New York, NY-11419');
/*!40000 ALTER TABLE `restaurantprofile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seatingarea`
--

DROP TABLE IF EXISTS `seatingarea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seatingarea` (
  `table_id` int(11) NOT NULL,
  `size` int(5) NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'Available',
  `since` varchar(50) DEFAULT NULL,
  `cnf_code` int(11) DEFAULT NULL,
  PRIMARY KEY (`table_id`),
  KEY `fk_cnf_code_idx` (`cnf_code`),
  CONSTRAINT `fk_cnf_code` FOREIGN KEY (`cnf_code`) REFERENCES `reservation` (`cnf_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seatingarea`
--

LOCK TABLES `seatingarea` WRITE;
/*!40000 ALTER TABLE `seatingarea` DISABLE KEYS */;
INSERT INTO `seatingarea` VALUES (1,5,'Available',NULL,NULL),(2,6,'Available',NULL,NULL),(3,5,'Available',NULL,NULL),(4,5,'Occupied','1970-01-01T20:00:00.000Z',555577),(5,6,'Occupied','1970-01-01T18:30:00.000Z',555561),(6,5,'Occupied','1970-01-01T20:00:00.000Z',555576),(7,5,'Available',NULL,NULL),(8,5,'Occupied','1970-01-02T01:30:00.000Z',100001),(9,6,'Occupied','1970-01-01T19:00:00.000Z',555583);
/*!40000 ALTER TABLE `seatingarea` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-17 19:05:45
