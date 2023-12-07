-- MySQL dump 10.13  Distrib 8.0.35, for Linux (x86_64)
--
-- Host: localhost    Database: toolify
-- ------------------------------------------------------
-- Server version	8.0.35-0ubuntu0.23.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `addresses` (
  `addressId` int NOT NULL AUTO_INCREMENT,
  `streetName` varchar(255) NOT NULL,
  `streetNumber` varchar(10) NOT NULL,
  `cityName` varchar(255) NOT NULL,
  `postCode` varchar(10) NOT NULL,
  `userId` int DEFAULT NULL,
  `createdOn` datetime NOT NULL,
  `updatedOn` datetime DEFAULT NULL,
  PRIMARY KEY (`addressId`),
  KEY `userId` (`userId`),
  CONSTRAINT `addresses_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `categoryId` int NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(255) NOT NULL,
  `createdOn` datetime DEFAULT NULL,
  `updatedOn` datetime DEFAULT NULL,
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Power Tools',NULL,NULL),(2,'Hand Tools',NULL,NULL),(3,'Tool Set',NULL,NULL),(4,'Safety Equipments',NULL,NULL),(5,'Measuring and Layout Tools',NULL,NULL);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `productId` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` text,
  `price` decimal(10,2) NOT NULL,
  `quantity` int NOT NULL,
  `manufacturer` varchar(255) DEFAULT NULL,
  `voltage` varchar(50) DEFAULT NULL,
  `productDimensions` varchar(255) DEFAULT NULL,
  `itemWeight` decimal(10,2) DEFAULT NULL,
  `bodyMaterial` varchar(255) DEFAULT NULL,
  `itemModelNumber` varchar(50) DEFAULT NULL,
  `design` varchar(50) DEFAULT NULL,
  `colour` varchar(50) DEFAULT NULL,
  `batteriesRequired` varchar(25) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `categoryId` int DEFAULT NULL,
  `createdOn` datetime DEFAULT NULL,
  `updatedOn` datetime DEFAULT NULL,
  PRIMARY KEY (`productId`),
  KEY `categoryId` (`categoryId`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `categories` (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Cordless Hammer Drill','Bosch Professional 18 V system battery hammer drill GBH 18V-21 (max. Impact energy 2 J, without batteries and charger, in box)',259.99,15,'Bosch','18 Volts','14.09 x 8.94 x 4.49 cm',2.30,'Plastic','0611911100','without batteries/charger + in box','Blue','Yes','https://m.media-amazon.com/images/I/61Q1UbgbTBL._AC_SX679_.jpg',1,NULL,NULL),(2,'Cordless Angle Grinder','Bosch Professional 18V System Battery Angle Grinder, GWS 18V-7',149.99,10,'Bosch','18 Volts','35.6 x 14.1 x 12.6 cm',2.18,'Metal','06019H9001','125 mm, solo in box','Blue','Yes','https://m.media-amazon.com/images/I/61-uPIBgh3L.AC_SX679.jpg',1,NULL,NULL),(3,'Circular Saw','Bosch Professional GKS 190 hand saw. Not compatible with guide rails, 1400 watts, circular saw blade: 190 mm. Cut depth: 70 mm, in Box',139.99,10,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No','https://m.media-amazon.com/images/I/517-lGpCczL.AC_SX679.jpg',1,NULL,NULL),(4,'Multi Cutter','Bosch Professional 18V System Battery Multi Cutter GOP 18V-28. Oscillation angle: 1.4 °, without batteries and charger, in L-Boxx',164.99,10,'Bosch','18 Volts','44 x 36 x 15.5 cm',1.60,'Plastic','06018B6001','without battery with L-BOXX','Blue','Yes','https://m.media-amazon.com/images/I/51VH1eM8mNL.AC_SX679.jpg',1,NULL,NULL),(5,'Cordless Drill','Bosch 101107866 GSR 18 V-55 Cordless Drill, 18 Volt, 2 x 3.0 Ah (IEC) Li-Ion',269.99,20,'Bosch','18 Volts','50 x 50 x 28 cm',2.20,'Plastic','101107866','‎Ergonomic & DIY','Blue','No','https://m.media-amazon.com/images/I/61U2vRm3k8L.AC_SY879.jpg',1,NULL,NULL),(6,'Mechanics Tool Set','GJX Tool Box 140 Piece Mechanics Kit Tools and Socket Set Mechanics Tool Set Car Tool Accessory Set Socket Set Tool Set Electric Tool Set',415.99,10,'GJX',NULL,'44 x 35 x 9 cm',9.20,'Solid Plastic','B08DXTGCLW','Compact','Black',NULL,'https://m.media-amazon.com/images/I/614OZ7Xkw3L.__AC_SX300_SY300_QL70_ML2_.jpg',2,NULL,NULL),(7,'Home Repair Tool Kit','GJX Home Repair Tool Kit Professional Self-Tool Accessory Set General Household Tool Kit for Home Care with Plastic Tool Box Storage Case',189.99,15,'GJX',NULL,'37 x 26 x 8 cm',8.50,'Solid Plastic','B08CXRFV2Z','Compact','Orange, Black',NULL,'https://m.media-amazon.com/images/I/61rVepX57jL._AC_SX679_.jpg',2,NULL,NULL),(8,'Electrical Tool Set','Yato YT-39009 – 68 Piece Electrician Set',90.99,15,'Yato',NULL,'32 x 23 x 7 cm',4.50,'Plastic','YT-39009','Compact','Black',NULL,'https://m.media-amazon.com/images/I/81m4A76AVUL._AC_SX679_.jpg',2,NULL,NULL),(9,'Plumbing Tool Set','Projahn 8685 Plumbing Toolbox 95-Piece Set',319.99,10,'Projahn',NULL,'48.5 x 33.1 x 15 cm',10.10,'Plastic','PROJ-8685','Tool box','Black Silver',NULL,'https://m.media-amazon.com/images/I/71Rr0beTQ4L._AC_SX679_.jpg',2,NULL,NULL),(10,'Automotive Tool Set','Mannesmann M98430 Wrench Set 0.6 cm (0.25 Inch) + 0.95 cm + (3/8 Inch) + 1.27 cm (1/2 Inch) Drive 215 Pieces, M98430',99.99,10,'Mannesmann',NULL,'‎36 x 51 x 12 cm',12.00,'Alloy Steel','M98430','Single','Olive Green',NULL,'https://m.media-amazon.com/images/I/61EgdiOHI9L._AC_SX679_.jpg',2,NULL,NULL),(11,'Screwdriver Sets','Amazon Basics Precision Screwdriver Bit Set 51pc 1 Pack White Tri-Wing',11.99,20,'Amazon',NULL,'17.15 x 9.65 x 2.79 cm',0.30,'Alloy Steel','DS-AMZ036','Single','White, Black',NULL,'https://m.media-amazon.com/images/I/811LCu2VTbL._AC_SX679_.jpg',3,NULL,NULL),(12,'Wrench Sets','LIFERUN Ratchet Wrench Set (8-19 mm) 12-Piece Ratchet Spanner, Spanner, Hand Tools with Carry Bag',34.99,13,'LifeRun',NULL,'30 x 22 x 7 cm',2.00,'Vanadium Steel','LYFR-1111','Packed','Black',NULL,'https://m.media-amazon.com/images/I/71Eg3l3zVDL._AC_SX679_.jpg',3,NULL,NULL),(13,'Tape Measures','Bosch Professional tape measure 5 m (one-hand operation, belt clip, magnetic hook, 2 stop buttons, 27 mm nylon steel tape)',20.99,20,'Bosch',NULL,'8 x 7.7 x 4.3 cm',0.30,'Nylon','BSCH1600A016BH','Tape','Blue,Yellow',NULL,'https://m.media-amazon.com/images/I/51qWbjLJwlL.__AC_SX300_SY300_QL70_ML2_.jpg',3,NULL,NULL),(14,'Chisels','AojSup Wood Chisel Set of 6 Including Sharp Riser Set (6, 12, 19, 25, 32, 38 mm) and Sharpening Stone, Professional Chisel Set with 25° Wedge Angle, Wood Chisel Set with Robust Metal Impact Cap',19.99,10,'AojSup',NULL,'29.4 x 13.9 x 5.7 cm',1.60,'Metal','AJSP-A01','Tool','Orange,Black',NULL,'https://m.media-amazon.com/images/I/81h64dDD9SL.__AC_SX300_SY300_QL70_FMwebp_.jpg',3,NULL,NULL),(15,'Bolt Cutters','WORKPRO Bolt Cutter 210 mm, up to Diameter 2.3 mm, CR-MO/Rubber Handle, Mini Side Cutters with Lock',9.99,10,'Workpro',NULL,'23.7 x 7.59 x 3.2 cm',0.28,'Molybdenum Steel','W017017A','Cutter','Red,Blue',NULL,'https://m.media-amazon.com/images/I/7172idurm2L.__AC_SX300_SY300_QL70_ML2_.jpg',3,NULL,NULL),(16,'Safety Glasses','3M Deutschland Fahrenheit DE272934147 Safety Glasses AS / UV Protection Polycarbonate Clear',13.99,20,'3M',NULL,'‎30 x 13 x 5.9 cm',0.10,'Polycarbonat','3M-782833','Classic','Light Blue',NULL,'https://m.media-amazon.com/images/I/61ZBtY8V2LL._SX522_.jpg',4,NULL,NULL),(17,'Safety Harnesses','Fall Protection Safety Belt, 1.6 m Safety Harness, Fall Protection Set',35.99,10,'LOEPENLE',NULL,'‎33 x 29 x 12 cm',1.35,'Solid Plastic','LPE-72167','Safety','Orange',NULL,'https://m.media-amazon.com/images/I/71BQdwpVjhL.__AC_SX300_SY300_QL70_ML2_.jpg',4,NULL,NULL),(18,'Work Gloves','Work Gloves Men Faux Leather Multifunctional Lightweight Safety Gloves Breathable and High Dexterity Mechanic Work Gloves for Construction Yard Work',14.99,15,'FACELANDY',NULL,'25 x 21 x 0.7 cm',0.12,'‎Faux Leather Polyvinyl Chloride','‎FL-UK6082','Work glove','Grey',NULL,'https://m.media-amazon.com/images/I/41YOrVxMXUL._SY445_SX342_QL70_ML2_.jpg',4,NULL,NULL),(19,'Dust Masks','Powzdi Dust Mask Sports Mask Bicycle Mask Breathing Mask Training Mask Air Filter Mask with 5 Activated Carbon Filters',17.99,20,'Erwachsener',NULL,'19 x 14.71 x 5 cm',0.04,'Nylon','B087JY989','Mask','Black',NULL,'https://m.media-amazon.com/images/I/71mSpGsLj-S.__AC_SX300_SY300_QL70_ML2_.jpg',4,NULL,NULL),(20,'SafetyHat','ACE Patera Construction Helmet - Robust Protective Helmet',12.99,20,'ACE',NULL,'‎26.6 x 23.4 x 16.1 cm',0.40,'Synthetic','ACE-161044','Helmet','Yellow',NULL,'https://m.media-amazon.com/images/I/51PZyM-rNTL._AC_SX679_.jpg',4,NULL,NULL),(21,'Spirit Levels','Bosch Professional spirit level 25 cm with magnetic system (readable all around, aluminum casing, robust end caps)',29.99,20,'Bosch',NULL,'55 x 12 x 5 cm',0.25,'Magnet','1600A016BN','Leveler','Grey',NULL,'https://m.media-amazon.com/images/I/511kGaYYoKL.__AC_SX300_SY300_QL70_ML2_.jpg',5,NULL,NULL),(22,'Calipers','Newaner Digital Vernier Caliper, 150 mm/6 Inch Carbon Fibre Caliper Digital with Clear LCD and Backup Battery',7.99,15,'Newaner',NULL,'23.7 x 7.25 x 0.03 cm',0.10,'Carbon Fibre','YBKC-187','Scale','Black',NULL,'https://m.media-amazon.com/images/I/316lau9FkML._SY445_SX342_QL70_ML2_.jpg',5,NULL,NULL),(23,'Chalk Lines','Trade Heavy Duty Chalk Line Reel - 30m',8.99,10,'OX Tools',NULL,'4 x 10 x 19 cm',0.24,'Edelstahl, Metal','OX-T020830','Chalk','Blue,White',NULL,'https://m.media-amazon.com/images/I/71IkE-pE2XL.__AC_SY300_SX300_QL70_ML2_.jpg',5,NULL,NULL),(24,'Marking Gauges','Broco Wooden Marking Gauge, Stainless Steel Woodworking Double Head Scriber Ruler Scribe Tool Carpentry Tool',27.99,16,'Broco',NULL,'23.8 x 9.9 x 4.4 cm',0.25,'Stainless Steel','KC-XB2954','Marking','Red',NULL,'https://m.media-amazon.com/images/I/51JVAgXHIEL._AC_SY450_.jpg',5,NULL,NULL),(25,'Angle Finder','Angle Finder Ruler 8-Inch 360° Protractor Measure Tools 200 mm Multifunctional Nylon Ruler Meter with Zeroing and Locking Function',21.99,15,'WISMURHI',NULL,'23.6 x 6.3 x 3.2 cm',0.12,'Aluminium','82305-A200D','Scale','Black',NULL,'https://m.media-amazon.com/images/I/716VM8KIXzL._AC_SX679_.jpg',5,NULL,NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchaseItems`
--

DROP TABLE IF EXISTS `purchaseItems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchaseItems` (
  `purchaseItemsId` int NOT NULL AUTO_INCREMENT,
  `quantity` int NOT NULL,
  `purchasePrice` decimal(10,2) NOT NULL,
  `productId` int NOT NULL,
  `purchaseId` int DEFAULT NULL,
  PRIMARY KEY (`purchaseItemsId`),
  KEY `productId` (`productId`),
  KEY `purchaseItems_FK` (`purchaseId`),
  CONSTRAINT `purchaseItems_FK` FOREIGN KEY (`purchaseId`) REFERENCES `purchases` (`purchaseId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `purchaseItems_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `products` (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchaseItems`
--

LOCK TABLES `purchaseItems` WRITE;
/*!40000 ALTER TABLE `purchaseItems` DISABLE KEYS */;
INSERT INTO `purchaseItems` VALUES (1,1,20.00,3,NULL),(2,3,299.98,2,NULL),(3,1,269.99,5,NULL),(4,1,149.99,2,NULL),(5,1,139.99,3,NULL),(6,1,149.99,2,NULL),(7,1,139.99,3,NULL),(8,1,149.99,2,NULL),(9,1,139.99,3,NULL),(10,1,149.99,2,NULL),(11,1,139.99,3,NULL),(12,1,149.99,2,NULL),(13,1,139.99,3,NULL),(15,1,149.99,2,7),(16,1,139.99,3,7),(18,1,149.99,2,9),(19,1,139.99,3,9),(23,1,149.99,2,13),(24,1,139.99,3,13),(25,6,209.94,12,14),(26,4,111.96,24,14),(27,6,209.94,12,15),(28,4,111.96,24,15),(32,2,69.98,12,19),(33,4,111.96,24,19);
/*!40000 ALTER TABLE `purchaseItems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchases`
--

DROP TABLE IF EXISTS `purchases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchases` (
  `purchaseId` int NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `totalPrice` decimal(10,2) NOT NULL,
  `userId` int DEFAULT NULL,
  PRIMARY KEY (`purchaseId`),
  KEY `userId` (`userId`),
  CONSTRAINT `purchases_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchases`
--

LOCK TABLES `purchases` WRITE;
/*!40000 ALTER TABLE `purchases` DISABLE KEYS */;
INSERT INTO `purchases` VALUES (1,'2023-12-06 23:57:47',569.97,NULL),(2,'2023-12-07 00:03:05',289.98,5),(3,'2023-12-07 00:07:39',289.98,5),(4,'2023-12-07 00:11:18',289.98,5),(5,'2023-12-07 00:17:49',289.98,5),(6,'2023-12-07 00:20:05',289.98,5),(7,'2023-12-07 00:50:55',289.98,5),(9,'2023-12-07 01:21:51',289.98,5),(13,'2023-12-07 01:35:32',289.98,5),(14,'2023-12-07 14:41:28',321.90,1),(15,'2023-12-07 15:27:17',321.90,5),(19,'2023-12-07 21:45:46',181.94,5);
/*!40000 ALTER TABLE `purchases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `createdOn` datetime NOT NULL,
  `defaultCity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `defaultPincode` bigint NOT NULL,
  `defaultStreetName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `defaultStreetNumber` int NOT NULL,
  `hasRole` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `updatedOn` datetime DEFAULT NULL,
  `session` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'zabi','ansari','123n1122d11dddn4@gmail.com','$2a$10$djZAVtoyxQr1XUY118BrD.5UWH6BJWQzz4Sf4uA2oxBfjZLZRykny','2023-12-03 15:36:26','mumbai',76737,'jenaer',3,'DEFAULT','+491234567','2023-12-07 15:07:09',NULL),(2,'zabi','ansari','123n1122d11dsssdsdsdsddn4@gmail.com','$2a$10$9/hE513Z01zvVvoN.NNqkeiFJ1.KrDDq0DBQKJYmKkPY0Ivg93xi.','2023-12-03 19:04:20','mumbai',76737,'jenaer',22,'DEFAULT','23455',NULL,NULL),(4,'zabihullah','ansari','123ssdsdsdsddn4@gmail.com','$2a$10$EdBKeKecCCvjuqw5XRfDsemkewArlGnSamsz01hUuFnOaf13KEgja','2023-12-03 19:06:38','mumbai',76737,'jenaer',22,'DEFAULT','23455','2023-12-03 19:19:49',NULL),(5,'zassssssbi','ansari','test65wspppw@test.com','$2a$10$Q4XNtVJZq2RsAYEmGduf3OsvKo17hG2i82y/rmik6hbI6lZZQC8zy','2023-12-05 01:03:22','BW',12345,'abcdef',3,'DEFAULT','+491234567',NULL,NULL);
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

-- Dump completed on 2023-12-07 23:21:55
