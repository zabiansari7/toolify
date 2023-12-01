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
USE toolify;

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
  `deletedOn` datetime DEFAULT NULL,
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Power Tools',NULL,NULL,NULL),(2,'Hand Tools',NULL,NULL,NULL),(3,'Tool Set',NULL,NULL,NULL),(4,'Safety Equipments',NULL,NULL,NULL),(5,'Measuring and Layout Tools',NULL,NULL,NULL);
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
  `categoryId` int DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `createdOn` datetime DEFAULT NULL,
  `updatedOn` datetime DEFAULT NULL,
  `deletedOn` datetime DEFAULT NULL,
  PRIMARY KEY (`productId`),
  KEY `categoryId` (`categoryId`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `categories` (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Cordless Hammer Drill','Bosch Professional 18 V system battery hammer drill GBH 18V-21 (max. Impact energy 2 J, without batteries and charger, in box)',259.99,15,'Bosch','18 Volts','14.09 x 8.94 x 4.49 cm',2.30,'Plastic','0611911100','without batteries/charger + in box','Blue','Yes',1,'https://m.media-amazon.com/images/I/61Q1UbgbTBL._AC_SX679_.jpg',NULL,NULL,NULL),(2,'Cordless Angle Grinder','Bosch Professional 18V System Battery Angle Grinder, GWS 18V-7',149.99,12,'Bosch','18 Volts','35.6 x 14.1 x 12.6 cm',2.18,'Metal','06019H9001','125 mm, solo in box','Blue','Yes',1,'https://m.media-amazon.com/images/I/61-uPIBgh3L._AC_SX679_.jpg',NULL,NULL,NULL),(3,'Circular Saw','Bosch Professional GKS 190 hand saw. Not compatible with guide rails, 1400 watts, circular saw blade: 190 mm. Cut depth: 70 mm, in Box',139.99,10,'Bosch','18 Volts','44 x 36 x 15.5 cm',1.60,'Plastic','06018B6001','without battery with L-BOXX','Blue','Yes',1,'https://m.media-amazon.com/images/I/517-lGpCczL._AC_SX679_.jpg',NULL,NULL,NULL),(4,'Multi Cutter','Bosch Professional 18V System Battery Multi Cutter GOP 18V-28. Oscillation angle: 1.4 °, without batteries and charger, in L-Boxx',164.99,10,'Bosch','18 Volts','50 x 50 x 28 cm',2.20,'Plastic','101107866',NULL,'Blue','No',1,'https://m.media-amazon.com/images/I/51VH1eM8mNL._AC_SX679_.jpg',NULL,NULL,NULL),(5,'Cordless Drill','Bosch 101107866 GSR 18 V-55 Cordless Drill, 18 Volt, 2 x 3.0 Ah (IEC) Li-Ion',269.99,20,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL),(6,'SCREWDRIVER','Bosch 101107866 GSR 18 V-55 Cordless Drill, 18 Volt, 2 x 3.0 Ah (IEC) Li-Ion',269.99,20,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL),(7,'SCREWDRIVER','Bosch 101107866 GSR 18 V-55 Cordless Drill, 18 Volt, 2 x 3.0 Ah (IEC) Li-Ion',269.99,20,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL),(8,'SCREWDRIVER','Bosch 101107866 GSR 18 V-55 Cordless Drill, 18 Volt, 2 x 3.0 Ah (IEC) Li-Ion',269.99,20,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL),(9,'SCREWDRIVER','Bosch 101107866 GSR 18 V-55 Cordless Drill, 18 Volt, 2 x 3.0 Ah (IEC) Li-Ion',269.99,20,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL),(10,'SCREWDRIVER','Bosch 101107866 GSR 18 V-55 Cordless Drill, 18 Volt, 2 x 3.0 Ah (IEC) Li-Ion',269.99,20,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL),(13,'SCREWDRIVER','Bosch 101107866 GSR 18 V-55 Cordless Drill, 18 Volt, 2 x 3.0 Ah (IEC) Li-Ion',269.99,20,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL),(14,'SCREWDRIVER','Bosch 101107866 GSR 18 V-55 Cordless Drill, 18 Volt, 2 x 3.0 Ah (IEC) Li-Ion',269.99,20,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL),(15,'SCREWDRIVERs','Bosch IT',269.99,20,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL),(16,'SCREWDRIVER','Bosch 101107866 GSR 18 V-55 Cordless Drill, 18 Volt, 2 x 3.0 Ah (IEC) Li-Ion',269.99,20,NULL,'230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL),(17,'SCREWDRIVER',NULL,269.99,20,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL),(18,'SCREWDRIVER',NULL,269.99,20,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL),(19,'SCREWDRIVER',NULL,269.99,20,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL),(20,'SCREWDRIVER',NULL,269.99,20,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL),(21,'SCREWDRIVER',NULL,269.99,20,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL),(22,'SCREWDRIVER',NULL,269.99,20,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL),(24,'SCREWDRIVER',NULL,269.99,20,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL),(25,'SCREWDRIVER',NULL,269.99,20,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL),(26,'SCREWDRIVER',NULL,269.99,20,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL),(27,'SCREWDRIVER',NULL,269.99,20,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL),(28,'SCREWDRIVER',NULL,269.99,20,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL),(29,'SCREWDRIVER',NULL,269.99,20,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL),(30,'SCREWDRIVER','Bosch 101107866 GSR 18 V-55 Cordless Drill, 18 Volt, 2 x 3.0 Ah (IEC) Li-Ion',269.99,20,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL),(31,'SCREWDRIVER','Bosch 101107866 GSR 18 V-55 Cordless Drill, 18 Volt, 2 x 3.0 Ah (IEC) Li-Ion',269.99,20,'Bosch','230 Volts','20 x 30 x 10 cm',4.20,'Aluminium','601623000','GKS 190 (in box, without accessories)','Blue','No',1,'https://m.media-amazon.com/images/I/61U2vRm3k8L._AC_SY879_.jpg',NULL,NULL,NULL);
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
  `purchaseId` int DEFAULT NULL,
  `productId` int DEFAULT NULL,
  PRIMARY KEY (`purchaseItemsId`),
  KEY `purchaseId` (`purchaseId`),
  KEY `productId` (`productId`),
  CONSTRAINT `purchaseItems_ibfk_1` FOREIGN KEY (`purchaseId`) REFERENCES `purchases` (`purchaseId`),
  CONSTRAINT `purchaseItems_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `products` (`productId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchaseItems`
--

LOCK TABLES `purchaseItems` WRITE;
/*!40000 ALTER TABLE `purchaseItems` DISABLE KEYS */;
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
  `date` date NOT NULL,
  `totalPrice` decimal(10,2) NOT NULL,
  `userId` int DEFAULT NULL,
  PRIMARY KEY (`purchaseId`),
  KEY `userId` (`userId`),
  CONSTRAINT `purchases_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchases`
--

LOCK TABLES `purchases` WRITE;
/*!40000 ALTER TABLE `purchases` DISABLE KEYS */;
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
  `hasRole` varchar(25) NOT NULL,
  `mobile` varchar(100) DEFAULT NULL,
  `defaultStreetName` varchar(100) NOT NULL,
  `defaultPincode` bigint NOT NULL,
  `defaultCity` varchar(100) NOT NULL,
  `defaultStreetNumber` int NOT NULL,
  `createdOn` datetime NOT NULL,
  `updatedOn` datetime DEFAULT NULL,
  `deletedOn` datetime DEFAULT NULL,
  `session` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'zassssssbi','ansari','112334331n11122d11dddn4@gmail.com','123455','DEFAULT',NULL,'',0,'',0,'2023-11-28 17:53:36',NULL,NULL,NULL),(3,'zassssssbi','ansari','some@gmail.com','123455','ADMIN',NULL,'',0,'',0,'2023-11-28 17:53:36',NULL,NULL,NULL),(5,'zassssssbi','ansari','someone@gmail.com','$2a$10$wlT5vcJpFIhKMItT/RiLKeaBqG9dYgYp7ueHmHK3Ty7Rd6NUtiZhW','ADMIN','+491234567','abcdef',12345,'BW',3,'2023-11-28 17:53:36',NULL,NULL,NULL),(6,'zassssssbi','ansari','test@test.com','$2a$10$INIf5ow1oSs50u960OolyOYQaiOR4fJ7ZT6ject3hNu4jJpQYFyhm','ADMIN','+491234567','abcdef',12345,'BW',3,'2023-11-28 17:53:36',NULL,NULL,NULL),(7,'zassssssbi','ansari','test1@test.com','$2a$10$dueXCoEB6oYX5YsjOocEfuHLFL.G2BYX3.6Nl9a8fU6/ZwVUPepxe','DEFAULT','+491234567','abcdef',12345,'BW',3,'2023-11-28 17:53:36',NULL,NULL,NULL),(8,'zassssssbi','ansari','test2@test.com','$2a$10$k/beVftJxQDmSWMUCC.YjOt0a3fH9loY5PNW5o92uZSseHg5xhD.S','DEFAULT','+491234567','abcdef',12345,'BW',3,'2023-11-28 18:00:06',NULL,NULL,NULL),(9,'zabi','ansari','test3@test.com','$2a$10$ZBLSfI.dcQXigV832/EkPOiawBHagVIT5OO5XoBypmDg4jUozBrIO','ADMIN','+491234567','abcdef',12345,'BW',3,'2023-11-28 18:02:40',NULL,NULL,NULL),(11,'zassssssbi','ansari','test65@test.com','$2a$10$l04ew7cyIrV.I768Z6SNQePLLNYREYyBmvlX0gxD9k3KAh4ErC4xG','DEFAULT','+491234567','abcdef',12345,'BW',3,'2023-11-28 23:35:40',NULL,NULL,NULL),(13,'Cric','Kit','cric@cric.com','$2a$10$obd4KXTrRFX0Ot3t.1hV9ubFxjvHvwDV8O..HKFXasgWXCflx8MpS','DEFAULT','4567890','hgvhhjkkn.n',12345,'hbjbhjbjhjb',12,'2023-11-30 07:17:37',NULL,NULL,NULL),(18,'asassasadsad','adsadsadad','cric@crics.com','$2a$10$zDhGfAGRd55bNqWAmXT0Z.sMnaCktXYfJTnto8aawQVe0LNqyCNVS','DEFAULT','','sfghgf',123,'knkjbjhb',123,'2023-11-30 07:25:23',NULL,NULL,NULL),(19,'cricket','kit','cricket@cricket.com','$2a$10$xZFEQW7sk37pK/yMZ6mTgezD7CNod/EGa8ShE1dArwdpgFdsKXxAi','DEFAULT','56789','asdfghj',123333,'asdfghj',3,'2023-11-30 07:28:48',NULL,NULL,NULL),(31,'zassssssbi','ansari','test65w@test.com','$2a$10$PKqsGqET7LbY9TxNjydzAuDUGETn55RI02wX8Zka.eqfelrg8bb/S','DEFAULT','+491234567','abcdef',12345,'BW',3,'2023-11-30 08:29:23',NULL,NULL,NULL),(32,'ssdd','sss','crics@cric.com','$2a$10$A2hn599AUsf6FqIKAbbJR.ArFu8reY5oYT..A7ieOfrBhd8L5fK62','DEFAULT','','hbjhvv',1222,'jhbvjhjb',12,'2023-11-30 08:29:36',NULL,NULL,NULL);
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

-- Dump completed on 2023-12-01 23:28:37
